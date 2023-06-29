package com.levin.oak.base.services;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.*;
import javax.validation.*;
import java.util.*;
import javax.annotation.*;

//import javax.servlet.http.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import javax.validation.constraints.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import io.swagger.v3.oas.annotations.*;

import com.levin.oak.base.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 * 抽象服务类
 *
 * @author lilw
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:10, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[5dd765e029bcb2e88edf53feb780dbf7], 请不要修改和删除此行内容。
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

    /**
     *
     * @param type
     * @param <T>
     * @return
     */
    protected <T> T getSelfProxy(Class<T> type) {

        if (selfProxy == null) {
            selfProxy = applicationContext.getBean(type);
        }

        return (T) selfProxy;
    }

}
