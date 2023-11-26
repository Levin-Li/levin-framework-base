package com.levin.oak.base.interceptor;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 资源认证拦截器
 */
@Data
@Slf4j
public class ResourceAuthorizeInterceptor
        implements HandlerInterceptor {

    @Autowired
    AuthService authService;

    @Autowired
    RbacService rbacService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    BizTenantService bizTenantService;

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
        final String path = request.getRequestURI().substring(getLen(request.getContextPath()));

        boolean pass = frameworkProperties.getResourcesAcl()
                .stream()
                .filter(resCfg -> resCfg.isEnable() && resCfg.isPathMatched(path))
                .allMatch(resCfg -> isAuthorized(response, resCfg));

        if (!pass) {
            log.warn("*** 非法的资源访问*** 资源匹配路径：{} ，完整路径：{}", path, request.getRequestURL());
        }

        return pass;
    }

    /**
     * 资源权限校验
     * <p>
     * 资源访问，不跑出异常
     *
     * @param response
     * @param resCfg
     * @return
     */
    protected boolean isAuthorized(HttpServletResponse response, FrameworkProperties.ResCfg resCfg) {

        if (!resCfg.isEnable()) {
            return true;
        }

        if (resCfg.isDenied()) {
            return false;
        }

        // Assert.isTrue(authService.isLogin(), () -> new AuthorizationException(401, "未登录", path));

        if (!authService.isLogin()) {
            response.setStatus(401);
            return false;
        }

        bizTenantService.checkCurrentUserTenantInfo();

        if (resCfg.isOnlyRequireAuthenticated()) {
            return true;
        }

        boolean ok = rbacService.isAuthorized(authService.getUserInfo(), resCfg.isAndMode(), resCfg.getRequiredPermissions(), (rp, info) -> {
            //throw new AuthorizationException(403, "res-" + rp, "未授权的资源：" + info);
        });

        //Assert.isTrue(ok, () -> new AuthorizationException(403, "未授权的操作"));
        response.setStatus(403);

        return ok;
    }

    private int getLen(String txt) {
        return txt == null ? 0 : txt.length();
    }

}
