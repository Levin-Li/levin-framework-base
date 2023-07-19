package com.levin.oak.base.biz;

/**
 * 短信发送服务服务
 */
@FunctionalInterface
public interface SmsSendService {

    /**
     * 发送验证码
     * <p>
     * 如果发送成功返回code，否则返回错误原因
     *
     * @param tenantId
     * @param phone
     * @param code
     * @return
     */
    default String sendCode(String tenantId, String phone, String code) {
        return sendCode(tenantId, null, phone, code);
    }

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
