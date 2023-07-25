package com.levin.oak.base.biz.rbac;

import cn.hutool.crypto.SecureUtil;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.plugin.Res;
import com.levin.commons.plugin.ResLoader;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.rbac.SimpleResAction;
import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.ExpressionUtils;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 逻辑
 */
@Service(PLUGIN_PREFIX + "RbacService")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "RbacService", matchIfMissing = true)
@ResAuthorize(ignored = true)
public class RbacServiceImpl implements RbacService {

    @Autowired
    ApplicationContext context;

    @Autowired
    PluginManager pluginManager;

    @DubboReference
    BizRoleService bizRoleService;

    @DubboReference
    UserService userService;

    final ContextHolder<String, Res.Action> actionContextHolder = ContextHolder.buildContext(true);

    private static final BiConsumer<String, String> emptyConsumer = (v1, v2) -> {
    };

    @PostConstruct
    public void init() {
        log.info("默认权限控制服务启用...");
    }

    @Override
    public String encryptPassword(String pwd) {
        return StringUtils.hasText(pwd) ? SecureUtil.sha1(pwd) : null;
    }

    protected ContextHolder<String, Res.Action> getActionContext() {

        synchronized (actionContextHolder) {
            if (actionContextHolder.isEmpty()) {
                for (Plugin plugin : pluginManager.getInstalledPlugins()) {
                    //资源加载器
                    ResLoader resLoader = plugin.getResLoader();
                    if (resLoader == null) {
                        continue;
                    }
                    //第二层循环 资源类型
                    for (Identifiable resType : resLoader.getResTypes()) {
                        //全部加入
                        Collection<Res> resItems = resLoader.getResItems(resType.getId(), 0);
                        //第三层循环 资源列表
                        for (Res res : resItems) {
                            String prefix = String.join(getPermissionDelimiter(), res.getDomain().toString(), res.getType().toString(), res.getId().toString());
                            //第四层循环 资源操作
                            res.getActionList()
                                    .parallelStream()
                                    .filter(Objects::nonNull)
                                    .forEach(action -> {
                                        actionContextHolder.put(prefix + getPermissionDelimiter() + action.getId(), action);
                                    });
                        }
                    }
                }
            }
        }

        return actionContextHolder;
    }

    /**
     * @param requirePermission
     * @return
     */
    protected Res.Action getAction(String requirePermission) {
        return getActionContext().get(requirePermission);
    }


    /**
     * 获取匹配清单
     *
     * @param requirePermissionPattern
     * @return
     */
    protected Map<String, Res.Action> getMatchActions(String requirePermissionPattern) {

        Map<String, Res.Action> actionMap = new LinkedHashMap<>();

        getActionContext().getAll(true).forEach((k, v) -> {
            if (vagueMatch(requirePermissionPattern, k)) {
                actionMap.put(k, v);
            }
        });

        return actionMap;
    }

    /**
     * 字符串模糊匹配
     * <p>example:
     * <p> user* user-add   --  true
     * <p> user* art-add    --  false
     *
     * @param pattern 表达式
     * @param str     待匹配的字符串
     * @return 是否可以匹配
     */
    private static boolean vagueMatch(String pattern, String str) {
        // 如果表达式不带有*号，则只需简单equals即可 (速度提升200倍)
        if (!pattern.contains("*")) {
            return pattern.equals(str);
        }
        return Pattern.matches(pattern.replaceAll("\\*", ".*"), str);
    }


    /**
     * 判断：集合中是否包含指定元素（模糊匹配）
     */
    private static boolean hasElement(List<String> ownerPermissionList, String requestPermission) {
        return Optional.ofNullable(ownerPermissionList).map(patternList ->
                patternList.parallelStream()
                        .filter(Objects::nonNull)
                        .anyMatch(pattern -> pattern.equals(requestPermission) || vagueMatch(pattern, requestPermission))
        ).orElse(false);
    }

    @Override
    public RbacUserInfo<String> getUserInfo(Object userId) {
        return userService.findById((String) userId);
    }

    @Override
    public List<String> getPermissionList(Object userId) {

        RbacUserInfo<String> userInfo = getUserInfo(userId);

        List<String> roleList = userInfo.getRoleList();

        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }

        return bizRoleService.getRolePermissionList(userInfo.getTenantId(), roleList);
    }

    @Override
    public List<String> getRoleList(Object userId) {
        return getUserInfo(userId).getRoleList();
    }

    @Override
    public List<String> getCanAcccessOrgIdList(Object userId) {
        return null;
    }

    @Override
    public boolean isAuthorized(Object userId, ResAuthorize resAuthorize) {
        return isAuthorized(
                String.join(getPermissionDelimiter(), resAuthorize.domain(), resAuthorize.type(), resAuthorize.res()),
                SimpleResAction.newAction(resAuthorize),
                getRoleList(userId),
                getPermissionList(userId),
                getAuthorizeContext()
        );
    }

    public boolean isAuthorized(Object userId, String resPrefix, Res.Action action) {
        return isAuthorized(
                resPrefix,
                action,
                getRoleList(userId),
                getPermissionList(userId),
                getAuthorizeContext()
        );
    }

    /**
     * 当前用户是否能给目标用户分配指定的角色
     *
     * @param targetUserId
     * @param requireRoleCode
     * @param matchErrorConsumer 匹配错误回调 参数1为请求的角色，参数2为没有匹配的权限
     * @return
     */
    @Override
    public boolean canAssignRole(Object sourceUserId, Object targetUserId, String requireRoleCode, BiConsumer<String, String> matchErrorConsumer) {

        Assert.notNull(sourceUserId, "用户未登录");

        RbacUserInfo<String> userInfo = getUserInfo(sourceUserId);

        //1、如果是超级管理员，可以分配任何角色
        if (userInfo.isSuperAdmin()) {
            return true;
        }

        //2、如果当前用户已经拥有这个角色
        if (userInfo.getRoleList().contains(requireRoleCode)) {
            return true;
        }

        //3、用户全部的权限
        List<String> ownerPermissionList = getPermissionList(userInfo.getId());

        //4、角色的权限列表
        List<String> requirePermissionList = bizRoleService.getRolePermissionList(userInfo.getTenantId(), requireRoleCode);

        //5、匹配
        return isAuthorized(userInfo.getRoleList(), ownerPermissionList, true, requirePermissionList
                , (rp, info) -> Optional.ofNullable(matchErrorConsumer).orElse(emptyConsumer).accept(requireRoleCode, rp + " " + info)
        );
    }

    /**
     * 当前用户是否 拥有指定的权限列表
     *
     * @param requirePermissionList
     * @param matchErrorConsumer
     * @return
     */
    @Override
    public boolean isAuthorized(Object userId, boolean isRequireAllPermission, List<String> requirePermissionList, BiConsumer<String, String> matchErrorConsumer) {

        //Assert.isTrue(authService.isLogin(), "用户未登录");

        RbacUserInfo<String> userInfo = getUserInfo(userId);

        //如果是超级管理员
        if (userInfo.isSuperAdmin()) {
            //超级管理员 也按权限进行逻辑处理
            // return true;
        }

        return isAuthorized(userInfo.getRoleList(), getPermissionList(userInfo.getId()),
                isRequireAllPermission, requirePermissionList, matchErrorConsumer);
    }

    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 关键方法
     *
     * @param ownerRoleList       已经拥有的角色列表
     * @param ownerPermissionList 已经拥有的权限列表
     * @param requirePermission   请求的权限
     * @param matchErrorConsumer  匹配错误回调 参数1为请求的权限，参数2为错误原因
     * @return 是否可以访问指定资源
     */
    @Override
    public boolean isAuthorized(List<String> ownerRoleList, List<String> ownerPermissionList, String requirePermission, BiConsumer<String, String> matchErrorConsumer) {

        Assert.hasText(requirePermission, "检查的权限表达式为空");

        //如果是角色，不是权限
        if (isRole(requirePermission)) {

            boolean found = ownerRoleList.contains(requirePermission);

            if (!found) {
                Optional.ofNullable(matchErrorConsumer).orElse(emptyConsumer).accept(requirePermission, "role not found");
            }

            return found;
        }

        //
        if (ownerPermissionList.contains(requirePermission)) {
            return true;
        }

        Map<String, Res.Action> actionMap = new LinkedHashMap<>(1);

        //是否是通配权限表达式
        boolean isPattern = requirePermission.contains("*" + getPermissionDelimiter())
                || requirePermission.contains(getPermissionDelimiter() + "*");

        if (isPattern) {
            //如果包含通配权限，要拆解出权限清单
            actionMap = getMatchActions(requirePermission);
        } else {
            Res.Action action = getAction(requirePermission);
            if (action != null) {
                actionMap.put(requirePermission, action);
            }
        }

        if (actionMap.isEmpty()) {
            Optional.ofNullable(matchErrorConsumer).orElse(emptyConsumer).accept(requirePermission, "操作不存在");
            return false;
        }

//        Assert.notEmpty(actionMap, requirePermission + " 操作不存在");

        Map<String, Object> authorizeContext = getAuthorizeContext();

        AtomicBoolean result = new AtomicBoolean(true);

        for (Map.Entry<String, Res.Action> entry : actionMap.entrySet()) {

            String rp = entry.getKey();
            Res.Action action = entry.getValue();

            if (action.isIgnored() || action.isOnlyRequireAuthenticated()) {
                continue;
            }

            if (!isAuthorized(rp.substring(0, rp.lastIndexOf(getPermissionDelimiter())), action, ownerRoleList, ownerPermissionList, authorizeContext)) {
                result.set(false);
                Optional.ofNullable(matchErrorConsumer).orElse(emptyConsumer).accept(requirePermission, rp);
                break;
            }
        }

        return result.get();
    }

    /**
     * 在假设用户已经登录的情况下，授权验证，是否可以访问指定资源
     *
     * <p>
     * 关键方法
     *
     * @param resPrefix           资源前缀
     * @param action              动作
     * @param ownerRoleList       已经拥有的角色列表
     * @param ownerPermissionList 已经拥有的权限列表
     * @return 是否可以访问指定资源
     */
    protected boolean isAuthorized(String resPrefix, Res.Action action, List<String> ownerRoleList, List<String> ownerPermissionList, Map<String, Object>... exprContexts) {

        if (action.isIgnored() || action.isOnlyRequireAuthenticated()) {
            return true;
        }

        //过滤
        List<String> requireAnyRoles = action.getAnyRoles().parallelStream().filter(Objects::nonNull).collect(Collectors.toList());
        //生成表达式
        String requirePermission = String.join(getPermissionDelimiter(), resPrefix, action.getId());

        //1、权限检查闭包
        Supplier<Boolean> hasPermission = () -> hasElement(ownerPermissionList, requirePermission);

        //2、角色检查闭包
        Supplier<Boolean> hasAnyRoles = requireAnyRoles.isEmpty() ? null : () -> requireAnyRoles.parallelStream().anyMatch(ownerRoleList::contains);

        //表达式支持
        String verifyExpression = action.getVerifyExpression();

        //3、表达式闭包
        Supplier<Boolean> expressTrue = StringUtils.hasText(verifyExpression) ? () -> (Boolean) ExpressionUtils.evalSpEL(null, verifyExpression,
                (ctx) -> {
                    ctx.setBeanResolver(new BeanFactoryResolver(context));
                    // ctx.setVariable("stpLogic", StpUtil.stpLogic);
                    //设置环境变量
                    if (exprContexts != null) {
                        Stream.of(exprContexts).filter(Objects::nonNull).forEach(ctx::setVariables);
                    }
                    ctx.setVariable("action", action);
                    ctx.setVariable("resPrefix", resPrefix);
                    ctx.setVariable("ownerRoleList", ownerRoleList);
                    ctx.setVariable("ownerPermissionList", ownerPermissionList);
                }) : null;

        //合并闭包
        List<Supplier<Boolean>> suppliers = Arrays.asList(hasAnyRoles, hasPermission, expressTrue)
                .parallelStream()
                .filter(Objects::nonNull).collect(Collectors.toList());

        //执行判断
        return action.isAndMode()
                ? suppliers.parallelStream().allMatch(Supplier::get)
                : suppliers.parallelStream().anyMatch(Supplier::get);
    }

}
