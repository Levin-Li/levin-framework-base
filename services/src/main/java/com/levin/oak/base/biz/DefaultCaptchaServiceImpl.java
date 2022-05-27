package com.levin.oak.base.biz;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "DefaultCaptchaService")
@ConditionalOnClass({CaptchaUtil.class, RedissonClient.class})
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "DefaultCaptchaService", matchIfMissing = true)
@Slf4j
@CacheConfig(cacheNames = {ModuleOption.ID_PREFIX + "DefaultCaptchaService"})
public class DefaultCaptchaServiceImpl implements CaptchaService {

    private static final String CACHE_NAME = DefaultCaptchaServiceImpl.class.getName();

    @Resource
    CacheManager cacheManager;

    @Resource
    RedissonClient redissonClient;

    @Resource
    FrameworkProperties frameworkProperties;


    RMapCache<Object, Object> mapCache = null;


    ICaptcha iCaptcha;

    @PostConstruct
    void init() {

        mapCache = redissonClient.getMapCache(CACHE_NAME);

        iCaptcha = CaptchaUtil.createCircleCaptcha(200, 100, frameworkProperties.getCaptchaCodeLen(), 20);

        //首次调用比较慢
        iCaptcha.createCode();
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

        if (StringUtils.hasText(w) && StringUtils.hasText(h)) {
            iCaptcha = CaptchaUtil.createCircleCaptcha(Integer.parseInt(w), Integer.parseInt(h), frameworkProperties.getCaptchaCodeLen(), 20);
        }

        iCaptcha.createCode();

        String captchaCode = iCaptcha.getCode();

        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        request.getSession().setAttribute("captchaCode", captchaCode);

        iCaptcha.write(response.getOutputStream());

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
