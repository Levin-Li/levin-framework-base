package com.levin.oak.base.config;

import com.levin.oak.base.interceptor.AuthorizeAnnotationInterceptor;
import com.levin.oak.base.services.rbac.RbacService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.levin.oak.base.ModuleOption.*;

@Configuration(PLUGIN_PREFIX + "TestModuleWebMvcConfigurer")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "TestModuleWebMvcConfigurer", havingValue = "false", matchIfMissing = true)
public class ModuleWebMvcConfigurer implements WebMvcConfigurer {


    @PostConstruct
    void init() {
        log.info("init...");
    }

    @Resource
    RbacService rbacService;

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


        registry.addInterceptor(new AuthorizeAnnotationInterceptor(rbacService)).addPathPatterns(API_PATH + "**");

    }
}