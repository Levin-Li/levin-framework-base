package com.levin.oak.base.services.i18nres.req;

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

import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_I18nRes.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新国际化资源
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月7日 下午2:10:56, 代码生成哈希校验码：[dd4ebc4ef3e08c45ba151ebe8bcb8f6f]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateI18nResReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -1681554652L;

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
    @Schema(title = L_category)
    String category;

    @Size(max = 64)
    @Schema(title = L_lang)
    String lang;

    @Size(max = 768)
    @Schema(title = L_label)
    String label;

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


    public SimpleUpdateI18nResReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateI18nResReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateI18nResReq> T setCategory(String category) {
        this.category = category;
        return addUpdateField(E_I18nRes.category);
    }
    public <T extends SimpleUpdateI18nResReq> T setLang(String lang) {
        this.lang = lang;
        return addUpdateField(E_I18nRes.lang);
    }
    public <T extends SimpleUpdateI18nResReq> T setLabel(String label) {
        this.label = label;
        return addUpdateField(E_I18nRes.label);
    }
    public <T extends SimpleUpdateI18nResReq> T setDomain(String domain) {
        this.domain = domain;
        return addUpdateField(E_I18nRes.domain);
    }
    public <T extends SimpleUpdateI18nResReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_I18nRes.name);
    }
    public <T extends SimpleUpdateI18nResReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_I18nRes.lastUpdateTime);
    }
    public <T extends SimpleUpdateI18nResReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_I18nRes.orderCode);
    }
    public <T extends SimpleUpdateI18nResReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_I18nRes.enable);
    }
    public <T extends SimpleUpdateI18nResReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_I18nRes.editable);
    }
    public <T extends SimpleUpdateI18nResReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_I18nRes.remark);
    }
    public <T extends SimpleUpdateI18nResReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_I18nRes.optimisticLock);
    }



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
    public <T extends SimpleUpdateI18nResReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateI18nResReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.contains(fieldName) || needUpdateFields.add(fieldName);
        return (T) this;
    }


}
