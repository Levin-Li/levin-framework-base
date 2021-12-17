package com.levin.oak.base.services.org;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;
import java.util.stream.*;
import org.springframework.cache.annotation.*;
import org.springframework.transaction.annotation.*;
import org.springframework.util.*;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Org;

import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;

import com.levin.oak.base.*;


////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import com.levin.oak.base.entities.Org.*;
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import com.levin.oak.base.services.org.info.*;
    import com.levin.oak.base.entities.Org;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
 *  机构-服务实现
 *
 *@author auto gen by simple-dao-codegen 2021-12-17 11:53:24
 *
 */

//@Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@Service(PLUGIN_PREFIX + "OrgService")
@Slf4j
//@Validated
@Tag(name = E_Org.BIZ_NAME, description = E_Org.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + E_Org.SIMPLE_CLASS_NAME})
public class OrgServiceImpl implements OrgService {

    @Autowired
    private SimpleDao simpleDao;

    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    @Override
    public Long create(CreateOrgReq req){
        Org entity = simpleDao.create(req);
        return entity.getId();
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Long> batchCreate(List<CreateOrgReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    @Override
    //Srping 4.3提供了一个sync参数。是当缓存失效后，为了避免多个请求打到数据库,系统做了一个并发控制优化，同时只有一个线程会去数据库取数据其它线程会被阻塞。
    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_Org.CACHE_KEY_PREFIX + "#id")
    public OrgInfo findById(Long id) {
        return simpleDao.findOneByQueryObj(new QueryOrgReq().setId(id));
    }

    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "#req.id != null", key = E_Org.CACHE_KEY_PREFIX + "#req.id")    
    public int update(UpdateOrgReq req) {
        return simpleDao.updateByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    @Transactional(rollbackFor = Exception.class)
    @Override
    @CacheEvict(condition = "#reqList != null && #reqList.size() > 0", allEntries = true)
    public List<Integer> batchUpdate(List<UpdateOrgReq> reqList){
        return reqList.stream().map(this::update).collect(Collectors.toList());
    }

    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    @Override
    @Caching(evict = {  //尽量不用调用批量删除，会导致缓存清空
        @CacheEvict(condition = "#req.id != null", key = E_Org.CACHE_KEY_PREFIX + "#req.id"),
        @CacheEvict(condition = "#req.idList != null && #req.idList.length > 0", allEntries = true),
    })                    
    public int delete(DeleteOrgReq req) {
        return simpleDao.deleteByQueryObj(req);
    }

    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    @Override
    public PagingData<OrgInfo> query(QueryOrgReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }
}
