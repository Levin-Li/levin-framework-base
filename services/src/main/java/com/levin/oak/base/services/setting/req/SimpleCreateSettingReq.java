package com.levin.oak.base.services.setting.req;

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
import static com.levin.oak.base.entities.E_Setting.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.oak.base.entities.Setting.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 新增系统设置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午11:01:07, 代码生成哈希校验码：[49f48ccfd56937228a45927c9a75e8d1]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
public class SimpleCreateSettingReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 147875794L;

    @Schema(title = L_categoryName)
    @NotBlank
    @Size(max = 64)
    String categoryName;

    @Schema(title = L_groupName)
    @Size(max = 64)
    String groupName;

    @Schema(title = L_code)
    @NotBlank
    @Size(max = 64)
    String code;

    @Schema(title = L_valueType)
    @NotNull
    ValueType valueType;

    @Schema(title = L_valueContent)
    String valueContent;

    @Schema(title = L_nullable)
    Boolean nullable;

    @Schema(title = L_inputPlaceholder)
    @Size(max = 128)
    String inputPlaceholder;

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

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等
    }
}
