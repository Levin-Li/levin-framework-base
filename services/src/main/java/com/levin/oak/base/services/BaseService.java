package com.levin.oak.base.services;

import com.levin.commons.dao.SimpleDao;
import com.levin.oak.base.ModuleOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

//import javax.servlet.http.*;

//Auto gen by simple-dao-codegen 2022-3-25 17:01:35


/**
 * 抽象服务类
 *
 * @author lilw
 */
@Slf4j
public abstract class BaseService {

    @Autowired
    protected SimpleDao simpleDao;

    @Autowired
    protected ApplicationContext applicationContext;

    protected Object selfProxy = null;

    public final String getModuleId() {
        return ModuleOption.ID;
    }

    protected <T> T getSelfProxy(Class<T> type) {

        if (selfProxy == null) {
            selfProxy = applicationContext.getBean(type);
        }

        return (T) selfProxy;
    }

}
