package com.levin.oak.base.services.rbac;

import com.levin.commons.rbac.AuthorizationException;
import com.levin.commons.rbac.Permission;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.rbac.info.ModuleInfo;
import com.levin.oak.base.services.rbac.req.LoginReq;
import com.levin.oak.base.services.user.info.UserInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

@CacheConfig(cacheNames = {E_User.CLASS_NAME})
public interface RbacService {

    /**
     * 登录 并且返回 token
     *
     * @param req
     * @return token
     */
    String login(@NonNull LoginReq req) throws AuthorizationException;


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
     * 获取用户信息
     *
     * @return
     */
    UserInfo getUserInfo();

    /**
     * 用户登出
     */
    void logout();

    /**
     * 检查用户状态
     *
     * @param user
     */
    void checkUserState(@NonNull UserInfo user) throws AuthorizationException;

    /**
     * 检查方法授权
     *
     * @param method
     */
    void checkAuthorize(@NonNull Method method) throws AuthorizationException;

    /**
     * 获取权限分隔符
     *
     * @return
     */
    default String getDelimiter() {
        return Permission.DELIMITER;
    }


    /**
     * 获取授权的菜单列表
     *
     * @param userId
     * @return
     */
    List<MenuResInfo> getAuthorizedMenuList(boolean isShowNotPermissionMenu, @Nullable Object userId);

    /**
     * 获取资源授权清单
     *
     * @param userId 如果 userId 为null，则表示获取全部的
     * @return
     */
    List<ModuleInfo> getAuthorizedResList(@Nullable Object userId);

    /**
     * 获取指定用户的权限列表
     *
     * @param userId
     * @return
     */
//    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_User.CACHE_KEY_PREFIX + "#id")
    default List<String> getPermissionList(@NonNull Object userId) {
        return Collections.emptyList();
    }

    /**
     * 获取指定用户的角色列表
     *
     * @param userId
     * @return
     */
//    @Cacheable(sync = false, condition = "#id != null", unless = "#result == null ", key = E_User.CACHE_KEY_PREFIX + "#id")
    default List<String> getRoleList(@NonNull Object userId) {
        return Collections.emptyList();
    }

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
