package com.levin.oak.base.controller.rbac.dto;

import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;


@Schema(description = "登录用户信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class LoginInfo implements Serializable {

    @Schema(description = "access token")
    String accessToken;

    @Schema(description = "refreshToken")
    String refreshToken;


//    expire: number;
//    refreshToken: string;
//    refreshExpire: number;

    @Schema(description = "登录用户信息")
    UserInfo userInfo;

}
