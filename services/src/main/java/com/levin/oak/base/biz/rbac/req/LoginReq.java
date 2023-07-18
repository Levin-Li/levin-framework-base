package com.levin.oak.base.biz.rbac.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.IsNull;
import com.levin.commons.dao.annotation.logic.OR;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.rbac.AuthReq;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.domain.ServiceReq;
import com.levin.commons.service.support.InjectConsts;
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
@Schema(title = "用户认证")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = User.class, alias = E_User.ALIAS, resultClass = UserInfo.class)
public class LoginReq implements AuthReq, ServiceReq {

    private static final long serialVersionUID = -445263479L;

    @Schema(title = "租户ID", hidden = true)
    @InjectVar(isRequired = "false")
    //租户绑定域名启用时，可以
//    @OR(autoClose = true, condition = "#tenantBindDomainEnable")
    @IsNull(condition = "#account == 'sa'", desc = "只有 sa 用户允许租户ID为空")
    @Eq(condition = "#account != 'sa'", desc = "只有 sa 用户允许租户ID为空")
    protected String tenantId;

    @Schema(title = "登录名/手机号/邮箱", required = true)
    @NotBlank
    @OR(autoClose = true)
    @Eq(E_User.telephone)
    @Eq(E_User.email)
    @OrderBy(E_User.id)//排序
    protected String account;

    @Schema(title = "登录密码", description = "有短信验证码时，可以不要密码", required = true)
//    @NotBlank
    @Eq
    protected String password;

    @Schema(title = "图片/短信验证码")
    @Ignore
    protected String verificationCode;

    @Schema(title = "验证码类型", description = "可选择值：sms,captcha，默认是captcha")
    @Ignore
    protected String verificationCodeType = "captcha";

    @Schema(title = "客户端类型", hidden = true)
    @Ignore
    @InjectVar(value = InjectConsts.USER_AGENT, isRequired = "false")
    protected String ua;

    @Schema(title = "客户端类型", hidden = true)
    @Ignore
    protected String clientType;

    @Schema(title = "应用ID")
    @Ignore
    protected String appId;

}
