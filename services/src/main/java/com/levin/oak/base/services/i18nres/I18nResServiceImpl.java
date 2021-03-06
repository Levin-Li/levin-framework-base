package com.levin.oak.base.services.i18nres;

import com.levin.commons.dao.DaoSecurityException;
import com.levin.commons.dao.Paging;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.entities.E_I18nRes;
import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.i18nres.info.I18nResInfo;
import com.levin.oak.base.services.i18nres.req.*;
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
 * 国际化资源-服务实现
 *
 * @author auto gen by simple-dao-codegen 2022-4-2 19:44:59
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "I18nResService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "I18nResService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_I18nRes.BIZ_NAME, description = E_I18nRes.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + E_I18nRes.SIMPLE_CLASS_NAME})
public class I18nResServiceImpl extends BaseService implements I18nResService {

    @Autowired
    private SimpleDao simpleDao;

    protected I18nResService getSelfProxy() {
        return getSelfProxy(I18nResService.class);
    }

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Override
    public Long create(CreateI18nResReq req) {
        I18nRes entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<Long> batchCreate(List<CreateI18nResReq> reqList) {
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_I18nRes.CACHE_KEY_PREFIX + "#id")
    public I18nResInfo findById(Long id) {
        return findById(new I18nResIdReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null", condition = "#req.id != null", key = E_I18nRes.CACHE_KEY_PREFIX + "#req.id")
    public I18nResInfo findById(I18nResIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_I18nRes.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateI18nResReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return checkResult(simpleDao.updateByQueryObj(req), UPDATE_ACTION);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateI18nResReq> reqList) {
        //@Todo 优化批量提交
        int sum = reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n).sum();

        //Assert.isTrue(sum > 0, BATCH_UPDATE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_I18nRes.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int delete(I18nResIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return checkResult(simpleDao.deleteByQueryObj(req), DELETE_ACTION);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteI18nResReq req) {
        //@Todo 优化批量提交
        int sum = Stream.of(req.getIdList())
                .map(id -> simpleDao.copy(req, new I18nResIdReq().setId(id)))
                .map(idReq -> getSelfProxy().delete(idReq))
                .mapToInt(n -> n)
                .sum();

        //Assert.isTrue(sum > 0, BATCH_DELETE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<I18nResInfo> query(QueryI18nResReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public I18nResInfo findOne(QueryI18nResReq req) {
        return simpleDao.findOneByQueryObj(req);
    }

    @Override
    @Operation(tags = {BIZ_NAME}, summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_I18nRes.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

    protected int checkResult(int n, String action) {
        if (n > 1) {
            throw new DaoSecurityException("非法的" + action + "操作");
        }
        return n;
    }
}
