package com.levin.oak.base.services.setting.req;

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

import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Setting.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.levin.oak.base.entities.Setting.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新系统设置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:26, 代码生成哈希校验码：[2e761c0767d73600e0d703f7de50352b]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateSettingReq extends MultiTenantOrgReq<SimpleUpdateSettingReq> {

    private static final long serialVersionUID = 147875794L;

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

    @Size(max = 64)
    @Schema(title = L_categoryName)
    String categoryName;

    @Size(max = 64)
    @Schema(title = L_groupName)
    String groupName;

    @Size(max = 64)
    @Schema(title = L_code)
    String code;

    @Schema(title = L_valueType)
    ValueType valueType;

    @Schema(title = L_valueContent)
    String valueContent;

    @Schema(title = L_nullable)
    Boolean nullable;

    @Size(max = 128)
    @Schema(title = L_inputPlaceholder)
    String inputPlaceholder;

    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain , description = D_domain)
    String domain;

    @Size(max = 64)
    @Schema(title = L_name)
    String name;

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


    public SimpleUpdateSettingReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateSettingReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateSettingReq> T setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return addUpdateField(E_Setting.categoryName);
    }
    public <T extends SimpleUpdateSettingReq> T setGroupName(String groupName) {
        this.groupName = groupName;
        return addUpdateField(E_Setting.groupName);
    }
    public <T extends SimpleUpdateSettingReq> T setCode(String code) {
        this.code = code;
        return addUpdateField(E_Setting.code);
    }
    public <T extends SimpleUpdateSettingReq> T setValueType(ValueType valueType) {
        this.valueType = valueType;
        return addUpdateField(E_Setting.valueType);
    }
    public <T extends SimpleUpdateSettingReq> T setValueContent(String valueContent) {
        this.valueContent = valueContent;
        return addUpdateField(E_Setting.valueContent);
    }
    public <T extends SimpleUpdateSettingReq> T setNullable(Boolean nullable) {
        this.nullable = nullable;
        return addUpdateField(E_Setting.nullable);
    }
    public <T extends SimpleUpdateSettingReq> T setInputPlaceholder(String inputPlaceholder) {
        this.inputPlaceholder = inputPlaceholder;
        return addUpdateField(E_Setting.inputPlaceholder);
    }
    public <T extends SimpleUpdateSettingReq> T setDomain(String domain) {
        this.domain = domain;
        return addUpdateField(E_Setting.domain);
    }
    public <T extends SimpleUpdateSettingReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_Setting.name);
    }
    public <T extends SimpleUpdateSettingReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_Setting.lastUpdateTime);
    }
    public <T extends SimpleUpdateSettingReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_Setting.orderCode);
    }
    public <T extends SimpleUpdateSettingReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_Setting.enable);
    }
    public <T extends SimpleUpdateSettingReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_Setting.editable);
    }
    public <T extends SimpleUpdateSettingReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_Setting.remark);
    }
    public <T extends SimpleUpdateSettingReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_Setting.optimisticLock);
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
    public <T extends SimpleUpdateSettingReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateSettingReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.contains(fieldName) || needUpdateFields.add(fieldName);
        return (T) this;
    }

}
