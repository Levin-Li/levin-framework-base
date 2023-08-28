package com.levin.oak.base.config;

import com.levin.commons.plugin.PluginManager;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.InjectVarService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.interceptor.ControllerAuthorizeInterceptor;
import com.levin.oak.base.interceptor.DomainInterceptor;
import com.levin.oak.base.interceptor.ResourceAuthorizeInterceptor;
import com.levin.oak.base.utils.UrlPathUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementServerProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    RbacService rbacService;

    @Autowired
    AuthService authService;

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

    @PostConstruct
    void init() {

        log.info("init...");

        //设置默认排除的路径
        frameworkProperties.setDefaultExcludePathPatterns(
                Stream.of(serverProperties.getError().getPath()
                                , frameworkProperties.getApiDocPath() + "/**"
                                , Optional.ofNullable(managementServerProperties).map(p -> p.getBasePath() + "/**").orElse(null)
                                , Optional.ofNullable(webEndpointProperties).map(p -> p.getBasePath() + "/**").orElse(null)
                                , ClassUtils.isPresent("org.springdoc.core.GroupedOpenApi", null) ? "/v3/api-docs/**" : null

                        ).filter(StringUtils::hasText)
                        .map(UrlPathUtils::safeUrl)
                        .collect(Collectors.toList())
        );

        frameworkProperties.getTenantBindDomain().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));

        frameworkProperties.getControllerAcl().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));

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
            log.info("SpringMVC 视图访问路径：{}", frameworkProperties.getAdminPath());
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

        //线程级别用户权限清除
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        //清楚缓存数据
                        bizTenantService.clearThreadCacheData();
                        authService.clearThreadCacheData();
                        injectVarService.clearCache();
                        return true;
                    }

                    @Override
                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                        //清楚缓存数据
                        bizTenantService.clearThreadCacheData();
                        authService.clearThreadCacheData();
                        injectVarService.clearCache();
                    }
                })
                .excludePathPatterns(frameworkProperties.getDefaultExcludePathPatterns())
                .addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE);

        //要求租户绑定域名
        if (frameworkProperties.getTenantBindDomain().isEnable()) {
            HandlerInterceptor handlerInterceptor = new DomainInterceptor((domain) -> bizTenantService.setCurrentTenantByDomain(domain)
                    , (className) -> frameworkProperties.getTenantBindDomain().isPackageMatched(className)) {
                @Override
                public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                    super.afterCompletion(request, response, handler, ex);
                }
            };

            processDefaultPath(registry.addInterceptor(handlerInterceptor)
                    , frameworkProperties.getTenantBindDomain().getExcludePathPatterns()
                    , frameworkProperties.getTenantBindDomain().getIncludePathPatterns()
            ).order(Ordered.HIGHEST_PRECEDENCE + 1000);
        }

        //检查租户信息，要求所有的访问都必须有租户
        registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        if ((handler instanceof HandlerMethod)) {
                            bizTenantService.checkAndGetCurrentUserTenant();
                        }
                        return true;
                    }
                })
                .excludePathPatterns(frameworkProperties.getDefaultExcludePathPatterns())
                .addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE + 2000);

        //控制访问控制
        if (frameworkProperties.getControllerAcl().isEnable()) {

            HandlerInterceptor handlerInterceptor = new ControllerAuthorizeInterceptor(authService
                    , (className) -> frameworkProperties.getControllerAcl().isPackageMatched(className));

            processDefaultPath(registry.addInterceptor(handlerInterceptor)
                    , frameworkProperties.getControllerAcl().getExcludePathPatterns()
                    , frameworkProperties.getControllerAcl().getIncludePathPatterns()
            ).order(Ordered.HIGHEST_PRECEDENCE + 3000);

        }

        log.info("*** 全局资源拦截器已经启用，" + frameworkProperties.getResourcesAcl());

        //全局资源拦截器
        registry.addInterceptor(resourceAuthorizeInterceptor())
                .excludePathPatterns(frameworkProperties.getDefaultExcludePathPatterns())
                .addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE + 4000);

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
