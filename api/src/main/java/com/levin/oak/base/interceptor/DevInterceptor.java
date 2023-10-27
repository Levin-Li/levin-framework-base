package com.levin.oak.base.interceptor;


import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 开发者临时过滤器
 * <p>
 * 一般建议只拦截开发相关的路径
 */
@Slf4j
public class DevInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String domainName = request.getParameter("api_refer_domain");

        if (!StringUtils.hasText(domainName)) {
            //支持从头部获取域名
            domainName = request.getHeader("api_refer_domain");
        }

        if (!StringUtils.hasText(domainName)) {
            domainName = request.getServerName();
        }

        final String PARA_NAME = "devKey";

        String devKey = request.getParameter(PARA_NAME);

//        if (!StringUtils.hasText(devKey)) {
//            devKey = (String) request.getAttribute(PARA_NAME);
//        }

        if (!StringUtils.hasText(devKey)) {
            devKey = request.getHeader(PARA_NAME);
        }

        boolean fromCookie = false;
        Cookie[] cookies = request.getCookies();
        if (!StringUtils.hasText(devKey) && cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie != null
                        && PARA_NAME.equals(cookie.getName())) {
                    //获取同名的最后一个cookie，可能存在同名cookie
                    devKey = cookie.getValue();
                    fromCookie = true;
                }
            }
        }

        if (!StringUtils.hasText(devKey)) {
            devKey = (String) request.getAttribute(PARA_NAME);
        }

        if (!StringUtils.hasText(devKey)) {
            devKey = (String) request.getSession(true).getAttribute(PARA_NAME);
        }

        final String tmpAccessKey = SecureUtil.md5(domainName + "-开发者资源-" + DateUtil.format(new Date(), "yy-MM-dd-HH"));

        if (StringUtils.hasText(devKey)
                && devKey.equalsIgnoreCase(tmpAccessKey)) {

            request.getSession(true)
                    .setAttribute(PARA_NAME, devKey);

            request.setAttribute(PARA_NAME, devKey);

            response.addHeader(PARA_NAME, devKey);

            if (!fromCookie) {

                Cookie cookie = new Cookie(PARA_NAME, devKey);

                //路径必须填写，不然会导致多个同名Cookie存在
                cookie.setPath(PARA_NAME);
                cookie.setMaxAge(3600 - Calendar.getInstance().get(Calendar.MINUTE) * 60);
                cookie.setComment(PARA_NAME);
                response.addCookie(cookie);
            }

            return true;
        }

        log.info("[{}] 请求devKey：{} ，匹配的devKey={}", request.getRequestURL(), devKey, tmpAccessKey);

        return false;
    }

}
