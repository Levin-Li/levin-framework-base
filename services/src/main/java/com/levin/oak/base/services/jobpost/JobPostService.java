package com.levin.oak.base.services.jobpost;


import io.swagger.v3.oas.annotations.*;

import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.jobpost.req.*;
import com.levin.oak.base.services.jobpost.info.*;


/**
 *  工作岗位服务
 *  @author Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
public interface JobPostService {

    String ENTITY_NAME ="工作岗位";

    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    Long create(CreateJobPostReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    List<Long> batchCreate(List<CreateJobPostReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    JobPostInfo findById(Long id);

    @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
    int update(UpdateJobPostReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
    List<Integer> batchUpdate(List<UpdateJobPostReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    int delete(DeleteJobPostReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    PagingData<JobPostInfo> query(QueryJobPostReq req , Paging paging);

}
