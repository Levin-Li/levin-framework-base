package com.levin.oak.base.biz.rbac;

import com.levin.commons.plugin.Res;
import com.levin.commons.rbac.Permission;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import org.springframework.lang.Nullable;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.util.StringUtils.*;

/**
 * Rbac 基本服务
 * <p>
 * 1、获取可以使用的资源清单
 * 2、获取可以使用的菜单清单
 * 3、方法授权检查
 */
public interface RbacService {

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

        return hasText(requirePermission)
                && (
                requirePermission.contains(getPermissionDelimiter())
                        //*号也可以是权限
                        || "*".equals(trimWhitespace(requirePermission))
        );

    }

    /**
     * 是否是匹配模板
     *
     * @return
     */
    default boolean isPattern(String permission) {
        return StringUtils.hasText(permission) && permission.contains("*");
    }

    /**
     * 是否是角色
     *
     * @param requirePermission
     * @return
     */
    default boolean isRole(String requirePermission) {
        return hasText(requirePermission)
                && !isPermission(requirePermission)
                ;
    }


    /**
     * 文本*号匹配
     * <p>
     * 注意不支持问号
     *
     * <p>
     * Match a String against the given pattern, supporting the following simple pattern styles: "xxx*", "*xxx", "*xxx*" and "xxx*yyy" matches (with an arbitrary number of pattern parts), as well as direct equality.
     *
     * @param pattern
     * @param str
     * @return
     * @see PatternMatchUtils#simpleMatch
     */
    default boolean textPatternMatch(@Nullable String pattern, @Nullable String str) {
        return PatternMatchUtils.simpleMatch(pattern, str);
    }

    /**
     * 简单匹配
     *
     * <p>
     * 重要方法，能提升性能
     *
     * @param requirePermission
     * @param ownerPermission
     * @return
     */
    default boolean simpleMatch(String requirePermission, String ownerPermission) {

        //去除所有空字符
        requirePermission = trimWhitespace(requirePermission);
        //如果需要去权限为空
        if (!StringUtils.hasText(requirePermission)) {
            return true;
        }

        //去除所有空字符
        ownerPermission = trimWhitespace(ownerPermission);
        if (!StringUtils.hasText(ownerPermission)) {
            return false;
        }

        //1、如果相等，直接返回
        if (ownerPermission.equals(requirePermission)) {
            return true;
        }

        //是否是角色
        boolean opIsRole = isRole(ownerPermission);
        boolean rpIsRole = isRole(requirePermission);

        if (opIsRole || rpIsRole) {
            //2、只要是角色，就只能是角色之间比较
            return opIsRole && rpIsRole && textPatternMatch(ownerPermission, requirePermission);
        }

        //3、如果拥有权限是模板
        if (isPattern(ownerPermission)) {

            //拥有的权限 A*:B*:C*:D*
            final String[] ownerList = ownerPermission.split(getPermissionDelimiter());

            final AtomicInteger idx = new AtomicInteger(-1);

            //切割出单个比较项目
            return Stream.of(requirePermission.split(getPermissionDelimiter()))
                    .allMatch(rp -> textPatternMatch(
                                    //超过数组长度以后，总是取最后一个
                                    trimWhitespace(ownerList[idx.updateAndGet(oldValue -> oldValue < ownerList.length ? oldValue + 1 : oldValue)])
                                    , trimWhitespace(rp)
                            )
                    );
        }

        return false;
    }


    /**
     * 多个匹配
     *
     * @param requirePermission
     * @param ownerPermissions
     * @return
     */
    default boolean simpleMatch(final String requirePermission, Collection<String> ownerPermissions) {

        if (!StringUtils.hasText(requirePermission)) {
            return true;
        }

        return ownerPermissions != null
                && !ownerPermissions.isEmpty()
                && ownerPermissions.stream().anyMatch(op -> simpleMatch(requirePermission, op));
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

        {
            //如果不需要权限
            if (requirePermissionList == null
                    || requirePermissionList.isEmpty()) {
                return true;
            }

            //过滤空的权限列表
            requirePermissionList = requirePermissionList.stream().filter(StringUtils::hasText).collect(Collectors.toList());

            if (requirePermissionList.isEmpty()) {
                return true;
            }
        }

        ///////////////////////////////////////////////////////////////

        Predicate<String> predicate = rp -> isAuthorized(ownerRoleList, ownerPermissionList, rp, matchErrorConsumer);

        //是否要求匹配所有权限
        return isRequireAllPermission ?
                requirePermissionList.stream().allMatch(predicate)
                : requirePermissionList.stream().anyMatch(predicate);
    }

    /**
     * 授权验证，是否可以访问指定资源
     * <p>
     * 匹配单个权限
     *
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
