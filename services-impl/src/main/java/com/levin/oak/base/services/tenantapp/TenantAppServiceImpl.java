package com.levin.oak.base.services.tenantapp;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import javax.annotation.*;
import java.util.*;
import java.util.stream.*;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.StringUtils;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.dao.*;

import javax.persistence.PersistenceException;
import cn.hutool.core.lang.*;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

//import org.apache.dubbo.config.spring.context.annotation.*;
import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.TenantApp;

import com.levin.oak.base.services.tenantapp.req.*;
import com.levin.oak.base.services.tenantapp.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 租户应用-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:40, 代码生成哈希校验码：[8ada70dd30bf844c5f39d80e1761f4d8]，请不要修改和删除此行内容。
 *
 */

@Service(PLUGIN_PREFIX + "TenantAppService")
@DubboService

@ConditionalOnMissingBean({TenantAppService.class}) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "TenantAppService", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_TenantApp.BIZ_NAME, description = E_TenantApp.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_TenantApp.SIMPLE_CLASS_NAME})
public class TenantAppServiceImpl extends BaseService implements TenantAppService {

    protected TenantAppService getSelfProxy(){
        return getSelfProxy(TenantAppService.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public String create(CreateTenantAppReq req){
        //保存自动先查询唯一约束，并给出错误信息
        TenantApp entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public List<String> batchCreate(List<CreateTenantAppReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    //@CacheEvict(condition = "#isNotEmpty(#req.id)", key = E_TenantApp.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean update(UpdateTenantAppReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    public int update(SimpleUpdateTenantAppReq setReq, QueryTenantAppReq whereReq){
       return simpleDao.updateByQueryObj(setReq, whereReq);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int batchUpdate(List<UpdateTenantAppReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    //@CacheEvict(condition = "#isNotEmpty(#req.id)", key = E_TenantApp.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(TenantAppIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int batchDelete(DeleteTenantAppReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new TenantAppIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n ? 1 : 0)
            .sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<TenantAppInfo> query(QueryTenantAppReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    public PagingData<SimpleTenantAppInfo> simpleQuery(QueryTenantAppReq req, Paging paging){
        return simpleDao.findPagingDataByQueryObj(SimpleTenantAppInfo.class, req, paging);
    }

    @Operation(summary = STAT_ACTION)
    @Override
    public PagingData<StatTenantAppReq.Result> stat(StatTenantAppReq req , Paging paging){
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Override
    @Operation(summary = STAT_ACTION)
    public int count(QueryTenantAppReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    //@Cacheable(condition = "#isNotEmpty(#id)", unless = "#result == null ", key = E_TenantApp.CACHE_KEY_PREFIX + "#id")
    public TenantAppInfo findById(String id) {
        return findById(new TenantAppIdReq().setId(id));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    //@CachePut(unless = "#result == null" , condition = "#isNotEmpty(#req.id)" , key = E_TenantApp.CACHE_KEY_PREFIX + "#req.id")
    public TenantAppInfo findById(TenantAppIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public TenantAppInfo findOne(QueryTenantAppReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public TenantAppInfo findUnique(QueryTenantAppReq req){
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#isNotEmpty(#key)", key = E_TenantApp.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
