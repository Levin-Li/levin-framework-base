package com.levin.oak.base.services.user;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.conditional.ConditionalOn;
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
//import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.User;
import static com.levin.oak.base.entities.E_User.*;

import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.User.*;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import java.util.Date;
import com.levin.oak.base.entities.Org;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 用户-服务实现
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月24日 下午11:00:11, 代码生成哈希校验码：[1aed75ed98565f632aee54cd506b2a20]，请不要修改和删除此行内容。
 *
 */

@Service(PLUGIN_PREFIX + "UserService")
//@DubboService

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "UserService",havingValue = "", matchIfMissing = true)
//@Slf4j

//@Valid只能用在controller， @Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_User.SIMPLE_CLASS_NAME})
public class UserServiceImpl extends BaseService implements UserService {

    protected UserService getSelfProxy(){
        //return getSelfProxy(UserService.class);
        return getSelfProxy(UserServiceImpl.class);
    }

    @Operation(summary = CREATE_ACTION)
    @Transactional
    @Override
    public String create(CreateUserReq req){
        //保存自动先查询唯一约束，并给出错误信息
        User entity = simpleDao.create(req, true);
        return entity.getId();
    }

    @Operation(summary = BATCH_CREATE_ACTION)
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    @Transactional
    @Override
    public List<String> batchCreate(List<CreateUserReq> reqList){
        return reqList.stream().map(this::create).collect(Collectors.toList());
    }


    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.id")
    @Transactional
    public boolean update(UpdateUserReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleUpdateByQueryObj(req);
    }

    @Operation(summary = UPDATE_ACTION)
    @Override
    @CacheEvict(allEntries = true, condition = "#result > 0")
    public int update(SimpleUpdateUserReq setReq, QueryUserReq whereReq){
       return simpleDao.updateByQueryObj(setReq, whereReq);
    }

    @Operation(summary = BATCH_UPDATE_ACTION)
    @Transactional
    @Override
    @CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#reqList)  && #result > 0")
    public int batchUpdate(List<UpdateUserReq> reqList){
        //@Todo 优化批量提交
        return reqList.stream().map(req -> getSelfProxy().update(req)).mapToInt(n -> n ? 1 : 0).sum();
    }

    @Operation(summary = DELETE_ACTION)
    @Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX + "#req.tenantId + #req.id")
    @Transactional
    public boolean delete(UserIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.singleDeleteByQueryObj(req);
    }

    @Operation(summary = BATCH_DELETE_ACTION)
    @Transactional
    @Override
    @CacheEvict(allEntries = true, condition = "@spelUtils.isNotEmpty(#req.idList) && #result > 0")
    public int batchDelete(DeleteUserReq req){
        //@Todo 优化批量提交
        return Stream.of(req.getIdList())
            .map(id -> simpleDao.copy(req, new UserIdReq().setId(id)))
            .map(idReq -> getSelfProxy().delete(idReq))
            .mapToInt(n -> n ? 1 : 0)
            .sum();
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public PagingData<UserInfo> query(QueryUserReq req, Paging paging) {
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Operation(summary = QUERY_ACTION + "-指定列", description = "通常用于字段过多的情况，提升性能")
    public PagingData<SimpleUserInfo> simpleQuery(QueryUserReq req, Paging paging){
        return simpleDao.findPagingDataByQueryObj(SimpleUserInfo.class, req, paging);
    }

    @Operation(summary = STAT_ACTION)
    @Override
    public PagingData<StatUserReq.Result> stat(StatUserReq req , Paging paging){
        return simpleDao.findPagingDataByQueryObj(req, paging);
    }

    @Override
    @Operation(summary = STAT_ACTION)
    public int count(QueryUserReq req){
        return (int) simpleDao.countByQueryObj(req);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX + "#id")
    public UserInfo findById(String id) {
        return findById(new UserIdReq().setId(id));
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    @Override
    @CachePut(unless = "#result == null" , condition = "@spelUtils.isNotEmpty(#req.id)" , key = CK_PREFIX + "#req.tenantId + #req.id")
    public UserInfo findById(UserIdReq req) {
        Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
        return simpleDao.findUnique(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public UserInfo findOne(QueryUserReq req){
        return simpleDao.findOneByQueryObj(req);
    }

    @Operation(summary = QUERY_ACTION)
    @Override
    public UserInfo findUnique(QueryUserReq req){
        return simpleDao.findUnique(req);
    }

    @Override
    @Operation(summary = CLEAR_CACHE_ACTION, description = "缓存Key通常是ID")
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#key)", key = CK_PREFIX + "#key")
    public void clearCache(Object key) {
    }

}
