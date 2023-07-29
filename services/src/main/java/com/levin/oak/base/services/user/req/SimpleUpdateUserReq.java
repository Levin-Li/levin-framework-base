package com.levin.oak.base.services.user.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

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
 * 更新用户
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月29日 下午11:45:32, 代码生成哈希校验码：[98c2ccbb36ee2e7d625a8b0a415927fc]，请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
// 默认更新注解
@Update
public class SimpleUpdateUserReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(description = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 20)
    @Schema(title = L_telephone, description = D_telephone)
    String telephone;

    @Size(max = 32)
    @Schema(title = L_email, description = D_email)
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

    @InjectVar(
            domain = "dao",
            expectBaseType = String.class,
            converter = PrimitiveArrayJsonConverter.class,
            isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_tagList)
    List<String> tagList;

    @Schema(title = L_category)
    Category category;

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

    @InjectVar(
            domain = "dao",
            expectBaseType = String.class,
            converter = PrimitiveArrayJsonConverter.class,
            isRequired = "false")
    @Size(max = 1800)
    @Schema(title = L_roleList)
    List<String> roleList;

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

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @PostConstruct
    public void preUpdate() {
        // @todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }
}
