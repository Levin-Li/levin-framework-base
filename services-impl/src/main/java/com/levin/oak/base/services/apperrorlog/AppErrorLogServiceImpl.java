package com.levin.oak.base.services.apperrorlog;

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
import org.apache.dubbo.config.annotation.*;

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
 * 应用错误日志-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月24日 15:26:14, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[fb7c545939ccb12713c68a2c36ecc290], 请不要修改和删除此行内容。
 */

@Service(PLUGIN_PREFIX + "AppErrorLogService")
@DubboService

@ConditionalOnMissingBean({AppErrorLogService.class}) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AppErrorLogService", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_AppErrorLog.BIZ_NAME, description = E_AppErrorLog.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_AppErrorLog.SIMPLE_CLASS_NAME})
public class AppErrorLogServiceImpl extends BaseService implements AppErrorLogService {

    protected AppErrorLogService getSelfProxy(){
        return getSelfProxy(AppErrorLogService.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public Long create(CreateAppErrorLogReq req){
        //保存自动先查询唯一约束，并给出错误信息
        AppErrorLog entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public List<Long> batchCreate(List<CreateAppErrorLogReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    //@Cacheable(condition = "#id != null", unless = "#result == null ", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#id")
    public AppErrorLogInfo findById(Long id) {
        return findById(new AppErrorLogIdReq().setId(id));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    //@CachePut(unless = "#result == null" , condition = "#req.id != null" , key = E_AppErrorLog.CACHE_KEY_PREFIX + "#req.id")
    public AppErrorLogInfo findById(AppErrorLogIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    //@CacheEvict(condition = "#req.id != null", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean update(UpdateAppErrorLogReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int batchUpdate(List<UpdateAppErrorLogReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    //@CacheEvict(condition = "#req.id != null", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean delete(AppErrorLogIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public int batchDelete(DeleteAppErrorLogReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new AppErrorLogIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n ? 1 : 0)
            .sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<AppErrorLogInfo> query(QueryAppErrorLogReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    /**
     * 指定选择列查询
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    public PagingData<SimpleAppErrorLogInfo> simpleQuery(QueryAppErrorLogReq req, Paging paging){
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    /**
     * 简单统计
     *
     * @param req
     * @param paging 分页设置，可空
     * @return pagingData 分页数据
     */
    @Operation(summary = STAT_ACTION)
    @Override
    public PagingData<StatAppErrorLogReq.Result> stat(StatAppErrorLogReq req , Paging paging){
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Override
    @Operation(summary = STAT_ACTION)
    public int count(QueryAppErrorLogReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public AppErrorLogInfo findOne(QueryAppErrorLogReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public AppErrorLogInfo findUnique(QueryAppErrorLogReq req){
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_AppErrorLog.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
