package com.levin.oak.base.biz.rbac;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rbac 权限列表服务
 */
public interface RbacPermissionService<U> {
    /**
     * 获取用户的角色列表
     *
     * @param principal
     * @return
     */
    List<String> getRoleList(@NotNull U principal);

    /**
     * 获取用户的权限列表
     *
     * @param principal
     * @return
     */
    List<String> getPermissionList(@NotNull U principal);
}
