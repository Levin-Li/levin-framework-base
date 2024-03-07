package com.levin.oak.base.interceptor;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizWhitelistService;
import com.levin.oak.base.services.whitelist.WhitelistService;
import com.levin.oak.base.services.whitelist.info.WhitelistInfo;
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
 * 白名单拦截器
 */
@Data
@Slf4j
@Accessors(chain = true)
public class WhitelistInterceptor
        implements HandlerInterceptor {

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    WhitelistService whitelistService;

    @Autowired
    BizWhitelistService bizWhitelistService;

    @Autowired
    ServerProperties serverProperties;

    @PostConstruct
    public void init() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //如果使用过滤器模式，则直接返回
        return frameworkProperties.getWhitelist().isUseWebFilter() || canPass(request, response, handler);
    }

    public boolean canPass(HttpServletRequest request, HttpServletResponse response, Object handler) {

        FrameworkProperties.WhitelistCfg whitelistCfg = frameworkProperties.getWhitelist();

        if (!whitelistCfg.isEnable()
                //如果不是HandlerMethod，直接放行
                || (whitelistCfg.isOnlyControllerMethod() && handler != null && !(handler instanceof HandlerMethod))) {
            return true;
        }

        String path = getRequestPath(request);// request.getRequestURI();

        if (!StringUtils.hasText(path)
                || !whitelistCfg.isPathMatched(path)) {
            return true;
        }

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


    private String getRequestPath(HttpServletRequest request) {

        String contextPath = StringUtils.trimWhitespace(serverProperties.getServlet().getContextPath());

        if (StringUtils.hasText(contextPath)) {
            contextPath = contextPath.replace("//", "/");
            while (contextPath.trim().endsWith("/")) {
                contextPath = contextPath.trim();
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
        }

        String path = request.getRequestURI().replace("//", "/");

        //去除应用路径
        if (StringUtils.hasText(contextPath)
                && path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());
        }

        return path;
    }


}
