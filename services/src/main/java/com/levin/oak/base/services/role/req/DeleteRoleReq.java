package com.levin.oak.base.services.role.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
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
import com.levin.oak.base.entities.Role;
import java.util.List;
import com.levin.oak.base.entities.Role.*;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.oak.base.services.role.info.*;
import java.util.Set;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 删除角色
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:26, 代码生成哈希校验码：[bad6ce49f168c1bb187073ee93c5ea49]，请不要修改和删除此行内容。
 *
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS)
public class DeleteRoleReq extends MultiTenantOrgReq<DeleteRoleReq> {

    private static final long serialVersionUID = -445356492L;

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件" , hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;


    @Schema(title = L_id + "集合", required = true, requiredMode = REQUIRED)
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
