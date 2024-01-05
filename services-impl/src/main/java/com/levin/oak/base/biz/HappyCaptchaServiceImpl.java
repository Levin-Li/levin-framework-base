package com.levin.oak.base.biz;

import com.levin.oak.base.ModuleOption;
import com.ramostear.captcha.AbstractCaptcha;
import com.ramostear.captcha.core.AnimCaptcha;
import com.ramostear.captcha.core.Captcha;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "HappyCaptchaService")
@ConditionalOnClass({AnimCaptcha.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "HappyCaptchaService", havingValue = "true", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "HappyCaptchaService"}, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")

public class HappyCaptchaServiceImpl extends AbstractCaptchaService implements CaptchaService {

    /**
     * 填充验证码
     *
     * @param code
     * @param genParams
     */
    @Override
    protected void fillCode(Code code, Map<String, ? extends Serializable> genParams) {

        String w = getParam(genParams, "", "width", "w");
        String h = getParam(genParams, "", "height", "h");

        //随机验证码
        AbstractCaptcha captcha = (System.currentTimeMillis() % 2 == 0) ? new AnimCaptcha() : new Captcha();

        if (StringUtils.hasText(w) && StringUtils.hasText(h)) {
            captcha.setWidth(Integer.parseInt(w.trim()));
            captcha.setHeight(Integer.parseInt(h.trim()));
        }

        //
        captcha.setFont(new Font(null, Font.PLAIN, (int) (captcha.getHeight() * 0.75)));

        captcha.setLength(frameworkProperties.getVerificationCodeLen());

        // captcha.setFont(Fonts.getInstance().defaultFont());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        captcha.render(out);

        code.setCode(captcha.getCode())
                .setImgData(out.toByteArray())
                .setContentType("image/gif");

    }

}
