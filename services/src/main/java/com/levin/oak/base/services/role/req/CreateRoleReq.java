package com.levin.oak.base.services.role.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.Role.OrgDataScope;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增角色
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:51
 */
@Schema(description = "新增角色")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS)
public class CreateRoleReq extends MultiTenantReq {

    private static final long serialVersionUID = -445356492L;

    @Schema(description = "编码", required = true)
    @NotBlank
    private String code;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "部门数据权限", required = true)
    @NotNull
    private OrgDataScope orgDataScope;

    @Schema(description = "指定的部门列表")
    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> assignedOrgIdList;

    @Schema(description = "资源权限列表")
    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> permissionList;

    @Schema(description = "系统子域")
    @Size(max = 64)
    private String domain;

    @Schema(description = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(description = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    private String pinyinName;

    @Schema(description = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(description = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(description = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(description = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(description = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(description = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(description = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据



        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
