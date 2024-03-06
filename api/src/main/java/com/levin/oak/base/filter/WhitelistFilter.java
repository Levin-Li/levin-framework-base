package com.levin.oak.base.filter;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.oak.base.biz.BizWhitelistService;
import com.levin.oak.base.services.whitelist.WhitelistService;
import com.levin.oak.base.services.whitelist.info.WhitelistInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 *
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
//@Component(PLUGIN_PREFIX + "WhitelistFilter") //考虑提升性能，只在拦截器中处理
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "WhitelistFilter", matchIfMissing = true)
public class WhitelistFilter extends OncePerRequestFilter {

    @Autowired
    WhitelistService whitelistService;

    @Autowired
    BizWhitelistService bizWhitelistService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        WhitelistInfo whitelistInfo = whitelistService.findById(path);

        if (whitelistInfo == null) {
            filterChain.doFilter(request, response);
            return;
        }

        String clientIp = IPAddrUtils.try2GetUserRealIPAddr(request);

        Assert.isTrue(bizWhitelistService.anyMatch(() -> clientIp, whitelistInfo.getIpList()), "IP地址非法");
        Assert.isTrue(bizWhitelistService.anyMatch(request::getMethod, whitelistInfo.getMethodList()), "非法的请求方法" + request.getMethod());


        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));

        Assert.isTrue(bizWhitelistService.anyMatch(() -> userAgent.getBrowser() != null ? userAgent.getBrowser().getName() : null, whitelistInfo.getBrowserList()), "不支持的浏览器");

        Assert.isTrue(bizWhitelistService.anyMatch(() -> userAgent.getOs() != null ? userAgent.getOs().getName() : null, whitelistInfo.getOsList()), "不支持的系统类型");

        Assert.isTrue(bizWhitelistService.anyMatch(() -> userAgent.getEngine() != null ? userAgent.getEngine().getName() : null, whitelistInfo.getBrowserTypeList()), "不支持的浏览器类型");

        Assert.isTrue(bizWhitelistService.anyMatch(() -> IPAddrUtils.searchIpRegion(clientIp), whitelistInfo.getRegionList()), "不支持的地区");

        filterChain.doFilter(request, response);

    }
}
