package com.levin.oak.base.services.tenantapp.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.TenantApp;
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
 * 更新租户应用
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月21日 下午4:56:54, 代码生成哈希校验码：[034617d81f00637776472136fe3a6566]，请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = TenantApp.class, alias = E_TenantApp.ALIAS)
// 默认更新注解
@Update
public class UpdateTenantAppReq extends MultiTenantReq {

    private static final long serialVersionUID = 1292984857L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件", hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

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

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @Schema(title = L_enable)
    Boolean enable;

    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    public UpdateTenantAppReq(String id) {
        this.id = id;
    }

    public UpdateTenantAppReq updateIdWhenNotBlank(String id) {
        if (isNotBlank(id)) {
            this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        // @todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }
}
