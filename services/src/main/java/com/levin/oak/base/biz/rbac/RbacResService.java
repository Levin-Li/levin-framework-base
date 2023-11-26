package com.levin.oak.base.biz.rbac;

import com.levin.oak.base.biz.rbac.info.ModuleInfo;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Rbac 资源服务
 * <p>
 * 1、获取可以使用的菜单清单
 */
public interface RbacResService<U> {

    /**
     * 获取指定用户的授权菜单列表
     *
     * @param principal 不允许为空
     * @return
     */
    default List<MenuResInfo> getAuthorizedMenuList(@NotNull U principal) {
        return getAuthorizedMenuList(false, principal);
    }

    /**
     * 获取指定用户的授权菜单列表
     *
     * @param principal 不允许为空
     * @return
     */
    List<MenuResInfo> getAuthorizedMenuList(boolean isShowNotPermissionMenu, @NotNull U principal);

    /**
     * 获取资源授权清单
     *
     * @param principal 为null返回所有的资源授权清单
     * @return
     */
    List<ModuleInfo> getAuthorizedResList(@Nullable U principal);

}
