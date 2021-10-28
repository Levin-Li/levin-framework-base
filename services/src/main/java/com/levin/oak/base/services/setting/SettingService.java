package com.levin.oak.base.services.setting;


import io.swagger.v3.oas.annotations.*;

import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;


/**
 *  系统设置服务
 *  @author Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
public interface SettingService {

    String ENTITY_NAME ="系统设置";

    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    Long create(CreateSettingReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    List<Long> batchCreate(List<CreateSettingReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    SettingInfo findById(Long id);

    @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
    int update(UpdateSettingReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
    List<Integer> batchUpdate(List<UpdateSettingReq> reqList);

    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    int delete(DeleteSettingReq req);

    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    PagingData<SettingInfo> query(QuerySettingReq req , Paging paging);

}
