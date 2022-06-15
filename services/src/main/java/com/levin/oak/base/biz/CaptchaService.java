package com.levin.oak.base.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 图片验证码服务
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @param account
     */
    String genCode(HttpServletRequest request, HttpServletResponse response, String tenantId, String appId, String account);

    /**
     * 校验验证码
     *
     * @param code
     * @return
     */
    boolean verification(String tenantId, String appId, String account, String code);

}
