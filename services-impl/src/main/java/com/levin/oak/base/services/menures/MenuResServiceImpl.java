package com.levin.oak.base.services.menures;

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
import com.levin.oak.base.entities.MenuRes;
import static com.levin.oak.base.entities.E_MenuRes.*;

import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import com.levin.oak.base.entities.MenuRes;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.rbac.MenuItem.*;
import java.util.Set;
import com.levin.oak.base.services.menures.info.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 菜单-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月28日 下午5:09:28, 代码生成哈希校验码：[dc0ccfd04648c51073bb451ebae114cf]，请不要修改和删除此行内容。
 *
 */

@Service(MenuResService.SERVICE_BEAN_NAME)

@ConditionalOnProperty(name = MenuResService.SERVICE_BEAN_NAME, havingValue = "true", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_MenuRes.BIZ_NAME, description = E_MenuRes.BIZ_NAME + MAINTAIN_ACTION)

//*** 提示 *** 如果要注释缓存注解的代码可以在实体类上加上@javax.persistence.Cacheable(false)，然后重新生成代码
@CacheConfig(cacheNames = MenuResService.CACHE_NAME, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")

// *** 提示 *** 请尽量不要修改本类，如果需要修改，请在BizMenuResServiceImpl业务类中重写业务逻辑

public class MenuResServiceImpl extends BaseService<MenuResServiceImpl> implements MenuResService {


    /**
    * 创建记录，返回主键ID
    * @param req
    * @return pkId 主键ID
    */
    @Operation(summary = CREATE_ACTION)
    @Transactional
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#result)", key = CK_PREFIX_EXPR + "#result") //创建也清除缓存，防止空值缓存的情况
    public String create(CreateMenuResReq req){
        //dao支持保存前先自动查询唯一约束，并给出错误信息
        MenuRes entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    @Transactional
    @Override
    public List<String> batchCreate(List<CreateMenuResReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX_EXPR + "#req.id")//, beforeInvocation = true
    @Transactional
    public boolean update(UpdateMenuResReq req, Object... queryObjs) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req, queryObjs);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @Transactional
    @CacheEvict(allEntries = true, condition = "#result > 0")
    public int batchUpdate(SimpleUpdateMenuResReq setReq, QueryMenuResReq whereReq, Object... queryObjs){
       return simpleDao.updateByQueryObj(setReq, whereReq, queryObjs);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#reqList)  && #result > 0")
    public int batchUpdate(List<UpdateMenuResReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX_EXPR + "#req.id") //#req.tenantId +  , beforeInvocation = true
    @Transactional
    public boolean delete(MenuResIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#req.idList) && #result > 0")
    public int batchDelete(DeleteMenuResReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new MenuResIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n ? 1 : 0)
            .sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<MenuResInfo> query(QueryMenuResReq req, Paging paging, Object... queryObjs) {
        return simpleDao.findPagingDataByQueryObj(req, paging, queryObjs);
    }

    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    public PagingData<MenuResInfo> selectQuery(QueryMenuResReq req, Paging paging, String... columnNames){
        return simpleDao.forSelect(MenuResInfo.class, req, paging).select(columnNames).findPaging(null, paging);
    }

    @Override
    @Operation(summary = STAT_ACTION)
    public int count(QueryMenuResReq req, Object... queryObjs){
        return (int) simpleDao.countByQueryObj(req, queryObjs);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    //如果要注释缓存注解的代码可以在实体类上加上@javax.persistence.Cacheable(false)，然后重新生成代码
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX_EXPR + "#id")
    public MenuResInfo findById(String id) {
        return findById(new MenuResIdReq().setId(id));
    }

    //调用本方法会导致不会对租户ID经常过滤，如果需要调用方对租户ID进行核查
    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    @Cacheable(unless = "#result == null" , condition = "@spelUtils.isNotEmpty(#req.id)" , key = CK_PREFIX_EXPR + "#req.id") //#req.tenantId + 
    public MenuResInfo findById(MenuResIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public MenuResInfo findOne(QueryMenuResReq req, Object... queryObjs){
        return simpleDao.findOneByQueryObj(req, queryObjs);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public MenuResInfo findUnique(QueryMenuResReq req){
        //记录超过一条时抛出异常 throws IncorrectResultSizeDataAccessException
        return simpleDao.findUnique(req);
    }

    /**
    * 清除缓存
    * @param keySuffix 缓存Key后缀，不包含前缀
    */
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "通常是主键ID")
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#keySuffix)", key = CK_PREFIX_EXPR + "#keySuffix")
    public void clearCacheByKeySuffix(Object keySuffix){
    }

    /**
    * 清除缓存
    * @param key 缓存Key
    */
    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "完整的缓存Key")
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#key)", key = "'' + #key")
    public void clearCache(Object key) {
    }

    /**
    * 清除[MenuResService.CACHE_NAME]缓存中的所有缓存
    * @param key 缓存Key
    */
    @Override
    @Operation(summary = CLEAR_CACHE_ACTION,  description = "清除所有缓存")
    @CacheEvict(allEntries = true)
    public void clearAllCache() {
    }

}
