package com.levin.oak.base.entities.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "签名访问控制", description = "部分注重安全的接口需要动态验证码的支持，需要先询问服务器是否要验证码，如果要验证码则需要客户端提交验证码")
public class SignAcl extends UrlAcl {

    @Schema(title = "应用ID参数名称", description = "默认参数名：sign")
    @Column(length = 16)
    protected String signParamName;

}
