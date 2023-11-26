package com.levin.oak.base.biz.rbac;


import org.springframework.lang.NonNull;

import java.lang.reflect.Method;

/**
 * Rbac 基本服务
 * <p>
 * 1、获取可以使用的资源清单
 * 2、获取可以使用的菜单清单
 * 3、方法授权检查
 */
@FunctionalInterface
public interface RbacMethodService<U> {

    /**
     * 检查当前用户的方法调用授权
     *
     * @param method 控制器或是服务的方法
     */
    boolean canAccess(U principal, Object beanOrClass, @NonNull Method method);

}
