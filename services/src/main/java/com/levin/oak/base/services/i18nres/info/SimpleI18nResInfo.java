package com.levin.oak.base.services.i18nres.info;

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
import static com.levin.oak.base.entities.E_I18nRes.*;
////////////////////////////////////
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 国际化资源
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月19日 上午12:53:17, 代码生成哈希校验码：[92781ef521bef1d83494cf5ffe30a0af]，请不要修改和删除此行内容。
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
public class SimpleI18nResInfo implements Serializable {

    private static final long serialVersionUID = -1681554652L;

    @NotNull
    @Schema(title = L_id)
    Long id;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category)
    String category;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_lang)
    String lang;

    @NotBlank
    @Size(max = 768)
    @Schema(title = L_label)
    String label;

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
