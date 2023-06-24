package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.commons.dao.repository.RepositoryFactoryBean;
import com.levin.commons.dao.repository.annotation.EntityRepository;
import com.levin.commons.service.proxy.ProxyBeanScan;

import com.levin.commons.service.support.*;
import com.levin.commons.utils.*;

import javax.annotation.*;

import com.levin.oak.base.autoconfigure.FrameworkProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.apache.dubbo.config.spring.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

//Auto gen by simple-dao-codegen 2023年6月24日 下午12:28:38

/**
 * 模块自举配置
 * <p>
 * 模块需要自举加载的内容都需要配置在该类中
 */
@Configuration(PLUGIN_PREFIX + "ModuleStarterConfiguration")
@Slf4j

// Spring data jpa scan，jpa querydsl entity class ...
@EntityScan({PACKAGE_NAME})

// Spring 扫描
@ComponentScan({PACKAGE_NAME})

// 自定义注解接口 扫描
@ProxyBeanScan(basePackages = {PACKAGE_NAME}, scanType = EntityRepository.class, factoryBeanClass = RepositoryFactoryBean.class)

// FeignClients 扫描
@EnableFeignClients({PACKAGE_NAME})

// Dubbo 扫描
@DubboComponentScan(basePackages = {PACKAGE_NAME})

@EnableConfigurationProperties({FrameworkProperties.class})

public class ModuleStarterConfiguration {

    @Autowired
    Environment environment;

    @Autowired
    TaskSchedulingProperties taskSchedulingProperties;

    @Bean
    @ConditionalOnMissingBean
    ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(taskSchedulingProperties.getPool().getSize());
    }
}
