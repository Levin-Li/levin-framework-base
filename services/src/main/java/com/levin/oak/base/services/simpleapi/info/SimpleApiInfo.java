package com.levin.oak.base.services.simpleapi.info;

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
import static com.levin.oak.base.entities.E_SimpleApi.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.oak.base.entities.SimpleApi.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////


/**
 * 简单动态接口
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月14日 下午3:54:11, 代码生成哈希校验码：[0551f79fa1a69d69bd42bd46c7cdbb13]，请不要修改和删除此行内容。
 *
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
public class SimpleApiInfo implements Serializable {

    private static final long serialVersionUID = 1021385738L;


    @Size(max = 16)
    @Schema(title = L_methods , description = D_methods)
    String methods;

    @NotNull
    @Schema(title = L_language)
    Language language;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_type)
    String type;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_category)
    String category;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_groupName)
    String groupName;

    @Schema(title = L_icon)
    String icon;

    @NotBlank
    @Size(max = 800)
    @Schema(title = L_path)
    String path;

    @InjectVar(domain = "dao",  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations)
    List<String> requireAuthorizations;

    @Schema(title = L_content)
    String content;

    @Size(max = 128)
    @Schema(title = L_domain , description = D_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @JsonIgnore(value=true)
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

    @Size(max = 128)
    @Schema(title = L_orgId)
    String orgId;

    @Size(max = 128)
    @Schema(title = L_tenantId)
    String tenantId;

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
