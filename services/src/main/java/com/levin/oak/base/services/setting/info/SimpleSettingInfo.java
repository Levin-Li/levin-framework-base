package com.levin.oak.base.services.setting.info;

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
import static com.levin.oak.base.entities.E_Setting.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Setting.*;
import java.util.Date;
////////////////////////////////////

/**
 * 系统设置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月25日 13:59:25, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[8b3790d1c3a517cc70fd65deba4471ae], 请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({tenantId})
@Select
public class SimpleSettingInfo implements Serializable {

    private static final long serialVersionUID = 147875794L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_categoryName)
    String categoryName;

    @Size(max = 64)
    @Schema(title = L_groupName)
    String groupName;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_code)
    String code;

    @NotNull
    @Schema(title = L_valueType)
    ValueType valueType;

    @Schema(title = L_valueContent)
    String valueContent;

    @Schema(title = L_nullable)
    Boolean nullable;

    @Size(max = 128)
    @Schema(title = L_inputPlaceholder)
    String inputPlaceholder;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_orgId)
    String orgId;

    @Size(max = 128)
    @Schema(title = L_tenantId)
    String tenantId;


}
