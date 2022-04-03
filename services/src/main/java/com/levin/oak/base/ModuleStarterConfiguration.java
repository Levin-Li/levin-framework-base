package com.levin.oak.base;

import com.levin.commons.dao.repository.RepositoryFactoryBean;
import com.levin.commons.dao.repository.annotation.EntityRepository;
import com.levin.commons.service.proxy.ProxyBeanScan;
import com.levin.oak.base.autoconfigure.FrameworkBaseProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static com.levin.oak.base.ModuleOption.PACKAGE_NAME;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


//Auto gen by simple-dao-codegen 2022-1-25 23:59:26

@Configuration(PLUGIN_PREFIX + "ModuleStarterConfiguration")
@Slf4j

//spring data scan，jpa querydsl entity class ...
@EntityScan({PACKAGE_NAME})

@ComponentScan({PACKAGE_NAME})

@ProxyBeanScan(basePackages = {PACKAGE_NAME}, scanType = EntityRepository.class, factoryBeanClass = RepositoryFactoryBean.class)

@EnableConfigurationProperties({FrameworkBaseProperties.class})

public class ModuleStarterConfiguration {

    @Resource
    Environment environment;

    @Resource
    TaskSchedulingProperties taskSchedulingProperties;

    @Bean
    @ConditionalOnMissingBean
    ScheduledExecutorService scheduledExecutorService() {

        return new ScheduledThreadPoolExecutor(taskSchedulingProperties.getPool().getSize());
    }

}
