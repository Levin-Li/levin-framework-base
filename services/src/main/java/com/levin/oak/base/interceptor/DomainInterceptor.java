package com.levin.oak.base.interceptor;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

/**
 * 域名拦截器
 */
public class DomainInterceptor implements HandlerInterceptor {

    Consumer<String> consumer;

    public DomainInterceptor(Consumer<String> consumer) {
        this.consumer = consumer;
    }

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

        onDomain(domainName);

        return true;
    }


    protected void onDomain(String domain) {

        if (consumer != null) {
            consumer.accept(domain);
        } else {

        }

    }
}
