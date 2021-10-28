package com.levin.oak.base.services.menures;


import io.swagger.v3.oas.annotations.*;

import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;


/**
 *  菜单服务
 *  @author Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
public interface MenuResService {

    String ENTITY_NAME ="菜单";

    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    Long create(CreateMenuResReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    List<Long> batchCreate(List<CreateMenuResReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    MenuResInfo findById(Long id);

    @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
    int update(UpdateMenuResReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
    List<Integer> batchUpdate(List<UpdateMenuResReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    int delete(DeleteMenuResReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    PagingData<MenuResInfo> query(QueryMenuResReq req , Paging paging);

}
