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
//import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.TenantApp;
import static com.levin.oak.base.entities.E_TenantApp.*;

import com.levin.oak.base.services.tenantapp.req.*;
import com.levin.oak.base.services.tenantapp.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 租户应用-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月25日 下午1:50:23, 代码生成哈希校验码：[f44614897f4b00a0f17a23a63f367852]，请不要修改和删除此行内容。
 *
 */

@Service(TenantAppService.SERVICE_BEAN_NAME)

@ConditionalOnProperty(name = TenantAppService.SERVICE_BEAN_NAME, havingValue = "true", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_TenantApp.BIZ_NAME, description = E_TenantApp.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_TenantApp.SIMPLE_CLASS_NAME})
public class TenantAppServiceImpl extends BaseService implements TenantAppService {

    protected TenantAppService getSelfProxy(){
        //return getSelfProxy(TenantAppService.class);
        return getSelfProxy(TenantAppServiceImpl.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Transactional
    @Override
    public String create(CreateTenantAppReq req){
        //保存自动先查询唯一约束，并给出错误信息
        TenantApp entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Transactional
    @Override
    public List<String> batchCreate(List<CreateTenantAppReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }


    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id")//, beforeInvocation = true
    @Transactional
    public boolean update(UpdateTenantAppReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @Transactional
    @CacheEvict(allEntries = true, condition = "#result > 0")
    public int update(SimpleUpdateTenantAppReq setReq, QueryTenantAppReq whereReq){
       return simpleDao.updateByQueryObj(setReq, whereReq);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#reqList)  && #result > 0")
    public int batchUpdate(List<UpdateTenantAppReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id") //#req.tenantId +  , beforeInvocation = true
    @Transactional
    public boolean delete(TenantAppIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#req.idList) && #result > 0")
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
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX + "#id")
    public TenantAppInfo findById(String id) {
        return findById(new TenantAppIdReq().setId(id));
    }

    //调用本方法会导致不会对租户ID经常过滤，如果需要调用方对租户ID进行核查
    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    @Cacheable(unless = "#result == null" , condition = "@spelUtils.isNotEmpty(#req.id)" , key = CK_PREFIX + "#req.id") //#req.tenantId + 
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
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#key)", key = CK_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
