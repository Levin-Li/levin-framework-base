package com.levin.oak.base.services.tenantapp.info;

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
import static com.levin.oak.base.entities.E_TenantApp.*;
////////////////////////////////////
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 租户应用
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 下午5:31:50, 代码生成哈希校验码：[ea885110899d5e303362b41dbdd0ea98]，请不要修改和删除此行内容。
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
public class SimpleTenantAppInfo implements Serializable {

    private static final long serialVersionUID = 1292984857L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Size(max = 255)
    @Schema(title = L_logo)
    String logo;

    @Size(max = 255)
    @Schema(title = L_entryUrl)
    String entryUrl;

    @Size(max = 255)
    @Schema(title = L_infoUrl)
    String infoUrl;

    @Size(max = 1800)
    @InjectVar(
            domain = "dao",
            isRequired = "false",
            converter = PrimitiveArrayJsonConverter.class,
            expectBaseType = String.class)
    @Schema(title = L_modules)
    List<String> modules;

    @Size(max = 255)
    @Schema(title = L_appSecret, description = D_appSecret)
    String appSecret;

    @Schema(title = L_salePrice, description = D_salePrice)
    BigDecimal salePrice;

    @Schema(title = L_purchasePrice, description = D_purchasePrice)
    BigDecimal purchasePrice;

    @Size(max = 255)
    @Schema(title = L_orderNo, description = D_orderNo)
    String orderNo;

    @Schema(title = L_expiredTime, description = D_expiredTime)
    Date expiredTime;

    @Size(max = 128)
    @InjectVar(value = InjectConst.TENANT_ID)
    @Schema(title = L_tenantId)
    String tenantId;
}
