package com.levin.oak.base.services.org;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  机构-服务接口
 *  @author Auto gen by simple-dao-codegen 2022-1-26 17:07:14
 */
@Tag(name = E_Org.BIZ_NAME, description = E_Org.BIZ_NAME + MAINTAIN_ACTION)
public interface OrgService {

    String BIZ_NAME = E_Org.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateOrgReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateOrgReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    OrgInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    OrgInfo findById(OrgIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateOrgReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateOrgReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(OrgIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    List<Integer> batchDelete(DeleteOrgReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<OrgInfo> query(QueryOrgReq req , Paging paging);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    OrgInfo findOne(QueryOrgReq req);
}
