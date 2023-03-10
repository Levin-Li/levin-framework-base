package com.levin.oak.base.services.role.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.In;
import com.levin.commons.dao.annotation.NotEq;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 删除角色
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */
@Schema(description = "删除角色")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS)
public class DeleteRoleReq extends MultiTenantReq {

    private static final long serialVersionUID = -445356492L;

    @Schema(description = "id集合")
    @In(value = E_Role.id, require = true)
    @NotEmpty
    private String[] idList;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    //不允许删除SA角色
    @Schema(description = "无需设置", hidden = true)
    @NotEq(require = true)
    private final String notEqCode = RbacRoleObject.SA_ROLE;

    public DeleteRoleReq(String... idList) {
        this.idList = idList;
    }

    public DeleteRoleReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
