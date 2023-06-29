package com.levin.oak.base.biz;

import cn.hutool.captcha.CaptchaUtil;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

//@Service(PLUGIN_PREFIX + "SmsCodeService")
@DubboService
@ConditionalOnClass({CaptchaUtil.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SmsCodeService", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "SmsCodeService"})
public class SmsCodeServiceImpl
        implements SmsCodeService {

    private static final String CACHE_NAME = SmsCodeServiceImpl.class.getName();

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired(required = false)
    SmsSender smsSender;

    RMapCache<String, Object> mapCache = null;

    @PostConstruct
    void init() {
        mapCache = redissonClient.getMapCache(CACHE_NAME);
        log.warn("未发现短信发送服务：" + SmsSender.class.getName());
    }

    @Override
    public String genAndSendSmsCode(String tenantId, String appId, String account, String phoneNo) {

        Assert.isTrue(frameworkProperties.isEnableSmsVerificationCode(), "短信验证码已经禁用");

        Assert.hasText(account, "帐号不能为空");
        Assert.hasText(phoneNo, "手机号不能为空");

        final String prefix = String.join("|", tenantId, appId, account);

        //使用纳秒随机码
        String code = "" + System.nanoTime();

        code = code.substring(code.length() - frameworkProperties.getVerificationCodeLen());

        final String genCode = code.toLowerCase();

        //如果有发送服务
        if (smsSender != null) {
            //@todo 发送短信验证码
            String resp = null;

            try {
                resp = smsSender.sendCode(tenantId, appId, phoneNo, code);
            } catch (Exception e) {
                resp = e.getMessage();
                log.error("短信发送失败", e);
            }

            //如果
            if (!code.equals(resp)) {
                throw new IllegalStateException("短信发送失败-" + resp);
            }
        } else if (frameworkProperties.isEnableMockSmsSend()) {
            //模拟短信发送
            code = "mock:" + code;
        } else {
            throw new IllegalStateException("1短信发送失败，通道不可用");
        }

        mapCache.fastPut(prefix + genCode, System.currentTimeMillis(),
                frameworkProperties.getVerificationCodeDurationOfMinutes(), TimeUnit.MINUTES);

        return code;

    }

    @Override
    public boolean verification(String tenantId, String appId, String account, String code) {

        Assert.isTrue(frameworkProperties.isEnableSmsVerificationCode(), "短信验证码已经禁用");

        Assert.hasText(account, "帐号不能为空");
        Assert.hasText(code, "短信验证码不能为空");

        final String prefix = String.join("|", tenantId, appId, account);

        Long putTime = (Long) mapCache.remove(prefix + code.toLowerCase());
        //小余1分钟
        return putTime != null && (System.currentTimeMillis() - putTime)
                < frameworkProperties.getVerificationCodeDurationOfMinutes() * 60 * 1000L;
    }
}
