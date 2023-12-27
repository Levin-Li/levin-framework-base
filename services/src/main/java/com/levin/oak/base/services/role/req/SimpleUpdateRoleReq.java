package com.levin.oak.base.services.role.req;

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

import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Role.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Role;
import java.util.List;
import com.levin.oak.base.entities.Role.*;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.oak.base.services.role.info.*;
import java.util.Set;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新角色
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月25日 下午6:02:57, 代码生成哈希校验码：[6d289ab186d9ab1e68d61840abbb947a]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateRoleReq extends MultiTenantOrgReq<SimpleUpdateRoleReq> {

    private static final long serialVersionUID = -445356492L;

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

    @Size(max = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    @Schema(title = L_domain , description = D_domain)
    String domain;

    @Size(max = 128)
    @Schema(title = L_parentId)
    String parentId;

    @Schema(title = L_extendable)
    Boolean extendable;

    @Schema(title = L_mutex)
    Boolean mutex;

    @Schema(title = L_userLimit)
    Integer userLimit;

    @Size(max = 1800)
    @Schema(title = L_precondition , description = D_precondition)
    String precondition;

    @Size(max = 128)
    @Schema(title = L_code)
    String code;

    @Schema(title = L_expiredDate)
    Date expiredDate;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_orgDataScope , description = D_orgDataScope)
    OrgDataScope orgDataScope;

    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_assignedOrgIdList , description = D_assignedOrgIdList)
    List<String> assignedOrgIdList;

    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_permissionList , description = D_permissionList)
    List<String> permissionList;

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


    public SimpleUpdateRoleReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateRoleReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateRoleReq> T setDomain(String domain) {
        this.domain = domain;
        return addUpdateField(E_Role.domain);
    }
    public <T extends SimpleUpdateRoleReq> T setParentId(String parentId) {
        this.parentId = parentId;
        return addUpdateField(E_Role.parentId);
    }
    public <T extends SimpleUpdateRoleReq> T setExtendable(Boolean extendable) {
        this.extendable = extendable;
        return addUpdateField(E_Role.extendable);
    }
    public <T extends SimpleUpdateRoleReq> T setMutex(Boolean mutex) {
        this.mutex = mutex;
        return addUpdateField(E_Role.mutex);
    }
    public <T extends SimpleUpdateRoleReq> T setUserLimit(Integer userLimit) {
        this.userLimit = userLimit;
        return addUpdateField(E_Role.userLimit);
    }
    public <T extends SimpleUpdateRoleReq> T setPrecondition(String precondition) {
        this.precondition = precondition;
        return addUpdateField(E_Role.precondition);
    }
    public <T extends SimpleUpdateRoleReq> T setCode(String code) {
        this.code = code;
        return addUpdateField(E_Role.code);
    }
    public <T extends SimpleUpdateRoleReq> T setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
        return addUpdateField(E_Role.expiredDate);
    }
    public <T extends SimpleUpdateRoleReq> T setIcon(String icon) {
        this.icon = icon;
        return addUpdateField(E_Role.icon);
    }
    public <T extends SimpleUpdateRoleReq> T setOrgDataScope(OrgDataScope orgDataScope) {
        this.orgDataScope = orgDataScope;
        return addUpdateField(E_Role.orgDataScope);
    }
    public <T extends SimpleUpdateRoleReq> T setAssignedOrgIdList(List<String> assignedOrgIdList) {
        this.assignedOrgIdList = assignedOrgIdList;
        return addUpdateField(E_Role.assignedOrgIdList);
    }
    public <T extends SimpleUpdateRoleReq> T setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
        return addUpdateField(E_Role.permissionList);
    }
    public <T extends SimpleUpdateRoleReq> T setNodePath(String nodePath) {
        this.nodePath = nodePath;
        return addUpdateField(E_Role.nodePath);
    }
    public <T extends SimpleUpdateRoleReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_Role.name);
    }
    public <T extends SimpleUpdateRoleReq> T setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
        return addUpdateField(E_Role.pinyinName);
    }
    public <T extends SimpleUpdateRoleReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_Role.lastUpdateTime);
    }
    public <T extends SimpleUpdateRoleReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_Role.orderCode);
    }
    public <T extends SimpleUpdateRoleReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_Role.enable);
    }
    public <T extends SimpleUpdateRoleReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_Role.editable);
    }
    public <T extends SimpleUpdateRoleReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_Role.remark);
    }
    public <T extends SimpleUpdateRoleReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_Role.optimisticLock);
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
    public <T extends SimpleUpdateRoleReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateRoleReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.add(fieldName);
        return (T) this;
    }

}
