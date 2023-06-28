package com.levin.oak.base.services.user;

import com.levin.commons.dao.DaoSecurityException;
import com.levin.commons.dao.Paging;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.*;
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
 * 用户-服务实现
 *
 * @author auto gen by simple-dao-codegen 2022-4-2 19:50:05
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

//@Service(PLUGIN_PREFIX + "UserService")
@DubboService
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "UserService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + E_User.SIMPLE_CLASS_NAME})
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private SimpleDao simpleDao;

    @Autowired
    AuthService authService;

    @Autowired
    FrameworkProperties frameworkProperties;

    protected UserService getSelfProxy() {
        return getSelfProxy(UserService.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Override
    public String create(CreateUserReq req) {

        checkCreateOrUpdateAccount(req.getEmail(), req.getTelephone());

        //密码加密
        User entity = simpleDao.create(req.setPassword(encryptPwd(req.getPassword())));

        return entity.getId();
    }

    private String encryptPwd(String pwd) {
        return StringUtils.hasText(pwd) ? authService.encryptPassword(pwd) : null;
    }

    private void checkCreateOrUpdateAccount(String... accounts) {

        for (String account : accounts) {
            //不允许创建或是变更为 SA 帐号
            Assert.isTrue(!authService.isSuperAdmin(account), "帐号已经存在");
        }

    }

    @Operation(summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<String> batchCreate(List<CreateUserReq> reqList) {
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(condition = "#id != null", unless = "#result == null ", key = E_User.CACHE_KEY_PREFIX + "#id")
    public UserInfo findById(String id) {
        return findById(new UserIdReq().setId(id));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null", condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    public UserInfo findById(UserIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findOneByQueryObj(req);
    }

    @Override
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateUserPwdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        Assert.hasText(req.getOldPassword(), "旧密码不能为空");
        Assert.hasText(req.getPassword(), "新密码不能为空");

        req.setOldPassword(encryptPwd(req.getOldPassword()))
                .setPassword(encryptPwd(req.getPassword()));

        return checkResult(simpleDao.updateByQueryObj(req), UPDATE_ACTION);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public boolean update(UpdateUserReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        //检查帐号名称
        checkCreateOrUpdateAccount(req.getEmail(), req.getTelephone());

        //密码加密
        return simpleDao.singleUpdateByQueryObj(req.setPassword(encryptPwd(req.getPassword())));
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateUserReq> reqList) {

        //@Todo 优化批量提交
        int sum = reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();

        //Assert.isTrue(sum > 0, BATCH_UPDATE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_User.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public boolean delete(UserIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        //不允许删除SA用户
        return simpleDao.singleDeleteByQueryObj(req, notSa());
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteUserReq req) {

        //@Todo 优化批量提交
        int sum = Stream.of(req.getIdList())
                .map(id -> simpleDao.copy(req, new UserIdReq().setId(id)))
                .map(idReq -> getSelfProxy().delete(idReq))
                .mapToInt(n -> n ? 1 : 0)
                .sum();

        //Assert.isTrue(sum > 0, BATCH_DELETE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<UserInfo> query(QueryUserReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, notSa(), paging);
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
    public PagingData<StatUserReq.Result> stat(StatUserReq req , Paging paging){
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
    public int count(QueryUserReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public UserInfo findOne(QueryUserReq req) {
        return simpleDao.findOneByQueryObj(req, notSa());
    }

    private NotReq notSa() {
        return new NotReq().setAccount(AuthService.SA_ACCOUNT);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public UserInfo findUnique(QueryUserReq req) {
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_User.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

    protected int checkResult(int n, String action) {
        if (n > 1) {
            throw new DaoSecurityException("非法的" + action + "操作");
        }
        return n;
    }
}
