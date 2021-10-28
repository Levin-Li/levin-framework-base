package com.levin.oak.base.services.rbac;

import com.levin.oak.base.services.rbac.req.LoginReq;
import com.levin.oak.base.services.user.info.UserInfo;


public interface AuthService {

    /**
     * 登录
     *
     * @param req
     * @return
     */
    UserInfo login(LoginReq req);

    /**
     * 加密密码
     *
     * @param pwd
     * @return
     */
    String encryptPassword(String pwd);

}
