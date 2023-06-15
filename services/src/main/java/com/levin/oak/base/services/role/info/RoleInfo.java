package com.levin.oak.base.services.role.info;


import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */
@Schema(title = "角色")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = -445356492L;


    @NotNull
    @Schema(title = "id")
    private String id;


    @NotBlank
    @Schema(title = "编码")
    private String code;


    @Schema(title = "图标")
    private String icon;


    @NotNull
    @Schema(title = "部门数据权限")
    private OrgDataScope orgDataScope;


    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "指定的部门列表")
    private List<String> assignedOrgIdList;


    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "资源权限列表")
    private List<String> permissionList;


    @Size(max = 64)
    @Schema(title = "租户ID")
    private String tenantId;


    @Size(max = 64)
    @Schema(title = "系统子域")
    private String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称")
    private String name;


    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(title = "创建者")
    private String creator;


    @NotNull
    @Schema(title = "创建时间")
    private Date createTime;


    @Schema(title = "更新时间")
    private Date lastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(title = "是否允许")
    private Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑")
    private Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;


}
