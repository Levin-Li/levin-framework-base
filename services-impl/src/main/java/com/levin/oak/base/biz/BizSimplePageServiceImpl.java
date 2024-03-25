package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import javax.annotation.*;
import java.util.*;
import java.util.stream.*;

import com.levin.oak.base.biz.bo.simplepage.StatSimplePageReq;
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
import org.springframework.dao.*;

import javax.persistence.PersistenceException;

import cn.hutool.core.lang.*;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimplePage;

import com.levin.oak.base.services.simplepage.*;
import com.levin.oak.base.services.simplepage.req.*;
import com.levin.oak.base.services.simplepage.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConst;

import java.util.List;
import java.util.Date;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 简单页面-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月13日 下午4:53:28, 代码生成哈希校验码：[17815ed93deb05a19d8cbf2b899be2b0]，请不要修改和删除此行内容。
 */

// 事务隔离级别
// Propagation.REQUIRED：默认的事务传播级别，它表示如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。
// Propagation.SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
// Propagation.MANDATORY：（mandatory：强制性）如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
// Propagation.REQUIRES_NEW：表示创建一个新的事务，如果当前存在事务，则把当前事务挂起。也就是说不管外部方法是否开启事务，Propagation.REQUIRES_NEW
// 修饰的内部方法会新开启自己的事务，且开启的事务相互独立，互不干扰。
// Propagation.NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
// Propagation.NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
// Propagation.NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于 PROPAGATION_REQUIRED。

@Service(PLUGIN_PREFIX + "BizSimplePageServiceImpl")
//@DubboService
@ConditionalOnProperty(
        prefix = PLUGIN_PREFIX,
        name = "BizSimplePageServiceImpl",
        matchIfMissing = true)
@Slf4j

// @Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
// @Validated
@Tag(name = E_SimplePage.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_SimplePage.SIMPLE_CLASS_NAME}, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")
public class BizSimplePageServiceImpl extends BaseService<BizSimplePageServiceImpl> implements BizSimplePageService {

    @Autowired
    SimplePageService simplePageService;

    // @Transactional(rollbackFor = RuntimeException.class)
    // public void update(UpdateReq req){
    //    simplePageService.update(req);
    // }


    @Override
    public SimplePageInfo findOnePage(QuerySimplePageReq req) {

        return simpleDao.selectFrom(SimplePage.class)
//                  .disableEmptyValueFilter()
                .eq(E_SimpleEntity.type, req.getType())
                .eq(E_SimpleEntity.category, req.getCategory())
                .eq(E_SimpleEntity.path, req.getPath())
                .isNullOrEq(E_SimpleEntity.tenantId, req.getTenantId())
                //排序,本租户优先
                .orderByStatement(OrderBy.Type.Desc, new Case()
                        .when(E_SimpleEntity.tenantId + " IS NULL", "0")
                        .elseExpr("1")
                        .toString("(", ")")
                ).orderByAsc(E_SimpleEntity.orderCode)
                .findOne(SimplePageInfo.class);

    }

    @Override
    public StatSimplePageReq.Result stat(StatSimplePageReq req, SimplePaging paging) {
        return simpleDao.findOneByQueryObj(req, paging);
    }
}
