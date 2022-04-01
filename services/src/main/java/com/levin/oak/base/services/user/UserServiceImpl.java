package com.levin.oak.base.services.user;

import com.levin.commons.dao.DaoSecurityException;
import com.levin.commons.dao.Paging;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.PagingData;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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

import javax.annotation.Resource;
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
 * 用户-服务实现
 *
 * @author auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "UserService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "UserService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + E_User.SIMPLE_CLASS_NAME})
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private SimpleDao simpleDao;

    @Resource
    AuthService authService;

    protected UserService getSelfProxy() {
        return getSelfProxy(UserService.class);
    }

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Override
    public Long create(CreateUserReq req) {

        checkSARole(req.getRoleList(), null);

        User entity = simpleDao.create(req);

        return entity.getId();
    }


    protected void checkSARole(List<String> roleList, String errorInfo) {

        //如果有超级角色，需要检查当前用户，是否是超管
        if (roleList != null
                && roleList.stream().map(StringUtils::trimAllWhitespace)
                .anyMatch(name -> RbacRoleObject.SA_ROLE.equalsIgnoreCase(name))) {

            if (errorInfo == null) {
                errorInfo = "当前用户未拥有超管角色";
            }

            Assert.isTrue(authService.getUserInfo().isSuperAdmin(), errorInfo);
        }
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Long> batchCreate(List<CreateUserReq> reqList) {
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_User.CACHE_KEY_PREFIX + "#id")
    public UserInfo findById(Long id) {
        return findById(new UserIdReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null", condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    public UserInfo findById(UserIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateUserReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        checkSARole(req.getRoleList(), null);

        int n = simpleDao.updateByQueryObj(req);

        if (n > 1) {
            throw new DaoSecurityException("非法的" + UPDATE_ACTION + "操作");
        }

        return n;
    }

    @Override
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateUserPwdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        Assert.hasText(req.getOldPassword(), "旧密码不能为空");
        Assert.hasText(req.getPassword(), "新密码不能为空");

        req.setOldPassword(authService.encryptPassword(req.getOldPassword()))
                .setPassword(authService.encryptPassword(req.getPassword()));

        int n = simpleDao.updateByQueryObj(req);

        if (n > 1) {
            throw new DaoSecurityException("非法的" + UPDATE_ACTION + "操作");
        }

        return n;
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Integer> batchUpdate(List<UpdateUserReq> reqList) {
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int delete(UserIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        int n = simpleDao.deleteByQueryObj(req);

        if (n > 1) {
            throw new DaoSecurityException("非法的" + DELETE_ACTION + "操作");
        }

        return n;
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Integer> batchDelete(DeleteUserReq req) {
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
                .map(id -> simpleDao.copy(req, new UserIdReq().setId(id)))
                .map(idReq -> getSelfProxy().delete((UserIdReq) idReq))
                .collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<UserInfo> query(QueryUserReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public UserInfo findOne(QueryUserReq req) {
        return simpleDao.findOneByQueryObj(req);
    }


    /**
     * 清除缓存
     *
     * @param key
     */
    @Override
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_User.CACHE_KEY_PREFIX + "#key")
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION)
    public void clearCache(Object key) {
    }
}
