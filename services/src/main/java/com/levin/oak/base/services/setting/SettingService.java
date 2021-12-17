package com.levin.oak.base.services.setting;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  系统设置-服务接口
 *  @author Auto gen by simple-dao-codegen 2021-12-17 11:57:22
 */
@Tag(name = E_Setting.BIZ_NAME, description = E_Setting.BIZ_NAME + MAINTAIN_ACTION)
public interface SettingService {

    String BIZ_NAME = E_Setting.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateSettingReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateSettingReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    SettingInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateSettingReq req);

    //尽量不用调用批量删除，会导致缓存清空
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateSettingReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(DeleteSettingReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<SettingInfo> query(QuerySettingReq req , Paging paging);

}
