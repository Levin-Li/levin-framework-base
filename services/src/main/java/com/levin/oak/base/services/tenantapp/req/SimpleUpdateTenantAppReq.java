package com.levin.oak.base.services.tenantapp.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import com.levin.commons.dao.annotation.Ignore;

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
//自动导入列表
import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新租户应用
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月29日 上午10:45:58, 代码生成哈希校验码：[032ad5135b6661d3e34deeac5c14483d]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = TenantApp.class, alias = E_TenantApp.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateTenantAppReq extends MultiTenantReq<SimpleUpdateTenantAppReq> {

    private static final long serialVersionUID = 1292984857L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final Set<String> needUpdateFields = new HashSet<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件" , hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && !#_isQuery && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

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
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_modules)
    List<String> modules;

    @Size(max = 255)
    @Schema(title = L_appSecret , description = D_appSecret)
    String appSecret;

    @Schema(title = L_salePrice , description = D_salePrice)
    BigDecimal salePrice;

    @Schema(title = L_purchasePrice , description = D_purchasePrice)
    BigDecimal purchasePrice;

    @Size(max = 255)
    @Schema(title = L_orderNo , description = D_orderNo)
    String orderNo;

    @Schema(title = L_expiredTime , description = D_expiredTime)
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

    @Eq(desc = "乐观锁更新条件")
    @Update(incrementMode = true, paramExpr = "1", condition = "", desc = "乐观锁版本号 + 1")
    @Schema(title = L_optimisticLock)
    Integer optimisticLock;


    public SimpleUpdateTenantAppReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateTenantAppReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateTenantAppReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_TenantApp.name);
    }
    public <T extends SimpleUpdateTenantAppReq> T setLogo(String logo) {
        this.logo = logo;
        return addUpdateField(E_TenantApp.logo);
    }
    public <T extends SimpleUpdateTenantAppReq> T setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
        return addUpdateField(E_TenantApp.entryUrl);
    }
    public <T extends SimpleUpdateTenantAppReq> T setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
        return addUpdateField(E_TenantApp.infoUrl);
    }
    public <T extends SimpleUpdateTenantAppReq> T setModules(List<String> modules) {
        this.modules = modules;
        return addUpdateField(E_TenantApp.modules);
    }
    public <T extends SimpleUpdateTenantAppReq> T setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return addUpdateField(E_TenantApp.appSecret);
    }
    public <T extends SimpleUpdateTenantAppReq> T setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
        return addUpdateField(E_TenantApp.salePrice);
    }
    public <T extends SimpleUpdateTenantAppReq> T setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
        return addUpdateField(E_TenantApp.purchasePrice);
    }
    public <T extends SimpleUpdateTenantAppReq> T setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return addUpdateField(E_TenantApp.orderNo);
    }
    public <T extends SimpleUpdateTenantAppReq> T setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
        return addUpdateField(E_TenantApp.expiredTime);
    }
    public <T extends SimpleUpdateTenantAppReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_TenantApp.lastUpdateTime);
    }
    public <T extends SimpleUpdateTenantAppReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_TenantApp.orderCode);
    }
    public <T extends SimpleUpdateTenantAppReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_TenantApp.enable);
    }
    public <T extends SimpleUpdateTenantAppReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_TenantApp.editable);
    }
    public <T extends SimpleUpdateTenantAppReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_TenantApp.remark);
    }
    public <T extends SimpleUpdateTenantAppReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_TenantApp.optimisticLock);
    }

   ////////////////////////////////////////////////////////////////////////////////

    /**
    * 是否更新字段
    *
    * @param fieldName
    * @return
    */
    public boolean isUpdateField(String fieldName) {
        return needUpdateFields.contains(fieldName);
    }

    /**
    * 是否更新字段，并删除更新标记，下次调用将不再更新
    *
    * @param fieldName
    * @return 需要更新字段返回 true
    */
    public <T extends SimpleUpdateTenantAppReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateTenantAppReq> T addUpdateField(String fieldName) {
        boolean isAdd = this.forceUpdate && needUpdateFields.add(fieldName);
        return (T) this;
    }

}
