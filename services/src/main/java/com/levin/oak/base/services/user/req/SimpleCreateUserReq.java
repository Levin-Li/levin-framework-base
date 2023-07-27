package com.levin.oak.base.services.user.req;

// import static com.levin.oak.base.ModuleOption.*;
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
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.User.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;

////////////////////////////////////

/**
 * 新增用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:44, 代码生成哈希校验码：[2a5d71aca805604c62254cb885bcbfd3]，请不要修改和删除此行内容。
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
// @EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
public class SimpleCreateUserReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(title = L_telephone, description = D_telephone)
    @Size(max = 20)
    String telephone;

    @Schema(title = L_email, description = D_email)
    @Size(max = 32)
    String email;

    @Schema(title = L_password)
    @Size(max = 256)
    String password;

    @Schema(title = L_nickname)
    @Size(max = 32)
    String nickname;

    @Schema(title = L_avatar)
    String avatar;

    @Schema(title = L_sex)
    Sex sex;

    @Schema(title = L_tagList)
    @InjectVar(
            domain = "dao",
            expectBaseType = String.class,
            converter = PrimitiveArrayJsonConverter.class,
            isRequired = "false")
    @Size(max = 1800)
    List<String> tagList;

    @Schema(title = L_category)
    Category category;

    @Schema(title = L_expiredDate)
    Date expiredDate;

    @Schema(title = L_state)
    @NotNull
    State state;

    @Schema(title = L_staffNo)
    @Size(max = 32)
    String staffNo;

    @Schema(title = L_jobPostCode)
    @Size(max = 128)
    String jobPostCode;

    @Schema(title = L_roleList)
    @InjectVar(
            domain = "dao",
            expectBaseType = String.class,
            converter = PrimitiveArrayJsonConverter.class,
            isRequired = "false")
    @Size(max = 1800)
    List<String> roleList;

    @Schema(title = L_wxOpenId)
    @Size(max = 64)
    String wxOpenId;

    @Schema(title = L_aliOpenId)
    @Size(max = 64)
    String aliOpenId;

    @Schema(title = L_domain)
    @Size(max = 128)
    String domain;

    @Schema(title = L_name)
    @NotBlank
    @Size(max = 64)
    String name;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等
    }
}
