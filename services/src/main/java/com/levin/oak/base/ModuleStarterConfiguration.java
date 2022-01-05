package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.commons.dao.repository.RepositoryFactoryBean;
import com.levin.commons.dao.repository.annotation.EntityRepository;
import com.levin.commons.service.proxy.ProxyBeanScan;

import com.levin.commons.service.support.*;
import com.levin.commons.utils.*;
import org.springframework.context.annotation.*;
import org.springframework.core.env.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;


//Auto gen by simple-dao-codegen 2022-1-5 13:46:42

@Configuration(PLUGIN_PREFIX + "ModuleStarterConfiguration")
@Slf4j

//spring data scan，jpa querydsl entity class ...
@EntityScan({PACKAGE_NAME})

@ComponentScan({PACKAGE_NAME})

@ProxyBeanScan(basePackages = {PACKAGE_NAME} , scanType = EntityRepository.class , factoryBeanClass = RepositoryFactoryBean.class)
public class ModuleStarterConfiguration {

    @Autowired
    Environment environment;


}
