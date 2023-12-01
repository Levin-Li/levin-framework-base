package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserObject;
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

        Class<?> controllerClass = beanOrClass != null ? (beanOrClass instanceof Class ? (Class) beanOrClass : beanOrClass.getClass())
                : method.getDeclaringClass();

        ///////////////////////////////获取 res 和 action 用于权限验证 //////////////////////////////////////////
        //
        Tag tag = controllerClass.getAnnotation(Tag.class);
        Operation operation = method.getAnnotation(Operation.class);

        String res = controllerClass.getSimpleName().replace("Controller", "");

        if (tag != null) {
            res = Arrays.asList(tag.name(), tag.description())
                    .stream().filter(StringUtils::hasText).findFirst().orElse(res);
        }

        String action = method.getName();
        if (operation != null) {
            action = Arrays.asList(operation.summary(), operation.operationId(), operation.description())
                    .stream().filter(StringUtils::hasText).findFirst().orElse(action);
        }
        /////////////////////////////// 获取注解 ///////////////////////////////////////////////////
        ResAuthorize resAuthorize = cache.get(method);
        //如果没有放入
        if (resAuthorize == null
                && !cache.containsKey(method)) {

            resAuthorize = ClassUtils.merge(MapUtils.putFirst(ResPermission.Fields.res, res)
                            .put(ResPermission.Fields.action, action).build()
                    //默认条件为不空或是不是空字符串则覆盖
                    , (k, v) -> v != null && (!(v instanceof CharSequence) || StringUtils.hasText((CharSequence) v))

                    , ResAuthorize.class,
                    AnnotatedElementUtils.getMergedAnnotation(controllerClass, ResAuthorize.class),
                    AnnotatedElementUtils.getMergedAnnotation(method, ResAuthorize.class)
            );

            if (resAuthorize == null) {
                resAuthorize = defaultResAuthorize;
            }

            cache.put(method, resAuthorize);
        }

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

        return rbacService.isAuthorized((Serializable) userInfo, resAuthorize);

    }


}
