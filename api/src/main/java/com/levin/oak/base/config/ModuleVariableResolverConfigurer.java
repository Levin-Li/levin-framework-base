package com.levin.oak.base.config;

import com.levin.commons.service.support.*;
import com.levin.oak.base.biz.BizOrgService;
import com.levin.oak.base.biz.rbac.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cn.dev33.satoken.stp.StpUtil.isLogin;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


/**
 * 模块变量解析器配置
 */
@Slf4j
@Configuration(PLUGIN_PREFIX + "ModuleVariableResolverConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleVariableResolverConfigurer", matchIfMissing = true)
public class ModuleVariableResolverConfigurer
        implements VariableResolverConfigurer {

    @Autowired
    VariableInjector variableInjector;

    @Autowired
    AuthService authService;

    @Autowired
    BizOrgService bizOrgService;

    @Autowired
    Environment environment;

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

        //全局动态变量，每次请求都会执行
        vrm.add(VariableInjector.newResolverByMap(this::getGlobalContextVars));

        vrm.add(
                new VariableResolver() {
                    @Override
                    public boolean isSupported(String name) {
                        return name.toLowerCase().startsWith("env:");
                    }

                    @Override
                    public <T> ValueHolder<T> resolve(String key, T originalValue, boolean throwExWhenNotFound, boolean isRequireNotNull, Type... expectTypes) throws VariableNotFoundException {

                        if (!isSupported(key)) {
                            return ValueHolder.notValue(key);
                        }

                        key = key.substring(4);

                        return (ValueHolder<T>) new ValueHolder<>()
                                .setValue(environment.getProperty(key))
                                .setName(key)
                                .setHasValue(environment.containsProperty(key));
                    }
                }
        );
    }

    /**
     * 全局的环境变量
     *
     * @return
     */
    protected List<Map<String, ?>> getGlobalContextVars() {

        //每次请求都会获取的变量
        //@todo 增加全局的动态变量
        return Collections.emptyList();
    }

    @Bean(PLUGIN_PREFIX + "DefaultHttpRequestInfoResolver")
    @Order(100)
    @ConditionalOnMissingBean(HttpRequestInfoResolver.class)
    HttpRequestInfoResolver httpRequestInfoResolver() {
        return new HttpRequestInfoResolver();
    }

    /**
     * 模块级别请求变量解析器
     * 模块前缀 PLUGIN_PREFIX 会被识别为模块级别的变量解析器
     *
     * @return VariableResolver
     */
    @Bean(PLUGIN_PREFIX + "DefaultModuleVariableResolver")
    @Order(300)
    VariableResolver defaultModuleVariableResolver() {
        return VariableInjector.newResolverByMap(this::getModuleContextVars);
    }

    /**
     * 模块级别的环境变量，仅对本模块生效
     */
    protected List<Map<String, ?>> getModuleContextVars() {

        //每次请求都会获取的变量
        //@todo 增加本模块的动态变量

        return Collections.emptyList();
    }

}
