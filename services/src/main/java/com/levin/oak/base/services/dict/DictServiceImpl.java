package com.levin.oak.base.services.dict;

import com.levin.commons.dao.DaoSecurityException;
import com.levin.commons.dao.Paging;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.entities.Dict;
import com.levin.oak.base.entities.E_Dict;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.dict.info.DictInfo;
import com.levin.oak.base.services.dict.req.*;
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
 * 字典-服务实现
 *
 * @author auto gen by simple-dao-codegen 2022-4-2 19:44:58
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "DictService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "DictService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Dict.BIZ_NAME, title = E_Dict.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + E_Dict.SIMPLE_CLASS_NAME})
public class DictServiceImpl extends BaseService implements DictService {

    @Autowired
    private SimpleDao simpleDao;

    protected DictService getSelfProxy() {
        return getSelfProxy(DictService.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Override
    public String create(CreateDictReq req) {
        Dict entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<String> batchCreate(List<CreateDictReq> reqList) {
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(condition = "#id != null", unless = "#result == null ", key = E_Dict.CACHE_KEY_PREFIX + "#id")
    public DictInfo findById(String id) {
        return findById(new DictIdReq().setId(id));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null", condition = "#req.id != null", key = E_Dict.CACHE_KEY_PREFIX + "#req.id")
    public DictInfo findById(DictIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Dict.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateDictReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return checkResult(simpleDao.updateByQueryObj(req), UPDATE_ACTION);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateDictReq> reqList) {
        //@Todo 优化批量提交
        int sum = reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n).sum();

        //Assert.isTrue(sum > 0, BATCH_UPDATE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Dict.CACHE_KEY_PREFIX + "#req.id")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int delete(DictIdReq req) {

        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");

        return checkResult(simpleDao.deleteByQueryObj(req), DELETE_ACTION);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteDictReq req) {
        //@Todo 优化批量提交
        int sum = Stream.of(req.getIdList())
                .map(id -> simpleDao.copy(req, new DictIdReq().setId(id)))
                .map(idReq -> getSelfProxy().delete(idReq))
                .mapToInt(n -> n)
                .sum();

        //Assert.isTrue(sum > 0, BATCH_DELETE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<DictInfo> query(QueryDictReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public DictInfo findOne(QueryDictReq req) {
        return simpleDao.findOneByQueryObj(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, title = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_Dict.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

    protected int checkResult(int n, String action) {
        if (n > 1) {
            throw new DaoSecurityException("非法的" + action + "操作");
        }
        return n;
    }
}
