package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserObject;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.rbac.ResPermission;
import com.levin.commons.service.support.ContextHolder;
import com.levin.commons.utils.ClassUtils;
import com.levin.commons.utils.MapUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Role;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "RbacMethodService")
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Slf4j
@ConditionalOnProperty(value = PLUGIN_PREFIX + "RbacMethodService", havingValue = "true", matchIfMissing = true)
@ResAuthorize(ignored = true)
public class RbacMethodServiceImpl implements RbacMethodService<Serializable> {

    static final ContextHolder<Method, ResAuthorize> cache = ContextHolder.buildContext(true);

    final ResAuthorize defaultResAuthorize = getClass().getAnnotation(ResAuthorize.class);

    @Autowired
    RbacLoadService<Serializable> rbacLoadService;

    @Autowired
    RbacService<Serializable> rbacService;

    /**
     * 检查访问权限
     *
     * @return
     */
    /**
     * 检查当前用户的方法调用授权
     *
     * @param userPrincipal user
     * @param beanOrClass
     * @param method        控制器或是服务的方法
     */
    @Override
    public boolean canAccess(Serializable userPrincipal, Object beanOrClass, Method method) {

        if (method == null) {
            return true;
        }

        /////////////////////////////// 获取注解 ///////////////////////////////////////////////////
        ResAuthorize resAuthorize = RbacUtils.getMethodResAuthorize(beanOrClass, method);

        if (resAuthorize == null
                || resAuthorize.ignored()) {
            return true;
        }

        if (userPrincipal == null) {
//            throw new AuthorizationException(401, "未登录");
            return false;
        }

        RbacUserObject<String> userInfo = rbacLoadService.loadUser(userPrincipal);

        if (userInfo == null) {
//            throw new AuthorizationException(401, "未登录");
            return false;
        }

        if (resAuthorize.onlyRequireAuthenticated()) {
            return true;
        }

        ///////////////////////// 构建权限检查逻辑的闭包 //////////////////////////////////////

        boolean authorized = rbacService.isAuthorized(userInfo, resAuthorize);

        if (!authorized && log.isDebugEnabled()) {
            log.debug("***授权检查失败*** 用户：{} ，需要的权限：{}", userInfo, resAuthorize);
        }

        return authorized;

    }

}
