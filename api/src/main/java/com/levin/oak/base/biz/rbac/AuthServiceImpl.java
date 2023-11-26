package com.levin.oak.base.biz.rbac;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.PhoneUtil;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.AuthReq;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.rbac.ResPermission;
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
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Role;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 认证服务
 *
 * @todo 分离web相关部分
 */

@Slf4j
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Service(PLUGIN_PREFIX + "DefaultAuthService")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "DefaultAuthService", havingValue = "true", matchIfMissing = true)
@ResAuthorize(ignored = true)
public class AuthServiceImpl
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

    @Autowired(required = false)
    CaptchaService captchaService;

    @Autowired(required = false)
    SmsCodeService smsCodeService;

    @Autowired
    RbacService<Serializable> rbacService;

    @Autowired
    RbacLoadService<Serializable> rbacLoadService;

    @Autowired
    BizUserService bizUserService;


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
        boolean isSA = bizUserService.isSuperAdminAccount(req.getAccount());

        if (!isSA) {
            Assert.hasText(req.getTenantId(), "未知的租户");
        }

        UserInfo user = bizUserService.findUser(req.setPassword(null));

        auditUser(user);

        Assert.hasText(user.getTelephone(), "用户手机号未知");

        Assert.isTrue(PhoneUtil.isMobile(user.getTelephone().trim()), "用户手机号错误");

        String code = smsCodeService.genAndSendSmsCode(req.getTenantId(), req.getAppId(), req.getAccount(), user.getTelephone());

        return code.trim().startsWith("mock:") ? code :
                ("验证码已经发送到" + PhoneUtil.subBefore(user.getTelephone()) + "*" + PhoneUtil.subAfter(user.getTelephone()));

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
        boolean isSA = bizUserService.isSuperAdminAccount(req.getAccount());

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
            Assert.isTrue(smsCodeService.verify(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "短信验证码错误");

        } else if ("captcha".equalsIgnoreCase(req.getVerificationCodeType())) {

            //验证图片验证码
            if (frameworkProperties.isEnableCaptchaVerificationCode()) {
                Assert.notNull(captchaService, "图片验证码服务不存在");
                Assert.hasText(req.getVerificationCode(), "验证码不能为空");
                Assert.isTrue(captchaService.verify(req.getTenantId(), req.getAppId(), req.getAccount(), req.getVerificationCode()), "图片验证码错误");
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
        UserInfo user = bizUserService.findUser(req);

        auditUser(user);

        setCurrentUser(user);

        final LinkedHashMap params = new LinkedHashMap<>();

        Stream.of(extras).filter(Objects::nonNull).forEachOrdered(map -> params.putAll(map));

        BeanUtil.copyProperties(user, params, CopyOptions.create().ignoreNullValue());

        return auth(user.getId(), params);
    }

    /**
     * 直接认证，并返回token
     *
     * @param principal
     * @param params
     * @return 认证成功后的token
     */
    @Override
    public String auth(Serializable principal, Map<String, Object>... params) {

        Assert.notNull(principal, "principal is null");

//        Assert.hasText(principal, "principal is empty");

        //默认登录
        StpUtil.login(principal, getDeviceType(getValue("user-agent", params)));

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
                .filter(Objects::nonNull)
                .filter(map -> map.containsKey(key))
                .map(map -> (T) map.get(key))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isLogin() {

        if (!StpUtil.isLogin()) {
            return false;
        }

        try {
            return getUserInfo() != null;
        } catch (Exception e) {
            return false;
        }
    }


    protected void setCurrentUser(UserInfo userInfo) {
        currentUser.set(userInfo);
    }

    @Override
    public RbacUserInfo<String> getUserInfo() {

        UserInfo userInfo = currentUser.get();

        if (userInfo == null) {
            userInfo = auditUser((UserInfo) rbacLoadService.loadUser(getLoginId())).setPassword(null);
            currentUser.set(userInfo);
        }

        return userInfo;
    }

    @Override
    public String getLoginIdByToken(String token) {

        Assert.hasText(token, "token is empty");

        String principal = (String) StpUtil.getLoginIdByToken(token);

        if (!StringUtils.hasText(principal)) {
            throw new AuthorizationException(NotLoginException.INVALID_TOKEN_MESSAGE);
        }

        return principal;
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
    public Serializable getLoginId() {

        Object principal = StpUtil.getLoginId();

        if (principal == null) {
            throw new NotLoginException(NotLoginException.NOT_TOKEN_MESSAGE, StpUtil.getLoginType(), StpUtil.getLoginDevice());
        }

        return (Serializable) principal;
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
        } else if (ua.contains("Pad;")) {
            return "Pad";
        } else if (ua.contains("Windows ")
                || ua.contains("Macintosh;")
                || ua.contains(" Mac OS X ")) {
            return "PC";
        }

        return "Unknown";
    }

    @Override
    public void clearThreadCacheData() {
        currentUser.set(null);
    }

}
