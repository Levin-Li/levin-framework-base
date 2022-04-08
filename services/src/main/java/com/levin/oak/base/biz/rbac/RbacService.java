package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.AuthorizationException;
import com.levin.commons.rbac.Permission;
import com.levin.oak.base.biz.rbac.info.ModuleInfo;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Rbac 基本服务
 * <p>
 * 1、获取可以使用的资源清单
 * 2、获取可以使用的菜单清单
 * 3、方法授权检查
 */
public interface RbacService {

    /**
     * 获取权限分隔符
     *
     * @return
     */
    default String getDelimiter() {
        return Permission.DELIMITER;
    }

    /**
     * 检查当前用户的方法调用授权
     *
     * @param method 控制器或是服务的方法
     */
    void checkAuthorize(@NonNull Method method) throws AuthorizationException;

    /**
     * 获取指定用户的授权菜单列表
     *
     * @param userId 不允许为空
     * @return
     */
    List<MenuResInfo> getAuthorizedMenuList(boolean isShowNotPermissionMenu, @NotNull Object userId);

    /**
     * 获取资源授权清单
     *
     * @param userId 为null返回所有的资源授权清单
     * @return
     */
    List<ModuleInfo> getAuthorizedResList(@Nullable Object userId);

}
