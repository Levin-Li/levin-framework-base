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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Role.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Role.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
////////////////////////////////////

/**
 *  更新角色
 *  @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 下午6:00:38, 请不要修改和删除此行内容。
 *  代码生成哈希校验码：[0bc8c022bea33b18738cfc61d8b97433], 请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
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
public class UpdateRoleReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445356492L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_code)
    String code;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_orgDataScope , description = D_orgDataScope)
    OrgDataScope orgDataScope;

    @InjectVar(domain = "dao",  expectBaseType = String.class,  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = L_assignedOrgIdList , description = D_assignedOrgIdList)
    List<String> assignedOrgIdList;

    @InjectVar(domain = "dao",  expectBaseType = String.class,  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = L_permissionList , description = D_permissionList)
    List<String> permissionList;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;


    public UpdateRoleReq(String id) {
        this.id = id;
    }

    public UpdateRoleReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
        this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}
