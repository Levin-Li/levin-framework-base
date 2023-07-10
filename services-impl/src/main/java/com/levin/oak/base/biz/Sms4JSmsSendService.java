package com.levin.oak.base.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.google.gson.Gson;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.sms4j.aliyun.config.AlibabaConfig;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.universal.SupplierConfig;
import org.dromara.sms4j.cloopen.config.CloopenConfig;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.dromara.sms4j.emay.config.EmayConfig;
import org.dromara.sms4j.huawei.config.HuaweiConfig;
import org.dromara.sms4j.jdcloud.config.JdCloudConfig;
import org.dromara.sms4j.provider.enumerate.SupplierType;
import org.dromara.sms4j.tencent.config.TencentConfig;
import org.dromara.sms4j.yunpian.config.YunpianConfig;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

//@Service(PLUGIN_PREFIX + "SmsSendService")
@DubboService
@ConditionalOnClass({SmsFactory.class, SmsBlend.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SmsSendService", matchIfMissing = true)
@ConditionalOnMissingBean(SmsSendService.class)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "SmsSendService"})
public class Sms4JSmsSendService
        implements SmsSendService {

    private static final String CACHE_NAME = Sms4JSmsSendService.class.getName();

    public static final String CFG_CODE = "短信通道配置";

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    SettingService settingService;

    @Autowired
    BizSettingService bizSettingService;

    RMapCache<String, Object> mapCache = null;

    Map<String, SmsBlend> smsBlendMap = MapUtil.newConcurrentHashMap();

    private static Gson gson = new Gson();

    private static Map<SupplierType, Class<? extends SupplierConfig>> configClass = new ConcurrentHashMap<>();

    static {
        configClass.put(SupplierType.ALIBABA, AlibabaConfig.class);
        configClass.put(SupplierType.HUAWEI, HuaweiConfig.class);
        configClass.put(SupplierType.TENCENT, TencentConfig.class);
        configClass.put(SupplierType.JD_CLOUD, JdCloudConfig.class);
        configClass.put(SupplierType.EMAY, EmayConfig.class);
        configClass.put(SupplierType.CLOOPEN, CloopenConfig.class);
        configClass.put(SupplierType.YUNPIAN, YunpianConfig.class);
    }

    @PostConstruct
    void init() {
        mapCache = redissonClient.getMapCache(CACHE_NAME);
        log.info("短信发送服务启用-" + Sms4JSmsSendService.class.getName());
    }

    protected Map<String, Object> getSmsSetting(String tenantId, String appId) {

        if (!StringUtils.hasText(appId)) {
            appId = "";
        }

        String code = CFG_CODE + "|" + appId;

        String value = bizSettingService.getValue(tenantId, code, () -> {

                    Map<String, Object> config = MapUtil.builder("ref_doc", (Object) "Json格式，具体配置参考文档：https://wind.kim/doc/supplier/")
                            .put("channelType", SupplierType.ALIBABA.name())
                            .build();
                    return new SettingInfo().setCategoryName(CFG_CODE)
                            .setCode(code)
                            .setValueType(Setting.ValueType.Json)
                            .setInputPlaceholder("Json格式,channelType属性配置通道类型")
                            .setValueContent(gson.toJson(config))
                            .setName(CFG_CODE)
                            .setRemark("内容必须为Json格式，channelType属性配置通道类型：" +
                                    configClass.keySet()
                                    + "\n具体配置参考文档：https://wind.kim/doc/supplier/");
                }
        );

        return (Map<String, Object>) (StringUtils.hasText(value) ? gson.fromJson(value, Map.class) : Collections.emptyMap());
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

        Map<String, Object> smsSetting = getSmsSetting(tenantId, appId);

        if (!smsSetting.containsKey("channelType")) {
            SmsFactory.createSmsBlend(SupplierType.ALIBABA).sendMessage(phone, code);
        } else {
            String channelType = (String) smsSetting.getOrDefault("channelType", SupplierType.ALIBABA.name());

            Assert.notBlank(channelType, "系统配置[" + CFG_CODE + "]中通道类型(channelType)属性必须指定");

            SupplierType supplierType = Stream.of(SupplierType.values()).filter(v -> v.name().equalsIgnoreCase(channelType)).findFirst().orElse(null);

            Assert.notNull(supplierType, "不支持的短信通道:{}", channelType);

//            发送固定消息模板短信   https://wind.kim/doc/api/#%F0%9F%93%A8%E6%A0%87%E5%87%86%E7%9F%AD%E4%BF%A1
//            此方法将使用配置文件中预设的短信模板进行短信发送 该方法指定的模板变量只能存在一个（配置文件中） 如使用的是腾讯的短信，参数字符串中可以同时存在多个参数，使用 & 分隔例如：您的验证码为{1}在{2}分钟内有效，可以传为 message="1234"+"&"+"5"

            Class<? extends SupplierConfig> type = configClass.get(supplierType);

            Assert.notNull(type, "通道配置不支持:{}", supplierType);

            supplierType.getProviderFactory().createMultitonSms(newConfig(smsSetting, type)).sendMessage(phone, code);
        }

        return code;
    }


    private <T extends SupplierConfig> T newConfig(Map<String, Object> smsSetting, Class<?> type) {
        return (T) BeanUtil.toBeanIgnoreError(smsSetting, type);
    }

}