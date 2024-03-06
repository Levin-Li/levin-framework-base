package com.levin.oak.base.services.whitelist.req;

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

import com.levin.oak.base.entities.Whitelist;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Whitelist.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新白名单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月6日 下午2:55:00, 代码生成哈希校验码：[27427b9f666126ada2c414cae9e2ad07]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Whitelist.class, alias = E_Whitelist.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateWhitelistReq extends BaseReq {

    private static final long serialVersionUID = 1491943753L;

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
    @Schema(title = L_moduleId)
    String moduleId;

    @Size(max = 64)
    @Schema(title = L_moduleName)
    String moduleName;

    @Size(max = 255)
    @Schema(title = L_title)
    String title;

    @Size(max = 1800)
    @Schema(title = L_domainList , description = D_domainList)
    String domainList;

    @Size(max = 1800)
    @Schema(title = L_regionList , description = D_regionList)
    String regionList;

    @Size(max = 1800)
    @Schema(title = L_ipList , description = D_ipList)
    String ipList;

    @Schema(title = L_osList , description = D_osList)
    String osList;

    @Schema(title = L_browserList , description = D_browserList)
    String browserList;

    @Schema(title = L_browserTypeList , description = D_browserTypeList)
    String browserTypeList;

    @Size(max = 64)
    @Schema(title = L_methodList , description = D_methodList)
    String methodList;

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


    public SimpleUpdateWhitelistReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateWhitelistReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateWhitelistReq> T setModuleId(String moduleId) {
        this.moduleId = moduleId;
        return addUpdateField(E_Whitelist.moduleId);
    }
    public <T extends SimpleUpdateWhitelistReq> T setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return addUpdateField(E_Whitelist.moduleName);
    }
    public <T extends SimpleUpdateWhitelistReq> T setTitle(String title) {
        this.title = title;
        return addUpdateField(E_Whitelist.title);
    }
    public <T extends SimpleUpdateWhitelistReq> T setDomainList(String domainList) {
        this.domainList = domainList;
        return addUpdateField(E_Whitelist.domainList);
    }
    public <T extends SimpleUpdateWhitelistReq> T setRegionList(String regionList) {
        this.regionList = regionList;
        return addUpdateField(E_Whitelist.regionList);
    }
    public <T extends SimpleUpdateWhitelistReq> T setIpList(String ipList) {
        this.ipList = ipList;
        return addUpdateField(E_Whitelist.ipList);
    }
    public <T extends SimpleUpdateWhitelistReq> T setOsList(String osList) {
        this.osList = osList;
        return addUpdateField(E_Whitelist.osList);
    }
    public <T extends SimpleUpdateWhitelistReq> T setBrowserList(String browserList) {
        this.browserList = browserList;
        return addUpdateField(E_Whitelist.browserList);
    }
    public <T extends SimpleUpdateWhitelistReq> T setBrowserTypeList(String browserTypeList) {
        this.browserTypeList = browserTypeList;
        return addUpdateField(E_Whitelist.browserTypeList);
    }
    public <T extends SimpleUpdateWhitelistReq> T setMethodList(String methodList) {
        this.methodList = methodList;
        return addUpdateField(E_Whitelist.methodList);
    }
    public <T extends SimpleUpdateWhitelistReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_Whitelist.lastUpdateTime);
    }
    public <T extends SimpleUpdateWhitelistReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_Whitelist.orderCode);
    }
    public <T extends SimpleUpdateWhitelistReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_Whitelist.enable);
    }
    public <T extends SimpleUpdateWhitelistReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_Whitelist.editable);
    }
    public <T extends SimpleUpdateWhitelistReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_Whitelist.remark);
    }
    public <T extends SimpleUpdateWhitelistReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_Whitelist.optimisticLock);
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
    public <T extends SimpleUpdateWhitelistReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateWhitelistReq> T addUpdateField(String fieldName) {
        boolean isAdd = this.forceUpdate && needUpdateFields.add(fieldName);
        return (T) this;
    }

}
