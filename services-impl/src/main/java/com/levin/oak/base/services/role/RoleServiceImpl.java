package com.levin.oak.base.services.role;

import com.levin.commons.dao.DaoSecurityException;
import com.levin.commons.dao.Paging;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.PagingData;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.role.info.RoleInfo;
import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import com.levin.oak.base.services.tenant.req.StatTenantReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.entities.EntityConst.*;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 角色-服务实现
 *
 * @author auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

//@Service(PLUGIN_PREFIX + "RoleService")
@DubboService
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "RoleService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + E_Role.SIMPLE_CLASS_NAME})
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private SimpleDao simpleDao;

    protected RoleService getSelfProxy() {
        return getSelfProxy(RoleService.class);
    }

    @Operation(summary = CREATE_ACTION)
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
        Assert.hasText(code, "角色编码不能为空");
        Assert.isTrue(code.startsWith("R_"), "角色编码必须以 R_ 开头");
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<String> batchCreate(List<CreateRoleReq> reqList) {
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(condition = "#id != null", unless = "#result == null ", key = E_Role.CACHE_KEY_PREFIX + "#id")
    public RoleInfo findById(String id) {
        return findById(new RoleIdReq().setId(id));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null", condition = "#req.id != null", key = E_Role.CACHE_KEY_PREFIX + "#req.id")
    public RoleInfo findById(RoleIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Role.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public boolean update(UpdateRoleReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return simpleDao.singleUpdateByQueryObj(req);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateRoleReq> reqList) {
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Role.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public boolean delete(RoleIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        //关键逻辑
        //req.setContainsPublicData(false);

        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteRoleReq req) {
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
                .map(id -> simpleDao.copy(req, new RoleIdReq().setId(id)))
                .map(idReq -> getSelfProxy().delete((RoleIdReq) idReq))
                .mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<RoleInfo> query(QueryRoleReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    /**
     * 简单统计
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(tags = {BIZ_NAME}, summary = STAT_ACTION)
    @Override
    public PagingData<StatRoleReq.Result> stat(StatRoleReq req , Paging paging){
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Override
    @Operation(tags = {BIZ_NAME}, summary = STAT_ACTION)
    public int count(QueryRoleReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public RoleInfo findOne(QueryRoleReq req) {
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public RoleInfo findUnique(QueryRoleReq req){
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_Role.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
