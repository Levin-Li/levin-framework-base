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
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Role.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
////////////////////////////////////

/**
 * 角色
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 下午6:00:38, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[8668d81ac22e906be5553b862330233c], 请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = -445356492L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = REQUIRED)
    String id;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_code , required = true, requiredMode = REQUIRED)
    String code;


    @Schema(title = L_icon )
    String icon;


    @NotNull
    @Schema(title = L_orgDataScope , description = D_orgDataScope , required = true, requiredMode = REQUIRED)
    OrgDataScope orgDataScope;


    @InjectVar(domain = "dao",  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = L_assignedOrgIdList , description = D_assignedOrgIdList )
    List<String> assignedOrgIdList;


    @InjectVar(domain = "dao",  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = L_permissionList , description = D_permissionList )
    List<String> permissionList;


    @Size(max = 128)
    @Schema(title = L_domain )
    String domain;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name , required = true, requiredMode = REQUIRED)
    String name;


    @Size(max = 128)
    @Schema(title = L_orgId )
    String orgId;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_creator )
    String creator;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = REQUIRED)
    Date createTime;


    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;


    @Schema(title = L_orderCode )
    Integer orderCode;


    @NotNull
    @Schema(title = L_enable , required = true, requiredMode = REQUIRED)
    Boolean enable;


    @NotNull
    @Schema(title = L_editable , required = true, requiredMode = REQUIRED)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = L_remark )
    String remark;


}
