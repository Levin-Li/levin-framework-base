package com.levin.oak.base.services.rbac;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.*;
import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.ExpressionUtils;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.rbac.info.ActionInfo;
import com.levin.oak.base.services.rbac.info.ModuleInfo;
import com.levin.oak.base.services.rbac.info.ResInfo;
import com.levin.oak.base.services.rbac.info.ResTypeInfo;
import com.levin.oak.base.services.rbac.req.LoginReq;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.CreateUserReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


@Service(PLUGIN_PREFIX + "RbacService")
@Slf4j
//@ConditionalOnMissingBean(AuthService.class)

@ResAuthorize(ignored = true)
public class RbacServiceImpl implements RbacService {

    static final Gson gson = new Gson();

    static final Type resPermissionListType = new TypeToken<List<ResPermission>>() {
    }.getType();

    static final Type listStrType = new TypeToken<List<String>>() {
    }.getType();

    static final ContextHolder<Method, ResAuthorize> cache = ContextHolder.buildContext(true);


    final ResAuthorize defaultResAuthorize = getClass().getAnnotation(ResAuthorize.class);


    @Resource
    ApplicationContext applicationContext;

    @Resource
    SimpleDao simpleDao;

    @Resource
    UserService userService;

    @Resource
    RoleService roleService;

    @Resource
    MenuResService menuResService;

    @Resource
    PluginManager pluginManager;

    @Override
    public String login(LoginReq req) {

        if (!StringUtils.hasText(req.getAccount())
                || !StringUtils.hasText(req.getPassword())) {
            throw new AuthorizationException("401", "请输入正确的帐号或密码");
        }

        //密码加密
        req.setPassword(encryptPassword(req.getPassword()));

        String deviceType = getDeviceType(req.getUa());

        UserInfo user = simpleDao.findOneByQueryObj(req);

        checkUserState(user);

        StpUtil.login("" + user.getId(), deviceType);

        return StpUtil.getTokenValue();
    }

    @Override
    public void checkUserState(UserInfo user) throws AuthorizationException {

        if (user == null) {
            throw new AuthorizationException("401", "1帐号或密码错误");
        }

//        if (!SaSecureUtil.sha1(req.getPassword()).equals(user.getPassword())) {
//            return ApiResp.error("2帐号或密码错误");
//        }

        if (!user.getEnable()
                || !User.State.Normal.equals(user.getState())) {
            throw new AuthorizationException("401", "2帐号状态异常");
        }

        if (user.getExpiredDate() != null
                && user.getExpiredDate().getTime() < System.currentTimeMillis()) {
            throw new AuthorizationException("401", "3帐号已过期");
        }
    }


    @Override
    public void logout() {
        StpUtil.logout();
    }

    //    @Override
    public List<Identifiable> getModuleList() {
        return applicationContext.getBeansOfType(Plugin.class).values()
                .parallelStream().map(plugin -> new IdentifiableObject()
                        .setId(plugin.getId()).setName(plugin.getName()).setRemark(plugin.getRemark()))
                .collect(Collectors.toList());
    }


    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 关键方法
     *
     * @param resPrefix      资源前缀
     * @param action         动作
     * @param roleList       角色列表
     * @param permissionList 权限列表
     * @return 是否可以访问指定资源
     */
    public boolean isAuthorized(String resPrefix, Res.Action action, List<String> roleList, List<String> permissionList, Map<String, Object>... exprContexts) {

        //过滤
        List<String> requireAnyRoles = action.getAnyRoles().parallelStream().filter(Objects::nonNull).collect(Collectors.toList());

        //生成表达式
        String requirePermission = String.join(getDelimiter(), resPrefix, action.getId());

        //1、权限检查闭包
        Supplier<Boolean> hasPermission = () -> SaStrategy.me.hasElement.apply(permissionList, requirePermission);

        //2、角色检查闭包
        Supplier<Boolean> hasAnyRoles = requireAnyRoles.isEmpty() ? null : () -> requireAnyRoles.parallelStream().anyMatch(roleList::contains);

        //表达式支持
        String verifyExpression = action.getVerifyExpression();

        //3、表达式闭包
        Supplier<Boolean> expressTrue = StringUtils.hasText(verifyExpression) ? () -> (Boolean) ExpressionUtils.evalSpEL(null, verifyExpression,
                (ctx) -> {
                    ctx.setBeanResolver(new BeanFactoryResolver(applicationContext));
                    ctx.setVariable("stpLogic", StpUtil.stpLogic);
                    //设置环境变量
                    if (exprContexts != null) {
                        Stream.of(exprContexts).filter(Objects::nonNull).forEach(ctx::setVariables);
                    }
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
     * 验证是否授权
     *
     * @param requirePermission
     * @param roleList
     * @param permissionList
     * @return
     */
    public boolean isAuthorized(String requirePermission, List<String> roleList, List<String> permissionList) {
        if (requirePermission.contains(getDelimiter())) {
            return SaStrategy.me.hasElement.apply(permissionList, requirePermission);
        } else {
            return roleList.contains(requirePermission);
        }
    }

    /**
     * 获取授权的菜单列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<MenuResInfo> getAuthorizedMenuList(Object userId) {

        //1、找出所有的菜单
        List<MenuResInfo> menuRes = simpleDao.selectFrom(MenuRes.class)
                .eq(E_MenuRes.enable, true)
                .orderBy(OrderBy.Type.Asc, E_MenuRes.orderCode)
                .find(MenuResInfo.class);

        //@todo

        final Map<Long, MenuResInfo> cacheMap = new LinkedHashMap<>();

        //2、放入Map
        menuRes.parallelStream()
                .forEachOrdered(m -> cacheMap.put(m.getId(), m));


        final Map<Long, MenuResInfo> cacheMap2 = new LinkedHashMap<>(cacheMap);

        //3、构建下级关系网络
        for (Map.Entry<Long, MenuResInfo> entry : cacheMap.entrySet()) {

            MenuResInfo menu = entry.getValue();

            Serializable parentId = menu.getParentId();

            if (parentId == null) {
                continue;
            }

            MenuResInfo parent = cacheMap2.get(parentId);

            if (parent.getChildren() == null) {
                parent.setChildren(new HashSet<>());
            }

            //设置关系
            parent.getChildren().add(menu);
        }

        if (userId != null) {

            List<String> roleList = StpUtil.getRoleList(userId);
            List<String> permissionList = StpUtil.getPermissionList(userId);

            for (Map.Entry<Long, MenuResInfo> entry : cacheMap2.entrySet()) {
                //
                MenuResInfo info = entry.getValue();

                List<String> requirePermissions = Stream.of(info.getRequireAuthorizations().split("[,;]"))
                        .filter(StringUtils::hasText).collect(Collectors.toList());

                if (requirePermissions.isEmpty()
                        || requirePermissions.parallelStream().allMatch(requirePermission -> this.isAuthorized(requirePermission, roleList, permissionList))) {
                    //如果没有要求权限，或是权限都满足，要求显示菜单
                    info.setEnable(true);
                } else if (Boolean.TRUE.equals(info.getAlwaysShow())) {
                    //菜单显示，但被禁用
                    info.setEnable(false);
                } else {
                    //如果没有权限
                    cacheMap.remove(entry.getKey());
                }
            }
        }

        return cacheMap.values().parallelStream()
                .filter(m -> m.getParentId() == null)
                .collect(Collectors.toList());
    }


    /**
     * 获取资源授权清单
     *
     * @param userId 如果 userId 为null，则表示获取全部的
     * @return
     */
    @Override
    public List<ModuleInfo> getAuthorizedResList(Object userId) {

        boolean hasUser = userId != null;

        if (hasUser) {
            UserInfo user = userService.findById(Long.parseLong(userId.toString()));
            checkUserState(user);
        }

        List<ModuleInfo> result = new LinkedList<>();

        List<String> roleList = hasUser ? StpUtil.getRoleList(userId) : null;
        List<String> permissionList = hasUser ? StpUtil.getPermissionList(userId) : null;

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

                    String prefix = String.join(getDelimiter(), "" + res.getDomain(), "" + res.getType(), "" + res.getId());

                    //第四层循环 资源操作
                    for (Res.Action action : new ArrayList<Res.Action>(res.getActionList())) {

                        ActionInfo actionInfo = new ActionInfo();

                        BeanUtils.copyProperties(action, actionInfo);

                        if (hasUser) {
                            //加入拥有的权限
                            if (isAuthorized(prefix, action, roleList, permissionList)) {
                                resInfo.getActionList().add(actionInfo);
                            }
                        } else {
                            resInfo.getActionList().add(actionInfo);
                        }
                    }

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

    @Override
    public List<String> getPermissionList(Object userId) {

        UserInfo user = userService.findById(Long.parseLong(userId.toString()));

        checkUserState(user);

        final List<String> permissionList = new LinkedList<>();

        simpleDao.selectFrom(Role.class)
                .select(E_Role.permissions)
                .eq(E_Role.enable, true)
                .in(E_Role.code, getRoleList(userId))
                .<String>find()
                .parallelStream()
                .filter(StringUtils::hasText)
                //JSON 转换
                .map(json -> (List<String>) gson.fromJson(json, listStrType))
//                .map(json -> (List<ResPermission>) gson.fromJson(json, resPermissionListType))
                .forEach(resPermissions -> {
                    //装换成数组
                    resPermissions.parallelStream().forEach(permissionList::add);
//                    resPermissions.parallelStream().map(ResPermission::toString).forEach(permissionList::add);
                });

        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object userId) {

        UserInfo user = userService.findById(Long.parseLong(userId.toString()));

        checkUserState(user);

        return StringUtils.hasText(user.getRoles()) ?
                gson.<List<String>>fromJson(user.getRoles(), listStrType)
                        .parallelStream()
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toList())
                : Collections.emptyList();
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

        Class<?> controllerClass = AopProxyUtils.ultimateTargetClass(method.getDeclaringClass());

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

        if (!StpUtil.isLogin()) {
            throw new AuthorizationException("not login", "未登录");
        }

        if (resAuthorize.onlyRequireAuthenticated()) {
            return;
        }

        ///////////////////////// 构建权限检查逻辑的闭包 //////////////////////////////////////

        boolean ok = isAuthorized(
                String.join(getDelimiter(), resAuthorize.domain(), resAuthorize.type(), resAuthorize.res()),
                SimpleResAction.newAction(resAuthorize),
                StpUtil.isLogin() ? StpUtil.getRoleList() : Collections.emptyList(),
                StpUtil.isLogin() ? StpUtil.getPermissionList() : Collections.emptyList()
        );

        if (!ok) {
            throw new AuthorizationException("AuthorizationException", "无法访问未授权的资源");
        }
    }

    @Override
    public String encryptPassword(String pwd) {
        return SaSecureUtil.sha1(pwd);
    }

    @Override
    public String getDeviceType(String ua) {

//        1. http://whatsmyuseragent.com/
//        2. http://whatsmyua.com/
//        3. http://www.useragentstring.com/

        if (ua.contains("Android ")
                || ua.contains("iPhone;")
                || ua.contains("iOS ")
                || ua.contains("iPhone OS ")) {
            return "Phone";
        } else if (ua.contains("iPad;")) {
            return "iPad";
        } else if (ua.contains("Windows ")
                || ua.contains("Macintosh;")
                || ua.contains(" Mac OS X ")) {
            return "PC";
        }

        return "Unknown";

    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {

        Role role = simpleDao.selectFrom(Role.class)
                .eq(E_Role.code, "SA")
                .findOne();

        if (role == null) {

            List<String> permissions = new LinkedList<>();

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType("*")
                    .setRes("*")
                    .setAction("*")
                    .toString());

            roleService.create(new CreateRoleReq()
                    .setCode("SA")
                    .setName("超级管理员")
                    .setOrgDataScope(Role.OrgDataScope.All)
                    .setPermissions(gson.toJson(permissions)));

            permissions.clear();

            permissions.add(new ResPermission()
                    .setDomain(ModuleOption.ID)
                    .setType("实体")
                    .setRes("*")
                    .setAction(EntityConst.QUERY_ACTION + "*")
                    .toString());

            roleService.create(new CreateRoleReq()
                    .setCode("test")
                    .setName("测试员")
                    .setOrgDataScope(Role.OrgDataScope.MySelf)
                    .setPermissions(gson.toJson(permissions)));
        }


        User user = simpleDao.selectFrom(User.class)
                .eq(E_User.loginName, "admin")
                .findOne();

        if (user == null) {

            List<String> roleList = new LinkedList<>();

            roleList.add("SA");
            userService.create(new CreateUserReq()
                    .setLoginName("admin")
                    .setPassword(encryptPassword("123456"))
                    .setName("超级管理员")
                    .setStaffNo("0000")
                    .setRoles(gson.toJson(roleList))

            );

            roleList.clear();
            roleList.add("test");

            userService.create(new CreateUserReq()
                    .setLoginName("test")
                    .setPassword(encryptPassword("123456"))
                    .setName("测试帐号")
                    .setStaffNo("9999")
                    .setRoles(gson.toJson(roleList))

            );
        }

    }

}
