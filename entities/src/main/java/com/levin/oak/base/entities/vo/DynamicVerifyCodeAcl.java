package com.levin.oak.base.entities.vo;


import com.levin.oak.base.entities.VerifyCodeType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "动态验证码配置", description = "部分注重安全的接口需要动态验证码的支持，需要先询问服务器是否要验证码，如果要验证码则需要客户端提交验证码")
public class DynamicVerifyCodeAcl extends UrlAcl {

    @Schema(title = "验证码类型")
    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    protected VerifyCodeType verifyCodeType;

    @Schema(title = "验证码参数名称", description = "默认参数名：verifyCode")
    @Column(length = 16)
    protected String verifyCodeParamName;

    @Schema(title = "应用ID参数名称", description = "默认参数名：appId")
    @Column(length = 16)
    protected String appIdParamName;

}
