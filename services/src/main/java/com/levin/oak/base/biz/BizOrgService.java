package com.levin.oak.base.biz;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

import com.levin.commons.rbac.RbacUserObject;
import com.levin.oak.base.services.role.info.RoleInfo;
import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.org.info.*;


////////////////////////////////////
//自动导入列表

////////////////////////////////////

/**
 * 机构-业务服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月12日 下午10:13:36, 代码生成哈希校验码：[ef7606220eff436c006c8d9cea6535b5]，请不要修改和删除此行内容。
 */

@Tag(name = E_Org.BIZ_NAME + "-业务服务", description = "")
public interface BizOrgService {

    default List<OrgInfo> loadOrgList(String tenantId) {
        return loadOrgList(tenantId, null);
    }

    /**
     * 加载租户的部门列表
     *
     * @return
     */
    List<OrgInfo> loadOrgList(String tenantId, String parentId);


    /**
     * 是否能访问所有部门
     *
     * @param userPrincipal
     * @return
     */
    boolean canAccessAllOrg(Serializable userPrincipal);

    /**
     * 加载当前用户有权限访问的部门列表
     *
     * @param userPrincipal
     * @param assembleTree
     * @param rootIdList    指定部分的根节点ID
     * @return
     */
    List<OrgInfo> loadOrgList(Serializable userPrincipal, boolean assembleTree, String... rootIdList);

    /**
     * 加载当前用户有权限访问的部门列表
     *
     * @param userSupplier
     * @param userRoleSupplier
     * @param assembleTree
     * @param rootIdList
     * @return
     */
    List<OrgInfo> loadOrgList(Supplier<RbacUserObject<String>> userSupplier, Function<RbacUserObject<String>, List<RoleInfo>> userRoleSupplier, boolean assembleTree, String... rootIdList);

    /**
     * 校验机构ID是否合法
     *
     * @param userPrincipal
     * @param parentId
     * @param orgId
     */
    void checkAccessible(Serializable userPrincipal, String tenantId, String parentId, String orgId);

}
