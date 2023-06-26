package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.util.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Role;

import com.levin.oak.base.services.role.*;
import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Role.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
////////////////////////////////////

/**
 *  角色-业务服务
 *
 * @author auto gen by simple-dao-codegen 2023年6月26日 下午6:06:01
 * 代码生成哈希校验码：[b925f45c4ab6d774c1676c55e0ff4d6b]
 */

@Tag(name = E_Role.BIZ_NAME + "-业务服务", description = "")
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
