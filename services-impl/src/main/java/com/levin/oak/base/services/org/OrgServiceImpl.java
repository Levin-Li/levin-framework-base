package com.levin.oak.base.services.org;

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
import com.levin.oak.base.entities.Org;
import static com.levin.oak.base.entities.E_Org.*;

import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 机构-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月7日 上午11:03:11, 代码生成哈希校验码：[e6b864f934d332687a2492f411f67f53]，请不要修改和删除此行内容。
 *
 */

@Service(OrgService.SERVICE_BEAN_NAME)

@ConditionalOnProperty(name = OrgService.SERVICE_BEAN_NAME, havingValue = "true", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_Org.BIZ_NAME, description = E_Org.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_Org.SIMPLE_CLASS_NAME})
public class OrgServiceImpl extends BaseService implements OrgService {

    protected OrgService getSelfProxy(){
        //return getSelfProxy(OrgService.class);
        return getSelfProxy(OrgServiceImpl.class);
    }

    /**
    * 创建记录，返回主键ID
    * @param req
    * @return pkId 主键ID
    */
    @Operation(summary = CREATE_ACTION)
    @Transactional
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#result)", key = CK_PREFIX + "#result") //创建也清除缓存，防止空值缓存的情况
    public String create(CreateOrgReq req){
        //dao支持保存前先自动查询唯一约束，并给出错误信息
        Org entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    @Transactional
    @Override
    public List<String> batchCreate(List<CreateOrgReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id")//, beforeInvocation = true
    @Transactional
    public boolean update(UpdateOrgReq req, Object... queryObjs) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req, queryObjs);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @Transactional
    @CacheEvict(allEntries = true, condition = "#result > 0")
    public int update(SimpleUpdateOrgReq setReq, QueryOrgReq whereReq){
       return simpleDao.updateByQueryObj(setReq, whereReq);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#reqList)  && #result > 0")
    public int batchUpdate(List<UpdateOrgReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id") //#req.tenantId +  , beforeInvocation = true
    @Transactional
    public boolean delete(OrgIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional
    @Override
    //@CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#req.idList) && #result > 0")
    public int batchDelete(DeleteOrgReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new OrgIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n ? 1 : 0)
            .sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<OrgInfo> query(QueryOrgReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    public PagingData<OrgInfo> selectQuery(QueryOrgReq req, Paging paging, String... columnNames){
        return simpleDao.forSelect(OrgInfo.class, req, paging).select(columnNames).findPaging(null, paging);
    }

    @Override
    @Operation(summary = STAT_ACTION)
    public int count(QueryOrgReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX + "#id")
    public OrgInfo findById(String id) {
        return findById(new OrgIdReq().setId(id));
    }

    //调用本方法会导致不会对租户ID经常过滤，如果需要调用方对租户ID进行核查
    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    @Cacheable(unless = "#result == null" , condition = "@spelUtils.isNotEmpty(#req.id)" , key = CK_PREFIX + "#req.id") //#req.tenantId + 
    public OrgInfo findById(OrgIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public OrgInfo findOne(QueryOrgReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public OrgInfo findUnique(QueryOrgReq req){
        //记录超过一条时抛出异常 throws IncorrectResultSizeDataAccessException
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#key)", key = CK_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
