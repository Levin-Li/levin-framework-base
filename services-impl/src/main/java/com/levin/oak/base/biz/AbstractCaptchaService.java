package com.levin.oak.base.biz;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Slf4j
public abstract class AbstractCaptchaService implements CaptchaService {

    @Autowired
    protected CacheManager cacheManager;

    @Autowired
    protected RedissonClient redissonClient;

    @Autowired
    protected FrameworkProperties frameworkProperties;

    protected RMapCache<Object, Object> mapCache = null;

    @PostConstruct
    protected void init() {
        mapCache = redissonClient.getMapCache(CaptchaService.class.getName());
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

        final String prefix = String.join("|", tenantId, appId, account);

        mapCache.fastPut(prefix + code.getCode().toLowerCase(), System.currentTimeMillis(),
                frameworkProperties.getVerificationCodeDurationOfMinutes(), TimeUnit.MINUTES);

        log.debug(prefix + " gen code: " + code.getCode());

        return code;
    }

    /**
     * 验证
     *
     * @param code
     * @return
     */
    @Override
    public boolean verification(String tenantId, String appId, String account, String code) {

        Assert.hasText(account, "帐号不能为空");
        Assert.hasText(code, "验证码不能为空");

        Assert.isTrue(frameworkProperties.isEnableCaptchaVerificationCode(), "系统图片验证码关闭");

        final String prefix = String.join("|", tenantId, appId, account);

        Long putTime = (Long) mapCache.remove(prefix + code.toLowerCase());

        //小余1分钟
        return putTime != null && (System.currentTimeMillis() - putTime)
                < frameworkProperties.getVerificationCodeDurationOfMinutes() * 60 * 1000L;

    }
}
