package com.levin.oak.base.services.dict;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.dict.req.*;
import com.levin.oak.base.services.dict.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  字典-服务接口
 *  @author Auto gen by simple-dao-codegen 2022-1-18 13:59:49
 */
@Tag(name = E_Dict.BIZ_NAME, description = E_Dict.BIZ_NAME + MAINTAIN_ACTION)
public interface DictService {

    String BIZ_NAME = E_Dict.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateDictReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateDictReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    DictInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    DictInfo findById(QueryDictByIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateDictReq req);

    //尽量不用调用批量删除，会导致缓存清空
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateDictReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(DeleteDictReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<DictInfo> query(QueryDictReq req , Paging paging);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    DictInfo findOne(QueryDictReq req);
}
