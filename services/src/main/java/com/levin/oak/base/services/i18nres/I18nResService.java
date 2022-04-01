package com.levin.oak.base.services.i18nres;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.i18nres.req.*;
import com.levin.oak.base.services.i18nres.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  国际化资源-服务接口
 *  @author Auto gen by simple-dao-codegen 2022-4-1 15:18:14
 */
@Tag(name = E_I18nRes.BIZ_NAME, description = E_I18nRes.BIZ_NAME + MAINTAIN_ACTION)
public interface I18nResService {

    String BIZ_NAME = E_I18nRes.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateI18nResReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateI18nResReq> reqList);

    /**
     * 通过主键查找记录，建议在服务内部调用，不要在控制器中调用
     */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    I18nResInfo findById(Long id);

    /**
    * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
    */
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    I18nResInfo findById(I18nResIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    int update(UpdateI18nResReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    List<Integer> batchUpdate(List<UpdateI18nResReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    int delete(I18nResIdReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    List<Integer> batchDelete(DeleteI18nResReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<I18nResInfo> query(QueryI18nResReq req , Paging paging);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    I18nResInfo findOne(QueryI18nResReq req);

    /**
    * 清除缓存
    */
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION,  description = "缓存Key通常是主键ID")
    void clearCache(Object key);

}
