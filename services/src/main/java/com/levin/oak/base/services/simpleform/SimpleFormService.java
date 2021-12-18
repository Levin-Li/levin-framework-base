package com.levin.oak.base.services.simpleform;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.simpleform.req.*;
import com.levin.oak.base.services.simpleform.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  简单表单-服务接口
 *  @author Auto gen by simple-dao-codegen 2021-12-18 11:15:49
 */
@Tag(name = E_SimpleForm.BIZ_NAME, description = E_SimpleForm.BIZ_NAME + MAINTAIN_ACTION)
public interface SimpleFormService {

    String BIZ_NAME = E_SimpleForm.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateSimpleFormReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateSimpleFormReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    SimpleFormInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateSimpleFormReq req);

    //尽量不用调用批量删除，会导致缓存清空
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateSimpleFormReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(DeleteSimpleFormReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<SimpleFormInfo> query(QuerySimpleFormReq req , Paging paging);

}
