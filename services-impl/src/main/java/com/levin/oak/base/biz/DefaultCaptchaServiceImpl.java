package com.levin.oak.base.biz;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.levin.oak.base.ModuleOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

//@Service(PLUGIN_PREFIX + "DefaultCaptchaService")
//@DubboService
@ConditionalOnClass({CaptchaUtil.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "DefaultCaptchaService",havingValue = "true",  matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "DefaultCaptchaService"})
public class DefaultCaptchaServiceImpl extends AbstractCaptchaService implements CaptchaService {

    ICaptcha iCaptcha;

    @PostConstruct
    @Override
    public void init() {
        super.init();
    }

    /**
     * 填充验证码
     *
     * @param code
     * @param genParams
     */
    @Override
    protected void fillCode(Code code, Map<String, ? extends Serializable> genParams) {

        if (iCaptcha == null) {
            iCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, frameworkProperties.getVerificationCodeLen(), 20);
        }

        String w = genParams != null ? ((Map) genParams).getOrDefault("w", "").toString() : null;
        String h = genParams != null ? ((Map) genParams).getOrDefault("h", "").toString() : null;

        if (StringUtils.hasText(w)
                && StringUtils.hasText(h)) {
            iCaptcha = CaptchaUtil.createCircleCaptcha(Integer.parseInt(w), Integer.parseInt(h), frameworkProperties.getVerificationCodeLen(), 20);
        }

        iCaptcha.createCode();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        iCaptcha.write(out);

        code.setCode(iCaptcha.getCode())
                .setImgData(out.toByteArray())
                .setContentType("image/gif");
    }

}
