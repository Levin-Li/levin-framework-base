package com.levin.oak.base.interceptor;

import com.levin.oak.base.biz.rbac.RbacService;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Supplier;

public class AuthorizeAnnotationInterceptor implements HandlerInterceptor {

    @Resource
    RbacService rbacService;

    Supplier<RbacService> supplier;

    public AuthorizeAnnotationInterceptor() {
    }

    public AuthorizeAnnotationInterceptor(RbacService rbacService) {
        Assert.notNull(rbacService, "rbacService is null");
        this.rbacService = rbacService;
    }

    public AuthorizeAnnotationInterceptor(Supplier<RbacService> supplier) {
        Assert.notNull(supplier, "supplier is null");
        this.supplier = supplier;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (rbacService == null && supplier != null) {
            rbacService = supplier.get();
        }

        if (rbacService == null) {
            throw new IllegalStateException("rbacService is null");
        }

        //检查权限
        rbacService.checkAuthorize(((HandlerMethod) handler).getMethod());

        return true;
    }

}
