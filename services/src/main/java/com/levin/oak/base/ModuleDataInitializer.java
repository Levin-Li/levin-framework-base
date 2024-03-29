package com.levin.oak.base;

import com.levin.commons.dao.SimpleDao;
import com.levin.oak.base.biz.rbac.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Slf4j
@Component(PLUGIN_PREFIX + "ModuleDataInitializer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleDataInitializer", matchIfMissing = true)
public class ModuleDataInitializer implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    SimpleDao dao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AuthService authService;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("[ {} ] on applicationContext ...", ModuleOption.ID);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (event.getApplicationContext() == applicationContext) {
            initData();
        }
    }

    void initData() {

        log.info("[ {} ] on init ...", ModuleOption.ID);

        Random random = new Random(this.hashCode());

        //@todo 初始化数据

        authService.initData();

        log.info("***** {} 数据初始化完成 ******", ModuleOption.ID);
    }

}
