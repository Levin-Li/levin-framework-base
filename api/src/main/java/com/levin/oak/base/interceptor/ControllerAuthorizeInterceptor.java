package com.levin.oak.base.interceptor;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacMethodService;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 控制器方法拦截器
 */
public class ControllerAuthorizeInterceptor implements HandlerInterceptor {

    @Autowired
    AuthService authService;

    @Autowired
    RbacMethodService<Serializable> rbacMethodService;

    Supplier<AuthService> supplier;

    Predicate<String> classNameFilter;


    public ControllerAuthorizeInterceptor(AuthService authService, RbacMethodService<Serializable> rbacMethodService, Predicate<String> classNameFilter) {
        Assert.notNull(authService, "authService is null");
        Assert.notNull(rbacMethodService, "rbacMethodService is null");
        this.authService = authService;
        this.rbacMethodService = rbacMethodService;
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

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Object bean = ((HandlerMethod) handler).getBean();

        Class<?> targetClass = AopUtils.getTargetClass(bean);

        if (targetClass == null) {
            targetClass = handlerMethod.getBeanType();
        }

        if (classNameFilter != null
                && !classNameFilter.test(targetClass.getName())) {
            return true;
        }

        if (authService == null && supplier != null) {
            authService = supplier.get();
        }

        if (authService == null) {
            throw new IllegalStateException("authService is null");
        }

        //检查权限
        boolean ok = rbacMethodService.canAccess(authService.isLogin() ? authService.getUserInfo() : null,targetClass, handlerMethod.getMethod());

        Assert.isTrue(ok, () -> new AuthorizationException("未授权的操作"));

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
