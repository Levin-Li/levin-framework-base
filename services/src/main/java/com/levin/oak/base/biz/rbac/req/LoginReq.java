package com.levin.oak.base.biz.rbac.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.IsNull;
import com.levin.commons.dao.annotation.logic.OR;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.domain.ServiceReq;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 用户认证
 *
 * @Author Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
@Schema(description = "用户认证")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS, resultClass = UserInfo.class)
public class LoginReq implements ServiceReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(description = "登录名/手机号/邮箱")
    @OR(autoClose = true)
    @Eq(E_User.loginName)
    @Eq(E_User.telephone)
    @Eq(E_User.email)
    @NotBlank
    private String account;

    @Schema(description = "登录密码")
    @Eq(require = true)
    @NotBlank
    private String password;

    @Schema(description = "租户ID", hidden = true)
    @InjectVar(isRequired = "false")
    @OR(autoClose = true)
    @IsNull
    @Eq
    private String tenantId;

//    @Schema(description = "验证码")
//    @Ignore
//    private String verificationCode;

    @Schema(description = "客户端类型", hidden = true)
    @Ignore
    private String ua;

    @Schema(description = "客户端类型", hidden = true)
    @Ignore
    private String clientType;

    @Schema(description = "签名串")
    @Ignore
    private String sign;

}