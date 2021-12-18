package com.levin.oak.base.services.area;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  区域-服务接口
 *  @author Auto gen by simple-dao-codegen 2021-12-18 11:15:49
 */
@Tag(name = E_Area.BIZ_NAME, description = E_Area.BIZ_NAME + MAINTAIN_ACTION)
public interface AreaService {

    String BIZ_NAME = E_Area.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    String create(CreateAreaReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<String> batchCreate(List<CreateAreaReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    AreaInfo findById(String code);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateAreaReq req);

    //尽量不用调用批量删除，会导致缓存清空
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateAreaReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(DeleteAreaReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<AreaInfo> query(QueryAreaReq req , Paging paging);

}
