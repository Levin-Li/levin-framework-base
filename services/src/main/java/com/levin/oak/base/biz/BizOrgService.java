package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.dao.domain.support.AbstractTreeObject;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import java.io.Serializable;
import java.util.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.validation.annotation.*;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Org;
import com.levin.oak.base.biz.bo.org.*;
import com.levin.oak.base.services.org.*;
import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;

import com.levin.oak.base.*;
import com.levin.oak.base.services.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;

import java.util.Date;

import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;

import java.util.Set;

import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

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
     * 创建记录，返回主键ID
     *
     * @param req
     * @return pkId 主键ID
     */
    @Operation(summary = CREATE_ACTION)
    String create(Serializable userPrincipal, @NotNull CreateOrgReq req);

    /**
     * 通过主键查找记录，同时可能注入其它过滤条件（如租户过滤，部门过滤，人员过滤），试图增加数据安全性
     *
     * @param req
     * @return data 数据详情
     */
    @Operation(summary = VIEW_DETAIL_ACTION)
    OrgInfo findById(Serializable userPrincipal, @NotNull OrgIdReq req);

    /**
     * 更新记录，并返回更新是否成功
     *
     * @param req
     * @param queryObjs 附加的查询条件或是更新内容
     * @return boolean 是否成功
     */
    @Operation(summary = UPDATE_ACTION)
    boolean update(Serializable userPrincipal, @NotNull UpdateOrgReq req, Object... queryObjs);

    /**
     * 删除记录，并返回删除是否成功
     *
     * @param req
     * @return boolean 删除是否成功
     */
    @Operation(summary = DELETE_ACTION)
    boolean delete(Serializable userPrincipal, @NotNull OrgIdReq req);

}
