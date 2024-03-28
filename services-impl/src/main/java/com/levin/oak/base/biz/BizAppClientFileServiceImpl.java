package com.levin.oak.base.biz;

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
import org.springframework.validation.annotation.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
//import org.springframework.dao.*;

import javax.persistence.PersistenceException;
import cn.hutool.core.lang.*;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

//import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AppClientFile;

import com.levin.oak.base.services.appclientfile.*;
import com.levin.oak.base.biz.bo.appclientfile.*;
import static com.levin.oak.base.services.appclientfile.AppClientFileService.*;
import com.levin.oak.base.services.appclientfile.req.*;
import com.levin.oak.base.services.appclientfile.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 *  客户端文件-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月29日 上午12:51:48, 代码生成哈希校验码：[207a1ad6cb7f1a2c1c76159cc234fdc7]，请不要修改和删除此行内容。
 *
 */

// 事务隔离级别
// Propagation.REQUIRED：默认的事务传播级别，它表示如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
// Propagation.SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
// Propagation.MANDATORY：（mandatory：强制性）如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
// Propagation.REQUIRES_NEW：表示创建一个新的事务，如果当前存在事务，则把当前事务挂起。也就是说不管外部方法是否开启事务，Propagation.REQUIRES_NEW 修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
// Propagation.NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
// Propagation.NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
// Propagation.NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于 PROPAGATION_REQUIRED。

@Service(PLUGIN_PREFIX + "BizAppClientFileService")

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizAppClientFileService", havingValue = "true", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_AppClientFile.BIZ_NAME + "-业务服务", description = "")

//*** 提示 *** 如果要注释缓存注解的代码可以在实体类上加上@javax.persistence.Cacheable(false)，然后重新生成代码
@CacheConfig(cacheNames = AppClientFileService.CACHE_NAME, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")

public class BizAppClientFileServiceImpl extends BaseService<BizAppClientFileServiceImpl> implements BizAppClientFileService {

    @Autowired
    AppClientFileService appClientFileService;


    /** 参考示例
    @Operation(summary = CREATE_ACTION)
    @Transactional
    //@Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#result)", key = CK_PREFIX_EXPR + "#result") //创建也清除缓存，防止空值缓存的情况
    public String create(CreateAppClientFileReq req){
        return appClientFileService.create(req);
    }

    @Operation(summary = VIEW_DETAIL_ACTION)
    //@Override
    //Spring 缓存变量可以使用Spring 容器里面的bean名称，SpEL支持使用@符号来引用Bean。
    @Cacheable(unless = "#result == null ", condition = "@spelUtils.isNotEmpty(#id)", key = CK_PREFIX_EXPR + "#id")
    public AppClientFileInfo findById(String id) {
        return appClientFileService.findById(id);
    }

    //调用本方法会导致不会对租户ID经常过滤，如果需要调用方对租户ID进行核查
    @Operation(summary = VIEW_DETAIL_ACTION)
    //@Override
    @Cacheable(unless = "#result == null" , condition = "@spelUtils.isNotEmpty(#req.id)" , key = CK_PREFIX_EXPR + "#req.id") //#req.tenantId + 
    public AppClientFileInfo findById(AppClientFileIdReq req) {
        return appClientFileService.findById(req);
    }

    @Operation(summary = UPDATE_ACTION)
    //@Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX_EXPR + "#req.id")//, beforeInvocation = true
    @Transactional
    public boolean update(UpdateAppClientFileReq req) {
        return appClientFileService.update(req);
    }

    @Operation(summary = DELETE_ACTION)
    //@Override
    @CacheEvict(condition = "@spelUtils.isNotEmpty(#req.id) && #result", key = CK_PREFIX_EXPR + "#req.id") //#req.tenantId +  , beforeInvocation = true
    @Transactional
    public boolean delete(AppClientFileIdReq req) {
        return appClientFileService.delete(req);
    }

    */

    /**
    * 统计
    *
    * @param req
    * @param paging 分页设置，可空
    * @return StatAppClientFileReq.Result
    */
    @Operation(summary = STAT_ACTION)
    public StatAppClientFileReq.Result stat(StatAppClientFileReq req, Paging paging){
        return simpleDao.findOneByQueryObj(req, paging);
    }

}
