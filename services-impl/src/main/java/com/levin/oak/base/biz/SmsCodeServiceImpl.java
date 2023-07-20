package com.levin.oak.base.biz;

import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "SmsCodeService")
@DubboService
@ConditionalOnClass({RedissonClient.class})
@ConditionalOnMissingBean(SmsCodeService.class)
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SmsCodeService", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "SmsCodeService"})
public class SmsCodeServiceImpl
        implements SmsCodeService {

    private static final String CACHE_NAME = SmsCodeService.class.getName();

//    @Autowired
//    RedissonClient redissonClient;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired(required = false)
    SmsSendService smsSender;

//    RMapCache<String, Object> mapCache = null;

    @Autowired
//    RedisOperations<String, Long> redisOperations;
    StringRedisTemplate redisTemplate;

    @PostConstruct
    void init() {

//        mapCache = redissonClient.getMapCache(CACHE_NAME);

        if (smsSender == null) {
            log.warn("未发现短信发送服务：" + SmsSendService.class.getName());
        }
    }

    @Override
    public String genAndSendSmsCode(String tenantId, String appId, String account, String phoneNo) {

        Assert.isTrue(frameworkProperties.isEnableSmsVerificationCode(), "系统短信验证码关闭");

        Assert.isTrue(frameworkProperties.getVerificationCodeLen() > 0, "系统短信验证码长度错误");

        Assert.hasText(account, "帐号不能为空");
        Assert.hasText(phoneNo, "手机号不能为空");

        final String prefix = String.join("|", tenantId, appId, account);

        //使用纳秒随机码
        String code = "" + System.nanoTime();

        final int codeLen = frameworkProperties.getVerificationCodeLen();

        Assert.isTrue(codeLen >= 4, "短信验证长度为不能小于4");

        code = code.substring(code.length() - codeLen);

        final String genCode = code.toLowerCase();

        Assert.hasText(genCode, "短信验证码生成失败");

        Assert.isTrue(codeLen == genCode.length(), "短信验证码生成失败-" + codeLen);

        //如果有发送服务
        if (smsSender != null) {
            //@todo 发送短信验证码
            String resp = null;

            try {
                resp = smsSender.sendCode(tenantId, appId, phoneNo, genCode);
            } catch (Exception e) {
                resp = e.getMessage();
                log.error("租户短信发送失败", e);
            }

            //如果
            if (!code.equals(resp)) {
                throw new IllegalStateException("短信发送失败");
            }
        } else if (frameworkProperties.isEnableMockSmsSend()) {
            //模拟短信发送
            code = "mock:" + code;
        } else {
            throw new IllegalStateException("1短信发送失败，通道不可用");
        }

        redisTemplate.opsForValue().
                set(CACHE_NAME + ":" + prefix + genCode, "" + System.currentTimeMillis(), frameworkProperties.getVerificationCodeDurationOfMinutes(), TimeUnit.MINUTES);

//        mapCache.put(prefix + genCode, System.currentTimeMillis(),
//                frameworkProperties.getVerificationCodeDurationOfMinutes(), TimeUnit.MINUTES);

        return code;
    }

    @Override
    public boolean verify(String tenantId, String appId, String account, String code) {

        Assert.hasText(account, "帐号不能为空");
        Assert.hasText(code, "短信验证码不能为空");

        Assert.isTrue(frameworkProperties.isEnableSmsVerificationCode(), "短信验证码关闭");

        final String prefix = String.join("|", tenantId, appId, account);

//        Long putTime = (Long) mapCache.remove(prefix + code.toLowerCase());

        String value = redisTemplate.opsForValue().getAndExpire(CACHE_NAME + ":" + prefix + code.toLowerCase(), 1, TimeUnit.MICROSECONDS);

        Long putTime = StringUtils.hasText(value) ? Long.decode(value) : null;

        //小余1分钟
        return putTime != null && (System.currentTimeMillis() - putTime)
                < frameworkProperties.getVerificationCodeDurationOfMinutes() * 60 * 1000L;
    }
}
