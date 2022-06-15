package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.SimpleAuthService;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 认证服务
 */
public interface AuthService extends SimpleAuthService<String, String> {

    /**
     * 超管帐号
     */
    String SA_ACCOUNT = "sa";

    /**
     * 初始化数据
     */
    default void initData() {
    }

    /**
     * 是否超级用户帐号
     *
     * @param account
     * @return
     */
    default boolean isSuperAdmin(String account) {
        return SA_ACCOUNT.equalsIgnoreCase(StringUtils.trimWhitespace(account));
    }

    /**
     * 清除线程缓存数据
     */
    void clearThreadCacheData();

    /**
     * 发送短信验证码到指定帐号
     * <p>
     * 返回短信验证码，如果为模拟通道，返回的验证码以mock:做为前缀
     *
     * @param tenantId
     * @param appId
     * @return 短信验证码，如果为模拟通道，返回的验证码以mock:做为前缀
     */
    String sendSmsCode(String tenantId, String appId, String account);

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
    String getLoginId();

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
    <U extends RbacUserInfo<String>> U getUserInfo(String loginId);

    /**
     * 获取用户的权限列表
     *
     * @param loginId
     * @return
     */
    List<String> getPermissionList(@NotNull String loginId);

    /**
     * 获取用户的角色列表
     *
     * @param loginId
     * @return
     */
    List<String> getRoleList(@NotNull String loginId);

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
