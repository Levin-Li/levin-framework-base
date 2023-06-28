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
import com.levin.oak.base.entities.SimpleApi;

import com.levin.oak.base.services.simpleapi.*;
import com.levin.oak.base.services.simpleapi.req.*;
import com.levin.oak.base.services.simpleapi.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.SimpleApi.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
////////////////////////////////////

/**
 *  简单动态接口-业务服务实现类
 *
 * @author auto gen by simple-dao-codegen 2023年6月28日 下午4:18:30
 * 代码生成哈希校验码：[75f71c99d229a2b907eb77af2f4171d2]
 */

@DubboService
//@Service(PLUGIN_PREFIX + "BizSimpleApiServiceImpl")

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizSimpleApiServiceImpl", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_SimpleApi.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_SimpleApi.SIMPLE_CLASS_NAME})
public class BizSimpleApiServiceImpl extends BaseService implements BizSimpleApiService {

    @Autowired
    SimpleApiService simpleApiService;

    protected BizSimpleApiServiceImpl getSelfProxy(){
        return getSelfProxy(BizSimpleApiServiceImpl.class);
    }

    //示例方法
    //@Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    //@Override
    //@CacheEvict(condition = "#req.id != null", key = E_SimpleApi.CACHE_KEY_PREFIX + "#req.id")
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    //public boolean update(UpdateSimpleApiReq req) {
    //    Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
    //    return simpleDao.singleUpdateByQueryObj(req);
    //}

}
