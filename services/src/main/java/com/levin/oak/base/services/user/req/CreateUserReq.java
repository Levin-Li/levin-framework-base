package com.levin.oak.base.services.user.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
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
 * 新增用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月18日 下午3:08:57, 代码生成哈希校验码：[c75649995e5bd5ee57b5648a081c7c81]，请不要修改和删除此行内容。
 *
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
public class CreateUserReq extends MultiTenantOrgReq<CreateUserReq> {

    private static final long serialVersionUID = -445263479L;

    @Schema(title = L_telephone , description = D_telephone )
    @Size(max = 20)
    String telephone;

    @Schema(title = L_email , description = D_email )
    @Size(max = 32)
    String email;

    @Schema(title = L_password )
    @Size(max = 256)
    String password;

    @Schema(title = L_nickname )
    @Size(max = 32)
    String nickname;

    @Schema(title = L_avatar )
    String avatar;

    @Schema(title = L_sex )
    Sex sex;

    @Schema(title = L_tagList )
    @Size(max = 1800)
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    List<String> tagList;

    @Schema(title = L_category )
    Category category;

    @Schema(title = L_loginFailedCount )
    Integer loginFailedCount;

    @Schema(title = L_lockExpiredTime )
    Date lockExpiredTime;

    @Schema(title = L_expiredDate )
    Date expiredDate;

    @Schema(title = L_state )
    @NotNull
    State state;

    @Schema(title = L_staffNo )
    @Size(max = 32)
    String staffNo;

    @Schema(title = L_jobPostCode )
    @Size(max = 128)
    String jobPostCode;

    @Schema(title = L_roleList )
    @Size(max = 1800)
    @InjectVar(domain = "dao", isRequired = "false", converter = PrimitiveArrayJsonConverter.class, expectBaseType = String.class)
    List<String> roleList;

    @Schema(title = L_mfaSecretKey )
    @Size(max = 64)
    String mfaSecretKey;

    @Schema(title = L_wxOpenId )
    @Size(max = 64)
    String wxOpenId;

    @Schema(title = L_aliOpenId )
    @Size(max = 64)
    String aliOpenId;

    @Schema(title = L_name )
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = L_pinyinName , description = D_pinyinName )
    @Size(max = 128)
    String pinyinName;

    @Schema(title = L_creator , hidden = true)
    @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Size(max = 128)
    String creator;

    @Schema(title = L_createTime , hidden = true)
    Date createTime;

    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;

    @Schema(title = L_orderCode )
    Integer orderCode;

    @Schema(title = L_enable )
    Boolean enable;

    @Schema(title = L_editable )
    Boolean editable;

    @Schema(title = L_remark )
    @Size(max = 512)
    String remark;

    @Schema(title = L_optimisticLock )
    Integer optimisticLock;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等

        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
    }
}
