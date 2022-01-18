package com.levin.oak.base.services.rbac;

import com.levin.commons.rbac.SimpleAuthService;
import com.levin.commons.rbac.UserBaseInfo;

import java.util.List;


/**
 * 认证服务
 */
public interface AuthService extends SimpleAuthService {

    /**
     * 是否登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户ID
     *
     * @return
     */
    <T> T getLoginUserId();

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    <U extends UserBaseInfo> U getUserInfo();

    /**
     * 获取用户的权限列表
     *
     * @param loginId 如果为空，则默认为当前用户
     * @return
     */
    List<String> getPermissionList(Object loginId);

    /**
     * 获取用户的角色列表
     *
     * @param loginId 如果为空，则默认为当前用户
     * @return
     */
    List<String> getRoleList(Object loginId);


    /**
     * 用户登出
     */
    void logout();

    /**
     * 加密密码
     *
     * @param pwd
     * @return
     */
    String encryptPassword(String pwd);

    /**
     * 获取设备类型
     *
     * @param ua
     * @return
     */
    String getDeviceType(String ua);

}
