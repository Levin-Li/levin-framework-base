package com.levin.oak.base.services.role;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;



import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.*;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.dao.*;
import javax.persistence.PersistenceException;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Role;

import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Role.*;
import java.util.List;
import com.levin.commons.rbac.ResPermission;
import java.util.Date;
////////////////////////////////////

/**
 *  角色-服务实现
 *
 *@author auto gen by simple-dao-codegen 2022-1-26 17:07:14
 *
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "RoleService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "RoleService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + E_Role.SIMPLE_CLASS_NAME})
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private SimpleDao simpleDao;

    protected RoleService getSelfProxy(){
        return getSelfProxy(RoleService.class);
    }

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Override
    public Long create(CreateRoleReq req){
        Role entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Long> batchCreate(List<CreateRoleReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_Role.CACHE_KEY_PREFIX + "#id")
    public RoleInfo findById(Long id) {
        return findById(new RoleIdReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null" , condition = "#req.id != null" , key = E_Role.CACHE_KEY_PREFIX + "#req.id")
    public RoleInfo findById(RoleIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Role.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateRoleReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        int n = simpleDao.updateByQueryObj(req);

        if(n > 1){
            throw new DaoSecurityException("非法的" + UPDATE_ACTION +"操作");
        }

        return n;
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Integer> batchUpdate(List<UpdateRoleReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Role.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int delete(RoleIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        int n = simpleDao.deleteByQueryObj(req);

        if(n > 1){
            throw new DaoSecurityException("非法的" + DELETE_ACTION +"操作");
        }

        return n;
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Integer> batchDelete(DeleteRoleReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new RoleIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete((RoleIdReq)idReq))
            .collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<RoleInfo> query(QueryRoleReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public RoleInfo findOne(QueryRoleReq req){
        return simpleDao.findOneByQueryObj(req);
    }

}
