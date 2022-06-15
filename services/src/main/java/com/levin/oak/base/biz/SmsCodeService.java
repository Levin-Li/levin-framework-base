package com.levin.oak.base.biz;

/**
 * 简单短信服务
 */
public interface SmsCodeService {

    /**
     * 生成并发送短信验证码
     * <p>
     * 如果为模拟通道，返回的验证码以mock:做为前缀
     *
     * @param tenantId
     * @param appId
     * @param phoneNo
     * @return 短信验证码，如果为模拟通道，返回的验证码以mock:做为前缀
     */
    String genAndSendSmsCode(String tenantId, String appId, String account, String phoneNo);

    /**
     * 校验验证码
     *
     * @return
     */
    boolean verification(String tenantId, String appId, String account, String code);

}
