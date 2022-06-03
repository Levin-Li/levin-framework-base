package com.levin.oak.base.interceptor;

import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源认证拦截器
 */
@Data
@Slf4j
public class ResourceAuthorizeInterceptor
        implements HandlerInterceptor {

    @Resource
    AuthService authService;

    @Resource
    RbacService rbacService;

    @Resource
    FrameworkProperties frameworkProperties;


    @PostConstruct
    public void init() {
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ((handler instanceof HandlerMethod)) {
            return true;
        }

        if (frameworkProperties.getResourcesAcl().isEmpty()) {
            return true;
        }

        if (authService == null) {
            throw new IllegalStateException("authService is null");
        }

        if (frameworkProperties == null) {
            throw new IllegalStateException("frameworkProperties is null");
        }

//        request.getRequestURL() 返回全路径
//        request.getRequestURI() 返回除去host（域名或者ip）部分的路径
//        request.getContextPath() 返回工程名部分，如果工程映射为/，此处返回则为空
//        request.getServletPath() 返回除去host和工程名部分的路径
//        此处是运行结果
//        request.getRequestURL():http://localhost:8080/bzbs/system/login.jsp
//        request.getRequestURI():/bzbs/system/login.jsp
//        request.getContextPath():/bzbs
//        request.getServletPath():/system/login.jsp


        //检查权限

        //去除上下文路径
        String path = request.getRequestURI().substring(getLen(request.getContextPath()));

//        frameworkProperties.getResourcesAcl()
//                .stream()
//                .filter(resCfg -> resCfg.isEnable() && resCfg.isPathMatched(path))
//                .filter(resCfg -> {
//
//                });

        return true;
    }

    protected boolean isAuthorized(FrameworkProperties.ResCfg resCfg) {

        if (!resCfg.isEnable()) {
            return true;
        }

        if (!authService.isLogin()) {
            return false;
        }

        if (resCfg.isOnlyRequireAuthenticated()) {
            return true;
        }

//        Object loginUserId = authService.getLoginUserId();
//        rbacService.isAuthorized(authService.getRoleList(loginUserId)
//                ,authService.getPermissionList(loginUserId)
//                ,resCfg.getRequiredPermissions())

        return false;

    }

    private int getLen(String txt) {
        return txt == null ? 0 : txt.length();
    }

}
