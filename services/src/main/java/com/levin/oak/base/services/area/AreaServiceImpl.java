package com.levin.oak.base.services.area;

import com.levin.commons.dao.DaoSecurityException;
import com.levin.commons.dao.Paging;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.E_Area;
import com.levin.oak.base.services.BaseService;
import com.levin.oak.base.services.area.info.AreaInfo;
import com.levin.oak.base.services.area.req.*;
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
 * 区域-服务实现
 *
 * @author auto gen by simple-dao-codegen 2022-4-2 19:44:59
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "AreaService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AreaService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Area.BIZ_NAME, description = E_Area.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + E_Area.SIMPLE_CLASS_NAME})
public class AreaServiceImpl extends BaseService implements AreaService {

    @Autowired
    private SimpleDao simpleDao;

    protected AreaService getSelfProxy() {
        return getSelfProxy(AreaService.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Override
    public String create(CreateAreaReq req) {
        Area entity = simpleDao.create(req);
        return entity.getCode();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public List<String> batchCreate(List<CreateAreaReq> reqList) {
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(condition = "#code != null", unless = "#result == null ", key = E_Area.CACHE_KEY_PREFIX + "#code")
    public AreaInfo findById(String code) {
        return findById(new AreaIdReq().setCode(code));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //只更新缓存
    @CachePut(unless = "#result == null", condition = "#req.code != null", key = E_Area.CACHE_KEY_PREFIX + "#req.code")
    public AreaInfo findById(AreaIdReq req) {
        Assert.notNull(req.getCode(), BIZ_NAME + " code 不能为空");
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.code != null", key = E_Area.CACHE_KEY_PREFIX + "#req.code")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int update(UpdateAreaReq req) {

        Assert.notNull(req.getCode(), BIZ_NAME + " code 不能为空");

        return checkResult(simpleDao.updateByQueryObj(req), UPDATE_ACTION);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchUpdate(List<UpdateAreaReq> reqList) {
        //@Todo 优化批量提交
        int sum = reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n).sum();

        //Assert.isTrue(sum > 0, BATCH_UPDATE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "#req.code != null", key = E_Area.CACHE_KEY_PREFIX + "#req.code")
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    public int delete(AreaIdReq req) {

        Assert.notNull(req.getCode(), BIZ_NAME + " code 不能为空");

        return checkResult(simpleDao.deleteByQueryObj(req), DELETE_ACTION);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Override
    public int batchDelete(DeleteAreaReq req) {
        //@Todo 优化批量提交
        int sum = Stream.of(req.getCodeList())
                .map(code -> simpleDao.copy(req, new AreaIdReq().setCode(code)))
                .map(idReq -> getSelfProxy().delete(idReq))
                .mapToInt(n -> n)
                .sum();

        //Assert.isTrue(sum > 0, BATCH_DELETE_ACTION + BIZ_NAME + "失败");

        return sum;
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<AreaInfo> query(QueryAreaReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public AreaInfo findOne(QueryAreaReq req) {
        return simpleDao.findOneByQueryObj(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "#key != null && #key.toString().trim().length() > 0", key = E_Area.CACHE_KEY_PREFIX + "#key")
    public void clearCache(Object key) {
    }

    protected int checkResult(int n, String action) {
        if (n > 1) {
            throw new DaoSecurityException("非法的" + action + "操作");
        }
        return n;
    }
}
