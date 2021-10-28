package com.levin.oak.base.services.org;


import io.swagger.v3.oas.annotations.*;

import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;


/**
 *  机构服务
 *  @author Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
public interface OrgService {

    String ENTITY_NAME ="机构";

    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    Long create(CreateOrgReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    List<Long> batchCreate(List<CreateOrgReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    OrgInfo findById(Long id);

    @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
    int update(UpdateOrgReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
    List<Integer> batchUpdate(List<UpdateOrgReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    int delete(DeleteOrgReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    PagingData<OrgInfo> query(QueryOrgReq req , Paging paging);

}
