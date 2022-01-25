package com.levin.oak.base.services.role.info;


import com.levin.commons.dao.annotation.select.Select;
import com.levin.commons.rbac.ResPermission;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Role.OrgDataScope;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
* 角色
* @Author Auto gen by simple-dao-codegen 2022-1-18 13:59:49
*/
@Schema(description ="角色")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@Select
public class RoleInfo2 implements Serializable {

   private static final long serialVersionUID = -445356492L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @NotBlank
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
   @Size(max = 64)
   @Schema(description = "租户ID")
   private String tenantId;


   @Size(max = 64)
   @Schema(description = "系统子域")
   private String domain;


   @NotBlank
   @Size(max = 512)
   @Schema(description = "名称")
   private String name;


   @Size(max = 512)
   @Schema(description = "拼音名称-拼音首字母")
   private String pinyinName;


   @Size(max = 128)
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


   @Size(max = 512)
   @Schema(description = "备注")
   private String remark;


}
