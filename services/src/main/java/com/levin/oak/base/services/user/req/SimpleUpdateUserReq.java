package com.levin.oak.base.services.user.req;

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

import com.levin.oak.base.entities.User;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_User.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.User.*;
import java.util.Date;
import com.levin.commons.dao.domain.OrganizedObject;
import java.io.Serializable;
import com.levin.commons.dao.domain.StatefulObject;
import com.levin.commons.service.support.InjectConst;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.oak.base.entities.Org;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 更新用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:06, 代码生成哈希校验码：[0a587f32f3794ab8aa7f8a1eb06b1f92]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)

//字段更新策略，强制更新时，只要字段被调用set方法，则会被更新，不管是否空值。否则只有值不为[null，空字符串, 空数组，空集合]时才会被更新。
@Update(condition = "forceUpdate ? isUpdateField(#_fieldName) : #" + C.VALUE_NOT_EMPTY)
public class SimpleUpdateUserReq extends MultiTenantOrgReq<SimpleUpdateUserReq> {

    private static final long serialVersionUID = -445263479L;

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

    @Size(max = 20)
    @Schema(title = L_telephone , description = D_telephone)
    String telephone;

    @Size(max = 32)
    @Schema(title = L_email , description = D_email)
    String email;

    @Size(max = 256)
    @Schema(title = L_password)
    String password;

    @Size(max = 32)
    @Schema(title = L_nickname)
    String nickname;

    @Schema(title = L_avatar)
    String avatar;

    @Schema(title = L_sex)
    Sex sex;

    @Size(max = 1800)
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_tagList)
    List<String> tagList;

    @Schema(title = L_category)
    Category category;

    @Schema(title = L_loginFailedCount)
    Integer loginFailedCount;

    @Schema(title = L_lockExpiredTime)
    Date lockExpiredTime;

    @Schema(title = L_expiredDate)
    Date expiredDate;

    @Schema(title = L_state)
    State state;

    @Size(max = 32)
    @Schema(title = L_staffNo)
    String staffNo;

    @Size(max = 128)
    @Schema(title = L_jobPostCode)
    String jobPostCode;

    @Size(max = 1800)
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    @Schema(title = L_roleList)
    List<String> roleList;

    @Size(max = 64)
    @Schema(title = L_mfaSecretKey)
    String mfaSecretKey;

    @Size(max = 64)
    @Schema(title = L_wxOpenId)
    String wxOpenId;

    @Size(max = 64)
    @Schema(title = L_aliOpenId)
    String aliOpenId;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

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


    public SimpleUpdateUserReq() {
        this.forceUpdate = false;
    }

    /**
    * 强制更新
    *
    * @param forceUpdate
    */
    public SimpleUpdateUserReq(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

    public <T extends SimpleUpdateUserReq> T setTelephone(String telephone) {
        this.telephone = telephone;
        return addUpdateField(E_User.telephone);
    }
    public <T extends SimpleUpdateUserReq> T setEmail(String email) {
        this.email = email;
        return addUpdateField(E_User.email);
    }
    public <T extends SimpleUpdateUserReq> T setPassword(String password) {
        this.password = password;
        return addUpdateField(E_User.password);
    }
    public <T extends SimpleUpdateUserReq> T setNickname(String nickname) {
        this.nickname = nickname;
        return addUpdateField(E_User.nickname);
    }
    public <T extends SimpleUpdateUserReq> T setAvatar(String avatar) {
        this.avatar = avatar;
        return addUpdateField(E_User.avatar);
    }
    public <T extends SimpleUpdateUserReq> T setSex(Sex sex) {
        this.sex = sex;
        return addUpdateField(E_User.sex);
    }
    public <T extends SimpleUpdateUserReq> T setTagList(List<String> tagList) {
        this.tagList = tagList;
        return addUpdateField(E_User.tagList);
    }
    public <T extends SimpleUpdateUserReq> T setCategory(Category category) {
        this.category = category;
        return addUpdateField(E_User.category);
    }
    public <T extends SimpleUpdateUserReq> T setLoginFailedCount(Integer loginFailedCount) {
        this.loginFailedCount = loginFailedCount;
        return addUpdateField(E_User.loginFailedCount);
    }
    public <T extends SimpleUpdateUserReq> T setLockExpiredTime(Date lockExpiredTime) {
        this.lockExpiredTime = lockExpiredTime;
        return addUpdateField(E_User.lockExpiredTime);
    }
    public <T extends SimpleUpdateUserReq> T setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
        return addUpdateField(E_User.expiredDate);
    }
    public <T extends SimpleUpdateUserReq> T setState(State state) {
        this.state = state;
        return addUpdateField(E_User.state);
    }
    public <T extends SimpleUpdateUserReq> T setStaffNo(String staffNo) {
        this.staffNo = staffNo;
        return addUpdateField(E_User.staffNo);
    }
    public <T extends SimpleUpdateUserReq> T setJobPostCode(String jobPostCode) {
        this.jobPostCode = jobPostCode;
        return addUpdateField(E_User.jobPostCode);
    }
    public <T extends SimpleUpdateUserReq> T setRoleList(List<String> roleList) {
        this.roleList = roleList;
        return addUpdateField(E_User.roleList);
    }
    public <T extends SimpleUpdateUserReq> T setMfaSecretKey(String mfaSecretKey) {
        this.mfaSecretKey = mfaSecretKey;
        return addUpdateField(E_User.mfaSecretKey);
    }
    public <T extends SimpleUpdateUserReq> T setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
        return addUpdateField(E_User.wxOpenId);
    }
    public <T extends SimpleUpdateUserReq> T setAliOpenId(String aliOpenId) {
        this.aliOpenId = aliOpenId;
        return addUpdateField(E_User.aliOpenId);
    }
    public <T extends SimpleUpdateUserReq> T setDomain(String domain) {
        this.domain = domain;
        return addUpdateField(E_User.domain);
    }
    public <T extends SimpleUpdateUserReq> T setName(String name) {
        this.name = name;
        return addUpdateField(E_User.name);
    }
    public <T extends SimpleUpdateUserReq> T setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName;
        return addUpdateField(E_User.pinyinName);
    }
    public <T extends SimpleUpdateUserReq> T setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return addUpdateField(E_User.lastUpdateTime);
    }
    public <T extends SimpleUpdateUserReq> T setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
        return addUpdateField(E_User.orderCode);
    }
    public <T extends SimpleUpdateUserReq> T setEnable(Boolean enable) {
        this.enable = enable;
        return addUpdateField(E_User.enable);
    }
    public <T extends SimpleUpdateUserReq> T setEditable(Boolean editable) {
        this.editable = editable;
        return addUpdateField(E_User.editable);
    }
    public <T extends SimpleUpdateUserReq> T setRemark(String remark) {
        this.remark = remark;
        return addUpdateField(E_User.remark);
    }
    public <T extends SimpleUpdateUserReq> T setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
        return addUpdateField(E_User.optimisticLock);
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
    public <T extends SimpleUpdateUserReq> T removeUpdateField(String fieldName) {
          needUpdateFields.remove(fieldName);
        return (T) this;
    }

    /**
    * 添加更新字段
    *
    * @param fieldName
    * @return
    */
    public <T extends SimpleUpdateUserReq> T addUpdateField(String fieldName) {
        boolean isAdd = this.forceUpdate && needUpdateFields.add(fieldName);
        return (T) this;
    }

}
