package com.levin.oak.base.services.notice;

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


import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Notice;

import com.levin.oak.base.services.notice.req.*;
import com.levin.oak.base.services.notice.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
////////////////////////////////////

/**
 *  通知-服务实现
 *
 *@author auto gen by simple-dao-codegen 2022-6-20 16:50:12
 *
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "NoticeService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "NoticeService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Notice.BIZ_NAME, description = E_Notice.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + E_Notice.SIMPLE_CLASS_NAME})
public class NoticeServiceImpl extends BaseService implements NoticeService {

    @Resource
    private SimpleDao simpleDao;

    protected NoticeService getSelfProxy(){
        return getSelfProxy(NoticeService.class);
    }

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public String create(CreateNoticeReq req){
        Notice entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<String> batchCreate(List<CreateNoticeReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_Notice.CACHE_KEY_PREFIX + "#id")
    public NoticeInfo findById(String id) {
        return findById(new NoticeIdReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null" , condition = "#req.id != null" , key = E_Notice.CACHE_KEY_PREFIX + "#req.id")
    public NoticeInfo findById(NoticeIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Notice.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateNoticeReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return checkResult(simpleDao.updateByQueryObj(req), UPDATE_ACTION);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateNoticeReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n).sum();
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Notice.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int delete(NoticeIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return checkResult(simpleDao.deleteByQueryObj(req), DELETE_ACTION);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteNoticeReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new NoticeIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n)
            .sum();
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<NoticeInfo> query(QueryNoticeReq req, Paging paging) {
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
    public PagingData<StatNoticeReq.Result> stat(StatNoticeReq req , Paging paging){
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public NoticeInfo findOne(QueryNoticeReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    /**
     * 统计记录数
     *
     * @param req
     * @return record count
     */
    @Override
    @Operation(tags = {BIZ_NAME}, summary = STAT_ACTION)
    public int count(QueryNoticeReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Override
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_Notice.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

    protected int checkResult(int n, String action) {
        if (n > 1) {
            throw new DaoSecurityException("非法的" + action + "操作");
        }
        return n;
    }
}
