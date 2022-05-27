package com.levin.oak.base.biz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 图片验证码服务
 */
public interface CaptchaService {
    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @param params
     */
    String genCode(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params);

    /**
     * 校验验证码
     *
     * @param request
     * @param code
     * @param params
     * @return
     */
    boolean verification(HttpServletRequest request, String code, Map<String, Object> params);

}
