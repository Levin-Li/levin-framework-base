package com.levin.oak.base.biz.rbac;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Rbac 权限列表服务
 */
public interface RbacPermissionService<UID> {
    /**
     * 获取用户的角色列表
     *
     * @param userId
     * @return
     */
    List<String> getRoleList(@NotNull UID userId);

    /**
     * 获取用户的权限列表
     *
     * @param userId
     * @return
     */
    List<String> getPermissionList(@NotNull UID userId);
}
