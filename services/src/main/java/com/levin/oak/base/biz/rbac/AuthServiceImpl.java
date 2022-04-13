package com.levin.oak.base.biz.rbac;

import cn.dev33.satoken.exception.IdTokenInvalidException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.*;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.rbac.req.LoginReq;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.appclient.req.CreateAppClientReq;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.CreateTenantReq;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.CreateUserReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 认证服务
 */

@Slf4j
@Order
//@ConditionalOnMissingBean(AuthService.class)
@ConditionalOnProperty(value = PLUGIN_PREFIX + "DefaultAuthService", matchIfMissing = true)
@Service(PLUGIN_PREFIX + "DefaultAuthService")
public class AuthServiceImpl
        extends BaseService
        implements AuthService, ApplicationListener<ContextRefreshedEvent> {

//    static final Gson gson = new Gson();
//
//    static final Type resPermissionListType = new TypeToken<List<ResPermission>>() {
//    }.getType();
//
//    static final Type listStrType = new TypeToken<List<String>>() {
//    }.getType();

    @Resource
    ApplicationContext context;

    @Resource
    UserService userService;

    @Resource
    RoleService roleService;

    @Resource
    BizRoleService bizRoleService;

    @Resource
    MenuResService menuResService;

    @Resource
    PluginManager pluginManager;

    @Resource
    TenantService tenantService;

    @PostConstruct
    public void init() {

        log.info("默认认证服务启用...");

        //
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.info("On ContextRefreshedEvent " + event);

        if (event.getApplicationContext() == this.context) {

        }

    }


    /**
     * 认证，并返回token
     *
     * @param account
     * @param password
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    @Override
    public String auth(String tenantId, String account, String password, String userAgent, Map<String, Object>... params) {
        return loginByPassword(new LoginReq().setTenantId(tenantId).setAccount(account).setPassword(password).setUa(userAgent));
    }


    /**
     * 直接认证，并返回token
     *
     * @param loginId
     * @param userAgent
     * @param params
     * @return 认证成功后的token
     */
    @Override
    public String auth(String loginId, String userAgent, Map<String, Object>... params) {

//        Assert.notNull(loginId, "loginId is null");

        Assert.hasText(loginId, "loginId is empty");

        //默认登录
        StpUtil.login(loginId, getDeviceType(userAgent));

        return StpUtil.getTokenValue();
    }

    @Override
    public boolean isLogin() {
        return StpUtil.isLogin();
    }


    @Override
    public RbacUserInfo<String> getUserInfo() {
        return getUserInfo(getLoginUserId());
    }

    /**
     * 获取当前登录用户信息
     *
     * @param loginId
     * @return
     */
    @Override
    public RbacUserInfo<String> getUserInfo(Object loginId) {
        return auditUser(userService.findById(Long.parseLong(loginId.toString())).setPassword(null));
    }

    @Override
    public String getLoginIdByToken(String token) {

        Assert.hasText(token, "token is empty");

        Object loginId = StpUtil.getLoginIdByToken(token);

        if (loginId == null || !StringUtils.hasText(loginId.toString())) {
            throw new IdTokenInvalidException("token:" + token);
        }

        return loginId.toString();
    }

    @Override
    public void invalidate(String token) {

        if (StringUtils.hasText(token)) {
            StpUtil.logoutByTokenValue(token);
        }

    }

    @Override
    public void logout() {
        invalidate(StpUtil.getTokenValue());
    }

    public String loginByPassword(LoginReq req) {

        if (!StringUtils.hasText(req.getAccount())
                || !StringUtils.hasText(req.getPassword())) {
            throw new AuthorizationException("account", "请输入正确的帐号或密码");
        }

        UserInfo user = simpleDao.findOneByQueryObj(
                //密码加密
                req.setPassword(encryptPassword(req.getPassword()))
        );

        auditUser(user);

        return auth(user.getId().toString(), req.getUa());

    }

    /**
     * 审计用户
     *
     * @param user
     * @return
     * @throws AuthorizationException
     */
//    @Override
    public UserInfo auditUser(UserInfo user) throws AuthorizationException {

        if (user == null) {
            throw new IllegalArgumentException("1帐号或密码错误");
        }

        if (!user.getEnable()
                || !User.State.Normal.equals(user.getState())) {
            throw new IllegalArgumentException("2帐号状态异常");
        }

        if (user.getExpiredDate() != null
                && user.getExpiredDate().getTime() < System.currentTimeMillis()) {
            throw new IllegalArgumentException("3帐号已过期");
        }

        //如果是无租户用户，必须是 SA 角色的用户
        if (!StringUtils.hasText(user.getTenantId())) {
            Assert.isTrue(user.isSuperAdmin(), "非法的无租户用户");
        }

        return user;
    }

    @Override
    public <T> T getLoginUserId() {

        Object loginId = StpUtil.getLoginId();

        if (loginId == null) {
            throw NotLoginException.newInstance("user", "未登录");
        }

        return (T) loginId;
    }


    @Override
    public List<String> getPermissionList(Object loginId) {

        Assert.notNull(loginId, "loginId is null");

//        if (loginId == null) {
//            loginId = getLoginUserId();
//        }

        List<String> roleList = getRoleList(loginId);

        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }

        UserInfo userInfo = (UserInfo) getUserInfo(loginId);

        auditUser(userInfo);

        return bizRoleService.getRolePermissionList(userInfo.getTenantId(), userInfo.getRoleList());
    }

    @Override
    public List<String> getRoleList(Object loginId) {

        Assert.notNull(loginId, "loginId is null");

//        if (loginId == null) {
//            loginId = getLoginUserId();
//        }

        UserInfo user = userService.findById(Long.parseLong(loginId.toString()));

        auditUser(user);

        return user.getRoleList();// JsonStrArrayUtils.parse(user.getRoleList(), null, null);
    }

    @Override
    public String encryptPassword(String pwd) {

        if (!StringUtils.hasText(pwd)) {
            return null;
        }

        return SaSecureUtil.sha1(pwd);
    }

    @Override
    public String getDeviceType(String ua) {

//        1. http://whatsmyuseragent.com/
//        2. http://whatsmyua.com/
//        3. http://www.useragentstring.com/

        if (ua == null) {
            return "Unknown";
        }

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

        log.info("初始化用户数据和菜单数据");

        initUser();

        initMenu();
    }

    /**
     * 自动创建一些空菜单
     */
    private void initMenu() {

        for (Plugin plugin : pluginManager.getInstalledPlugins()) {

            MenuRes menu = simpleDao.selectFrom(MenuRes.class)
                    .eq(E_MenuRes.subSystem, plugin.getId())
                    .isNull(E_Role.tenantId).findOne();

            if (menu != null) {
                continue;
            }

            MenuRes pluginRootMenu = simpleDao.create(new MenuRes().setSubSystem(plugin.getId())
                    .setName(plugin.getName())
                    .setEnable(plugin.isEnable())
                    .setOrderCode(plugin.getOrderCode())
                    .setRemark(plugin.getRemark()));

            RbacUtils.getMenuItemByController(context, plugin.getId(), EntityConst.QUERY_ACTION)
                    .parallelStream().forEach(menuItem -> {
                //创建菜单
                log.info("创建插件[ {} ]的默认菜单[ {} --> {}]", plugin.getId(), menuItem.getName(), menuItem.getPath());
                simpleDao.create(simpleDao.copy(menuItem, new MenuRes().setParentId(pluginRootMenu.getId()),
                        1, E_MenuRes.parentId, E_MenuRes.children, E_MenuRes.parent));
            });

        }

    }

    private void initUser() {

        QueryTenantReq req = new QueryTenantReq().setContainsDomainList(Arrays.asList("127.0.0.1"));

        TenantInfo tenantInfo = tenantService.findOne(req);

        if (tenantInfo == null) {
            String id = tenantService.create(new CreateTenantReq()
                    .setName("默认租户")
                    .setRemark("支持本地地址")
                    .setDomainList(Arrays.asList("127.0.0.1", "localhost"))
            );

            tenantInfo = tenantService.findById(id);

//            tenantInfo = tenantService.findOne(req);
        }

        long count = simpleDao.selectFrom(AppClient.class)
                .select(E_AppClient.appId)
                .eq(E_AppClient.tenantId, tenantInfo.getId())
                .count();

        if (count < 1) {
            simpleDao.create(new CreateAppClientReq()
                    .setName("默认应用")
                    .setTenantId(tenantInfo.getId())
            );
        }

        Role role = simpleDao.selectFrom(Role.class)
                .eq(E_Role.code, RbacRoleObject.SA_ROLE)
                .isNull(E_Role.tenantId)
                .findOne();

        if (role == null) {

            List<String> permissions = new LinkedList<>();

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType("*")
                    .setRes("*")
                    .setAction("*")
                    .toString());

            simpleDao.create(new CreateRoleReq()
                    .setCode(RbacRoleObject.SA_ROLE)
                    .setName("超级管理员")
                    .setEditable(false)
                    .setOrgDataScope(Role.OrgDataScope.All)
                    .setPermissionList(permissions));

            ///////////////////////////////////////////////////

            permissions.clear();

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType(EntityConst.TYPE_NAME)
                    .setRes("*")
                    .setAction("*")
                    .toString());

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType(EntityConst.COMMON_TYPE_NAME)
                    .setRes("*")
                    .setAction("*")
                    .toString());

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType(EntityConst.SYS_TYPE_NAME)
                    .setRes("*")
                    .setAction("*")
                    .toString());

            simpleDao.create(new CreateRoleReq()
                    .setCode(RbacRoleObject.ADMIN_ROLE)
                    .setName("管理员")
                    .setEditable(false)
                    .setOrgDataScope(Role.OrgDataScope.All)
                    .setPermissionList(permissions));

            ///////////////////////////////////////////////////

            permissions.clear();

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType(EntityConst.TYPE_NAME)
                    .setRes("*")
                    .setAction(EntityConst.QUERY_ACTION + "*")
                    .toString());

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType(EntityConst.TYPE_NAME)
                    .setRes("*")
                    .setAction(EntityConst.STAT_ACTION + "*")
                    .toString());

            permissions.add(new ResPermission()
                    .setDomain("*")
                    .setType(EntityConst.TYPE_NAME)
                    .setRes("*")
                    .setAction(EntityConst.VIEW_DETAIL_ACTION + "*")
                    .toString());

            simpleDao.create(new CreateRoleReq()
                    .setCode("R_TEST")
                    .setName("只读测试员")
                    .setOrgDataScope(Role.OrgDataScope.MySelf)
                    .setPermissionList(permissions));

            ///////////////////////////////////////////////////
        }


        User user = simpleDao.selectFrom(User.class)
                .isNull(E_User.tenantId)
                .eq(E_User.loginName, "sa")
                .findOne();

        if (user != null) {
            return;
        }

        List<String> roleList = new LinkedList<>();

        roleList.add(RbacRoleObject.SA_ROLE);

        simpleDao.create(new CreateUserReq()
                .setLoginName("sa")
                .setPassword(encryptPassword("123456"))
                .setName("超级管理员")
                .setEditable(false)
                .setStaffNo("0000")
                .setRoleList(roleList)
        );

        ///////////////////////////////////////////////////

        roleList.clear();
        roleList.add(RbacRoleObject.ADMIN_ROLE);

        simpleDao.create(new CreateUserReq()
                .setLoginName("admin")
                .setPassword(encryptPassword("123456"))
                .setName("管理员")
                .setStaffNo("9999")
                .setRoleList(roleList)
                .setTenantId(tenantInfo.getId())

        );

        ///////////////////////////////////////////////////

        roleList.clear();
        roleList.add("test");

        simpleDao.create(new CreateUserReq()
                .setLoginName("test")
                .setPassword(encryptPassword("123456"))
                .setName("只读测试员")
                .setStaffNo("8888")
                .setRoleList(roleList)
                .setTenantId(tenantInfo.getId())

        );

        ///////////////////////////////////////////////////

    }

}
