package com.levin.oak.base.biz;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


@Slf4j
public abstract class AbstractCaptchaService implements CaptchaService {

//    @Autowired
//    protected CacheManager cacheManager;

    @Autowired
    protected RedissonClient redissonClient;

    private static final String CACHE_NAME = CaptchaService.class.getName();

    @Autowired
    protected FrameworkProperties frameworkProperties;

    protected RMapCache<Object, Object> mapCache = null;

    @Autowired
    StringRedisTemplate redisTemplate;
//    RedisOperations<String, Long> redisOperations;

    @PostConstruct
    protected void init() {
        mapCache = redissonClient.getMapCache(CaptchaService.class.getName());
    }

    protected String getParam(Map<String, ? extends Serializable> params, String defaultValue, String... keys) {

        if (keys == null || keys.length == 0
                || params == null || params.isEmpty()) {
            return defaultValue;
        }

        return Stream.of(keys)
                .filter(StringUtils::hasText)
                .map(key -> params.get(key))
                .filter(Objects::nonNull)
                .map(Object::toString)
                .filter(StringUtils::hasText)
                .findFirst()
                .orElse(defaultValue);
    }

    /**
     * 填充验证码
     *
     * @param code
     * @param genParams
     */
    protected abstract void fillCode(Code code, Map<String, ? extends Serializable> genParams);

    /**
     * 生成验证码
     *
     * @param tenantId
     * @param appId
     * @param account
     * @param genParams
     * @return
     */
    @Override
    public Code genCode(String tenantId, String appId, String account, Map<String, ? extends Serializable> genParams) {

        Assert.hasText(account, "帐号不能为空");

        Assert.isTrue(frameworkProperties.isEnableCaptchaVerificationCode(), "系统图片验证码关闭");

        Assert.isTrue(frameworkProperties.getVerificationCodeLen() > 0, "系统图片验证码长度错误");

        Code code = new Code().setContentType("image/gif");

        fillCode(code, genParams);

        Assert.hasText(code.getCode(), "验证码生成失败");

        //图片验证码忽略大小写

        final String cacheKey = CACHE_NAME + "_" + String.join("_", tenantId, appId, account) + code.getCode().toLowerCase();

//        redisTemplate.opsForValue().
//                set(cacheKey, "" + System.currentTimeMillis(), frameworkProperties.getVerificationCodeDurationOfMinutes(), TimeUnit.MINUTES);

        mapCache.put(cacheKey, System.currentTimeMillis()
                , frameworkProperties.getVerificationCodeDurationOfMinutes(), TimeUnit.MINUTES);

//        log.debug(prefix + " gen code: " + code.getCode());

        return code;
    }

    /**
     * 验证
     *
     * @param code
     * @return
     */
    @Override
    public boolean verify(String tenantId, String appId, String account, String code) {

        Assert.hasText(account, "帐号不能为空");
        Assert.hasText(code, "验证码不能为空");

        Assert.isTrue(frameworkProperties.isEnableCaptchaVerificationCode(), "系统图片验证码关闭");

        //图片验证码忽略大小写

        final String cacheKey = CACHE_NAME + "_" + String.join("_", tenantId, appId, account) + code.toLowerCase();

//        String value = redisTemplate.opsForValue().getAndDelete(cacheKey);
//        Long putTime = StringUtils.hasText(value) ? Long.parseLong(value) : null;

        Long putTime = (Long) mapCache.remove(cacheKey);

        //小余1分钟
        return putTime != null && (System.currentTimeMillis() - putTime)
                < frameworkProperties.getVerificationCodeDurationOfMinutes() * 60 * 1000L;

    }
}
