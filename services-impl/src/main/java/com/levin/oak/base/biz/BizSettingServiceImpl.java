package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;

import cn.hutool.core.bean.BeanUtil;

import javax.annotation.*;
import java.util.function.Supplier;

import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.service.support.SpringCacheEventListener;
import com.levin.commons.service.support.SpringCacheEventListener.Action;
import com.levin.oak.base.biz.bo.setting.StatSettingReq;
import com.levin.oak.base.biz.bo.setting.UpdateSettingValueReq;
import org.springframework.cache.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.tags.*;

import cn.hutool.core.lang.*;

import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.setting.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;

import com.levin.oak.base.services.*;
import org.springframework.util.StringUtils;


////////////////////////////////////
//自动导入列表

////////////////////////////////////

/**
 * 系统设置-业务服务实现类
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月30日 上午11:56:29, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[c2765b51f028d3f4c1e4331ed59da1d9], 请不要修改和删除此行内容。
 */

//@DubboService
@Service(PLUGIN_PREFIX + "BizSettingServiceImpl")

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizSettingServiceImpl", havingValue = "true", matchIfMissing = true)
@Slf4j

//@Valid只能用在controller，@Validated可以用在其他被spring管理的类上。
//@Validated
@Tag(name = E_Setting.BIZ_NAME + "-业务服务", description = "")
@CacheConfig(cacheNames = SettingService.CACHE_NAME, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")
public class BizSettingServiceImpl extends BaseService<BizSettingServiceImpl> implements BizSettingService {

    @Autowired
    SettingService settingService;

    @PostConstruct
    public void init() {

        //清除缓存
        settingService.clearAllCache();

        SpringCacheEventListener.add(
                //清除缓存
                (ctx, cache, action, key, value) -> cache.clear()
                //只要有人变更缓存，清除整个缓存
                , SettingService.CACHE_NAME, SettingService.CK_PREFIX, Action.Put, Action.Evict);

    }

    /**
     * 统计系统设置
     *
     * @param req
     * @param paging
     * @return
     */
    @Override
    public StatSettingReq.Result stat(StatSettingReq req, SimplePaging paging) {
        return simpleDao.findOneByQueryObj(req, paging);
    }

    /**
     * 更新或是清楚缓存
     *
     * @param tenantId
     * @param code
     */
    @Override
    @CacheEvict(condition = "#code != null", key = SettingService.CK_PREFIX_EXPR + "('' + #tenantId + #code)")
    public void clearCache(String tenantId, String code) {

    }

    @CacheEvict(condition = "#code != null", key = SettingService.CK_PREFIX_EXPR + "('' + #tenantId + #code)")
    @Override
    public boolean updateValue(String tenantId, String code, String valueContent) {

        Assert.notBlank(code, "[系统设置]的编码不能为空");

        return simpleDao.singleUpdateByQueryObj(
                new UpdateSettingValueReq()
                .setCode(code)
                .setValueContent(valueContent)
                .setTenantId(tenantId)
        );

    }

    @CachePut(unless = "#result == null", condition = "#code != null", key = SettingService.CK_PREFIX_EXPR + "('' + #tenantId + #code)")
    @Override
    public String getValue(String tenantId, String code, Supplier<SettingInfo> supplierForCreateIfNotaExist) {

        Assert.notBlank(code, "[系统设置]的编码不能为空");

        SettingInfo info = settingService.findOne(new QuerySettingReq().setCode(code).setTenantId(tenantId));

        if (info == null && supplierForCreateIfNotaExist != null) {

            info = supplierForCreateIfNotaExist.get();

            if (info != null) {
                info.setId(settingService.create(
                        BeanUtil.copyProperties(info, CreateSettingReq.class)
                                .setTenantId(tenantId)
                                .setId((StringUtils.hasText(tenantId) ? tenantId : "") + ":" + code)
                ));
            }
        }

        return info != null ? info.getValueContent() : null;
    }

}
