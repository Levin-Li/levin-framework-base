package com.levin.oak.base.config;

import cn.hutool.core.util.StrUtil;
import com.levin.commons.plugin.PluginManager;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.InjectVarService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacMethodService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.interceptor.*;
import com.levin.oak.base.utils.UrlPathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.*;


/**
 * 配置权限控制，资源拦截等需求
 */
@Configuration(PLUGIN_PREFIX + "ModuleWebMvcConfigurer")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ModuleWebMvcConfigurer", matchIfMissing = true)
public class ModuleWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    RbacService<Serializable> rbacService;

    @Autowired
    AuthService authService;

    @Autowired
    RbacMethodService<Serializable> rbacMethodService;

    @Autowired
    BizTenantService bizTenantService;

    @Autowired
    InjectVarService injectVarService;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    WebProperties webProperties;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    PluginManager pluginManager;

    @Autowired(required = false)
    WebEndpointProperties webEndpointProperties;

    @Autowired(required = false)
    ManagementServerProperties managementServerProperties;

    //
    @Value("${springdoc.swagger-ui.path:}")
    String springdocUiPath;

    @Autowired
    Environment environment;

    @Autowired
    DataSourceProperties dataSourceProperties;

    @PostConstruct
    void init() {

        log.info("应用 ActiveProfiles:" + Arrays.asList(environment.getActiveProfiles()) + " , 工作目录：[" + new File(".").getAbsolutePath() + "]" + " , 数据库URL：[" + dataSourceProperties.getUrl() + "]");

        final String host = Optional.ofNullable(serverProperties.getAddress()).orElse(InetAddress.getLoopbackAddress()).getHostAddress();

        final Integer port = Optional.ofNullable(serverProperties.getPort()).orElse(8080);

        final String contextPath = Optional.ofNullable(serverProperties.getServlet().getContextPath()).orElse("/");

        final String rootUrl = host + ((port == 80) ? "" : (":" + port)) + (contextPath.startsWith("/") ? "" : "/") + contextPath;

        //设置默认排除的路径
        boolean hasSpringDoc = ClassUtils.isPresent("org.springdoc.core.GroupedOpenApi", null);

        boolean hasApiDocPath = StrUtil.isNotBlank(frameworkProperties.getApiDocPath());

        boolean hasSpringDocUiPath = StrUtil.isNotBlank(springdocUiPath);

        frameworkProperties.setDefaultExcludePathPatterns(
                Stream.of("/" + serverProperties.getError().getPath()
                                , "/favicon.ico"
                                , hasSpringDoc && hasApiDocPath ? "/" + frameworkProperties.getApiDocPath() + "/**" : null
                                , hasSpringDoc && hasSpringDocUiPath ? "/" + springdocUiPath + "/**" : null
                                , Optional.ofNullable(managementServerProperties).map(p -> p.getBasePath()).filter(StringUtils::hasText).map(p -> "/" + p + "/**").orElse(null)
                                , Optional.ofNullable(webEndpointProperties).map(p -> p.getBasePath()).filter(StringUtils::hasText).map(p -> "/" + p + "/**").orElse(null)
                                , hasSpringDoc && (hasApiDocPath || hasSpringDocUiPath) ? "/v3/api-docs/**" : null

                        ).filter(StringUtils::hasText)
                        .map(url -> "/" + url)
                        .map(UrlPathUtils::safeUrl)
                        .collect(Collectors.toList())
        );

        if (hasSpringDoc && (hasApiDocPath || hasSpringDocUiPath)) {
            if (hasApiDocPath) {
                log.info("*** Api文档(knife4j)访问路径：{}", "http://" + UrlPathUtils.safeUrl(rootUrl + "/" + frameworkProperties.getApiDocPath()));
            }
            if (hasSpringDocUiPath) {
                log.info("*** Api文档(swagger-ui)访问路径：{}", "http://" + UrlPathUtils.safeUrl(rootUrl + "/" + springdocUiPath));
            }
        }

        log.info("*** 资源拦截器默认排除的路径：{}", frameworkProperties.getDefaultExcludePathPatterns());

        frameworkProperties.getTenantBindDomain().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));

        frameworkProperties.getControllerAcl().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public OncePerRequestFilter clearThreadCacheDataFilterBean() {

        //通过FilterRegistrationBean实例设置优先级可以生效
        //通过@WebFilter无效

        OncePerRequestFilter filter = new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                //log.debug("Web Filter:{}", request.getRequestURL());
                try {
                    clearThreadCacheData();
                    filterChain.doFilter(request, response);
                } finally {
                    clearThreadCacheData();
                }
            }
        };

        return filter;

//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(filter);//注册自定义过滤器
//        bean.setName("clearThreadCacheDataFilter");//过滤器名称
//        bean.addUrlPatterns("/**");//过滤所有路径
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);//优先级，越低越优先
//
//        log.debug("初始化线程变量清除过滤器.");
//
//        return bean;
    }

    /**
     * 配置静态访问资源
     * spring boot 默认的{ "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //{ "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };

        //注意每个资源路径后面的路径加 / !!! 重要的事情说三遍
        //注意每个资源路径后面的路径加 / !!! 重要的事情说三遍
        //注意每个资源路径后面的路径加 / !!! 重要的事情说三遍

        if (StringUtils.hasText(frameworkProperties.getApiDocPath())) {
            registry.addResourceHandler(UrlPathUtils.safeUrl("/" + frameworkProperties.getApiDocPath() + "/**"))
                    .addResourceLocations(webProperties.getResources().getStaticLocations());
        }

    }

    /**
     * 视图配置
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //增加 JSP 支持
//         registry.jsp("/WEB-INF/jsp/", ".jsp");

        if (StringUtils.hasText(frameworkProperties.getAdminPath())) {
            //  registry.freeMarker();
            //  log.info("SpringMVC 视图访问路径：{}", frameworkProperties.getAdminPath());
        }

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // configurer.enable();
        // configurer.enable("defaultServletName");

        // 此时会注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的，它会尝试映射/。当DispatcherServelt映射/时（/ 和/ 是有区别的），并且没有找到合适的Handler来处理请求时，就会交给DefaultServletHttpRequestHandler 来处理。注意：这里的静态资源是放置在web根目录下，而非WEB-INF 下。
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping(API_PATH + "**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*");
    }

    protected void clearThreadCacheData() {
        bizTenantService.clearThreadCacheData();
        authService.clearThreadCacheData();
        injectVarService.clearCache();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //https://sa-token.dev33.cn/
        // 注册路由拦截器，自定义认证规则
//        registry.addInterceptor(new SaRouteInterceptor((req, res, handler)->{
//            // 根据路由划分模块，不同模块不同鉴权
//            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
//            SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
//            SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
//            SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
//            SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));
//        })).addPathPatterns("/**");

        //线程级别用户权限清除，注意必须是所有路径, 也可以用过滤器清除线程缓存变量
//        registry.addInterceptor(new HandlerInterceptor() {
//                    @Override
//                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//                        //清楚缓存数据
//                        clearThreadCacheData();
//                        return true;
//                    }
//
//                    @Override
//                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//                        //清楚缓存数据
//                        clearThreadCacheData();
//                    }
//                })
//                .addPathPatterns("/**")
//                .order(Ordered.HIGHEST_PRECEDENCE);


        if (!frameworkProperties.getUrlAcl().isUseWebFilter()) {
            //白名单
            registry.addInterceptor(accessWhitelistInterceptor())
                    .addPathPatterns("/**")
                    .order(Ordered.HIGHEST_PRECEDENCE);

            log.info("*** 动态基于IP的访问控制拦截器已经启用，" + frameworkProperties.getUrlAcl());
        }

        //api 文档资源特别处理
        if (StringUtils.hasText(frameworkProperties.getApiDocPath())) {
            //允许登录用户访问文档
            registry.addInterceptor(new DevInterceptor(null, request -> authService.isLogin()))
                    .addPathPatterns(UrlPathUtils.safeUrl("/" + frameworkProperties.getApiDocPath() + "/**"))
                    .order(Ordered.HIGHEST_PRECEDENCE);
        }

        //要求租户绑定域名
        if (frameworkProperties.getTenantBindDomain().isEnable()) {

            HandlerInterceptor handlerInterceptor = new DomainInterceptor(
                    (domain) -> bizTenantService.setCurrentTenantByDomain(domain)
                    , (className) -> frameworkProperties.getTenantBindDomain().isPackageMatched(className)
            );

            processDefaultPath(registry.addInterceptor(handlerInterceptor)
                    , frameworkProperties.getTenantBindDomain().getExcludePathPatterns()
                    , frameworkProperties.getTenantBindDomain().getIncludePathPatterns()
            ).order(Ordered.HIGHEST_PRECEDENCE + 1000);

            log.info("*** 租户绑定域名拦截器已经启用，" + frameworkProperties.getTenantBindDomain());
        }

        //检查租户信息，要求所有的访问都必须有租户
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        if ((handler instanceof HandlerMethod)) {
                            bizTenantService.checkCurrentUserTenantInfo();
                        }
                        return true;
                    }
                })
                .excludePathPatterns(frameworkProperties.getDefaultExcludePathPatterns())
                .addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE + 2000);

        //控制访问控制
        if (frameworkProperties.getControllerAcl().isEnable()) {

            HandlerInterceptor handlerInterceptor = new ControllerAuthorizeInterceptor(authService, rbacMethodService
                    , (className) -> frameworkProperties.getControllerAcl().isPackageMatched(className));

            processDefaultPath(registry.addInterceptor(handlerInterceptor)
                    , frameworkProperties.getControllerAcl().getExcludePathPatterns()
                    , frameworkProperties.getControllerAcl().getIncludePathPatterns()
            ).order(Ordered.HIGHEST_PRECEDENCE + 3000);

            log.info("*** 基于权限的访问控制拦截器已经启用，" + frameworkProperties.getControllerAcl());
        }

        //全局资源拦截器
        registry.addInterceptor(resourceAuthorizeInterceptor())
                .excludePathPatterns(frameworkProperties.getDefaultExcludePathPatterns())
                .addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE + 4000);
        log.info("*** 全局资源拦截器已经启用，" + frameworkProperties.getResourcesAcl());


        if (frameworkProperties.isEnableApiDynamicVerificationCode()) {

            processDefaultPath(
                    registry.addInterceptor(dynamicVerificationInterceptor()),
                    null, null
            ).order(Ordered.HIGHEST_PRECEDENCE + 5000);

            log.info("*** 动态验证码拦截器已经启用，" + frameworkProperties.getResourcesAcl());
        }

    }

    /**
     * 资源拦截器
     *
     * @return
     */
    @Bean
    public ResourceAuthorizeInterceptor resourceAuthorizeInterceptor() {
        return new ResourceAuthorizeInterceptor();
    }

    @Bean
    public UrlAclInterceptor accessWhitelistInterceptor() {
        return new UrlAclInterceptor();
    }


    @Bean
    DynamicVerificationInterceptor dynamicVerificationInterceptor() {
        return new DynamicVerificationInterceptor();
    }

    private InterceptorRegistration processDefaultPath(InterceptorRegistration registration, List<String> excludePathPatterns, List<String> includePathPatterns) {

        registration.excludePathPatterns(frameworkProperties.getDefaultExcludePathPatterns());

        if (excludePathPatterns != null) {
            excludePathPatterns.forEach(url -> registration.excludePathPatterns(UrlPathUtils.safeUrl(url)));
        }

        if (includePathPatterns == null || includePathPatterns.isEmpty()) {
            //默认加入所有
            registration.addPathPatterns("/**");
        } else {
            includePathPatterns.forEach(url -> registration.addPathPatterns(UrlPathUtils.safeUrl(url)));
        }

        return registration;
    }


}
