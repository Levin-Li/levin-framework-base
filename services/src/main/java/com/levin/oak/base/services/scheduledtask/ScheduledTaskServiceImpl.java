package com.levin.oak.base.services.scheduledtask;

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
import com.levin.oak.base.entities.ScheduledTask;

import com.levin.oak.base.services.scheduledtask.req.*;
import com.levin.oak.base.services.scheduledtask.info.*;


////////////////////////////////////
//自动导入列表
    import java.util.Date;
    import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 *  调度任务-服务实现
 *
 *@author auto gen by simple-dao-codegen 2021-11-12 9:56:30
 *
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "ScheduledTaskService")
@Slf4j
//@Validated
@Tag(name = E_ScheduledTask.BIZ_NAME, description = E_ScheduledTask.BIZ_NAME + MAINTAIN_ACTION)
public class ScheduledTaskServiceImpl implements ScheduledTaskService {

    @Autowired
    private SimpleDao simpleDao;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION + BIZ_NAME)
    @Override
    public Long create(CreateScheduledTaskReq req){
        ScheduledTask entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION + BIZ_NAME)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Long> batchCreate(List<CreateScheduledTaskReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION + BIZ_NAME)
    @Override
    public ScheduledTaskInfo findById(Long id) {
        return simpleDao.findOneByQueryObj(new QueryScheduledTaskReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION + BIZ_NAME)
    @Override
    public int update(UpdateScheduledTaskReq req) {
        return simpleDao.updateByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION + BIZ_NAME)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Integer> batchUpdate(List<UpdateScheduledTaskReq> reqList){
        return reqList.stream().map(this::update).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION + BIZ_NAME)
    @Override
    public int delete(DeleteScheduledTaskReq req) {
        return simpleDao.deleteByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION + BIZ_NAME)
    @Override
    public PagingData<ScheduledTaskInfo> query(QueryScheduledTaskReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }
}
