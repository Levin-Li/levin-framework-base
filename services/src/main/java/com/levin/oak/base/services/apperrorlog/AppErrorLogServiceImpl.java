package com.levin.oak.base.services.apperrorlog;

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
import com.levin.oak.base.entities.AppErrorLog;

import com.levin.oak.base.services.apperrorlog.req.*;
import com.levin.oak.base.services.apperrorlog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  应用错误日志-服务实现
 *
 *@author auto gen by simple-dao-codegen 2022-4-2 19:44:58
 *
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "AppErrorLogService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AppErrorLogService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_AppErrorLog.BIZ_NAME, description = E_AppErrorLog.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + E_AppErrorLog.SIMPLE_CLASS_NAME})
public class AppErrorLogServiceImpl extends BaseService implements AppErrorLogService {

    @Autowired
    private SimpleDao simpleDao;

    protected AppErrorLogService getSelfProxy(){
        return getSelfProxy(AppErrorLogService.class);
    }

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Override
    public Long create(CreateAppErrorLogReq req){
        AppErrorLog entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Long> batchCreate(List<CreateAppErrorLogReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#id")
    public AppErrorLogInfo findById(Long id) {
        return findById(new AppErrorLogIdReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null" , condition = "#req.id != null" , key = E_AppErrorLog.CACHE_KEY_PREFIX + "#req.id")
    public AppErrorLogInfo findById(AppErrorLogIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateAppErrorLogReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return checkResult(simpleDao.updateByQueryObj(req), UPDATE_ACTION);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateAppErrorLogReq> reqList){
        //@Todo 优化批量提交
        int sum = reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n).sum();

        //Assert.isTrue(sum > 0, BATCH_UPDATE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int delete(AppErrorLogIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

       return checkResult(simpleDao.deleteByQueryObj(req), DELETE_ACTION);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteAppErrorLogReq req){
        //@Todo 优化批量提交
//        int sum = Stream.of(req.getIdList())
//            .map(id -> simpleDao.copy(req, new AppErrorLogIdReq().setId(id)))
//            .map(idReq -> getSelfProxy().delete(idReq))
//            .mapToInt(n -> n)
//            .sum();

        //Assert.isTrue(sum > 0, BATCH_DELETE_ACTION + BIZ_NAME + "失败");

        return simpleDao.deleteByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<AppErrorLogInfo> query(QueryAppErrorLogReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public AppErrorLogInfo findOne(QueryAppErrorLogReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    @Override
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

    protected int checkResult(int n, String action) {
        if (n > 1) {
            throw new DaoSecurityException("非法的" + action + "操作");
        }
        return n;
    }
}
