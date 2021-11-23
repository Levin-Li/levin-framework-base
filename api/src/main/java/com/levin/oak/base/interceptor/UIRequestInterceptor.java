package com.levin.oak.base.interceptor;

import com.levin.oak.base.services.rbac.RbacService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UI 请求拦截器
 */
public class UIRequestInterceptor implements HandlerInterceptor {

    @Resource
    RbacService rbacService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();




        return false;
    }

}
