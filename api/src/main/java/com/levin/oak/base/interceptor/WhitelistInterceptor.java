package com.levin.oak.base.interceptor;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.BizWhitelistService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.services.whitelist.WhitelistService;
import com.levin.oak.base.services.whitelist.info.WhitelistInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 白名单拦截器
 */
@Data
@Slf4j
public class WhitelistInterceptor
        implements HandlerInterceptor {

    @Autowired
    AuthService authService;

    @Autowired
    RbacService rbacService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    BizTenantService bizTenantService;

    @Autowired
    WhitelistService whitelistService;

    @Autowired
    BizWhitelistService bizWhitelistService;

    @PostConstruct
    public void init() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果不是HandlerMethod，直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String path = request.getRequestURI();

        WhitelistInfo whitelistInfo = whitelistService.findById(path);

        if (whitelistInfo == null
                || !Boolean.TRUE.equals(whitelistInfo.getEnable())) {
            return true;
        }

        String clientIp = IPAddrUtils.try2GetUserRealIPAddr(request);

        boolean passed = bizWhitelistService.anyMatch(() -> clientIp, whitelistInfo.getIpList());

        if (!passed) {
            log.warn("非法的IP({})访问：{}，允许的IP列表：{}", clientIp, request.getRequestURL().toString(), whitelistInfo.getIpList());
        }

        Assert.isTrue(passed, "不支持的地址请求");

        passed = bizWhitelistService.anyMatch(() -> request.getServerName(), whitelistInfo.getDomainList());

        if (!passed) {
            log.warn("非法的域名({})访问：{}，允许的域名列表：{}", request.getServerName(), request.getRequestURL().toString(), whitelistInfo.getDomainList());
        }

        Assert.isTrue(passed, "不支持的域名请求");

        Assert.isTrue(bizWhitelistService.anyMatch(request::getMethod, whitelistInfo.getMethodList()), "不支持的请求方法" + request.getMethod());

        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));

        Assert.isTrue(bizWhitelistService.anyMatch(() -> userAgent.getBrowser() != null ? userAgent.getBrowser().getName() : null, whitelistInfo.getBrowserList()), "不支持的浏览器");

        Assert.isTrue(bizWhitelistService.anyMatch(() -> userAgent.getOs() != null ? userAgent.getOs().getName() : null, whitelistInfo.getOsList()), "不支持的系统类型");

        Assert.isTrue(bizWhitelistService.anyMatch(() -> userAgent.getEngine() != null ? userAgent.getEngine().getName() : null, whitelistInfo.getBrowserTypeList()), "不支持的浏览器类型");

        //地区匹配  ("中国|0|", "")
        Assert.isTrue(bizWhitelistService.anyMatch(() -> IPAddrUtils.searchIpRegion(clientIp), whitelistInfo.getRegionList()), "不支持的地区");

        return true;
    }

}
