package com.levin.oak.base.interceptor;


import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 控制器方法域名拦截器
 */
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

        if (!StringUtils.hasText(devKey)) {
            devKey = request.getHeader(PARA_NAME);
        }

        if (!StringUtils.hasText(devKey)) {
            devKey = Stream.of(request.getCookies())
                    .filter(Objects::nonNull)
                    .filter(cookie -> cookie.getName().equalsIgnoreCase(PARA_NAME))
                    .map(cookie -> cookie.getValue())
                    .findFirst().orElse(null);
        }

        if (!StringUtils.hasText(devKey)) {
            devKey = (String) request.getAttribute(PARA_NAME);
        }

        if (!StringUtils.hasText(devKey)) {
            devKey = (String) request.getSession(true).getAttribute(PARA_NAME);
        }

        if (StringUtils.hasText(devKey)
                && devKey.equalsIgnoreCase(SecureUtil.md5(domainName + "-开发者资源-" + DateUtil.format(new Date(), "yy-MM-dd-HH")))) {

            request.getSession(true)
                    .setAttribute(PARA_NAME, devKey);

            request.setAttribute(PARA_NAME, devKey);

            response.addHeader(PARA_NAME, devKey);

            response.addCookie(new Cookie(PARA_NAME, devKey));

            return true;
        }

        return false;
    }


}
