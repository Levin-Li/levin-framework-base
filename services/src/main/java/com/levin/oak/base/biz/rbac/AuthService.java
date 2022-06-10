package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.SimpleAuthService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 认证服务
 */
public interface AuthService extends SimpleAuthService {

    /**
     * 初始化数据
     */
    default void initData() {
    }

    /**
     * 清除线程缓存数据
     */
    void clearThreadCacheData();

    /**
     * 是否登录
     *
     * @return
     */
    boolean isLogin();

    /**
     * 获取登录用户ID
     * <p>
     * 必须返回数据，如果用户没有登录，必须抛出异常
     *
     * @return
     */
    <T> T getLoginUserId();

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    <U extends RbacUserInfo<String>> U getUserInfo();

    /**
     * 获取用户信息
     *
     * @return
     */
    <U extends RbacUserInfo<String>> U getUserInfo(Object loginId);

    /**
     * 获取用户的权限列表
     *
     * @param loginId
     * @return
     */
    List<String> getPermissionList(@NotNull Object loginId);

    /**
     * 获取用户的角色列表
     *
     * @param loginId
     * @return
     */
    List<String> getRoleList(@NotNull Object loginId);

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
