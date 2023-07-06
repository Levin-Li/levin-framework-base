package com.levin.oak.base.biz;

import cn.hutool.cache.CacheUtil;
import cn.hutool.core.map.MapUtil;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.services.setting.req.QuerySettingReq;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.dromara.sms4j.provider.enumerate.SupplierType;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

//@Service(PLUGIN_PREFIX + "Sms4jSmsSender")
@DubboService
@ConditionalOnClass({SmsFactory.class, SmsBlend.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SmsSender", matchIfMissing = true)
@ConditionalOnMissingBean(SmsSender.class)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "SmsSender"})
public class Sms4jSmsSender
        implements SmsSender {

    private static final String CACHE_NAME = Sms4jSmsSender.class.getName();

    public static final String CFG_CODE = "短信通道配置";

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    SettingService settingService;

    RMapCache<String, Object> mapCache = null;

    Map<String, SmsBlend> smsBlendMap = MapUtil.newConcurrentHashMap();

    @PostConstruct
    void init() {
        mapCache = redissonClient.getMapCache(CACHE_NAME);
        log.info("短信发送服务启用-" + Sms4jSmsSender.class.getName());
    }

    protected SettingInfo getSmsSetting(String tenantId, String appId) {

        if (!StringUtils.hasText(appId)) {
            appId = "";
        }

        String code = CFG_CODE + appId;

        SettingInfo info = settingService.findUnique(new QuerySettingReq().setCode(code).setTenantId(tenantId));

        return info;
    }

    /**
     * 发送验证码
     * <p>
     * 如果发送成功返回code，否则返回错误原因
     *
     * @param tenantId
     * @param appId
     * @param phone
     * @param code
     * @return code or errInfo 如果发送成功返回code，否则返回错误原因
     */
    @Override
    public String sendCode(String tenantId, String appId, String phone, String code) {

        if (!StringUtils.hasText(appId)) {
            appId = "";
        }

        // String key = tenantId + appId;

        //@todo 后期支持从数据库配置

        SmsFactory.createSmsBlend(SupplierType.TENCENT).sendMessage(phone, code);

        return code;
    }

}
