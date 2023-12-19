package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.io.Serializable;
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
import com.levin.commons.service.support.InjectConst;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Role.*;

import java.util.List;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;

import javax.validation.constraints.NotNull;
import java.util.Date;
////////////////////////////////////

/**
 * 角色-业务服务
 *
 * @author auto gen by simple-dao-codegen 2023年6月26日 下午6:06:01
 * 代码生成哈希校验码：[b925f45c4ab6d774c1676c55e0ff4d6b]
 */

@Tag(name = E_Role.BIZ_NAME + "-业务服务", description = "")
public interface BizRoleService<U> {

    /**
     * 查询列表
     *
     * @param req
     * @param paging
     * @return
     */
    @Operation(summary = QUERY_LIST_ACTION)
    PagingData<RoleInfo> list(U userPrincipal, QueryRoleReq req, SimplePaging paging);

    /**
     * 查询用户角色列表
     *
     * @param userPrincipal
     * @return
     */
    List<RoleInfo> loadUserRoleList(U userPrincipal);

    /**
     * 创建记录，返回主键ID
     *
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    String create(U userPrincipal, @NotNull CreateRoleReq req);

    /**
     * 更新记录，并返回更新是否成功
     *
     * @param req
     * @return boolean 是否成功
     */
    @Operation(summary = UPDATE_ACTION)
    boolean update(U userPrincipal, @NotNull UpdateRoleReq req);

    /**
     * 删除记录，并返回删除是否成功
     *
     * @param req
     * @return boolean 删除是否成功
     */
    @Operation(summary = DELETE_ACTION)
    boolean delete(U userPrincipal, @NotNull RoleIdReq req);

}
