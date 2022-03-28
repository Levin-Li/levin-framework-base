package com.levin.oak.base.config;

import com.levin.commons.service.support.*;
import com.levin.oak.base.biz.InjectVarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 模块变量解析器配置
 */
@Slf4j
@Configuration(PLUGIN_PREFIX + "ModuleVariableResolverConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleVariableResolverConfigurer", matchIfMissing = true)
public class ModuleVariableResolverConfigurer
        implements VariableResolverConfigurer {

//    @Resource
//    VariableInjector variableInjector;


    @Resource
    InjectVarService injectVarService;

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

        vrm.add(VariableInjector.newSupportSpelAndGroovyResolvers(this::getGlobalContextVars));
    }


    /**
     * 全局的环境变量
     *
     * @return
     */
    protected List<Map<String, ?>> getGlobalContextVars() {

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
    @Bean(PLUGIN_PREFIX + "DefaultModuleVariableResolverList")
    @Order(2)
    List<VariableResolver> defaultModuleVariableResolver() {
        return VariableInjector.newSupportSpelAndGroovyResolvers(this::getModuleContextVars);
    }

    /**
     * 模块级别的环境变量，仅对本模块生效
     *
     * @return vars
     * @see com.levin.oak.base.aspect.ModuleWebControllerAspect#injectVar
     */
    protected List<Map<String, ?>> getModuleContextVars() {

        //每次请求都会获取的变量
        //return Arrays.asList(MapUtils.putFirst(InjectConsts.ORG_ID, "123456789").build());

        //@todo 增加本模块的动态变量

        return injectVarService.getInjectVars();

        //  return Collections.emptyList();
    }
}