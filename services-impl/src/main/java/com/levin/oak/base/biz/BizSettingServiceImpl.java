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

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.dao.*;

import javax.persistence.PersistenceException;

import cn.hutool.core.lang.*;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;

import org.apache.dubbo.config.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.services.setting.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Setting.*;

import java.util.Date;
////////////////////////////////////

/**
 * 系统设置-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月30日 上午11:56:29, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[c2765b51f028d3f4c1e4331ed59da1d9], 请不要修改和删除此行内容。
 */

@DubboService
//@Service(PLUGIN_PREFIX + "BizSettingServiceImpl")

@ConditionalOnMissingBean({BizSettingService.class}) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizSettingServiceImpl", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_Setting.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_Setting.SIMPLE_CLASS_NAME})
public class BizSettingServiceImpl extends BaseService implements BizSettingService {

    @Autowired
    SettingService settingService;

    protected BizSettingServiceImpl getSelfProxy() {
        return getSelfProxy(BizSettingServiceImpl.class);
    }

    //示例方法
    //@Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    //@Override
    //@CacheEvict(condition = "#req.id != null", key = E_Setting.CACHE_KEY_PREFIX + "#req.id")
    //@Transactional(rollbackFor = {PersistenceException.class, DataAccessException.class})
    //public boolean update(UpdateSettingReq req) {
    //    Assert.notNull(req.getId(), BIZ_NAME + " id 不能为空");
    //    return simpleDao.singleUpdateByQueryObj(req);
    //}


    @PostConstruct
    public void init() {

    }

    @CacheEvict(condition = "#code != null", key = E_Setting.CACHE_KEY_PREFIX + "(#tenantId + #code)")
    @Override
    public boolean updateValue(String tenantId, String code, String valueContent) {
        return simpleDao.updateTo(Setting.class)
                .eq(E_Setting.tenantId, tenantId)
                .eq(E_Setting.code, code)
                .set(E_Setting.valueContent, valueContent)
                .singleUpdate();
    }

    @CachePut(unless = "#result == null", condition = "#code != null", key = E_Setting.CACHE_KEY_PREFIX + "(#tenantId + #code)")
    @Override
    public String getValue(String tenantId, String code) {
        SettingInfo info = settingService.findUnique(new QuerySettingReq().setCode(code).setTenantId(tenantId));
        return info != null ? info.getValueContent() : null;
    }

}
