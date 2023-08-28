package com.levin.oak.base;

import com.levin.commons.service.support.*;
//import de.codecentric.boot.admin.server.config.EnableAdminServer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.task.TaskExecutorBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.*;
import org.springframework.beans.factory.annotation.*;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.plugin.support.PluginManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import org.apache.dubbo.config.spring.context.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.stream.Stream;

/**
 *  启动类
 *  @author Auto gen by simple-dao-codegen 2023-2-5 11:13:19
 */
@SpringBootApplication
@Slf4j
@EnableScheduling
@EnableCaching
@EnableAsync
@EnableDubboConfig
//@EnableAdminServer
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    Environment environment;

    @Lazy
    @Bean(name = {"applicationTaskExecutor", "taskExecutor"})
    @ConditionalOnMissingBean(name = {"applicationTaskExecutor", "taskExecutor"})
    public ThreadPoolTaskExecutor applicationTaskExecutor(@Autowired TaskExecutorBuilder builder) {
        return builder.build();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CorsFilter corsFilter() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedMethod(CorsConfiguration.ALL);
        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addAllowedOriginPattern(CorsConfiguration.ALL);

        config.setMaxAge(18000L);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OAK框架-基础模块")
                        .version("1.0.0")
                        .description("基础模块")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com")
                        ));
    }

//    @Bean
//    PluginManager pluginManager() {
//        return new PluginManagerImpl() {
//            @Override
//            public void onApplicationEvent(ContextRefreshedEvent event) {
//                log.info("创建自定义的插件管理器-" + getClass().getSimpleName());
//                super.onApplicationEvent(event);
//            }
//        };
//    }

    @Bean
    VariableResolverConfigurer variableResolverConfigurer() {
        return variableResolverManager -> {

            //加入全局变量
            // variableResolverManager.add(
            //         MapUtils.putFirst(ModuleOption.ID+"_x1", "x1_value")
            //                 .put(ModuleOption.ID+"_x2", "x2_value")
            //                 .build());

            //@todo 增加自定义变量解析器
            //加入
            variableResolverManager.add( new VariableResolver() {
                /**
                 * 获取变量
                 * <p>
                 * 方法必须永远返回一个ValueHolder对象
                 *
                 * @param key                变量名
                 * @param originalValue       原值
                 * @param throwExWhenNotFound 当变量无法解析时是否抛出异常
                 * @param isRequireNotNull
                 * @param expectTypes         期望的类型
                 * @return ValueHolder<T>
                 * @throws VariableNotFoundException 如果变量无法获取将抛出异常
                 */
                @Override
                public <T> ValueHolder<T> resolve(String key, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws VariableNotFoundException {

                    if (!key.startsWith("env:")) {
                        return ValueHolder.notValue(key);
                    }

                    key = key.substring(4);

                    return (ValueHolder<T>) new ValueHolder<>()
                            .setValue(environment.getProperty(key))
                            .setHasValue(environment.containsProperty(key));

                }
            });
        };
    }

}
