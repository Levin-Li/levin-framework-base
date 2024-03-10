package com.levin.oak.base.services.dynamicverifycodecfg.req;

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

import com.levin.oak.base.entities.DynamicVerifyCodeCfg;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_DynamicVerifyCodeCfg.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.oak.base.entities.VerifyCodeType;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新动态验证码配置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月10日 上午9:21:46, 代码生成哈希校验码：[915ab4d343f39981417ddef41cae3f92]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = DynamicVerifyCodeCfg.class, alias = E_DynamicVerifyCodeCfg.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateDynamicVerifyCodeCfgReq extends MultiTenantReq<SimpleUpdateDynamicVerifyCodeCfgReq> {

    private static final long serialVersionUID = -491629507L;

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

    @Size(max = 255)
    @Schema(title = L_title)
    String title;

    @Size(max = 64)
    @Schema(title = L_moduleId)
    String moduleId;

    @Size(max = 64)
    @Schema(title = L_moduleName)
    String moduleName;

    @Size(max = 1800)
    @Schema(title = L_domainList , description = D_domainList)
    String domainList;

    @Size(max = 1800)
    @Schema(title = L_regionList , description = D_regionList)
    String regionList;

    @Size(max = 1800)
    @Schema(title = L_ipList , description = D_ipList)
    String ipList;

    @Size(max = 1800)
    @Schema(title = L_ipExcludeList , description = D_ipExcludeList)
    String ipExcludeList;

    @Schema(title = L_verifyCodeType)
    VerifyCodeType verifyCodeType;

    @Size(max = 16)
    @Schema(title = L_verifyCodeParamName , description = D_verifyCodeParamName)
    String verifyCodeParamName;

    @Size(max = 16)
    @Schema(title = L_appIdParamName , description = D_appIdParamName)
    String appIdParamName;

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


    public SimpleUpdateDynamicVerifyCodeCfgReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateDynamicVerifyCodeCfgReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setTitle(String title) {
        this.title = title;
        return addUpdateField(E_DynamicVerifyCodeCfg.title);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setModuleId(String moduleId) {
        this.moduleId = moduleId;
        return addUpdateField(E_DynamicVerifyCodeCfg.moduleId);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return addUpdateField(E_DynamicVerifyCodeCfg.moduleName);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setDomainList(String domainList) {
        this.domainList = domainList;
        return addUpdateField(E_DynamicVerifyCodeCfg.domainList);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setRegionList(String regionList) {
        this.regionList = regionList;
        return addUpdateField(E_DynamicVerifyCodeCfg.regionList);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setIpList(String ipList) {
        this.ipList = ipList;
        return addUpdateField(E_DynamicVerifyCodeCfg.ipList);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setIpExcludeList(String ipExcludeList) {
        this.ipExcludeList = ipExcludeList;
        return addUpdateField(E_DynamicVerifyCodeCfg.ipExcludeList);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setVerifyCodeType(VerifyCodeType verifyCodeType) {
        this.verifyCodeType = verifyCodeType;
        return addUpdateField(E_DynamicVerifyCodeCfg.verifyCodeType);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setVerifyCodeParamName(String verifyCodeParamName) {
        this.verifyCodeParamName = verifyCodeParamName;
        return addUpdateField(E_DynamicVerifyCodeCfg.verifyCodeParamName);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setAppIdParamName(String appIdParamName) {
        this.appIdParamName = appIdParamName;
        return addUpdateField(E_DynamicVerifyCodeCfg.appIdParamName);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_DynamicVerifyCodeCfg.lastUpdateTime);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_DynamicVerifyCodeCfg.orderCode);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_DynamicVerifyCodeCfg.enable);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_DynamicVerifyCodeCfg.editable);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_DynamicVerifyCodeCfg.remark);
    }
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_DynamicVerifyCodeCfg.optimisticLock);
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
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateDynamicVerifyCodeCfgReq> T addUpdateField(String fieldName) {
        boolean isAdd = this.forceUpdate && needUpdateFields.add(fieldName);
        return (T) this;
    }

}
