package com.levin.oak.base.biz.rbac;

import cn.dev33.satoken.exception.IdTokenInvalidException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.PhoneUtil;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.*;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.BizUserService;
import com.levin.oak.base.biz.CaptchaService;
import com.levin.oak.base.biz.SmsCodeService;
import com.levin.oak.base.biz.rbac.req.LoginReq;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 认证服务
 *
 * @todo 分离web相关部分
 */

@Slf4j
@Order
@Service(PLUGIN_PREFIX + "DefaultAuthService")
@ConditionalOnMissingBean(AuthService.class)
@ConditionalOnProperty(value = PLUGIN_PREFIX + "DefaultAuthService", matchIfMissing = true)
@ResAuthorize(ignored = true)
public class AuthServiceImpl
        implements AuthService,RbacPermissionThreadCachedService<String>, ApplicationListener<ContextRefreshedEvent> {

    final ResAuthorize defaultResAuthorize = getClass().getAnnotation(ResAuthorize.class);

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

    @Autowired(required = false)
    CaptchaService captchaService;

    @Autowired(required = false)
    SmsCodeService smsCodeService;

    @Autowired
    RbacService rbacService;


    @DubboReference
    BizUserService bizUserService;


    static final ContextHolder<Method, ResAuthorize> cache = ContextHolder.buildContext(true);


    /**
     * 用户权限的线程级别缓存
     */
    final ContextHolder<String, List<String>> permissionListThreadCache = ContextHolder.buildThreadContext(false, true);

    final static ThreadLocal<UserInfo> currentUser = new ThreadLocal<>();

    @PostConstruct
    public void init() {
        log.info("默认认证服务启用...");
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

        //如果是超级用户，必须要密码
        boolean isSA = isSuperAdmin(req.getAccount());

        if (!isSA) {
            Assert.hasText(req.getTenantId(), "未知的租户");
        }

        UserInfo user = bizUserService.findUser(req.setPassword(null));

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
            req = BeanUtil.copyProperties(authReq, LoginReq.class);
        }

        Assert.hasText(req.getAccount(), "登录帐号不能为空");

        //如果是超级帐号，必须要密码
        boolean isSA = isSuperAdmin(req.getAccount());

        if (!isSA) {
            Assert.hasText(req.getTenantId(), "未知的租户");
        }

        boolean requirePwd = true;

        if ("sms".equalsIgnoreCase(req.getVerificationCodeType())) {

            Assert.isTrue(frameworkProperties.isEnableSmsVerificationCode(), "短信验证关闭");
            Assert.notNull(smsCodeService, "短信验证码服务不存在");

            Assert.hasText(req.getVerificationCode(), "验证码不能为空");

            //如果是短信验证码，可以不验证密码
            requirePwd = false;

            //验证短信
            Assert.isTrue(smsCodeService.verification(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "短信验证码错误");

        } else if ("captcha".equalsIgnoreCase(req.getVerificationCodeType())) {

            //验证图片验证码
            if (frameworkProperties.isEnableCaptchaVerificationCode()) {
                Assert.notNull(captchaService, "图片验证码服务不存在");
                Assert.hasText(req.getVerificationCode(), "验证码不能为空");
                Assert.isTrue(captchaService.verification(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "图片验证码错误");
            } else {
                //可以不验证
            }

        } else {
            throw new IllegalArgumentException("不支持的验证码类型:" + req.getVerificationCodeType());
        }

        //@todo 考虑超级用户必须要密码
        if (requirePwd) {
            Assert.hasText(req.getPassword(), "密码不能为空");
        }

        //存在多个用户的情况
        UserInfo user = bizUserService.findUser(
                //密码加密
                req.setPassword(encryptPassword(req.getPassword()))
        );

        auditUser(user);

        setCurrentUser(user);

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


    protected void setCurrentUser(UserInfo userInfo) {
        currentUser.set(userInfo);
    }

    @Override
    public RbacUserInfo<String> getUserInfo() {

        UserInfo userInfo = currentUser.get();

        if (userInfo == null) {
            userInfo = (UserInfo) getUserInfo(getLoginId());
            currentUser.set(userInfo);
        }

        return userInfo;
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

        Assert.notNull(user, "1帐号不存在");

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
    public List<String> getRoleList(String userId) {

        Assert.notNull(userId, "userId is null");

        return permissionListThreadCache.getAndAutoPut("R-" + userId, null,
                () -> {
                    List<String> roleList = rbacService.getRoleList(userId);

                    if (roleList == null || roleList.isEmpty()) {
                        return Collections.emptyList();
                    }

                    UserInfo userInfo = (UserInfo) getUserInfo(userId);

                    return userInfo.getRoleList();
                }
        );
    }

    public List<String> getPermissionList(String userId) {

        Assert.notNull(userId, "userId is null");

        return permissionListThreadCache.getAndAutoPut("P-" + userId, null,
                () -> {
                    List<String> roleList = rbacService.getRoleList(userId);

                    if (roleList == null || roleList.isEmpty()) {
                        return Collections.emptyList();
                    }

                    UserInfo userInfo = (UserInfo) getUserInfo(userId);

                    return bizRoleService.getRolePermissionList(userInfo.getTenantId(), roleList);
                }
        );
    }


    @Override
    public String encryptPassword(String pwd) {
        return StringUtils.hasText(pwd) ? (pwd) : null;
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

    @Override
    public void clearThreadCacheData() {
        permissionListThreadCache.clear();
    }


    /**
     * 检查访问权限
     *
     * @return
     */
    @Override
    public void checkAuthorize(Object beanOrClass, @NonNull Method method) throws AuthorizationException {

        if (method == null) {
            return;
        }

        Class<?> controllerClass = beanOrClass != null ? (beanOrClass instanceof Class ? (Class) beanOrClass : beanOrClass.getClass())
                : method.getDeclaringClass();// AopProxyUtils.ultimateTargetClass(method.getDeclaringClass());

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

        if (getUserInfo() == null) {
            throw new AuthorizationException(401, "未登录");
        }

        if (resAuthorize.onlyRequireAuthenticated()) {
            return;
        }

        ///////////////////////// 构建权限检查逻辑的闭包 //////////////////////////////////////

        String loginId = getLoginId();

//        boolean ok = rbacService.isAuthorized(
//                String.join(rbacService.getPermissionDelimiter(), resAuthorize.domain(), resAuthorize.type(), resAuthorize.res()),
//                SimpleResAction.newAction(resAuthorize),
//                rbacService.getRoleList(loginId),
//                getPermissionList(loginId),
//                rbacService.getAuthorizeContext()
//        );

        boolean ok =  rbacService.isAuthorized(loginId,resAuthorize);

        if (!ok) {
            throw new AuthorizationException(401, "未授权的操作");
        }
    }
}
