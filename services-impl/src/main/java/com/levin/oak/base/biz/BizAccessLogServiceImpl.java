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
import org.springframework.dao.*;

import javax.persistence.PersistenceException;
import cn.hutool.core.lang.*;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AccessLog;

import com.levin.oak.base.services.accesslog.*;
import com.levin.oak.base.services.accesslog.req.*;
import com.levin.oak.base.services.accesslog.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;

////////////////////////////////////
// 自动导入列表
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 访问日志-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 上午2:26:21, 代码生成哈希校验码：[0b2c0c0c098465466b51ce857c78f6f5]，请不要修改和删除此行内容。
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

@Service(PLUGIN_PREFIX + "BizAccessLogServiceImpl")
@DubboService
@ConditionalOnMissingBean({BizAccessLogService.class}) // 默认只有在无对应服务才启用
@ConditionalOnProperty(
        prefix = PLUGIN_PREFIX,
        name = "BizAccessLogServiceImpl",
        matchIfMissing = true)
@Slf4j

// @Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
// @Validated
@Tag(name = E_AccessLog.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_AccessLog.SIMPLE_CLASS_NAME})
public class BizAccessLogServiceImpl extends BaseService implements BizAccessLogService {

    @Autowired AccessLogService accessLogService;

    protected BizAccessLogServiceImpl getSelfProxy() {
        return getSelfProxy(BizAccessLogServiceImpl.class);
    }

    // @Transactional(rollbackFor = RuntimeException.class)
    // public void update(UpdateReq req){
    //    accessLogService.update(req);
    // }

}
