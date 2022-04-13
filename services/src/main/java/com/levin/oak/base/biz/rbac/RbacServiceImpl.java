package com.levin.oak.base.biz.rbac;


import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.*;
import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.ExpressionUtils;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.rbac.info.ActionInfo;
import com.levin.oak.base.biz.rbac.info.ModuleInfo;
import com.levin.oak.base.biz.rbac.info.ResInfo;
import com.levin.oak.base.biz.rbac.info.ResTypeInfo;
import com.levin.oak.base.entities.E_MenuRes;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.menures.req.QueryMenuResReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


@Service(PLUGIN_PREFIX + "RbacService")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "RbacService", matchIfMissing = true)
@ResAuthorize(ignored = true)
public class RbacServiceImpl extends BaseService implements RbacService {

    static final ContextHolder<Method, ResAuthorize> cache = ContextHolder.buildContext(true);

    final ResAuthorize defaultResAuthorize = getClass().getAnnotation(ResAuthorize.class);

    @Resource
    ApplicationContext context;

    @Resource
    PluginManager pluginManager;

    @Resource
    AuthService authService;

    @Resource
    MenuResService menuResService;

    @Resource
    BizRoleService bizRoleService;

    final ContextHolder<String, Res.Action> actionContextHolder = ContextHolder.buildContext(true);


    private static final BiConsumer<String, String> emptyConsumer = (v1, v2) -> {
    };

    @PostConstruct
    public void init() {
        log.info("默认权限控制服务启用...");
    }


    /**
     * @param requirePermission
     * @return
     */
    protected Res.Action getAction(String requirePermission) {

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
                            String prefix = String.join(getDelimiter(), res.getDomain().toString(), res.getType().toString(), res.getId().toString());
                            //第四层循环 资源操作
                            res.getActionList()
                                    .parallelStream()
                                    .filter(Objects::nonNull)
                                    .forEach(action -> {
                                        actionContextHolder.put(prefix + getDelimiter() + action.getId(), action);
                                    });
                        }
                    }
                }
            }
        }

        return actionContextHolder.get(requirePermission);
    }


    /**
     * 获取匹配清单
     *
     * @param requirePermissionPattern
     * @return
     */
    protected Map<String, Res.Action> getMatchActions(String requirePermissionPattern) {

        Map<String, Res.Action> actionMap = new LinkedHashMap<>();

        actionContextHolder.getAll(true).forEach((k, v) -> {
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

    /**
     * 当前用户是否能给目标用户分配指定的角色
     *
     * @param targetUserId
     * @param requireRoleCode
     * @param matchErrorConsumer 匹配错误回调 参数1为请求的角色，参数2为没有匹配的权限
     * @return
     */
    @Override
    public boolean canAssignRole(Object targetUserId, String requireRoleCode, BiConsumer<String, String> matchErrorConsumer) {

        RbacUserInfo<String> userInfo = authService.getUserInfo();

        //如果是超级管理员，可以分配任何角色
        if (userInfo.isSuperAdmin()) {
            return true;
        }

        //如果当前用户已经拥有这个角色
        if (userInfo.getRoleList().contains(requireRoleCode)) {
            return true;
        }

        //用户全部的权限
        List<String> ownerPermissionList = authService.getPermissionList(userInfo.getId());

        //角色的权限列表
        List<String> requirePermissionList = bizRoleService.getRolePermissionList(userInfo.getTenantId(), requireRoleCode);

        //匹配
        return isAuthorized(userInfo.getRoleList(), ownerPermissionList, requirePermissionList
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
    public boolean isAuthorized(List<String> requirePermissionList, BiConsumer<String, String> matchErrorConsumer) {

        RbacUserInfo<String> userInfo = authService.getUserInfo();

        //如果是超级管理员
        if (userInfo.isSuperAdmin()) {
            //超级管理员 也按权限进行逻辑处理
            // return true;
        }

        return isAuthorized(userInfo.getRoleList(), authService.getPermissionList(userInfo.getId())
                , requirePermissionList, matchErrorConsumer);
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
        if (!requirePermission.contains(getDelimiter())) {
            return ownerRoleList.contains(requirePermission);
        }

        Map<String, Res.Action> actionMap = new LinkedHashMap<>(1);

        //是否是通配权限表达式
        boolean isPattern = requirePermission.contains("*");

        if (isPattern) {
            //获取匹配的权限清单
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

            if (!isAuthorized(rp.substring(0, rp.lastIndexOf(getDelimiter())), action, ownerRoleList, ownerPermissionList, authorizeContext)) {
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
        String requirePermission = String.join(getDelimiter(), resPrefix, action.getId());

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


    /**
     * 检查访问权限
     *
     * @return
     */
    @Override
    public void checkAuthorize(@NonNull Method method) throws AuthorizationException {

        if (method == null) {
            return;
        }

        Class<?> controllerClass = method.getDeclaringClass();// AopProxyUtils.ultimateTargetClass(method.getDeclaringClass());

        ///////////////////////////////获取 res 和 action 用于权限验证 //////////////////////////////////////////
        //
        Tag tag = controllerClass.getAnnotation(Tag.class);
        Operation operation = method.getAnnotation(Operation.class);

        String res = controllerClass.getSimpleName().replace("Controller", "");

        if (tag != null) {
            res = Arrays.asList(tag.name(), tag.description())
                    .stream().filter(StringUtils::hasText).findFirst().orElse(res);
        }

        String action = method.getName();
        if (operation != null) {
            action = Arrays.asList(operation.summary(), operation.operationId(), operation.description())
                    .stream().filter(StringUtils::hasText).findFirst().orElse(action);
        }
        /////////////////////////////// 获取注解 ///////////////////////////////////////////////////
        ResAuthorize resAuthorize = cache.get(method);
        //如果没有放入
        if (resAuthorize == null
                && !cache.containsKey(method)) {

            resAuthorize = ClassUtils.merge(MapUtils.putFirst(ResPermission.Fields.res, res)
                            .put(ResPermission.Fields.action, action).build()
                    //默认条件为不空或是不是空字符串则覆盖
                    , (k, v) -> v != null && (!(v instanceof CharSequence) || StringUtils.hasText((CharSequence) v))

                    , ResAuthorize.class,
                    AnnotatedElementUtils.getMergedAnnotation(controllerClass, ResAuthorize.class),
                    AnnotatedElementUtils.getMergedAnnotation(method, ResAuthorize.class)
            );

            if (resAuthorize == null) {
                resAuthorize = defaultResAuthorize;
            }

            cache.put(method, resAuthorize);
        }

        if (resAuthorize == null
                || resAuthorize.ignored()) {
            return;
        }

        if (authService.getUserInfo() == null) {
            throw new AuthorizationException("NotLogin", "未登录");
        }

        if (resAuthorize.onlyRequireAuthenticated()) {
            return;
        }

        ///////////////////////// 构建权限检查逻辑的闭包 //////////////////////////////////////

        boolean ok = isAuthorized(
                String.join(getDelimiter(), resAuthorize.domain(), resAuthorize.type(), resAuthorize.res()),
                SimpleResAction.newAction(resAuthorize),
                authService.getRoleList(authService.getLoginUserId()),
                authService.getPermissionList(authService.getLoginUserId()),
                getAuthorizeContext()
        );

        if (!ok) {
            throw new AuthorizationException("UnAuthorization", "未授权的操作");
        }
    }

    /**
     * 获取授权的菜单列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<MenuResInfo> getAuthorizedMenuList(boolean isShowNotPermissionMenu, Object userId) {

        Assert.notNull(userId, "无效的用户标识");

        RbacUserInfo<String> userInfo = authService.getUserInfo(userId);

        Assert.notNull(userInfo, "无效的用户标识");

        //1、找出所有的菜单 包括公共菜单和租户自有菜单
//        List<MenuResInfo> menuRes = simpleDao.selectFrom(MenuRes.class)
//                .eq(E_MenuRes.enable, true)
//                //公共菜单和租户自有菜单
//                .isNullOrEq(E_MenuRes.tenantId, userInfo.getTenantId())
//                .orderBy(OrderBy.Type.Asc, E_MenuRes.orderCode)
//                .find(MenuResInfo.class);

        List<MenuResInfo> menuRes = simpleDao.findByQueryObj(
                new QueryMenuResReq()
                        .setEnable(true)
                        .setContainsPublicData(true)
                        .setOrderBy(E_MenuRes.orderCode)
                        .setOrderDir(OrderBy.Type.Asc)
                        .setTenantId(userInfo.getTenantId())
        );

        final Map<Long, MenuResInfo> cacheMap = new LinkedHashMap<>();

        //2、放入Map
        menuRes.parallelStream()
                .forEachOrdered(m -> cacheMap.put(m.getId(), m));

        final Map<Long, MenuResInfo> cacheMap2 = new LinkedHashMap<>(cacheMap);

        //3、构建菜单层级
        for (Map.Entry<Long, MenuResInfo> entry : cacheMap.entrySet()) {

            MenuResInfo menu = entry.getValue();

            Serializable parentId = menu.getParentId();

            if (parentId == null) {
                continue;
            }

            menu.setParent(null);

            MenuResInfo parent = cacheMap2.get(parentId);

            if (parent.getChildren() == null) {
                parent.setChildren(new HashSet<>());
            }

            //设置关系
            parent.getChildren().add(menu);
        }

        ///////////////////////////////////////////////////////////////////////////
        //过滤出有权限的菜单，或是设置为enable = false

        List<String> roleList = authService.getRoleList(userId);
        List<String> permissionList = authService.getPermissionList(userId);

        for (Map.Entry<Long, MenuResInfo> entry : cacheMap2.entrySet()) {
            //
            MenuResInfo info = entry.getValue();
            //获取菜单要求的权限
            List<String> requirePermissions = !StringUtils.hasText(info.getRequireAuthorizations()) ? Collections.emptyList()
                    : JsonStrArrayUtils.parse(info.getRequireAuthorizations(), StringUtils::hasText, (txt) -> txt);

            if (requirePermissions.isEmpty()
                    || requirePermissions.parallelStream().allMatch(requirePermission -> this.isAuthorized(roleList, permissionList, requirePermission, null))) {
                //如果没有要求权限，或是权限都满足，要求显示菜单
                info.setEnable(true);
            } else if (isShowNotPermissionMenu && Boolean.TRUE.equals(info.getAlwaysShow())) {
                //菜单显示，但被禁用
                info.setEnable(false);
            } else {
                //如果没有权限
                MenuResInfo menuResInfo = cacheMap.remove(entry.getKey());
            }
        }

        //清除临时
        cacheMap2.clear();

        //清除没有权限的菜单
        cacheMap.forEach((k, v) -> clearTree(cacheMap, v.getChildren()));

        return cacheMap.values().parallelStream()
                .filter(m -> m.getParentId() == null)
                .filter(m -> m.getChildren() != null && !m.getChildren().isEmpty())
                .collect(Collectors.toList());
    }

    private void clearTree(Map<Long, MenuResInfo> cacheMap, Collection<MenuResInfo> children) {

        if (children == null) {
            return;
        }

        for (MenuResInfo child : new ArrayList<>(children)) {

            if (!cacheMap.containsKey(child.getId())) {
                children.remove(child);
            }

            clearTree(cacheMap, child.getChildren());
        }
    }

    /**
     * 获取资源授权清单
     *
     * @param userId 如果 userId 为null，则表示获取全部的
     * @return
     */
    @Override
    public List<ModuleInfo> getAuthorizedResList(Object userId) {

        //
        boolean hasUser = userId != null && (!(userId instanceof CharSequence) || StringUtils.hasText(userId.toString()));

        List<String> roleList = hasUser ? authService.getRoleList(userId) : Collections.emptyList();
        List<String> permissionList = hasUser ? authService.getPermissionList(userId) : Collections.emptyList();

        //返回结果
        List<ModuleInfo> result = new LinkedList<>();

        //第一层循环 插件
        for (Plugin plugin : pluginManager.getInstalledPlugins()) {

            ModuleInfo moduleInfo = new ModuleInfo();

            BeanUtils.copyProperties(plugin, moduleInfo);

            //资源加载器
            ResLoader resLoader = plugin.getResLoader();

            if (resLoader == null) {
                continue;
            }

            //第二层循环 资源类型
            for (Identifiable resType : resLoader.getResTypes()) {

                ResTypeInfo typeInfo = new ResTypeInfo();

                BeanUtils.copyProperties(resType, typeInfo);

                //全部加入
                Collection<Res> resItems = resLoader.getResItems(resType.getId(), 0);

                //第三层循环 资源列表
                for (Res res : resItems) {

                    ResInfo resInfo = new ResInfo();

                    BeanUtils.copyProperties(res, resInfo, ResInfo.Fields.actionList);

                    String prefix = String.join(getDelimiter(), res.getDomain().toString(), res.getType().toString(), res.getId().toString());

                    //第四层循环 资源操作
                    for (Res.Action action : new ArrayList<Res.Action>(res.getActionList())) {

                        ActionInfo actionInfo = new ActionInfo().setPermissionExpr(prefix + getDelimiter() + action.getId());

                        BeanUtils.copyProperties(action, actionInfo);

                        if (hasUser) {
                            //加入拥有的权限
                            if (action.isIgnored()
                                    || action.isOnlyRequireAuthenticated()
                                    || isAuthorized(roleList, permissionList, actionInfo.getPermissionExpr(), null)) {
//                                resInfo.getActionList().add(action);
                                resInfo.getActionList().add(actionInfo);
                            }
                        } else {
//                            resInfo.getActionList().add(action);
                            resInfo.getActionList().add(actionInfo);
                        }
                    }

                    //如果没有内容，不加入
                    if (!resInfo.getActionList().isEmpty()) {
                        typeInfo.getResList().add(resInfo);
                    }
                }

                //如果没有内容，不加入
                if (!typeInfo.getResList().isEmpty()) {
                    moduleInfo.getTypeList().add(typeInfo);
                }
            }

            //如果没有内容，不加入
            if (!moduleInfo.getTypeList().isEmpty()) {
                result.add(moduleInfo);
            }
        }

        return result;
    }
}
