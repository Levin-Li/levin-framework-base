package com.levin.oak.base.biz.rbac;

import com.levin.oak.base.biz.rbac.info.ModuleInfo;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rbac 资源服务
 * <p>
 * 1、获取可以使用的菜单清单
 */
public interface RbacResService {

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
