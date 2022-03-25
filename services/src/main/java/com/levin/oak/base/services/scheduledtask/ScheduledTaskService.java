package com.levin.oak.base.services.scheduledtask;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.scheduledtask.req.*;
import com.levin.oak.base.services.scheduledtask.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  调度任务-服务接口
 *  @author Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Tag(name = E_ScheduledTask.BIZ_NAME, description = E_ScheduledTask.BIZ_NAME + MAINTAIN_ACTION)
public interface ScheduledTaskService {

    String BIZ_NAME = E_ScheduledTask.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateScheduledTaskReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateScheduledTaskReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    ScheduledTaskInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    ScheduledTaskInfo findById(ScheduledTaskIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateScheduledTaskReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateScheduledTaskReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(ScheduledTaskIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    List<Integer> batchDelete(DeleteScheduledTaskReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<ScheduledTaskInfo> query(QueryScheduledTaskReq req , Paging paging);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    ScheduledTaskInfo findOne(QueryScheduledTaskReq req);
}
