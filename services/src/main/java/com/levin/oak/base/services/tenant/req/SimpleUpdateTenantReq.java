package com.levin.oak.base.services.tenant.req;

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

import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Tenant.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新平台租户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月8日 下午11:11:15, 代码生成哈希校验码：[64e1f525476e366608a27637440e06d7]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final List<String> needUpdateFields = new ArrayList<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件" , hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 128)
    @Schema(title = L_sysName)
    String sysName;

    @Schema(title = L_sysLogo)
    String sysLogo;

    @Schema(title = L_logo)
    String logo;

    @Size(max = 128)
    @Schema(title = L_code)
    String code;

    @Size(max = 255)
    @Schema(title = L_tenantKey)
    String tenantKey;

    @Schema(title = L_balance)
    Double balance;

    @Schema(title = L_licenseCnt)
    Integer licenseCnt;

    @Schema(title = L_remainingLicenseCnt)
    Integer remainingLicenseCnt;

    @Schema(title = L_licenseExpire)
    Date licenseExpire;

    @Size(max = 32)
    @Schema(title = L_contractPerson)
    String contractPerson;

    @Size(max = 32)
    @Schema(title = L_contractPhone)
    String contractPhone;

    @Size(max = 1200)
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_domainList)
    List<String> domainList;

    @Size(max = 64)
    @Schema(title = L_appId)
    String appId;

    @Size(max = 512)
    @Schema(title = L_appSecret)
    String appSecret;

    @Size(max = 512)
    @Schema(title = L_encryptKey)
    String encryptKey;

    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName)
    String pinyinName;

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


    public SimpleUpdateTenantReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateTenantReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateTenantReq> T setSysName(String sysName) {
        this.sysName = sysName;
        return addUpdateField(E_Tenant.sysName);
    }
    public <T extends SimpleUpdateTenantReq> T setSysLogo(String sysLogo) {
        this.sysLogo = sysLogo;
        return addUpdateField(E_Tenant.sysLogo);
    }
    public <T extends SimpleUpdateTenantReq> T setLogo(String logo) {
        this.logo = logo;
        return addUpdateField(E_Tenant.logo);
    }
    public <T extends SimpleUpdateTenantReq> T setCode(String code) {
        this.code = code;
        return addUpdateField(E_Tenant.code);
    }
    public <T extends SimpleUpdateTenantReq> T setTenantKey(String tenantKey) {
        this.tenantKey = tenantKey;
        return addUpdateField(E_Tenant.tenantKey);
    }
    public <T extends SimpleUpdateTenantReq> T setBalance(Double balance) {
        this.balance = balance;
        return addUpdateField(E_Tenant.balance);
    }
    public <T extends SimpleUpdateTenantReq> T setLicenseCnt(Integer licenseCnt) {
        this.licenseCnt = licenseCnt;
        return addUpdateField(E_Tenant.licenseCnt);
    }
    public <T extends SimpleUpdateTenantReq> T setRemainingLicenseCnt(Integer remainingLicenseCnt) {
        this.remainingLicenseCnt = remainingLicenseCnt;
        return addUpdateField(E_Tenant.remainingLicenseCnt);
    }
    public <T extends SimpleUpdateTenantReq> T setLicenseExpire(Date licenseExpire) {
        this.licenseExpire = licenseExpire;
        return addUpdateField(E_Tenant.licenseExpire);
    }
    public <T extends SimpleUpdateTenantReq> T setContractPerson(String contractPerson) {
        this.contractPerson = contractPerson;
        return addUpdateField(E_Tenant.contractPerson);
    }
    public <T extends SimpleUpdateTenantReq> T setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
        return addUpdateField(E_Tenant.contractPhone);
    }
    public <T extends SimpleUpdateTenantReq> T setDomainList(List<String> domainList) {
        this.domainList = domainList;
        return addUpdateField(E_Tenant.domainList);
    }
    public <T extends SimpleUpdateTenantReq> T setAppId(String appId) {
        this.appId = appId;
        return addUpdateField(E_Tenant.appId);
    }
    public <T extends SimpleUpdateTenantReq> T setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        return addUpdateField(E_Tenant.appSecret);
    }
    public <T extends SimpleUpdateTenantReq> T setEncryptKey(String encryptKey) {
        this.encryptKey = encryptKey;
        return addUpdateField(E_Tenant.encryptKey);
    }
    public <T extends SimpleUpdateTenantReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_Tenant.name);
    }
    public <T extends SimpleUpdateTenantReq> T setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
        return addUpdateField(E_Tenant.pinyinName);
    }
    public <T extends SimpleUpdateTenantReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_Tenant.lastUpdateTime);
    }
    public <T extends SimpleUpdateTenantReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_Tenant.orderCode);
    }
    public <T extends SimpleUpdateTenantReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_Tenant.enable);
    }
    public <T extends SimpleUpdateTenantReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_Tenant.editable);
    }
    public <T extends SimpleUpdateTenantReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_Tenant.remark);
    }
    public <T extends SimpleUpdateTenantReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_Tenant.optimisticLock);
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
    public <T extends SimpleUpdateTenantReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateTenantReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.contains(fieldName) || needUpdateFields.add(fieldName);
        return (T) this;
    }

}
