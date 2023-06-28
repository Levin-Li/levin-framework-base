package com.levin.oak.base.services.user.info;

import static com.levin.oak.base.entities.EntityConst.*;

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
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.User.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import org.springframework.data.annotation.ReadOnlyProperty;
////////////////////////////////////

/**
 * 用户
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 上午9:18:57
 * 代码生成哈希校验码：[f413cf640406caa92f17377be5406505]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"org",})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class UserInfo implements RbacUserInfo<String>, Serializable {

    private static final long serialVersionUID = -445263479L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String id;


    @Size(max = 20)
    @Schema(title = L_telephone , description = D_telephone )
    String telephone;


    @Size(max = 32)
    @Schema(title = L_email , description = D_email )
    String email;


    @Size(max = 256)
    @Schema(title = L_password )
    String password;


    @Size(max = 32)
    @Schema(title = L_nickname )
    String nickname;


    @Schema(title = L_avatar )
    String avatar;


    @Schema(title = L_sex )
    Sex sex;


    @InjectVar(domain = "dao",  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_tagList )
    List<String> tagList;


    @Schema(title = L_category )
    Category category;


    @Schema(title = L_expiredDate )
    Date expiredDate;


    @NotNull
    @Schema(title = L_state , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    State state;


    @Size(max = 32)
    @Schema(title = L_staffNo )
    String staffNo;


    @Size(max = 128)
    @Schema(title = L_jobPostCode )
    String jobPostCode;


    @InjectVar(domain = "dao",  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_roleList )
    List<String> roleList;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_org )
    OrgInfo org;


    @Size(max = 64)
    @Schema(title = L_wxOpenId )
    String wxOpenId;


    @Size(max = 64)
    @Schema(title = L_aliOpenId )
    String aliOpenId;


    @Size(max = 64)
    @Schema(title = L_orgId )
    String orgId;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_domain )
    String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String name;


    @Size(max = 128)
    @Schema(title = L_creator )
    String creator;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date createTime;


    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;


    @Schema(title = L_orderCode )
    Integer orderCode;


    @NotNull
    @Schema(title = L_enable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean enable;


    @NotNull
    @Schema(title = L_editable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = L_remark )
    String remark;

    @ReadOnlyProperty
    @JsonIgnore
    @Override
    public boolean isTenantAdmin() {
        return RbacUserInfo.super.isTenantAdmin();
    }

    @ReadOnlyProperty
    @JsonIgnore
    @Override
    public boolean isSuperAdmin() {
        return RbacUserInfo.super.isSuperAdmin();
    }

}
