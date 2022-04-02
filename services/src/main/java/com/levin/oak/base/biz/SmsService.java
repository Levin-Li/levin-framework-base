package com.levin.oak.base.biz;

/**
 * 简单短信服务
 */
public interface SmsService {

    /**
     * 发送短信
     *
     * @param phoneNo
     * @param content
     * @return
     */
    boolean sendSms(String phoneNo, String content);

}
