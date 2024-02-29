package com.levin.oak.base.biz;


import java.io.Serializable;
import java.util.Map;

/**
 * MultiFactorAuthService
 * 多因子身份认证
 * 多因子身份认证（Multi-factor Authentication Service，MFAS）的目的是建立一个多层次的防御
 */
public interface MultiFactorAuthService {

    /**
     * @return
     */
    String genSecretKey();

    /**
     * 生成密钥
     *
     * @return
     */
    String genQrCode(String userName, String secretKey);

    /**
     * @param tenantId
     * @param appId
     * @param account
     * @param genParams
     * @return
     */
    String genCode(String tenantId, String appId, String account, Map<String, ? extends Serializable> genParams);

    /**
     * 校验验证码
     *
     * @param code
     * @return
     */
    default boolean verify(String tenantId, String account, String code) {
        return verify(tenantId, null, account, code);
    }

    /**
     * 校验验证码
     *
     * @param tenantId
     * @param appId
     * @param account
     * @param code
     * @return
     */
    boolean verify(String tenantId, String appId, String account, String code);

}
