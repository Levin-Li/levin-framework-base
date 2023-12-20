package com.levin.oak.base.biz;

import static com.levin.oak.base.entities.EntityConst.*;

import java.io.Serializable;
import java.util.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;


////////////////////////////////////
//自动导入列表

import javax.validation.constraints.NotNull;
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
     * 加载当前用户有权限访问的部门列表
     *
     * @return
     */
    List<OrgInfo> loadOrgList(Serializable userPrincipal, boolean assembleTree);

    /**
     * 校验机构ID是否合法
     *
     * @param userPrincipal
     * @param parentId
     * @param orgId
     */
    void checkAccessible(Serializable userPrincipal, String parentId, String orgId);

}
