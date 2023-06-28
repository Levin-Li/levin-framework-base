package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import javax.annotation.*;
import java.util.*;
import java.util.stream.*;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.dao.*;

import javax.persistence.PersistenceException;
import cn.hutool.core.lang.*;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AppErrorLog;

import com.levin.oak.base.services.apperrorlog.*;
import com.levin.oak.base.services.apperrorlog.req.*;
import com.levin.oak.base.services.apperrorlog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  应用错误日志-业务服务实现类
 *
 * @author auto gen by simple-dao-codegen 2023年6月28日 下午4:18:31
 * 代码生成哈希校验码：[efed3c463691e30e48e7e5d0ad8fb26b]
 */

@DubboService
//@Service(PLUGIN_PREFIX + "BizAppErrorLogServiceImpl")

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizAppErrorLogServiceImpl", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_AppErrorLog.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_AppErrorLog.SIMPLE_CLASS_NAME})
public class BizAppErrorLogServiceImpl extends BaseService implements BizAppErrorLogService {

    @Autowired
    AppErrorLogService appErrorLogService;

    protected BizAppErrorLogServiceImpl getSelfProxy(){
        return getSelfProxy(BizAppErrorLogServiceImpl.class);
    }

    //示例方法
    //@Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    //@Override
    //@CacheEvict(condition = "#req.id != null", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#req.id")
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    //public boolean update(UpdateAppErrorLogReq req) {
    //    Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
    //    return simpleDao.singleUpdateByQueryObj(req);
    //}

}
