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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * ????????????
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

    @Resource
    ServerProperties serverProperties;

    @Resource
    FrameworkProperties frameworkProperties;

    @Resource
    ResourceLoader resourceLoader;

    @Resource
    CaptchaService captchaService;

    @Resource
    SmsCodeService smsCodeService;

    /**
     * ?????????????????????????????????
     */
    final ContextHolder<String, List<String>> permissionListThreadCache = ContextHolder.buildThreadContext(false, true);

    @PostConstruct
    public void init() {

        log.info("????????????????????????...");

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

        Assert.hasText(req.getAccount(), "????????????????????????");

        //???????????????????????????????????????
        boolean isSA = isSuperAdmin(req.getAccount());

        if (!isSA) {
            Assert.hasText(req.getTenantId(), "???????????????");
        }

        UserInfo user = simpleDao.findOneByQueryObj(req.setPassword(null));

        auditUser(user);

        Assert.hasText(user.getTelephone(), "?????????????????????");

        Assert.isTrue(PhoneUtil.isMobile(user.getTelephone().trim()), "?????????????????????");

        String code = smsCodeService.genAndSendSmsCode(req.getTenantId(), req.getAppId(), req.getAccount(), user.getTelephone());

        return code.trim().startsWith("mock:") ? code :
                ("????????????????????????" + PhoneUtil.subBefore(user.getTelephone()) + "*" + PhoneUtil.subAfter(user.getTelephone()));

    }

    /**
     * ??????????????????token
     *
     * @return ??????????????????token
     */
    @Override
    public <REQ extends AuthReq> String auth(REQ authReq, Map<String, Object>... extras) {

        Assert.notNull(authReq, "??????????????????????????????");

        LoginReq req = null;

        if (authReq instanceof LoginReq) {
            req = (LoginReq) authReq;
        } else {
            //????????????
            req = simpleDao.copy(authReq, new LoginReq(), 1);
        }

        Assert.hasText(req.getAccount(), "????????????????????????");

        //???????????????????????????????????????
        boolean isSA = isSuperAdmin(req.getAccount());

        if (!isSA) {
            Assert.hasText(req.getTenantId(), "???????????????");
        }

        boolean requirePwd = true;

        //????????????????????????
        if (frameworkProperties.getVerificationCodeLen() > 1) {

            Assert.hasText(req.getVerificationCode(), "?????????????????????");

            if ("sms".equalsIgnoreCase(req.getVerificationCodeType())) {

                //????????????????????????????????????????????????
                requirePwd = false;

                //????????????
                Assert.isTrue(smsCodeService.verification(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "?????????????????????");

            } else if ("captcha".equalsIgnoreCase(req.getVerificationCodeType())) {

                //?????????????????????
                Assert.isTrue(captchaService.verification(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "?????????????????????");
            } else {
                throw new IllegalArgumentException("???????????????????????????:" + req.getVerificationCodeType());
            }
        }

        //@todo ?????????????????????????????????
        if (requirePwd) {
            Assert.hasText(req.getPassword(), "??????????????????");
        }

        //???????????????????????????
        UserInfo user = simpleDao.findOneByQueryObj(
                //????????????
                req.setPassword(encryptPassword(req.getPassword()))
        );

        auditUser(user);

        return auth(user.getId(), MapUtils.putFirst("user-agent", req.getUa()).build());
    }

    /**
     * ????????????????????????token
     *
     * @param loginId
     * @param params
     * @return ??????????????????token
     */
    @Override
    public String auth(String loginId, Map<String, Object>... params) {

//        Assert.notNull(loginId, "loginId is null");

        Assert.hasText(loginId, "loginId is empty");

        //????????????
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
     * ??????????????????????????????
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
        invalidate(StpUtil.getTokenValue());
    }

    /**
     * ????????????
     *
     * @param user
     * @return
     * @throws AuthorizationException
     */
//    @Override
    public UserInfo auditUser(UserInfo user) throws AuthorizationException {

        if (user == null) {
            throw new IllegalArgumentException("1???????????????");
        }

        if (!user.getEnable()
                || !User.State.Normal.equals(user.getState())) {
            throw new IllegalArgumentException("2??????????????????");
        }

        if (user.getExpiredDate() != null
                && user.getExpiredDate().getTime() < System.currentTimeMillis()) {
            throw new IllegalArgumentException("3???????????????");
        }

        //???????????????????????????????????? SA ???????????????
        if (!StringUtils.hasText(user.getTenantId())) {
            Assert.isTrue(user.isSuperAdmin(), "????????????????????????");
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
     * ???????????????
     */
    @Override
    public void initData() {

        log.info("????????????????????????????????????");

        initUser();

        initMenu();
    }

    @Override
    public void clearThreadCacheData() {
        permissionListThreadCache.clear();
    }

    /**
     * ???????????????????????????
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
                    .setPath(plugin.getPackageName() + "/" + plugin.getVersion() + "/admin/Index")
//                    .setIcon(defaultIcon)
                    .setDomain(plugin.getPackageName())
                    .setName(plugin.getName())
                    .setEnable(plugin.isEnable())
                    .setOrderCode(plugin.getOrderCode())
                    .setRemark(plugin.getRemark()));

            RbacUtils.getMenuItemByController(context, plugin.getPackageName(), EntityConst.QUERY_ACTION)
                    .parallelStream().forEach(menuItem -> {

                final int no = index.incrementAndGet();

                final String path = menuItem.getPath().replace("/api/", "/admin/");
                //????????????
                log.info("????????????{} - ??????[ {} ][ {} --> {}]", no, plugin.getId(), menuItem.getName(), path);

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

                //??????????????????
                log.info("????????????{} - ??????[ {} ][ {} --> {}]", no, plugin.getId(), menuItem.getName(), path);

                simpleDao.create(new SimplePage()
                                .setType(SimplePage.Type.json.name())
                                .setCategory("amis")
                                .setGroupName("??????????????????|" + plugin.getName())
                                .setPath(path)
                                //???????????????????????????
                                .setRequireAuthorizations(menuItem.getRequireAuthorizations())

//                        .setIcon(defaultIcon)
                                .setContent(AmisUtils.readAdminClassPathResource(path))
                                .setDomain(plugin.getPackageName())
                                .setName(menuItem.getName())
                                .setRemark("Amis????????????")
                );

            });
        }
    }


    private void initUser() {

        QueryTenantReq req = new QueryTenantReq().setOrderBy(E_Tenant.id).setOrderDir(OrderBy.Type.Asc);

        //??????????????????
        if (frameworkProperties.getTenantBindDomain().isEnable()) {
            req.setContainsDomainList(Arrays.asList("127.0.0.1"));
        }

        TenantInfo tenantInfo = tenantService.findOne(req);

        if (tenantInfo == null) {

            String id = tenantService.create(new CreateTenantReq()
                    .setName("????????????")
                    .setRemark("??????????????????")
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
                    .setName("????????????")
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
                    .setName("???????????????")
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
                            .setName("?????????")
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
                            .setName("???????????????")
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
                    .setPassword(encryptPassword("123456"))
                    .setName("?????????")
                    .setStaffNo("9999")
                    .setRoleList(Collections.singletonList(RbacRoleObject.ADMIN_ROLE))
                    .setTenantId(tenantInfo.getId())
            );
        }

        ///////////////////////////////////////////////////

    }


}
