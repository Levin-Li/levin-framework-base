package com.levin.oak.base.services.role.req;

// import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
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

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Role.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
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
 * 新增角色
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 上午2:26:19, 代码生成哈希校验码：[946860cc961b90becb40aeef8e267501]，请不要修改和删除此行内容。
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
// @EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS)
public class CreateRoleReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445356492L;

    @Schema(title = L_code)
    @NotBlank
    @Size(max = 128)
    String code;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_orgDataScope, description = D_orgDataScope)
    @NotNull
    OrgDataScope orgDataScope;

    @Schema(title = L_assignedOrgIdList, description = D_assignedOrgIdList)
    @InjectVar(
            domain = "dao",
            isRequired = "false",
            converter = PrimitiveArrayJsonConverter.class,
            expectBaseType = String.class)
    List<String> assignedOrgIdList;

    @Schema(title = L_permissionList, description = D_permissionList)
    @InjectVar(
            domain = "dao",
            isRequired = "false",
            converter = PrimitiveArrayJsonConverter.class,
            expectBaseType = String.class)
    List<String> permissionList;

    @Schema(title = L_domain, description = D_domain)
    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    String domain;

    @Schema(title = L_name)
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_optimisticLock)
    @JsonIgnore(value = true)
    Integer optimisticLock;

    @Schema(title = L_creator, hidden = true)
    // @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    // @Size(max = 128)
    String creator;

    @Schema(title = L_createTime, hidden = true)
    // @NotNull
    Date createTime;

    @Schema(title = L_lastUpdateTime, hidden = true)
    Date lastUpdateTime;

    @Schema(title = L_orderCode, hidden = true)
    Integer orderCode;

    @Schema(title = L_enable, hidden = true)
    // @NotNull
    Boolean enable;

    @Schema(title = L_editable, hidden = true)
    // @NotNull
    Boolean editable;

    @Schema(title = L_remark, hidden = true)
    // @Size(max = 512)
    String remark;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等

        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }
    }
}
