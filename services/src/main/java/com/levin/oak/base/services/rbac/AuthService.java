package com.levin.oak.base.services.rbac;

import com.levin.commons.rbac.AuthorizationException;
import com.levin.commons.rbac.UserBaseInfo;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.services.rbac.req.LoginReq;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.lang.NonNull;

import java.util.List;

@CacheConfig(cacheNames = {E_User.CLASS_NAME})
public interface AuthService {

    /**
     * 登录 并且返回 token
     *
     * @param req
     * @return token
     */
    String loginByPassword(@NonNull LoginReq req) throws AuthorizationException;

    /**
     * 登录
     *
     * @param info
     * @param deviceType
     * @return
     */
    String login(UserBaseInfo info, String deviceType);

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
     * 获取用户信息
     *
     * @return
     */
    UserBaseInfo getBaseUserInfo();


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

    /**
     * 初始化数据
     */
    void initData();

}
