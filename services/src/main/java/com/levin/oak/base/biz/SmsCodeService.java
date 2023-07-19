package com.levin.oak.base.biz;

/**
 * 简单短信服务
 */
public interface SmsCodeService {

    /**
     * 生成并发送短信验证码
     * <p>
     * 如果是模拟通道，返回的验证码以mock:做为前缀
     *
     * @param tenantId
     * @param account
     * @param phoneNo
     * @return
     */
    default String genAndSendSmsCode(String tenantId, String account, String phoneNo) {
        return genAndSendSmsCode(tenantId, null, account, phoneNo);
    }

    /**
     * 生成并发送短信验证码
     * <p>
     * 如果是模拟通道，返回的验证码以mock:做为前缀
     *
     * @param tenantId
     * @param appId
     * @param account
     * @param phoneNo
     * @return 短信验证码，如果为模拟通道，返回的验证码以mock:做为前缀
     */
    String genAndSendSmsCode(String tenantId, String appId, String account, String phoneNo);


    /**
     * 校验验证码
     *
     * @param tenantId
     * @param account
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
