package com.levin.oak.base.interceptor;

import com.levin.oak.base.biz.rbac.RbacService;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AuthorizeAnnotationInterceptor implements HandlerInterceptor {

    @Resource
    RbacService rbacService;

    Supplier<RbacService> supplier;

    Predicate<String> classNameFilter;

    public AuthorizeAnnotationInterceptor(RbacService rbacService, Predicate<String> classNameFilter) {
        Assert.notNull(rbacService, "rbacService is null");
        this.rbacService = rbacService;
        this.classNameFilter = classNameFilter;
    }

    public AuthorizeAnnotationInterceptor(Supplier<RbacService> supplier, Predicate<String> classNameFilter) {
        Assert.notNull(supplier, "supplier is null");
        this.supplier = supplier;
        this.classNameFilter = classNameFilter;
    }

    @PostConstruct
    public void init() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        if (classNameFilter != null
                && !classNameFilter.test(((HandlerMethod) handler).getBeanType().getName())) {
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

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

//        if (!(handler instanceof HandlerMethod)) {
//            return;
//        }

    }


}
