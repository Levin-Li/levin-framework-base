package com.levin.oak.base.interceptor;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson2.JSONObject;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.CaptchaService;
import com.levin.oak.base.biz.MultiFactorAuthService;
import com.levin.oak.base.biz.SmsCodeService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.VerifyCodeType;
import com.levin.oak.base.entities.vo.DynamicVerifyCodeAcl;
import com.levin.oak.base.entities.vo.UrlAcl;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.services.setting.req.CreateSettingReq;
import com.levin.oak.base.services.user.info.UserInfo;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.levin.oak.base.interceptor.UrlAclInterceptor.anyMatch;

/**
 * 动态验证码拦截器
 */
@Data
@Slf4j
@Accessors(chain = true)
public class DynamicVerificationInterceptor
        implements HandlerInterceptor {

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    SettingService settingService;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    AuthService authService;

    @Autowired(required = false)
    SmsCodeService smsCodeService;

    @Autowired(required = false)
    CaptchaService captchaService;

    @Autowired(required = false)
    MultiFactorAuthService multiFactorAuthService;

    @PostConstruct
    public void init() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 还有签名验证，证书验证等方式，这里只处理动态验证码

        if (!frameworkProperties.isEnableApiDynamicVerificationCode()
                //如果不是HandlerMethod，直接放行
                || (!(handler instanceof HandlerMethod))
                //未登录不处理
                || !authService.isLogin()
        ) {
            return true;
        }

        String path = UrlAclInterceptor.getRequestPath(request);// request.getRequestURI();

        if (!StringUtils.hasText(path)) {
            return true;
        }

        //登录后，优先使用登录的租户
        UserInfo userInfo = authService.getUserInfo();


        DynamicVerifyCodeAcl verifyCodeAcl = StringUtils.hasText(userInfo.getTenantId()) ? getAndAutoCreate("系统", "@" + userInfo.getTenantId(), userInfo.getTenantId()) : null;

        //
        if (verifyCodeAcl == null) {
            //如果租户没有自定义路径，则加载全局配置
            verifyCodeAcl = getAndAutoCreate("平台", "@", null);
        }

        if (verifyCodeAcl == null
                || verifyCodeAcl.getVerifyCodeType() == null
                || !Boolean.TRUE.equals(verifyCodeAcl.getEnable())) {
            return true;
        }

        //如果路径不匹配，则直接放行
        if (!canMatch(request, verifyCodeAcl)) {
            return true;
        }

        //用户必须是登录的用户
        Assert.notNull(userInfo, "无法获取有效的登录信息");

        String verifyCode = getParam(request, empty2Default(verifyCodeAcl.getVerifyCodeParamName(), "verifyCode"));

        Assert.notBlank(verifyCode, verifyCodeAcl.getVerifyCodeType().getDesc() + "不能为空");

        String appId = getParam(request, empty2Default(verifyCodeAcl.getAppIdParamName(), "appId"));

        if (verifyCodeAcl.getVerifyCodeType() == VerifyCodeType.sms) {

            Assert.notBlank(userInfo.getTelephone(), "用户{}手机号未填写", userInfo.getName());
            Assert.notNull(smsCodeService, "短信验证服务未启用");

            //校验验证码
            Assert.isTrue(smsCodeService.verify(userInfo.getTenantId(), appId, userInfo.getTelephone(), verifyCode));

        } else if (verifyCodeAcl.getVerifyCodeType() == VerifyCodeType.captcha) {

            Assert.notNull(captchaService, "图片验证服务未启用");

            Assert.isTrue(captchaService.verify(userInfo.getTenantId(), appId, userInfo.getId(), verifyCode));

        } else if (verifyCodeAcl.getVerifyCodeType() == VerifyCodeType.mfa) {

            Assert.notNull(multiFactorAuthService, "多因子验证服务未启用");

            Assert.notBlank(userInfo.getMfaSecretKey(), "用户{}多因子验证密钥未启用", userInfo.getName());

            Assert.isTrue(multiFactorAuthService.verify(userInfo.getTenantId(), appId, userInfo.getId(), verifyCode));

        } else {
            throw new IllegalArgumentException("不支持的验证码类型：" + verifyCodeAcl.getVerifyCodeType());
        }

        return true;
    }


    public static boolean canMatch(HttpServletRequest request, UrlAcl acl) {

        final String urlPath = UrlAclInterceptor.getRequestPath(request);

        final String clientIp = IPAddrUtils.try2GetUserRealIPAddr(request);

        //先检查URL是否匹配，如果不匹配则直接返回通过
        if (anyMatch(false, () -> urlPath, acl.getUrlPathExcludeList())
                || anyMatch(false, () -> clientIp, acl.getIpExcludeList())
                //检查URL是否匹配，如果不匹配则直接返回通过
                || !anyMatch(false, () -> urlPath, acl.getUrlPathList())) {
            return false;
        }

        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));

        return
                anyMatch(() -> clientIp, acl.getIpList())
                        && anyMatch(() -> request.getServerName(), acl.getDomainList())
                        && anyMatch(request::getMethod, acl.getMethodList())
                        && anyMatch(() -> userAgent.getBrowser() != null ? userAgent.getBrowser().getName() : null, acl.getBrowserList())
                        && anyMatch(() -> userAgent.getOs() != null ? userAgent.getOs().getName() : null, acl.getOsList())
                        && anyMatch(() -> userAgent.getEngine() != null ? userAgent.getEngine().getName() : null, acl.getBrowserTypeList())
                        && anyMatch(() -> IPAddrUtils.searchIpRegion(clientIp), acl.getRegionList())
                ;
    }


    private DynamicVerifyCodeAcl getAndAutoCreate(String title, String id, String tenantId) {

        id = DynamicVerifyCodeAcl.class.getSimpleName() + id;

        SettingInfo info = settingService.findById(id);

        if (info == null) {

            settingService.create(
                    new CreateSettingReq()
                            .setId(id)
                            //默认不启用
                            .setEnable(false)
                            .setEditable(true)
                            .setName("验证码访问校验-" + title)
                            .setGroupName("URL访问控制")
                            .setCategoryName("系统安全")
                            .setRemark("系统自动生成的配置")
                            .setCode(id)
                            //默认不启用
                            //.setValueContent(JSONObject.toJSONString(new UrlAccessControl().setTitle(title).setEnable(false).setUrlPathExcludeList("*"), JSONWriter.Feature.WriteNullStringAsEmpty))
                            .setEditor("form://" + DynamicVerifyCodeAcl.class.getName())
                            .setValueType(Setting.ValueType.Json)
                            .setTenantId(tenantId)
            );

            return null;
        }

        if (!Boolean.TRUE.equals(info.getEnable())
                || !StringUtils.hasText(info.getValueContent())) {
            return null;
        }

        return JSONObject.parseObject(info.getValueContent(), DynamicVerifyCodeAcl.class);
    }

    private String empty2Default(String value, String defaultValue) {

        if (StringUtils.hasText(value)) {
            return value;
        }

        return defaultValue;
    }

    private String getParam(HttpServletRequest request, String paramName) {

        String value = request.getParameter(paramName);

        if (!StringUtils.hasText(value)) {
            value = request.getHeader(paramName);
        }

        return value;
    }

}
