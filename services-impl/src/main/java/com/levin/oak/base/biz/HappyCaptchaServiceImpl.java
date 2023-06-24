package com.levin.oak.base.biz;

import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.core.Captcha;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "HappyCaptchaService")
@ConditionalOnClass({HappyCaptcha.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "HappyCaptchaService", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID + ModuleOption.CACHE_DELIM + "HappyCaptchaService"})
@Primary
public class HappyCaptchaServiceImpl implements CaptchaService {

    private static final String CACHE_NAME = HappyCaptchaServiceImpl.class.getName();

    @Autowired
    RedissonClient redissonClient;

    RMapCache<Object, Object> mapCache = null;

    @Autowired
    FrameworkProperties frameworkProperties;

    @PostConstruct
    void init() {
        mapCache = redissonClient.getMapCache(CACHE_NAME);
    }


    /**
     * 生成图片验证码
     *
     * @param request
     * @param response
     * @param tenantId
     * @param appId
     * @param account
     * @return
     */
    @SneakyThrows
    @Override
    public String genCode(HttpServletRequest request, HttpServletResponse response, String tenantId, String appId, String account) {

        Assert.hasText(account, "帐号不能为空");
        final String prefix = String.join("|", tenantId, appId, account);

        String w = request.getParameter("w");
        String h = request.getParameter("h");

        Captcha captcha = new Captcha();

        if (StringUtils.hasText(w) && StringUtils.hasText(h)) {
            captcha.setWidth(Integer.parseInt(w.trim()));
            captcha.setHeight(Integer.parseInt(h.trim()));
        }

        //
        captcha.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, (int) (captcha.getHeight() * 0.75)));

        captcha.setLength(frameworkProperties.getVerificationCodeLen());

        // captcha.setFont(Fonts.getInstance().defaultFont());

        String captchaCode = captcha.getCaptchaCode();

        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        request.getSession().setAttribute("captchaCode", captchaCode);

        captcha.render(response.getOutputStream());

        mapCache.fastPut(prefix + captchaCode.toLowerCase(), System.currentTimeMillis(),
                frameworkProperties.getVerificationCodeDurationOfMinutes(), TimeUnit.MINUTES);

        log.debug("gen code:" + captchaCode);

        return captchaCode;
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

        final String prefix = String.join("|", tenantId, appId, account);

        Long putTime = (Long) mapCache.remove(prefix + code.toLowerCase());
        //小余1分钟
        return putTime != null && (System.currentTimeMillis() - putTime)
                < frameworkProperties.getVerificationCodeDurationOfMinutes() * 60 * 1000L;
    }
}
