package com.levin.oak.base.biz.rbac;

import cn.dev33.satoken.exception.IdTokenInvalidException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.PhoneUtil;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.*;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.CaptchaService;
import com.levin.oak.base.biz.SmsCodeService;
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
import com.levin.oak.base.utils.AmisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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

    @Autowired
    ApplicationContext context;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    BizRoleService bizRoleService;

    @Autowired
    MenuResService menuResService;

    @Autowired
    PluginManager pluginManager;

    @Autowired
    TenantService tenantService;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    CaptchaService captchaService;

    @Autowired
    SmsCodeService smsCodeService;

    /**
     * 用户权限的线程级别缓存
     */
    final ContextHolder<String, List<String>> permissionListThreadCache = ContextHolder.buildThreadContext(false, true);

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

    @Override
    public String sendSmsCode(String tenantId, String appId, String account) {

        LoginReq req = new LoginReq().setAccount(account).setAppId(appId).setTenantId(tenantId);

        Assert.hasText(req.getAccount(), "登录帐号不能为空");

        //如果是超级帐号，必须要密码
        boolean isSA = isSuperAdmin(req.getAccount());

        if (!isSA) {
            Assert.hasText(req.getTenantId(), "未知的租户");
        }

        UserInfo user = simpleDao.findOneByQueryObj(req.setPassword(null));

        auditUser(user);

        Assert.hasText(user.getTelephone(), "用户手机号未知");

        Assert.isTrue(PhoneUtil.isMobile(user.getTelephone().trim()), "用户手机号错误");

        String code = smsCodeService.genAndSendSmsCode(req.getTenantId(), req.getAppId(), req.getAccount(), user.getTelephone());

        return code.trim().startsWith("mock:") ? code :
                ("验证码已经发生至" + PhoneUtil.subBefore(user.getTelephone()) + "*" + PhoneUtil.subAfter(user.getTelephone()));

    }

    /**
     * 认证，并返回token
     *
     * @return 认证成功后的token
     */
    @Override
    public <REQ extends AuthReq> String auth(REQ authReq, Map<String, Object>... extras) {

        Assert.notNull(authReq, "认证请求对象不能为空");

        LoginReq req = null;

        if (authReq instanceof LoginReq) {
            req = (LoginReq) authReq;
        } else {
            //拷贝对象
            req = simpleDao.copy(authReq, new LoginReq(), 1);
        }

        Assert.hasText(req.getAccount(), "登录帐号不能为空");

        //如果是超级帐号，必须要密码
        boolean isSA = isSuperAdmin(req.getAccount());

        if (!isSA) {
            Assert.hasText(req.getTenantId(), "未知的租户");
        }

        boolean requirePwd = true;

        //如果开启了验证码
        if (frameworkProperties.getVerificationCodeLen() > 1) {

            Assert.hasText(req.getVerificationCode(), "验证码不能为空");

            if ("sms".equalsIgnoreCase(req.getVerificationCodeType())) {

                //如果是短信验证码，可以不验证密码
                requirePwd = false;

                //验证短信
                Assert.isTrue(smsCodeService.verification(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "短信验证码错误");

            } else if ("captcha".equalsIgnoreCase(req.getVerificationCodeType())) {

                //验证图片验证码
                Assert.isTrue(captchaService.verification(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "图片验证码错误");
            } else {
                throw new IllegalArgumentException("不支持的验证码类型:" + req.getVerificationCodeType());
            }
        }

        //@todo 考虑超级用户必须要密码
        if (requirePwd) {
            Assert.hasText(req.getPassword(), "密码不能为空");
        }

        //存在多个用户的情况
        UserInfo user = simpleDao.findOneByQueryObj(
                //密码加密
                req.setPassword(encryptPassword(req.getPassword()))
        );

        auditUser(user);

        return auth(user.getId(), MapUtils.putFirst("user-agent", req.getUa()).build());
    }

    /**
     * 直接认证，并返回token
     *
     * @param loginId
     * @param params
     * @return 认证成功后的token
     */
    @Override
    public String auth(String loginId, Map<String, Object>... params) {

//        Assert.notNull(loginId, "loginId is null");

        Assert.hasText(loginId, "loginId is empty");

        //默认登录
        StpUtil.login(loginId, getDeviceType(getValue("user-agent", params)));

        return StpUtil.getTokenValue();
    }

    /**
     * @param key
     * @param params
     * @param <T>
     * @return
     */
    private static <T> T getValue(String key, Map<String, Object>... params) {
        return Stream.of(params)
                .filter(map -> map.containsKey(key))
                .map(map -> (T) map.get(key))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isLogin() {
        return StpUtil.isLogin();
    }


    @Override
    public RbacUserInfo<String> getUserInfo() {
        return getUserInfo(getLoginId());
    }

    /**
     * 获取当前登录用户信息
     *
     * @param loginId
     * @return
     */
    @Override
    public RbacUserInfo<String> getUserInfo(String loginId) {
        return auditUser(userService.findById(loginId).setPassword(null));
    }

    @Override
    public String getLoginIdByToken(String token) {

        Assert.hasText(token, "token is empty");

        String loginId = (String) StpUtil.getLoginIdByToken(token);

        if (!StringUtils.hasText(loginId)) {
            throw new IdTokenInvalidException("token:" + token);
        }

        return loginId;
    }

    @Override
    public void invalidate(String token) {
        if (StringUtils.hasText(token)) {
            StpUtil.logoutByTokenValue(token);
        }
    }

    @Override
    public void logout() {
        StpUtil.logout();
       // invalidate(StpUtil.getTokenValue());
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

        Assert.notNull(user,"1帐号不存在");

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
    public String getLoginId() {

        Object loginId = StpUtil.getLoginId();

        if (loginId == null) {
            throw NotLoginException.newInstance("user", NotLoginException.NOT_TOKEN);
        }

        return (String) loginId;
    }


    @Override
    public List<String> getPermissionList(String loginId) {

        Assert.notNull(loginId, "loginId is null");

        return permissionListThreadCache.getAndAutoPut("P-" + loginId, null,
                () -> {
                    List<String> roleList = getRoleList(loginId);

                    if (roleList == null || roleList.isEmpty()) {
                        return Collections.emptyList();
                    }

                    UserInfo userInfo = (UserInfo) getUserInfo(loginId);

                    auditUser(userInfo);

                    return bizRoleService.getRolePermissionList(userInfo.getTenantId(), roleList);
                }
        );
    }

    @Override
    public List<String> getRoleList(String loginId) {

        Assert.notNull(loginId, "loginId is null");

        return permissionListThreadCache.getAndAutoPut("R-" + loginId, null,
                // // JsonStrArrayUtils.parse(user.getRoleList(), null, null);
                () -> Collections.unmodifiableList(auditUser(userService.findById(loginId)).getRoleList())
        );
    }

    @Override
    public String encryptPassword(String pwd) {
        return StringUtils.hasText(pwd) ? SaSecureUtil.sha1(pwd) : null;
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

    @Override
    public void clearThreadCacheData() {
        permissionListThreadCache.clear();
    }

    /**
     * 自动创建一些空菜单
     */
    private void initMenu() {

        //  final String defaultIcon = "fa fa-list";// (serverProperties.getServlet().getContextPath() + "/" + frameworkProperties.getAdminPath() + "/img/menu-256.png").replace("//", "/");

        final AtomicInteger index = new AtomicInteger();

        for (Plugin plugin : pluginManager.getInstalledPlugins()) {

            MenuRes menu = simpleDao.selectFrom(MenuRes.class)
                    .eq(E_MenuRes.domain, plugin.getId())
                    .isNull(E_Role.tenantId)
                    .findOne();

            if (menu != null) {
                continue;
            }

            MenuRes pluginRootMenu = simpleDao.create(new MenuRes()
                    .setPath(plugin.getPackageName() + "/" + plugin.getVersion() + "/admin/index")
//                    .setIcon(defaultIcon)
                    .setDomain(plugin.getPackageName())
                    .setName(plugin.getName())
                    .setEnable(plugin.isEnable())
                    .setOrderCode(plugin.getOrderCode())
                    .setRemark(plugin.getRemark()));

            RbacUtils.getMenuItemByController(context, plugin.getPackageName(), EntityConst.QUERY_ACTION)
                    .stream().filter(Objects::nonNull).forEach(menuItem -> {

                final int no = index.incrementAndGet();

                final String path = menuItem.getPath().replace("/api/", "/admin/");

                //创建菜单
                log.info("创建菜单{} - 插件[ {} ][ {} --> {}]", no, plugin.getId(), menuItem.getName(), path);

                simpleDao.create(
                        simpleDao.copy(menuItem,
                                new MenuRes()
                                        .setPath(path)
//                                        .setIcon(defaultIcon)
                                        .setParentId(pluginRootMenu.getId()),
                                1,
                                E_MenuRes.parentId,
                                E_MenuRes.children,
                                E_MenuRes.icon,
                                E_MenuRes.parent,
                                E_MenuRes.path)
                );

                //创建默认页面
                log.info("创建页面{} - 插件[ {} ][ {} --> {}]", no, plugin.getId(), menuItem.getName(), path);

                simpleDao.create(new SimplePage()
                                .setType(SimplePage.Type.json.name())
                                .setCategory("amis")
                                .setGroupName("管理后台页面|" + plugin.getName())
                                .setPath(path)
                                //设置访问需要的权限
                                .setRequireAuthorizations(menuItem.getRequireAuthorizations())

//                        .setIcon(defaultIcon)
                                .setContent(AmisUtils.readAdminClassPathResource(path))
                                .setDomain(plugin.getPackageName())
                                .setName(menuItem.getName())
                                .setRemark("Amis默认页面")
                );

            });
        }
    }


    private void initUser() {

        QueryTenantReq req = new QueryTenantReq().setOrderBy(E_Tenant.id).setOrderDir(OrderBy.Type.Asc);

        //如果允许域名
        if (frameworkProperties.getTenantBindDomain().isEnable()) {
            req.setContainsDomainList(Arrays.asList("127.0.0.1"));
        }

        TenantInfo tenantInfo = tenantService.findOne(req);

        if (tenantInfo == null) {

            String id = tenantService.create(new CreateTenantReq()
                    .setName("测试租户")
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

            simpleDao.create(new CreateRoleReq()
                    .setCode(RbacRoleObject.SA_ROLE)
                    .setName("超级管理员")
                    .setEditable(false)
                    .setOrgDataScope(Role.OrgDataScope.All)
                    .setPermissionList(Collections.singletonList(
                            new ResPermission()
                                    .setDomain("*")
                                    .setType("*")
                                    .setRes("*")
                                    .setAction("*")
                                    .toString()
                            )
                    )
            );

        }
        ///////////////////////////////////////////////////

        role = simpleDao.selectFrom(Role.class)
                .eq(E_Role.code, RbacRoleObject.ADMIN_ROLE)
                .eq(E_User.tenantId, tenantInfo.getId())
                .findOne();

        if (role == null) {

            simpleDao.create(
                    new CreateRoleReq()
                            .setCode(RbacRoleObject.ADMIN_ROLE)
                            .setName("管理员")
                            .setEditable(false)

                            .setOrgDataScope(Role.OrgDataScope.All)
                            .setPermissionList(Arrays.asList(

                                    new ResPermission()
                                            .setDomain("*")
                                            .setType(EntityConst.TYPE_NAME)
                                            .setRes("*")
                                            .setAction("*")
                                            .toString(),

                                    new ResPermission()
                                            .setDomain("*")
                                            .setType(EntityConst.COMMON_TYPE_NAME)
                                            .setRes("*")
                                            .setAction("*")
                                            .toString(),

                                    new ResPermission()
                                            .setDomain("*")
                                            .setType(EntityConst.SYS_TYPE_NAME)
                                            .setRes("*")
                                            .setAction("*")
                                            .toString()
                            ))
                            .setTenantId(tenantInfo.getId())
            );

//            role = simpleDao.selectFrom(Role.class)
//                    .eq(E_Role.code, RbacRoleObject.ADMIN_ROLE)
//                    .eq(E_User.tenantId, tenantInfo.getId())
//                    .findOne();
        }

        ///////////////////////////////////////////////////

        User user = simpleDao.selectFrom(User.class)
                .isNull(E_User.tenantId)
                .eq(E_User.email, SA_ACCOUNT)
                .findOne();

        if (user == null) {
            simpleDao.create(
                    new CreateUserReq()
                            .setEmail(SA_ACCOUNT)
                            .setTelephone("18895279527")
                            .setPassword(encryptPassword("123456"))
                            .setName("超级管理员")
                            .setEditable(false)
                            .setStaffNo("0000")
                            .setRoleList(Collections.singletonList(RbacRoleObject.SA_ROLE))
            );
        }

        /////////////////////////////////////////////////////////////

        user = simpleDao.selectFrom(User.class)
                .eq(E_User.tenantId, tenantInfo.getId())
                //   .eq(E_User.email, "admin")
                .findOne();

        if (user == null) {
            simpleDao.create(new CreateUserReq()
                    .setEmail("admin")
                    .setTelephone("18095279527")
                    .setPassword(encryptPassword("123456"))
                    .setName("管理员")
                    .setStaffNo("9999")
                    .setRoleList(Collections.singletonList(RbacRoleObject.ADMIN_ROLE))
                    .setTenantId(tenantInfo.getId())
            );
        }

        ///////////////////////////////////////////////////

    }


}
