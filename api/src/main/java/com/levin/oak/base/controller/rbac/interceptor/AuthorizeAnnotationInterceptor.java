package com.levin.oak.base.controller.rbac.interceptor;

import com.levin.oak.base.services.rbac.RbacService;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizeAnnotationInterceptor implements HandlerInterceptor {

    @Resource
    RbacService rbacService;

    public AuthorizeAnnotationInterceptor() {
    }

    public AuthorizeAnnotationInterceptor(RbacService rbacService) {
        Assert.isNull(rbacService, "rbacService is null");
        this.rbacService = rbacService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //检查权限
        rbacService.checkAuthorize(((HandlerMethod) handler).getMethod());

        return true;
    }

}
