package com.levin.oak.base.services.role.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.role.info.*;
import com.levin.oak.base.entities.Role;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Role.*;
    import java.util.List;
    import com.levin.commons.rbac.ResPermission;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询角色
 *  @Author Auto gen by simple-dao-codegen 2021-11-23 16:11:31
 */
@Schema(description = "查询角色")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS
, resultClass = RoleInfo.class)
public class QueryRoleReq implements ServiceReq  {

    private static final long serialVersionUID = -445356492L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "编码")
    private String code;


    @Schema(description = "图标")
    private String icon;


    @Schema(description = "部门数据权限")
    private OrgDataScope orgDataScope;


    @Schema(description = "指定的部门列表")
    private String assignedOrgIdList;


    @Schema(description = "资源权限")
    private String permissions;




    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "系统子域")
    private String domain;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "大于等于创建时间")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间")
    @Lte
    private Date lteCreateTime;



    @Schema(description = "大于等于更新时间")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间")
    @Lte
    private Date lteLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryRoleReq(Long id) {
        this.id = id;
    }
}
