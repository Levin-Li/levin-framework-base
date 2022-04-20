package com.levin.oak.base.services.role.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.domain.InjectVar;
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

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新角色
 * Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */
@Schema(description = "更新角色")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS)
//默认更新注解
@Update
public class UpdateRoleReq extends MultiTenantReq {

    private static final long serialVersionUID = -445356492L;

    @Schema(description = "id", required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#user.isSuperAdmin()")
    final boolean eqEditable = true;

    //编码不允许修改
//    @NotBlank
//    @Schema(description = "编码")
//    private String code;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "部门数据权限")
    private OrgDataScope orgDataScope;

    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class)
    @Schema(description = "指定的部门列表")
    private List<String> assignedOrgIdList;

    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class)
    @Schema(description = "资源权限列表")
    private List<String> permissionList;

    @Size(max = 64)
    @Schema(description = "系统子域")
    private String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    private String name;

    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


    public UpdateRoleReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }

}
