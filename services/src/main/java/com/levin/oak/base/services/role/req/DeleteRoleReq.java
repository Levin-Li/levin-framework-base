package com.levin.oak.base.services.role.req;

import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.rbac.RbacRoleObject;
import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;

import java.util.*;

import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.*;

import static com.levin.oak.base.entities.E_Role.*;

import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConst;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Role.*;

import java.util.List;

import com.levin.commons.service.support.PrimitiveArrayJsonConverter;

import java.util.Date;
////////////////////////////////////

/**
 * 删除角色
 * //Auto gen by simple-dao-codegen 2023年6月28日 上午9:06:27
 * 代码生成哈希校验码：[c733cf16bfc7348d3493ee0907132ba0]
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
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

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConst.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    //不允许删除SA角色
    @Schema(title = "无需设置", hidden = true)
    @NotEq(require = true)
    final String notEqCode = RbacRoleObject.SA_ROLE;

    @Schema(title = L_id + "集合", required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @In(value = E_Role.id)
    @NotEmpty
    private String[] idList;

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
