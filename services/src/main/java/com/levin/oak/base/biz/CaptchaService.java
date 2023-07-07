package com.levin.oak.base.biz;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 图片验证码服务
 */
public interface CaptchaService {

    @Schema(title = "验证码")
    @Data
    @Accessors(chain = true)
    class Code implements Serializable {

        @Schema(title = "验证码文本")
        String code;

        @Schema(title = "")
        String contentType;

        @Schema(title = "验证码图片")
        byte[] imgData;

        @Schema(title = "验证码扩展信息")
        final Map<String, Serializable> extInfo = new LinkedHashMap<>();

    }

    /**
     * 生成验证码
     *
     * @param tenantId
     * @param appId
     * @param account
     * @param genParams
     * @return
     */
    Code genCode(String tenantId, String appId, String account, Map<String, ? extends Serializable> genParams);

    /**
     * 校验验证码
     *
     * @param code
     * @return
     */
    boolean verification(String tenantId, String appId, String account, String code);

}
