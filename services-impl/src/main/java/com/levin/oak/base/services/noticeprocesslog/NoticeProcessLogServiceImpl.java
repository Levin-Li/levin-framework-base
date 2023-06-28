package com.levin.oak.base.services.noticeprocesslog;

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
import com.levin.oak.base.entities.NoticeProcessLog;

import com.levin.oak.base.services.noticeprocesslog.req.*;
import com.levin.oak.base.services.noticeprocesslog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  通知处理日志-服务实现
 *
 *  @author auto gen by simple-dao-codegen 2023年6月28日 上午9:18:57
 *  代码生成哈希校验码：[98d7e33e6ea776d53e2c600b68ec4cb1]
 */

//@Service(PLUGIN_PREFIX + "NoticeProcessLogService")
@DubboService
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "NoticeProcessLogService", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_NoticeProcessLog.BIZ_NAME, description = E_NoticeProcessLog.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_NoticeProcessLog.SIMPLE_CLASS_NAME})
public class NoticeProcessLogServiceImpl extends BaseService implements NoticeProcessLogService {

    protected NoticeProcessLogService getSelfProxy(){
        return getSelfProxy(NoticeProcessLogService.class);
    }

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public String create(CreateNoticeProcessLogReq req){
        NoticeProcessLog entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<String> batchCreate(List<CreateNoticeProcessLogReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    //@Cacheable(condition = "#id != null", unless = "#result == null ", key = E_NoticeProcessLog.CACHE_KEY_PREFIX + "#id")
    public NoticeProcessLogInfo findById(String id) {
        return findById(new NoticeProcessLogIdReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    //@CachePut(unless = "#result == null" , condition = "#req.id != null" , key = E_NoticeProcessLog.CACHE_KEY_PREFIX + "#req.id")
    public NoticeProcessLogInfo findById(NoticeProcessLogIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    //@CacheEvict(condition = "#req.id != null", key = E_NoticeProcessLog.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public boolean update(UpdateNoticeProcessLogReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateNoticeProcessLogReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n?1:0).sum();
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    //@CacheEvict(condition = "#req.id != null", key = E_NoticeProcessLog.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public boolean delete(NoticeProcessLogIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteNoticeProcessLogReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new NoticeProcessLogIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n?1:0)
            .sum();
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<NoticeProcessLogInfo> query(QueryNoticeProcessLogReq req, Paging paging) {
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
    public PagingData<StatNoticeProcessLogReq.Result> stat(StatNoticeProcessLogReq req , Paging paging){
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
    public int count(QueryNoticeProcessLogReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public NoticeProcessLogInfo findOne(QueryNoticeProcessLogReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public NoticeProcessLogInfo findUnique(QueryNoticeProcessLogReq req){
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_NoticeProcessLog.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
