package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.SimpleAuthService;
import com.levin.commons.service.exception.AuthorizationException;
import org.springframework.lang.NonNull;

import java.lang.reflect.Method;


/**
 * 认证服务
 * <p>
 * 务必注意：本接口和当前登录用户有关，实现类必须和使用方在同一个虚拟机中。
 */
public interface AuthService extends SimpleAuthService<String, Object> {

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
    <U extends RbacUserInfo<String>> U loadUserInfo(String principal);

    /**
     * 用户登出
     */
    void logout();

    /**
     * 获取设备类型
     *
     * @param ua
     * @return
     */
    String getDeviceType(String ua);

    /**
     * 检查当前用户的方法调用授权
     *
     * @param method 控制器或是服务的方法
     */
    void checkAuthorize(Object beanOrClass, @NonNull Method method) throws AuthorizationException;

}
