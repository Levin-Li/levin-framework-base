package com.levin.oak.base.services.simplepage;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.simplepage.req.*;
import com.levin.oak.base.services.simplepage.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  简单页面-服务接口
 *  @author Auto gen by simple-dao-codegen 2022-1-26 17:07:14
 */
@Tag(name = E_SimplePage.BIZ_NAME, description = E_SimplePage.BIZ_NAME + MAINTAIN_ACTION)
public interface SimplePageService {

    String BIZ_NAME = E_SimplePage.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateSimplePageReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateSimplePageReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    SimplePageInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    SimplePageInfo findById(SimplePageIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateSimplePageReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateSimplePageReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(SimplePageIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    List<Integer> batchDelete(DeleteSimplePageReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<SimplePageInfo> query(QuerySimplePageReq req , Paging paging);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    SimplePageInfo findOne(QuerySimplePageReq req);
}
