package com.levin.oak.base.entities;

import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "动态验证码类型", description = "可选择值：sms,captcha,mfa; 默认是captcha，mfa是多因子认证(默认是Google Authenticator)")
public enum VerifyCodeType implements EnumDesc {

    @Schema(title = "图片验证码")
    captcha,

    @Schema(title = "短信验证码")
    sms,

    @Schema(title = "谷歌验证码")
    mfa,
    ;

    @Override
    public String toString() {
        return nameAndDesc();
    }
}