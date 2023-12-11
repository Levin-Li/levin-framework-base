package com.levin.oak.base.services.org.req;

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

import com.levin.oak.base.entities.Org;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Org.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.Date;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Org.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新机构
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月11日 上午9:08:40, 代码生成哈希校验码：[53e32099a95ebc74e98c26811de33805]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = Org.class, alias = E_Org.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateOrgReq extends MultiTenantReq {

    private static final long serialVersionUID = -1399842458L;

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
    @Schema(title = L_parentId)
    String parentId;

    @Size(max = 64)
    @Schema(title = L_code , description = D_code)
    String code;

    @Schema(title = L_icon)
    String icon;

    @Schema(title = L_state)
    State state;

    @Schema(title = L_type)
    Type type;

    @Size(max = 64)
    @Schema(title = L_industries)
    String industries;

    @Size(max = 64)
    @Schema(title = L_areaCode)
    String areaCode;

    @Size(max = 128)
    @Schema(title = L_level , description = D_level)
    String level;

    @Size(max = 128)
    @Schema(title = L_category , description = D_category)
    String category;

    @Schema(title = L_isExternal , description = D_isExternal)
    Boolean isExternal;

    @Size(max = 64)
    @Schema(title = L_contacts)
    String contacts;

    @Size(max = 20)
    @Schema(title = L_phones)
    String phones;

    @Size(max = 32)
    @Schema(title = L_emails)
    String emails;

    @Schema(title = L_address)
    String address;

    @Size(max = 32)
    @Schema(title = L_zipCode)
    String zipCode;

    @Schema(title = L_extInfo)
    String extInfo;

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


    public SimpleUpdateOrgReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateOrgReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateOrgReq> T setParentId(String parentId) {
        this.parentId = parentId;
        return addUpdateField(E_Org.parentId);
    }
    public <T extends SimpleUpdateOrgReq> T setCode(String code) {
        this.code = code;
        return addUpdateField(E_Org.code);
    }
    public <T extends SimpleUpdateOrgReq> T setIcon(String icon) {
        this.icon = icon;
        return addUpdateField(E_Org.icon);
    }
    public <T extends SimpleUpdateOrgReq> T setState(State state) {
        this.state = state;
        return addUpdateField(E_Org.state);
    }
    public <T extends SimpleUpdateOrgReq> T setType(Type type) {
        this.type = type;
        return addUpdateField(E_Org.type);
    }
    public <T extends SimpleUpdateOrgReq> T setIndustries(String industries) {
        this.industries = industries;
        return addUpdateField(E_Org.industries);
    }
    public <T extends SimpleUpdateOrgReq> T setAreaCode(String areaCode) {
        this.areaCode = areaCode;
        return addUpdateField(E_Org.areaCode);
    }
    public <T extends SimpleUpdateOrgReq> T setLevel(String level) {
        this.level = level;
        return addUpdateField(E_Org.level);
    }
    public <T extends SimpleUpdateOrgReq> T setCategory(String category) {
        this.category = category;
        return addUpdateField(E_Org.category);
    }
    public <T extends SimpleUpdateOrgReq> T setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
        return addUpdateField(E_Org.isExternal);
    }
    public <T extends SimpleUpdateOrgReq> T setContacts(String contacts) {
        this.contacts = contacts;
        return addUpdateField(E_Org.contacts);
    }
    public <T extends SimpleUpdateOrgReq> T setPhones(String phones) {
        this.phones = phones;
        return addUpdateField(E_Org.phones);
    }
    public <T extends SimpleUpdateOrgReq> T setEmails(String emails) {
        this.emails = emails;
        return addUpdateField(E_Org.emails);
    }
    public <T extends SimpleUpdateOrgReq> T setAddress(String address) {
        this.address = address;
        return addUpdateField(E_Org.address);
    }
    public <T extends SimpleUpdateOrgReq> T setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return addUpdateField(E_Org.zipCode);
    }
    public <T extends SimpleUpdateOrgReq> T setExtInfo(String extInfo) {
        this.extInfo = extInfo;
        return addUpdateField(E_Org.extInfo);
    }
    public <T extends SimpleUpdateOrgReq> T setNodePath(String nodePath) {
        this.nodePath = nodePath;
        return addUpdateField(E_Org.nodePath);
    }
    public <T extends SimpleUpdateOrgReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_Org.name);
    }
    public <T extends SimpleUpdateOrgReq> T setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
        return addUpdateField(E_Org.pinyinName);
    }
    public <T extends SimpleUpdateOrgReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_Org.lastUpdateTime);
    }
    public <T extends SimpleUpdateOrgReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_Org.orderCode);
    }
    public <T extends SimpleUpdateOrgReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_Org.enable);
    }
    public <T extends SimpleUpdateOrgReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_Org.editable);
    }
    public <T extends SimpleUpdateOrgReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_Org.remark);
    }
    public <T extends SimpleUpdateOrgReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_Org.optimisticLock);
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
    public <T extends SimpleUpdateOrgReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateOrgReq> T addUpdateField(String fieldName) {
        boolean isAdd = needUpdateFields.contains(fieldName) || needUpdateFields.add(fieldName);
        return (T) this;
    }

}
