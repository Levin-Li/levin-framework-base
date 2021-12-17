package com.levin.oak.base.services.menures;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  菜单-服务接口
 *  @author Auto gen by simple-dao-codegen 2021-12-17 11:57:23
 */
@Tag(name = E_MenuRes.BIZ_NAME, description = E_MenuRes.BIZ_NAME + MAINTAIN_ACTION)
public interface MenuResService {

    String BIZ_NAME = E_MenuRes.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateMenuResReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateMenuResReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    MenuResInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateMenuResReq req);

    //尽量不用调用批量删除，会导致缓存清空
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateMenuResReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(DeleteMenuResReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<MenuResInfo> query(QueryMenuResReq req , Paging paging);

}
