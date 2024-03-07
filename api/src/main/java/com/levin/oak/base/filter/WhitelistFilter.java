package com.levin.oak.base.filter;

import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizWhitelistService;
import com.levin.oak.base.interceptor.WhitelistInterceptor;
import com.levin.oak.base.services.whitelist.WhitelistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
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
@Component(PLUGIN_PREFIX + "WhitelistFilter") //考虑提升性能，只在拦截器中处理
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "WhitelistFilter", matchIfMissing = true)
public class WhitelistFilter extends OncePerRequestFilter {

    @Autowired
    WhitelistService whitelistService;

    @Autowired
    BizWhitelistService bizWhitelistService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    ServerProperties serverProperties;

    @Autowired(required = false)
    WhitelistInterceptor whitelistInterceptor;

    @PostConstruct
    public void init() {

        if (whitelistInterceptor == null) {
            whitelistInterceptor = new WhitelistInterceptor()
                    .setWhitelistService(whitelistService)
                    .setBizWhitelistService(bizWhitelistService)
                    .setFrameworkProperties(frameworkProperties)
                    .setServerProperties(serverProperties)
            ;
        }
    }

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

        if (!frameworkProperties.getWhitelist().isUseWebFilter()
                || whitelistInterceptor.canPass(request, response, null)) {
            filterChain.doFilter(request, response);
        }

    }


}
