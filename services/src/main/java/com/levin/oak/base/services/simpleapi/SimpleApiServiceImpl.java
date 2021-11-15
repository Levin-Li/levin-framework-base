package com.levin.oak.base.services.simpleapi;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimpleApi;

import com.levin.oak.base.services.simpleapi.req.*;
import com.levin.oak.base.services.simpleapi.info.*;


////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.SimpleApi.*;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  动态API-服务实现
 *
 *@author auto gen by simple-dao-codegen 2021-11-13 23:58:00
 *
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "SimpleApiService")
@Slf4j
//@Validated
@Tag(name = E_SimpleApi.BIZ_NAME, description = E_SimpleApi.BIZ_NAME + MAINTAIN_ACTION)
public class SimpleApiServiceImpl implements SimpleApiService {

    @Autowired
    private SimpleDao simpleDao;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Override
    public Long create(CreateSimpleApiReq req){
        SimpleApi entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Long> batchCreate(List<CreateSimpleApiReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    public SimpleApiInfo findById(Long id) {
        return simpleDao.findOneByQueryObj(new QuerySimpleApiReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    public int update(UpdateSimpleApiReq req) {
        return simpleDao.updateByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Integer> batchUpdate(List<UpdateSimpleApiReq> reqList){
        return reqList.stream().map(this::update).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    public int delete(DeleteSimpleApiReq req) {
        return simpleDao.deleteByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<SimpleApiInfo> query(QuerySimpleApiReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }
}