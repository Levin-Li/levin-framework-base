package com.levin.oak.base.biz;


import java.util.Arrays;
import java.util.List;

/**
 * 租户-服务接口
 */
public interface BizRoleService {

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    default List<String> getRolePermissionList(String tenantId, String... roleCodeList) {
        return getRolePermissionList(tenantId, Arrays.asList(roleCodeList));
    }

    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    List<String> getRolePermissionList(String tenantId, List<String> roleCodeList);

}
