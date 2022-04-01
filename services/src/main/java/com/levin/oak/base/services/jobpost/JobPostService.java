package com.levin.oak.base.services.jobpost;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.jobpost.req.*;
import com.levin.oak.base.services.jobpost.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  工作岗位-服务接口
 *  @author Auto gen by simple-dao-codegen 2022-4-1 15:18:14
 */
@Tag(name = E_JobPost.BIZ_NAME, description = E_JobPost.BIZ_NAME + MAINTAIN_ACTION)
public interface JobPostService {

    String BIZ_NAME = E_JobPost.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateJobPostReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateJobPostReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    JobPostInfo findById(Long id);

    /**
    * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
    */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    JobPostInfo findById(JobPostIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateJobPostReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateJobPostReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(JobPostIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    List<Integer> batchDelete(DeleteJobPostReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<JobPostInfo> query(QueryJobPostReq req , Paging paging);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    JobPostInfo findOne(QueryJobPostReq req);

    /**
    * 清除缓存
    */
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION,  description = "缓存Key通常是主键ID")
    void clearCache(Object key);

}
