package com.levin.oak.base.biz;

/**
 * 短信发生服务
 */
public interface SmsSender {

    /**
     * 发送验证码
     * <p>
     * 如果发送成功返回code，否则返回错误原因
     *
     * @param tenantId
     * @param appId
     * @param phone
     * @param code
     * @return code or errInfo 如果发送成功返回code，否则返回错误原因
     */
    String sendCode(String tenantId, String appId, String phone, String code);

}
