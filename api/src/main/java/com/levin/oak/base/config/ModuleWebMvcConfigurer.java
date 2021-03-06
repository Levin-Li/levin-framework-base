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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;

@Configuration(PLUGIN_PREFIX + "ModuleWebMvcConfigurer")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ModuleWebMvcConfigurer", matchIfMissing = true)
public class ModuleWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    RbacService rbacService;

    @Resource
    AuthService authService;

    @Resource
    BizTenantService bizTenantService;

    @Resource
    InjectVarService injectVarService;

    @Resource
    ServerProperties serverProperties;

    @Resource
    FrameworkProperties frameworkProperties;

    @Resource
    PluginManager pluginManager;

    @Value("${springfox.documentation.swagger-ui.base-url:/swagger-ui}")
    private String swaggerUiBaseUrl;

    @Value("${springfox.documentation.open-api.v3.path:/v3/api-docs}")
    private String openApiPath;


    @PostConstruct
    void init() {

        log.info("init...");

        frameworkProperties.getTenantBindDomain().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));

        frameworkProperties.getControllerAcl().friendlyTip(log.isInfoEnabled(), (info) -> log.info(info));

    }

    /**
     * ????????????????????????
     * spring boot ?????????{ "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //{ "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };

        //?????????????????????????????????????????? / !!! ????????????????????????
        //?????????????????????????????????????????? / !!! ????????????????????????
        //?????????????????????????????????????????? / !!! ????????????????????????

        //registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        if (StringUtils.hasText(frameworkProperties.getAdminPath())) {
            registry.addResourceHandler((frameworkProperties.getAdminPath() + "/**").replace("//", "/"))
                    .addResourceLocations("classpath:/templates" + ADMIN_UI_PATH);
        }

    }

    /**
     * ????????????
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //?????? JSP ??????
//         registry.jsp("/WEB-INF/jsp/", ".jsp");

        if (StringUtils.hasText(frameworkProperties.getAdminPath())) {
            //  registry.freeMarker();
        }

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // configurer.enable();
        // configurer.enable("defaultServletName");

        // ??????????????????????????????Handler???DefaultServletHttpRequestHandler?????????Handler??????????????????????????????????????????????????????/??????DispatcherServelt??????/??????/ ???/ ????????????????????????????????????????????????Handler?????????????????????????????????DefaultServletHttpRequestHandler ??????????????????????????????????????????????????????web?????????????????????WEB-INF ??????
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping(API_PATH + "**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //https://sa-token.dev33.cn/
        // ?????????????????????????????????????????????
//        registry.addInterceptor(new SaRouteInterceptor((req, res, handler)->{
//            // ???????????????????????????????????????????????????
//            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//            SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
//            SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
//            SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
//            SaRouter.match("/notice/**", r -> StpUtil.checkPermission("notice"));
//            SaRouter.match("/comment/**", r -> StpUtil.checkPermission("comment"));
//        })).addPathPatterns("/**");

        //??????????????????????????????
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                //??????????????????
                authService.clearThreadCacheData();
                injectVarService.clearCache();
                return true;
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                //??????????????????
                authService.clearThreadCacheData();
                injectVarService.clearCache();
            }
        }).addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE);

        if (frameworkProperties.getTenantBindDomain().isEnable()) {

            HandlerInterceptor handlerInterceptor = new DomainInterceptor((domain) -> bizTenantService.setCurrentTenantByDomain(domain)
                    , (className) -> frameworkProperties.getTenantBindDomain().isPackageMatched(className)) {
                @Override
                public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                    super.afterCompletion(request, response, handler, ex);
                }
            };

            List<String> includePathPatterns = frameworkProperties.getTenantBindDomain().getIncludePathPatterns();

            registry.addInterceptor(handlerInterceptor)
                    .excludePathPatterns(serverProperties.getError().getPath())
                    .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**")
                    .excludePathPatterns("/" + swaggerUiBaseUrl + "/**")
                    .excludePathPatterns("/" + openApiPath)
                    .excludePathPatterns(frameworkProperties.getTenantBindDomain().getExcludePathPatterns())
                    .addPathPatterns(includePathPatterns.isEmpty() ? Arrays.asList("/**") : includePathPatterns)
                    .order(Ordered.HIGHEST_PRECEDENCE + 1000);
        }

        {
            //??????????????????
            registry.addInterceptor(new HandlerInterceptor() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                    if ((handler instanceof HandlerMethod)) {
                        bizTenantService.checkAndGetCurrentUserTenant();
                    }

                    return true;
                }
            }).addPathPatterns("/**")
                    .order(Ordered.HIGHEST_PRECEDENCE + 2000);
        }

        if (frameworkProperties.getControllerAcl().isEnable()) {

            HandlerInterceptor handlerInterceptor = new ControllerAuthorizeInterceptor(rbacService
                    , (className) -> frameworkProperties.getControllerAcl().isPackageMatched(className));

            List<String> includePathPatterns = frameworkProperties.getControllerAcl().getIncludePathPatterns();

            registry.addInterceptor(handlerInterceptor)
                    .excludePathPatterns(serverProperties.getError().getPath())
                    .excludePathPatterns("/swagger-resources/**", "/swagger-ui/**")
                    .excludePathPatterns("/" + swaggerUiBaseUrl + "/**")
                    .excludePathPatterns("/" + openApiPath)
                    .excludePathPatterns(frameworkProperties.getControllerAcl().getExcludePathPatterns())
                    .addPathPatterns(includePathPatterns.isEmpty() ? Arrays.asList("/**") : includePathPatterns)
                    .order(Ordered.HIGHEST_PRECEDENCE + 3000);
        }

        if (StringUtils.hasText(frameworkProperties.getAdminPath())) {
            //???????????????
            registry.addInterceptor(resourceAuthorizeInterceptor())
                    .addPathPatterns((frameworkProperties.getAdminPath() + "/**").replace("//", "/"))
                    .order(Ordered.HIGHEST_PRECEDENCE + 4000);
        }

    }

    /**
     * ???????????????
     *
     * @return
     */
    @Bean
    public ResourceAuthorizeInterceptor resourceAuthorizeInterceptor() {
        return new ResourceAuthorizeInterceptor();
    }
}