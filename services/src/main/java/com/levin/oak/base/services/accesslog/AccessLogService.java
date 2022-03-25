package com.levin.oak.base.services.accesslog;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.accesslog.req.*;
import com.levin.oak.base.services.accesslog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  访问日志-服务接口
 *  @author Auto gen by simple-dao-codegen 2022-3-25 13:28:15
 */
@Tag(name = E_AccessLog.BIZ_NAME, description = E_AccessLog.BIZ_NAME + MAINTAIN_ACTION)
public interface AccessLogService {

    String BIZ_NAME = E_AccessLog.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateAccessLogReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateAccessLogReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    AccessLogInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    AccessLogInfo findById(AccessLogIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateAccessLogReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateAccessLogReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(AccessLogIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    List<Integer> batchDelete(DeleteAccessLogReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<AccessLogInfo> query(QueryAccessLogReq req , Paging paging);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    AccessLogInfo findOne(QueryAccessLogReq req);
}
