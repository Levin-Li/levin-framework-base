package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserObject;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 加载服务
 */
public interface RbacLoadService<U> {

    /**
     * 加载用户
     *
     * @param principal 登录ID
     * @return 用户信息
     */
    RbacUserObject<String> loadUser(U principal);

    /**
     * 获取用户的角色列表
     *
     * @param principal
     * @return
     */
    default List<String> getRoleList(@NotNull U principal) {
        return loadUser(principal).getRoleList();
    }

    /**
     * 获取用户的权限列表
     *
     * @param principal
     * @return
     */
    default List<String> getPermissionList(@NotNull U principal) {

        RbacUserObject<String> userInfo = loadUser(principal);

        List<String> roleList = userInfo.getRoleList();

        if (roleList == null || roleList.isEmpty()) {
            return Collections.emptyList();
        }

        return loadRolePermissionList(userInfo.getTenantId(), roleList);
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    default List<String> loadRolePermissionList(Serializable tenantId, String... roleCodeList) {
        return loadRolePermissionList(tenantId, Arrays.asList(roleCodeList));
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    List<String> loadRolePermissionList(Serializable tenantId, List<String> roleCodeList);

}
