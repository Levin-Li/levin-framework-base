package com.levin.oak.base.interceptor;

import cn.hutool.core.lang.Assert;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.CaptchaService;
import com.levin.oak.base.biz.MultiFactorAuthService;
import com.levin.oak.base.biz.SmsCodeService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.entities.VerifyCodeType;
import com.levin.oak.base.services.dynamicverifycodecfg.DynamicVerifyCodeCfgService;
import com.levin.oak.base.services.dynamicverifycodecfg.info.DynamicVerifyCodeCfgInfo;
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
    DynamicVerifyCodeCfgService cfgService;


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

        if (!frameworkProperties.isEnableApiDynamicVerificationCode()
                //如果不是HandlerMethod，直接放行
                || (!(handler instanceof HandlerMethod))
                //未登录不处理
                || !authService.isLogin()
        ) {
            return true;
        }

        String path = AccessWhitelistInterceptor.getRequestPath(request, serverProperties.getServlet().getContextPath());// request.getRequestURI();

        if (!StringUtils.hasText(path)) {
            return true;
        }

        //登录后，优先使用登录的租户
        UserInfo userInfo = authService.getUserInfo();

        DynamicVerifyCodeCfgInfo whitelistInfo = authService.isLogin() ? cfgService.findById(userInfo.getTenantId() + "@" + path) : null;

        //
        if (whitelistInfo == null) {
            //如果租户没有自定义路径，则加载全局配置
            whitelistInfo = cfgService.findById(path);
        }

        if (whitelistInfo == null
                || whitelistInfo.getVerifyCodeType() == null
                || !Boolean.TRUE.equals(whitelistInfo.getEnable())) {
            return true;
        }

        String clientIp = IPAddrUtils.try2GetUserRealIPAddr(request);

        //不匹配，则表示不需要动态验证
        if (
            //如果是白名单
                !AccessWhitelistInterceptor.noneMatch(() -> clientIp, whitelistInfo.getIpExcludeList())
                        || !AccessWhitelistInterceptor.anyMatch(() -> clientIp, whitelistInfo.getIpList())
                        || !AccessWhitelistInterceptor.anyMatch(() -> request.getServerName(), whitelistInfo.getDomainList())
                        || !AccessWhitelistInterceptor.anyMatch(() -> IPAddrUtils.searchIpRegion(clientIp), whitelistInfo.getRegionList())
        ) {
            return true;
        }

        //用户必须是登录的用户
        Assert.notNull(userInfo, "无法获取有效的登录信息");

        String verifyCode = getParam(request, empty2Default(whitelistInfo.getVerifyCodeParamName(), "verifyCode"));

        Assert.notBlank(verifyCode, whitelistInfo.getVerifyCodeType().getDesc() + "不能为空");

        String appId = getParam(request, empty2Default(whitelistInfo.getAppIdParamName(), "appId"));

        if (whitelistInfo.getVerifyCodeType() == VerifyCodeType.sms) {

            Assert.notBlank(userInfo.getTelephone(), "用户{}手机号未填写", userInfo.getName());
            Assert.notNull(smsCodeService, "短信验证服务未启用");

            //校验验证码
            Assert.isTrue(smsCodeService.verify(userInfo.getTenantId(), appId, userInfo.getTelephone(), verifyCode));

        } else if (whitelistInfo.getVerifyCodeType() == VerifyCodeType.captcha) {

            Assert.notNull(captchaService, "图片验证服务未启用");

            Assert.isTrue(captchaService.verify(userInfo.getTenantId(), appId, userInfo.getId(), verifyCode));

        } else if (whitelistInfo.getVerifyCodeType() == VerifyCodeType.mfa) {

            Assert.notNull(multiFactorAuthService, "多因子验证服务未启用");

            Assert.notBlank(userInfo.getMfaSecretKey(), "用户{}多因子验证密钥未启用", userInfo.getName());

            Assert.isTrue(multiFactorAuthService.verify(userInfo.getTenantId(), appId, userInfo.getId(), verifyCode));

        } else {
            throw new IllegalArgumentException("不支持的验证码类型：" + whitelistInfo.getVerifyCodeType());
        }

        return true;
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
