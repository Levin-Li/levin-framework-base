package com.levin.oak.base.services.menures.req;

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

import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_MenuRes.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.MenuRes;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.rbac.MenuItem.*;
import java.util.Set;
import com.levin.oak.base.services.menures.info.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新菜单
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月25日 下午6:02:58, 代码生成哈希校验码：[da82e31eb7201384b37e115318f12e0b]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateMenuResReq extends MultiTenantReq<SimpleUpdateMenuResReq> {

    private static final long serialVersionUID = -887712701L;

    //需要更新的字段
    @Ignore //dao 忽略
    @Schema(title = "需要更新的字段", hidden = true)
    protected final Set<String> needUpdateFields = new HashSet<>(5);

    @Schema(title = "是否强制更新", description = "强制更新模式时，只要字段被调用set方法，则会被更新，不管是否空值" , hidden = true)
    @Ignore //dao 忽略
    protected final boolean forceUpdate;

    //////////////////////////////////////////////////////////////////

    @Schema(description = "可编辑条件，如果是web环境需要增加可编辑的过滤条件" , hidden = true)
    @Eq(condition = IS_WEB_CONTEXT + " && " + NOT_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 64)
    @Schema(title = L_parentId)
    String parentId;

    @Schema(title = L_domain , description = D_domain)
    String domain;

    @Schema(title = L_module , description = D_module)
    String module;

    @Size(max = 1800)
    @Schema(title = L_requireAuthorizations)
    String requireAuthorizations;

    @Schema(title = L_alwaysShow)
    Boolean alwaysShow;

    @Size(max = 64)
    @Schema(title = L_target)
    String target;

    @Schema(title = L_actionType)
    ActionType actionType;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_path)
    String path;

    @Size(max = 1800)
    @Schema(title = L_params)
    String params;

    @Size(max = 1800)
    @Schema(title = L_nodePath , description = D_nodePath)
    String nodePath;

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


    public SimpleUpdateMenuResReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateMenuResReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateMenuResReq> T setParentId(String parentId) {
        this.parentId = parentId;
        return addUpdateField(E_MenuRes.parentId);
    }
    public <T extends SimpleUpdateMenuResReq> T setDomain(String domain) {
        this.domain = domain;
        return addUpdateField(E_MenuRes.domain);
    }
    public <T extends SimpleUpdateMenuResReq> T setModule(String module) {
        this.module = module;
        return addUpdateField(E_MenuRes.module);
    }
    public <T extends SimpleUpdateMenuResReq> T setRequireAuthorizations(String requireAuthorizations) {
        this.requireAuthorizations = requireAuthorizations;
        return addUpdateField(E_MenuRes.requireAuthorizations);
    }
    public <T extends SimpleUpdateMenuResReq> T setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
        return addUpdateField(E_MenuRes.alwaysShow);
    }
    public <T extends SimpleUpdateMenuResReq> T setTarget(String target) {
        this.target = target;
        return addUpdateField(E_MenuRes.target);
    }
    public <T extends SimpleUpdateMenuResReq> T setActionType(ActionType actionType) {
        this.actionType = actionType;
        return addUpdateField(E_MenuRes.actionType);
    }
    public <T extends SimpleUpdateMenuResReq> T setIcon(String icon) {
        this.icon = icon;
        return addUpdateField(E_MenuRes.icon);
    }
    public <T extends SimpleUpdateMenuResReq> T setPath(String path) {
        this.path = path;
        return addUpdateField(E_MenuRes.path);
    }
    public <T extends SimpleUpdateMenuResReq> T setParams(String params) {
        this.params = params;
        return addUpdateField(E_MenuRes.params);
    }
    public <T extends SimpleUpdateMenuResReq> T setNodePath(String nodePath) {
        this.nodePath = nodePath;
        return addUpdateField(E_MenuRes.nodePath);
    }
    public <T extends SimpleUpdateMenuResReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_MenuRes.name);
    }
    public <T extends SimpleUpdateMenuResReq> T setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
        return addUpdateField(E_MenuRes.pinyinName);
    }
    public <T extends SimpleUpdateMenuResReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_MenuRes.lastUpdateTime);
    }
    public <T extends SimpleUpdateMenuResReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_MenuRes.orderCode);
    }
    public <T extends SimpleUpdateMenuResReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_MenuRes.enable);
    }
    public <T extends SimpleUpdateMenuResReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_MenuRes.editable);
    }
    public <T extends SimpleUpdateMenuResReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_MenuRes.remark);
    }
    public <T extends SimpleUpdateMenuResReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_MenuRes.optimisticLock);
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
    public <T extends SimpleUpdateMenuResReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateMenuResReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.add(fieldName);
        return (T) this;
    }

}
