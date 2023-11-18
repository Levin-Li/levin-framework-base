package com.levin.oak.base.services.role.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
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
import static com.levin.oak.base.entities.E_Role.*;
////////////////////////////////////
import java.util.List;
import com.levin.oak.base.entities.Role.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 角色
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月19日 上午12:53:14, 代码生成哈希校验码：[7e33a8a029525ca3e5c2bd7022c7e3b0]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
@Select
public class SimpleRoleInfo implements Serializable {

    private static final long serialVersionUID = -445356492L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_code)
    String code;

    @Schema(title = L_icon)
    String icon;

    @NotNull
    @Schema(title = L_orgDataScope, description = D_orgDataScope)
    OrgDataScope orgDataScope;

    // @InjectVar(domain = "dao", isRequired = "false", converter =
    // PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_assignedOrgIdList, description = D_assignedOrgIdList)
    List<String> assignedOrgIdList;

    // @InjectVar(domain = "dao", isRequired = "false", converter =
    // PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_permissionList, description = D_permissionList)
    List<String> permissionList;

    // @InjectVar(value = "sysDomain", isRequired = "false")
    @Size(max = 128)
    @Schema(title = L_domain, description = D_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @JsonIgnore(value = true)
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    // @InjectVar(value = InjectConst.ORG_ID)
    @Size(max = 128)
    @Schema(title = L_orgId)
    String orgId;

    // @InjectVar(value = InjectConst.TENANT_ID)
    @Size(max = 128)
    @Schema(title = L_tenantId)
    String tenantId;
}
