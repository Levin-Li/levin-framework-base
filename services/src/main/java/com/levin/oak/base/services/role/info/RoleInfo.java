package com.levin.oak.base.services.role.info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

/////////////////////////////////////////////////////
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;

////////////////////////////////////
import com.levin.oak.base.entities.Role.*;
import java.util.List;
import com.levin.commons.rbac.ResPermission;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
* 角色
* @Author Auto gen by simple-dao-codegen 2021-11-12 9:56:30
*/
@Schema(description ="角色")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class RoleInfo implements Serializable {

   private static final long serialVersionUID = -445356492L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @NotNull
   @Schema(description = "编码")
   private String code;


   @Schema(description = "图标")
   private String icon;


   @NotNull
   @Schema(description = "部门数据权限")
   private OrgDataScope orgDataScope;


   @Schema(description = "指定的部门列表")
   private String assignedOrgIdList;


   @Schema(description = "资源权限")
   private String permissions;


   @Schema(description = "资源权限列表")
   private List<ResPermission> permissionList;


   @InjectVar
   @Schema(description = "租户ID")
   private String tenantId;


   @NotNull
   @Size(max = 512)
   @Schema(description = "名称")
   private String name;


   @Size(max = 512)
   @Schema(description = "创建者")
   private String creator;


   @NotNull
   @Schema(description = "创建时间")
   private Date createTime;


   @Schema(description = "更新时间")
   private Date lastUpdateTime;


   @Schema(description = "排序代码")
   private Integer orderCode;


   @NotNull
   @Schema(description = "是否允许")
   private Boolean enable;


   @NotNull
   @Schema(description = "是否可编辑")
   private Boolean editable;


   @Size(max = 1800)
   @Schema(description = "备注")
   private String remark;


}
