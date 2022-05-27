package com.levin.oak.base.biz;

import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.core.Captcha;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "HappyCaptchaService")
@ConditionalOnClass({HappyCaptcha.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "HappyCaptchaService", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + "HappyCaptchaService"})
@Primary
public class HappyCaptchaServiceImpl implements CaptchaService {

    private static final String CACHE_NAME = HappyCaptchaServiceImpl.class.getName();

    @Resource
    RedissonClient redissonClient;

    RMapCache<Object, Object> mapCache = null;

    @Resource
    FrameworkProperties frameworkProperties;

    @PostConstruct
    void init() {
        mapCache = redissonClient.getMapCache(CACHE_NAME);
    }

    /**
     * 生成
     *
     * @param request
     * @param response
     * @param params
     */
    @SneakyThrows
    @Override
    public String genCode(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) {

        String w = request.getParameter("w");
        String h = request.getParameter("h");

        Captcha captcha = new Captcha();

        if (StringUtils.hasText(w) && StringUtils.hasText(h)) {
            captcha.setWidth(Integer.parseInt(w.trim()));
            captcha.setHeight(Integer.parseInt(h.trim()));
        }

        //
        captcha.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, (int) (captcha.getHeight() * 0.75)));

        captcha.setLength(frameworkProperties.getCaptchaCodeLen());

        // captcha.setFont(Fonts.getInstance().defaultFont());

        String captchaCode = captcha.getCaptchaCode();

        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        request.getSession().setAttribute("captchaCode", captchaCode);

        captcha.render(response.getOutputStream());

        mapCache.fastPut(captchaCode.toLowerCase(), System.currentTimeMillis(), 1, TimeUnit.MINUTES);

        log.debug("gen code:" + captchaCode);

        return captchaCode;
    }

    /**
     * 验证
     *
     * @param request
     * @param code
     * @param params
     * @return
     */
    @Override
    public boolean verification(HttpServletRequest request, String code, Map<String, Object> params) {

        Assert.hasText(code, "验证码不能为空");

        Long putTime = (Long) mapCache.remove(code.toLowerCase());
        //小余1分钟
        return putTime != null && (System.currentTimeMillis() - putTime) < 60 * 1000L;
    }
}
