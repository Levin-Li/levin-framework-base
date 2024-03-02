package com.levin.oak.base.services.user.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

import com.levin.commons.rbac.RbacUserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
/////////////////////////////////////////////////////
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;

import static com.levin.oak.base.entities.E_User.*;
////////////////////////////////////
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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////


/**
 * 用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:06, 代码生成哈希校验码：[88bf38beb5323c0ab4d5678555552bf1]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"org",})
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
public class UserInfo implements RbacUserInfo<String>, Serializable, MultiTenantObject, OrganizedObject, StatefulObject {

    private static final long serialVersionUID = -445263479L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 128)
    @InjectVar(value = InjectConst.ORG_ID)
    @Schema(title = L_orgId)
    String orgId;

    @Size(max = 20)
    @Schema(title = L_telephone, description = D_telephone)
    String telephone;

    @Size(max = 32)
    @Schema(title = L_email, description = D_email)
    String email;

    @Size(max = 256)
    @JsonIgnore(value = true)
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

    @NotNull
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

    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_org)
    OrgInfo org;

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
    @InjectVar(value = InjectConst.TENANT_ID)
    @Schema(title = L_tenantId)
    String tenantId;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_pinyinName, description = D_pinyinName)
    String pinyinName;

    @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @NotNull
    @Schema(title = L_createTime)
    Date createTime;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @NotNull
    @Schema(title = L_enable)
    Boolean enable;

    @NotNull
    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

}
