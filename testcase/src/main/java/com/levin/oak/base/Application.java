package com.levin.oak.base;

import com.levin.commons.service.support.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.*;

import com.levin.commons.plugin.PluginManager;
import com.levin.commons.plugin.support.PluginManagerImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *  启动类
 *  @author Auto gen by simple-dao-codegen 2021-10-28 9:46:17
 */
@SpringBootApplication
@Slf4j
@EnableCaching
public class Application {



    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }

    @Autowired
    Environment environment;


    @Bean
    PluginManager pluginManager() {

        return new PluginManagerImpl() {
            @Override
            public void onApplicationEvent(ContextRefreshedEvent event) {
                log.info("创建自定义的插件管理器-" + getClass().getSimpleName());
                super.onApplicationEvent(event);
            }
        };
    }

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

        };
    }

}
