package com.levin.oak.base.services.user.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新用户
 * Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(description = "修改密码")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS)
//默认更新注解
@Update
public class UpdateUserPwdReq extends MultiTenantReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(description = "id", hidden = true)
    @NotNull
    @Eq(require = true)
    @InjectVar(InjectConsts.USER_ID)
    private String id;

    @Size(max = 128)
    @Schema(description = "旧密码")
    @NotBlank
    @Eq(require = true, value = E_User.password)
    private String oldPassword;

    @Size(max = 128)
    @Schema(description = "新密码")
    @NotBlank
    private String password;

    public UpdateUserPwdReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

    }

}
