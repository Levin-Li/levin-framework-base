package com.levin.oak.base.services.role;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.rbac.RbacRoleObject;
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
import com.levin.oak.base.entities.Role;
import static com.levin.oak.base.entities.E_Role.*;

import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.List;
import com.levin.oak.base.entities.Role.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 角色-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月24日 下午9:51:47, 代码生成哈希校验码：[24d9bcb12b7972b955388c5cc79dd336]，请不要修改和删除此行内容。
 *
 */

@Service(PLUGIN_PREFIX + "RoleService")
//@DubboService

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "RoleService", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_Role.SIMPLE_CLASS_NAME})
public class RoleServiceImpl extends BaseService implements RoleService {

    protected RoleService getSelfProxy(){
        //return getSelfProxy(RoleService.class);
        return getSelfProxy(RoleServiceImpl.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Transactional
    @Override
    public String create(CreateRoleReq req) {

        checkRoleCode(req.getCode());

        //不允许创建SA角色
        Assert.isTrue(!RbacRoleObject.SA_ROLE.equalsIgnoreCase(StringUtils.trimAllWhitespace(req.getCode())),
                "角色编码[" + RbacRoleObject.SA_ROLE + "]已经被系统使用");

        long cnt = simpleDao.countByQueryObj(new QueryRoleReq().setCode(req.getCode()).setTenantId(req.getTenantId()));

        //
        Assert.isTrue(cnt < 1, "角色编码" + req.getCode() + "已经存在");

        Role entity = simpleDao.create(req);

        return entity.getId();
    }

    private void checkRoleCode(String code) {
        Assert.notBlank(code, "角色编码不能为空");
        Assert.isTrue(code.startsWith("R_"), "角色编码必须以 R_ 开头");
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Transactional
    @Override
    public List<String> batchCreate(List<CreateRoleReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }


    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id")
    @Transactional
    public boolean update(UpdateRoleReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(allEntries = true, condition = "#result > 0")
    public int update(SimpleUpdateRoleReq setReq, QueryRoleReq whereReq){
       return simpleDao.updateByQueryObj(setReq, whereReq);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional
    @Override
    @CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#reqList)  && #result > 0")
    public int batchUpdate(List<UpdateRoleReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.tenantId + #req.id")
    @Transactional
    public boolean delete(RoleIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional
    @Override
    @CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#req.idList) && #result > 0")
    public int batchDelete(DeleteRoleReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new RoleIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n ? 1 : 0)
            .sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<RoleInfo> query(QueryRoleReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    public PagingData<SimpleRoleInfo> simpleQuery(QueryRoleReq req, Paging paging){
        return simpleDao.findPagingDataByQueryObj(SimpleRoleInfo.class, req, paging);
    }

    @Operation(summary = STAT_ACTION)
    @Override
    public PagingData<StatRoleReq.Result> stat(StatRoleReq req , Paging paging){
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Override
    @Operation(summary = STAT_ACTION)
    public int count(QueryRoleReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX + "#id")
    public RoleInfo findById(String id) {
        return findById(new RoleIdReq().setId(id));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    @CachePut(unless = "#result == null" , condition = "@spelUtils.isNotEmpty(#req.id)" , key = CK_PREFIX + "#req.tenantId + #req.id")
    public RoleInfo findById(RoleIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public RoleInfo findOne(QueryRoleReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public RoleInfo findUnique(QueryRoleReq req){
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#key)", key = CK_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
