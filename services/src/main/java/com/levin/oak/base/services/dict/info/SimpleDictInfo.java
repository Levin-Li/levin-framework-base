package com.levin.oak.base.services.dict.info;

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
import static com.levin.oak.base.entities.E_Dict.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Dict.*;
import java.util.List;
import com.levin.commons.service.support.DefaultJsonConverter;
import java.util.Date;

////////////////////////////////////

/**
 * 字典
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:43, 代码生成哈希校验码：[fe901899da1fa32e0f95cd122c8e8cbb]，请不要修改和删除此行内容。
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
public class SimpleDictInfo implements Serializable {

    private static final long serialVersionUID = -445779596L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @NotNull
    @Schema(title = L_type)
    Type type;

    @NotBlank
    @Size(max = 256)
    @Schema(title = L_code)
    String code;

    @InjectVar(domain = "dao", converter = DefaultJsonConverter.class, isRequired = "false")
    @Schema(title = L_itemList, description = D_itemList)
    List<Item> itemList;

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
