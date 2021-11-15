package com.levin.oak.base.services.scheduledlog;


import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.cache.annotation.*;
import java.util.*;

import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.scheduledlog.req.*;
import com.levin.oak.base.services.scheduledlog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.EntityConst.*;


/**
 *  调度日志-服务接口
 *  @author Auto gen by simple-dao-codegen 2021-11-15 15:01:48
 */
@Tag(name = E_ScheduledLog.BIZ_NAME, description = E_ScheduledLog.BIZ_NAME + MAINTAIN_ACTION)

@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + E_ScheduledLog.SIMPLE_CLASS_NAME})
public interface ScheduledLogService {

    String BIZ_NAME = E_ScheduledLog.BIZ_NAME;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    Long create(CreateScheduledLogReq req);

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    List<Long> batchCreate(List<CreateScheduledLogReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_User.CACHE_KEY_PREFIX + "#id")
    ScheduledLogInfo findById(Long id);

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @CacheEvict(condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    int update(UpdateScheduledLogReq req);

    //尽量不用调用批量删除，会导致缓存清空
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @CacheEvict(condition = "#reqList != null && #reqList.size() > 0", allEntries = true)
    List<Integer> batchUpdate(List<UpdateScheduledLogReq> reqList);

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Caching(evict = {  //尽量不用调用批量删除，会导致缓存清空
        @CacheEvict(condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id"),
        @CacheEvict(condition = "#req.idList != null && #req.idList.length > 0", allEntries = true),
    })
    int delete(DeleteScheduledLogReq req);

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    PagingData<ScheduledLogInfo> query(QueryScheduledLogReq req , Paging paging);

}
