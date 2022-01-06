package com.levin.oak.base.config;

import com.levin.oak.base.interceptor.AuthorizeAnnotationInterceptor;
import com.levin.oak.base.services.rbac.AuthService;
import com.levin.oak.base.services.rbac.AuthServiceImpl;
import com.levin.oak.base.services.rbac.RbacService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.levin.oak.base.ModuleOption.*;

@Configuration(PLUGIN_PREFIX + "ModuleWebMvcConfigurer")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ModuleWebMvcConfigurer", havingValue = "false", matchIfMissing = true)
public class ModuleWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    RbacService rbacService;

    @Resource
    AuthService authService;

    @Value("${" + PLUGIN_PREFIX + ".enableAuthorizeInterceptor:}")
    Boolean enableAuthorizeInterceptor = null;

    @PostConstruct
    void init() {
        log.info("init...");
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

        registry.addResourceHandler(ADMIN_UI_PATH + "**")
                .addResourceLocations("classpath:public" + ADMIN_UI_PATH);

        registry.addResourceHandler(H5_UI_PATH + "**")
                .addResourceLocations("classpath:public" + H5_UI_PATH);
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


        //如果没有强制禁用并且默认的认证服务没有被替换，或是强制启用
        if ((enableAuthorizeInterceptor == null && authService instanceof AuthServiceImpl)
                || Boolean.TRUE.equals(enableAuthorizeInterceptor)) {

            registry.addInterceptor(new AuthorizeAnnotationInterceptor(rbacService)).addPathPatterns(API_PATH + "**");

            log.info("模块认证拦截器[ {} ]已经启用 ，可以配置[{}.enableAuthorizeInterceptor]禁用", API_PATH, PLUGIN_PREFIX);

        } else {

            log.info("模块认证拦截器[ {} ]已经禁用 ，可以配置[{}.enableAuthorizeInterceptor]启用", API_PATH, PLUGIN_PREFIX);
        }

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // configurer.enable();
        // configurer.enable("defaultServletName");

        // 此时会注册一个默认的Handler：DefaultServletHttpRequestHandler，这个Handler也是用来处理静态文件的，它会尝试映射/。当DispatcherServelt映射/时（/ 和/ 是有区别的），并且没有找到合适的Handler来处理请求时，就会交给DefaultServletHttpRequestHandler 来处理。注意：这里的静态资源是放置在web根目录下，而非WEB-INF 下。
    }


    /**
     * 视图配置
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        //增加 JSP 支持
        // registry.jsp("/WEB-INF/jsp/", ".jsp");
    }

}