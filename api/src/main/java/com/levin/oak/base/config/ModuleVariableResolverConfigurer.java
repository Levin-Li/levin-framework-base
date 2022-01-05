package com.levin.oak.base.config;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.oak.base.*;
import com.levin.commons.service.support.*;
import com.levin.commons.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.*;
import java.util.*;


/**
 * 模块变量解析器配置
 */
@Configuration(PLUGIN_PREFIX + "ModuleVariableResolverConfigurer")
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ModuleVariableResolverConfigurer", havingValue = "false", matchIfMissing = true)
public class ModuleVariableResolverConfigurer
        implements VariableResolverConfigurer {

    @PostConstruct
    void init() {
        log.info("init...");
    }

    /**
     * 配置全局的变量
     *
     * @param vrm
     */
    @Override
    public void config(VariableResolverManager vrm) {

        //@todo
        //加入全局的变量解析器，比如用户数据，环境数据等

        //静态变量
        // @todo 加入全局的静态变量
        //vrm.add(MapUtils.putFirst("静态变量", "静态变量").build());

        //全局动态变量，每次请求都会执行
        vrm.add(new VariableResolver.MapVariableResolver(this::getGlobalContextVars));
    }


    /**
     * 全局的环境变量
     *
     * @return
     */
    protected List<Map<String, Object>> getGlobalContextVars() {

        //每次请求都会获取的变量

        //@todo 增加全局的动态变量

        //return Arrays.asList(MapUtils.putFirst(InjectConsts.ORG_ID, "123456789").build());

        return Collections.emptyList();
    }


    /**
     * 模块级别 http 请求变量解析器
     *
     * @return HttpRequestInfoResolver
     */
    @Bean(PLUGIN_PREFIX + "HttpRequestInfoResolver")
    @Order(1)
    HttpRequestInfoResolver moduleHttpRequestInfoResolver() {
        return new HttpRequestInfoResolver() {
        };
    }

    /**
     * 模块级别请求变量解析器
     *
     * @return VariableResolver
     */
    @Bean(PLUGIN_PREFIX + "DefaultModuleVariableResolver")
    @Order(2)
    VariableResolver defaultModuleVariableResolver() {
        return new VariableResolver.MapVariableResolver(this::getModuleContextVars);
    }

    /**
     * 模块级别的环境变量，仅对本模块生效
     *
     * @return vars
     * @see com.levin.oak.base.aspect.ModuleWebControllerAspect#injectVar
     */
    protected List<Map<String, Object>> getModuleContextVars() {

        //每次请求都会获取的变量
        //return Arrays.asList(MapUtils.putFirst(InjectConsts.ORG_ID, "123456789").build());

        //@todo 增加本模块的动态变量

        return Collections.emptyList();
    }
}