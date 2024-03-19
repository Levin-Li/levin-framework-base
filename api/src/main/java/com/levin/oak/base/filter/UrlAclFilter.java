package com.levin.oak.base.filter;

import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.interceptor.UrlAclInterceptor;
import com.levin.oak.base.services.setting.SettingService;
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
@Component(PLUGIN_PREFIX + "UrlAclFilter") //考虑提升性能，只在拦截器中处理
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AccessWhitelistFilter", matchIfMissing = true)
public class UrlAclFilter extends OncePerRequestFilter {


    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    ServerProperties serverProperties;

    @Autowired(required = false)
    UrlAclInterceptor whitelistInterceptor;

    @Autowired
    AuthService authService;

    @Autowired
    SettingService settingService;

    @PostConstruct
    public void init() {

        if (whitelistInterceptor == null) {
            whitelistInterceptor = new UrlAclInterceptor()
                    .setSettingService(settingService)
                    .setFrameworkProperties(frameworkProperties)
                    .setServerProperties(serverProperties)
                    .setAuthService(authService)
            ;
        }

        if (frameworkProperties.getUrlAcl().isUseWebFilter()) {
            log.info("*** 动态基于IP的访问控制过滤器已经启用，" + frameworkProperties.getUrlAcl());
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

        if (!frameworkProperties.getUrlAcl().isUseWebFilter()
                || whitelistInterceptor.canPass(request, response, null)) {
            filterChain.doFilter(request, response);
        }
    }

}
