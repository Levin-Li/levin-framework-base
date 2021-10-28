package com.levin.oak.base.services.dict;


import io.swagger.v3.oas.annotations.*;

import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.dict.req.*;
import com.levin.oak.base.services.dict.info.*;


/**
 *  字典服务
 *  @author Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
public interface DictService {

    String ENTITY_NAME ="字典";

    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    Long create(CreateDictReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    List<Long> batchCreate(List<CreateDictReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    DictInfo findById(Long id);

    @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
    int update(UpdateDictReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
    List<Integer> batchUpdate(List<UpdateDictReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    int delete(DeleteDictReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    PagingData<DictInfo> query(QueryDictReq req , Paging paging);

}
