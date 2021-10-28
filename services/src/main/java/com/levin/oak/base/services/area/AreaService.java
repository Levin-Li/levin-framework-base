package com.levin.oak.base.services.area;


import io.swagger.v3.oas.annotations.*;

import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;


/**
 *  区域服务
 *  @author Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
public interface AreaService {

    String ENTITY_NAME ="区域";

    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    String create(CreateAreaReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    List<String> batchCreate(List<CreateAreaReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    AreaInfo findById(String code);

    @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
    int update(UpdateAreaReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
    List<Integer> batchUpdate(List<UpdateAreaReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    int delete(DeleteAreaReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    PagingData<AreaInfo> query(QueryAreaReq req , Paging paging);

}
