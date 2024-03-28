package com.levin.oak.base.services.simpleform;

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
//import org.springframework.dao.*;

import javax.persistence.PersistenceException;
import cn.hutool.core.lang.*;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

//import org.apache.dubbo.config.spring.context.annotation.*;
//import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimpleForm;
import static com.levin.oak.base.entities.E_SimpleForm.*;

import com.levin.oak.base.services.simpleform.req.*;
import com.levin.oak.base.services.simpleform.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 简单表单-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月29日 上午12:46:09, 代码生成哈希校验码：[dfc9c67ea78cb3d2c3b02f0dad19b7a9]，请不要修改和删除此行内容。
 *
 */

@Service(SimpleFormService.SERVICE_BEAN_NAME)

@ConditionalOnProperty(name = SimpleFormService.SERVICE_BEAN_NAME, havingValue = "true", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_SimpleForm.BIZ_NAME, description = E_SimpleForm.BIZ_NAME + MAINTAIN_ACTION)

//*** 提示 *** 如果要注释缓存注解的代码可以在实体类上加上@javax.persistence.Cacheable(false)，然后重新生成代码
@CacheConfig(cacheNames = SimpleFormService.CACHE_NAME, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")

// *** 提示 *** 请尽量不要修改本类，如果需要修改，请在BizSimpleFormServiceImpl业务类中重写业务逻辑

public class SimpleFormServiceImpl extends BaseService<SimpleFormServiceImpl> implements SimpleFormService {


    /**
    * 创建记录，返回主键ID
    * @param req
    * @return pkId 主键ID
    */
    @Operation(summary = CREATE_ACTION)
    @Transactional
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#result)", key = CK_PREFIX_EXPR + "#result") //创建也清除缓存，防止空值缓存的情况
    public String create(CreateSimpleFormReq req){
        //dao支持保存前先自动查询唯一约束，并给出错误信息
        SimpleForm entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    @Transactional
    @Override
    public List<String> batchCreate(List<CreateSimpleFormReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX_EXPR + "#req.id")//, beforeInvocation = true
    @Transactional
    public boolean update(UpdateSimpleFormReq req, Object... queryObjs) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req, queryObjs);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @Transactional
    @CacheEvict(allEntries = true, condition = "#result > 0")
    public int batchUpdate(SimpleUpdateSimpleFormReq setReq, QuerySimpleFormReq whereReq, Object... queryObjs){
       return simpleDao.updateByQueryObj(setReq, whereReq, queryObjs);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#reqList)  && #result > 0")
    public int batchUpdate(List<UpdateSimpleFormReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX_EXPR + "#req.id") //#req.tenantId +  , beforeInvocation = true
    @Transactional
    public boolean delete(SimpleFormIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#req.idList) && #result > 0")
    public int batchDelete(DeleteSimpleFormReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new SimpleFormIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n ? 1 : 0)
            .sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<SimpleFormInfo> query(QuerySimpleFormReq req, Paging paging, Object... queryObjs) {
        return simpleDao.findPagingDataByQueryObj(req, paging, queryObjs);
    }

    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    public PagingData<SimpleFormInfo> selectQuery(QuerySimpleFormReq req, Paging paging, String... columnNames){
        return simpleDao.forSelect(SimpleFormInfo.class, req, paging).select(columnNames).findPaging(null, paging);
    }

    @Override
    @Operation(summary = STAT_ACTION)
    public int count(QuerySimpleFormReq req, Object... queryObjs){
        return (int) simpleDao.countByQueryObj(req, queryObjs);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    //如果要注释缓存注解的代码可以在实体类上加上@javax.persistence.Cacheable(false)，然后重新生成代码
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX_EXPR + "#id")
    public SimpleFormInfo findById(String id) {
        return findById(new SimpleFormIdReq().setId(id));
    }

    //调用本方法会导致不会对租户ID经常过滤，如果需要调用方对租户ID进行核查
    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    @Cacheable(unless = "#result == null" , condition = "@spelUtils.isNotEmpty(#req.id)" , key = CK_PREFIX_EXPR + "#req.id") //#req.tenantId + 
    public SimpleFormInfo findById(SimpleFormIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public SimpleFormInfo findOne(QuerySimpleFormReq req, Object... queryObjs){
        return simpleDao.findOneByQueryObj(req, queryObjs);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public SimpleFormInfo findUnique(QuerySimpleFormReq req){
        //记录超过一条时抛出异常 throws IncorrectResultSizeDataAccessException
        return simpleDao.findUnique(req);
    }

    /**
    * 清除缓存
    * @param key
    */
    @Operation(summary = GET_CACHE_ACTION, description = "通常是主键ID")
    @Cacheable(unless = "#result == null", condition = "@spelUtils.isNotEmpty(#key)", key = "#key")
    public <T> T getCache(String key){
        Assert.notBlank(key, "key is empty");
        return null;
    }


    /**
    * 清除缓存
    * @param key 缓存Key
    */
    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "完整的缓存Key")
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#key)", key = "#key")
    public void clearCache(String key) {
        Assert.notBlank(key, "key is empty");
    }

    /**
    * 清除[SimpleFormService.CACHE_NAME]缓存中的所有缓存
    *
    */
    @Override
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "清除所有缓存")
    @CacheEvict(allEntries = true)
    public void clearAllCache() {
    }

}
