package com.levin.oak.base.biz;

import com.levin.oak.base.ModuleOption;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Primary
@Service(PLUGIN_PREFIX + "DefaultCaptchaService")
@ConditionalOnClass({Captcha.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "DefaultCaptchaService", havingValue = "true", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "DefaultCaptchaService"}, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")
public class DefaultCaptchaServiceImpl extends AbstractCaptchaService implements CaptchaService {

    private static final List<Class<? extends Captcha>> types = Collections.unmodifiableList(Arrays.asList(GifCaptcha.class, ChineseCaptcha.class, ArithmeticCaptcha.class, ChineseGifCaptcha.class, SpecCaptcha.class));

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
    @SneakyThrows
    @Override
    protected void fillCode(Code code, Map<String, ? extends Serializable> genParams) {

        // 类型：Arithmetic，Gif,Chinese,ChineseGif
        String type = getParam(genParams, "", "type");

        //参考 https://gitee.com/ele-admin/EasyCaptcha?_from=gitee_search

        Class<? extends Captcha> typeClass = null;

        if (StringUtils.hasText(type)) {
            typeClass = types.stream().filter(c -> c.getSimpleName().contentEquals(type + "Captcha"))
                    .findFirst().orElse(null);
        }

        if (typeClass == null) {
            typeClass = types.get((int) (System.currentTimeMillis() % types.size()));
        }

        Captcha captcha = typeClass.getDeclaredConstructor().newInstance();

        //设置验证码长度
        captcha.setLen(frameworkProperties.getVerificationCodeLen());

        String w = getParam(genParams, "", "width", "w");
        String h = getParam(genParams, "", "height", "h");

        if (StringUtils.hasText(w)
                && StringUtils.hasText(h)) {
            captcha.setWidth(Integer.parseInt(w));
            captcha.setHeight(Integer.parseInt(h));
        }

        //生产验证码
        captcha.text();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        captcha.out(out);

        code.setCode(captcha.text())
                .setImgData(out.toByteArray())
                .setContentType("image/gif");
    }

}
