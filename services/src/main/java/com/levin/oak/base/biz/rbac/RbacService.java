package com.levin.oak.base.biz.rbac;

import com.levin.commons.plugin.Res;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.rbac.Permission;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * Rbac 基本服务
 * <p>
 * 1、获取可以使用的资源清单
 * 2、获取可以使用的菜单清单
 * 3、方法授权检查
 */
public interface RbacService {

    /**
     * 超管帐号
     */
    String SA_ACCOUNT = "sa";

    /**
     * 密码加密
     *
     * @param pwd
     * @return
     */
    String encryptPassword(String pwd);

    /**
     * 获取权限分隔符
     *
     * @return
     */
    default String getPermissionDelimiter() {
        return Permission.DELIMITER;
    }

    /**
     * 是否是权限
     *
     * @param requirePermission
     * @return
     */
    default boolean isPermission(String requirePermission) {
        return requirePermission.contains(getPermissionDelimiter());
    }

    /**
     * 是否是角色
     *
     * @param requirePermission
     * @return
     */
    default boolean isRole(String requirePermission) {
        return !isPermission(requirePermission);
    }

    /**
     * 获取认证上下文
     *
     * @return
     */
    default Map<String, Object> getAuthorizeContext() {
        return Collections.emptyMap();
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    RbacUserInfo<String> getUserInfo(Object userId);


    /**
     * 获取用户的角色列表
     *
     * @param userId
     * @return
     */
    List<String> getRoleList(@NotNull Object userId);

    /**
     * 获取用户的权限列表
     *
     * @param userId
     * @return
     */
    List<String> getPermissionList(@NotNull Object userId);

    /**
     * 获取一个用户有权限访问的组织ID列表
     *
     * @param userId
     * @return
     */
    List<String> getCanAcccessOrgIdList(@NotNull Object userId);


    /**
     * 用户对指定的注解是否有权限
     *
     * @param userId
     * @param resAuthorize
     * @return
     */
    boolean isAuthorized(@NotNull Object userId, ResAuthorize resAuthorize);

    /**
     * 用户对指定资源的动作是否有权限
     *
     * @param userId
     * @param resPrefix
     * @param action
     * @return
     */
    boolean isAuthorized(@NotNull Object userId, String resPrefix, Res.Action action);

    /**
     * 当前用户是否能给目标用户分配指定的角色
     *
     * @param targetUserId
     * @param requireRoleCodeList
     * @param matchErrorConsumer  匹配错误回调 参数1为请求的角色，参数2为没有匹配的权限
     * @return
     */
    default boolean canAssignRole(Object sourceUserId, Object targetUserId, List<String> requireRoleCodeList
            , BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {
        return requireRoleCodeList.stream().filter(StringUtils::hasText)
                .allMatch(requireRoleCode -> canAssignRole(sourceUserId, targetUserId, requireRoleCode, matchErrorConsumer));
    }

    /**
     * 源用户是否能给目标用户分配指定的角色
     *
     * @param sourceUserId
     * @param targetUserId
     * @param requireRoleCode
     * @param matchErrorConsumer 匹配错误回调 参数1为请求的角色，参数2为没有匹配的权限
     * @return
     */
    boolean canAssignRole(Object sourceUserId, Object targetUserId, String requireRoleCode
            , BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer);

    /**
     * 当前用户是否 拥有指定的权限列表
     *
     * @param matchErrorConsumer
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList
     * @return
     */
    default boolean isAuthorized(Object userId, BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer,
                                 boolean isRequireAllPermission, String... requirePermissionList) {
        return isAuthorized(userId, isRequireAllPermission, Arrays.asList(requirePermissionList), matchErrorConsumer);
    }

    /**
     * 当前用户是否 拥有指定的权限列表
     *
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList  权限列表可以包括角色，如果
     * @param matchErrorConsumer
     * @return
     */
    boolean isAuthorized(Object userId, boolean isRequireAllPermission, List<String> requirePermissionList,
                         BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer);

    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 关键方法
     *
     * @param ownerRoleList          已经拥有的角色列表
     * @param ownerPermissionList    已经拥有的权限列表
     * @param isRequireAllPermission 是否要求匹配所有的权限
     * @param requirePermissionList  请求的权限
     * @param matchErrorConsumer     匹配错误回调 参数1为请求的权限，参数2为错误原因
     * @return 是否可以访问指定资源
     */
    default boolean isAuthorized(List<String> ownerRoleList, List<String> ownerPermissionList,
                                 boolean isRequireAllPermission, List<String> requirePermissionList,
                                 BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer) {

        Predicate<String> predicate = rp -> isAuthorized(ownerRoleList, ownerPermissionList, rp, matchErrorConsumer);

        //是否要求匹配所有权限
        return isRequireAllPermission ?
                requirePermissionList.stream().allMatch(predicate)
                : requirePermissionList.stream().anyMatch(predicate);
    }

    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 关键方法
     *
     * @param requirePermission   请求的权限
     * @param ownerRoleList       已经拥有的角色列表
     * @param ownerPermissionList 已经拥有的权限列表
     * @param matchErrorConsumer  匹配错误回调 参数1为请求的权限，参数2为错误原因
     * @return 是否可以访问指定资源
     */
    boolean isAuthorized(List<String> ownerRoleList, List<String> ownerPermissionList, String requirePermission
            , BiConsumer<String/*参数1为请求的权限*/, String/*参数2为错误原因*/> matchErrorConsumer);

}
