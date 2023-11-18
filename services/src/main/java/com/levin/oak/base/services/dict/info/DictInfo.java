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
import java.util.List;
import java.util.Date;
import com.levin.oak.base.entities.Dict.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.DefaultJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 字典
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月19日 上午12:53:15, 代码生成哈希校验码：[998c8c63b4f18093fd1f833b649c55d6]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
public class DictInfo implements Serializable {

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

    // @InjectVar(domain = "dao", isRequired = "false", converter = DefaultJsonConverter.class,
    // expectBaseType = String.class)
    @Schema(title = L_itemList, description = D_itemList)
    List<Item> itemList;

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

    // @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @NotNull
    @Schema(title = L_createTime)
    Date createTime;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @NotNull
    @Schema(title = L_enable)
    Boolean enable;

    @NotNull
    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;
}
