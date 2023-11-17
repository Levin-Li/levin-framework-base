package com.levin.oak.base.services.tenantapp.req;

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
import static com.levin.oak.base.entities.E_TenantApp.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;

////////////////////////////////////

/**
 * 新增租户应用
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月17日 下午5:31:50, 代码生成哈希校验码：[5381ebf2c303be5f689a04b4c5ac4805]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = TenantApp.class, alias = E_TenantApp.ALIAS)
public class SimpleCreateTenantAppReq extends MultiTenantReq {

    private static final long serialVersionUID = 1292984857L;

    @Schema(title = L_name)
    @NotBlank
    @Size(max = 64)
    String name;

    @Schema(title = L_logo)
    @Size(max = 255)
    String logo;

    @Schema(title = L_entryUrl)
    @Size(max = 255)
    String entryUrl;

    @Schema(title = L_infoUrl)
    @Size(max = 255)
    String infoUrl;

    @Schema(title = L_modules)
    @Size(max = 1800)
    @InjectVar(
            domain = "dao",
            isRequired = "false",
            converter = PrimitiveArrayJsonConverter.class,
            expectBaseType = String.class)
    List<String> modules;

    @Schema(title = L_appSecret, description = D_appSecret)
    @Size(max = 255)
    String appSecret;

    @Schema(title = L_salePrice, description = D_salePrice)
    BigDecimal salePrice;

    @Schema(title = L_purchasePrice, description = D_purchasePrice)
    BigDecimal purchasePrice;

    @Schema(title = L_orderNo, description = D_orderNo)
    @Size(max = 255)
    String orderNo;

    @Schema(title = L_expiredTime, description = D_expiredTime)
    Date expiredTime;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等
    }
}
