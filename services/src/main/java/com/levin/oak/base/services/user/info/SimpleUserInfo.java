package com.levin.oak.base.services.user.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
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
import com.levin.oak.base.entities.User.*;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import java.util.Date;
import com.levin.oak.base.entities.Org;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月10日 上午2:41:22, 代码生成哈希校验码：[c80c8588bb53b3b026f546d8dc5b44ca]，请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(
        exclude = {
            "org",
        })
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
@Select
public class SimpleUserInfo implements Serializable {

    private static final long serialVersionUID = -445263479L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 20)
    @Schema(title = L_telephone, description = D_telephone)
    String telephone;

    @Size(max = 32)
    @Schema(title = L_email, description = D_email)
    String email;

    @Size(max = 256)
    @JsonIgnore
    @Schema(title = L_password)
    String password;

    @Size(max = 32)
    @Schema(title = L_nickname)
    String nickname;

    @Schema(title = L_avatar)
    String avatar;

    @Schema(title = L_sex)
    Sex sex;

    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_tagList)
    List<String> tagList;

    @Schema(title = L_category)
    Category category;

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

    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_roleList)
    List<String> roleList;

    // @Fetch //默认不加载，请通过查询对象控制
    @Schema(title = L_org)
    OrgInfo org;

    @Size(max = 64)
    @Schema(title = L_wxOpenId)
    String wxOpenId;

    @Size(max = 64)
    @Schema(title = L_aliOpenId)
    String aliOpenId;

    @Size(max = 128)
    @Schema(title = L_domain)
    String domain;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name)
    String name;

    @Size(max = 128)
    @Schema(title = L_orgId)
    String orgId;

    @Size(max = 128)
    @Schema(title = L_tenantId)
    String tenantId;
}
