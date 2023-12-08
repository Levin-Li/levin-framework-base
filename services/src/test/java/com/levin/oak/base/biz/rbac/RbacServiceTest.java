package com.levin.oak.base.biz.rbac;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.levin.commons.plugin.Res;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.oak.base.biz.rbac.info.ModuleInfo;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;

class RbacServiceTest {

    RbacService<Serializable> rbacService =  new RbacService<Serializable>(){
        /**
         * 设置用户加载服务
         *
         * @param rbacLoadService
         * @return
         */
        @Override
        public RbacService setUserLoadService(RbacLoadService<Serializable> rbacLoadService) {
            return null;
        }

        /**
         * 获取一个用户有权限访问的组织ID列表
         *
         * @param principal
         * @return
         */
        @Override
        public List<String> getCanAcccessOrgIdList(Serializable principal) {
            return null;
        }

        /**
         * 用户对指定的注解是否有权限
         *
         * @param principal
         * @param resAuthorize
         * @return
         */
        @Override
        public boolean isAuthorized(Serializable principal, ResAuthorize resAuthorize) {
            return false;
        }

        /**
         * 用户对指定资源的动作是否有权限
         *
         * @param principal
         * @param resPrefix
         * @param action
         * @return
         */
        @Override
        public boolean isAuthorized(Serializable principal, String resPrefix, Res.Action action) {
            return false;
        }

        /**
         * 源用户是否能给目标用户分配指定的角色
         *
         * @param sourcePrincipal
         * @param targetPrincipal
         * @param requireRoleCode
         * @param matchErrorConsumer 匹配错误回调 参数1为请求的角色，参数2为没有匹配的权限
         * @return
         */
        @Override
        public boolean canAssignRole(Serializable sourcePrincipal, Serializable targetPrincipal, String requireRoleCode, BiConsumer<String, String> matchErrorConsumer) {
            return false;
        }

        /**
         * 当前用户是否 拥有指定的权限列表
         *
         * @param principal
         * @param isRequireAllPermission 是否要求匹配所有的权限
         * @param requirePermissionList  权限列表可以包括角色，如果
         * @param matchErrorConsumer
         * @return
         */
        @Override
        public boolean isAuthorized(Serializable principal, boolean isRequireAllPermission, List<String> requirePermissionList, BiConsumer<String, String> matchErrorConsumer) {
            return false;
        }

        /**
         * 授权验证，是否可以访问指定资源
         * <p>
         * 匹配单个权限
         *
         * <p>
         * 关键方法
         *
         * @param ownerRoleList       已经拥有的角色列表
         * @param ownerPermissionList 已经拥有的权限列表
         * @param requirePermission   请求的权限
         * @param matchErrorConsumer  匹配错误回调 参数1为请求的权限，参数2为错误原因
         * @return 是否可以访问指定资源
         */
        @Override
        public boolean isAuthorized(List<String> ownerRoleList, List<String> ownerPermissionList, String requirePermission, BiConsumer<String, String> matchErrorConsumer) {
            return false;
        }
    };

    @Test
    public void testMatch() {

        String md5 = SecureUtil.md5("开发人员专用API密码" + DateUtil.format(new Date(), DatePattern.NORM_DATE_FORMAT));


        System.out.println(md5);

        Assert.isTrue(rbacService.isPattern("A*B"));

        Assert.isTrue(rbacService.isPattern("A|B"));

        Assert.isTrue(rbacService.isPattern("**"));

        Assert.isFalse(rbacService.isPattern("A"));


        Assert.isTrue(rbacService.isRole("R_A"));
        Assert.isTrue(rbacService.isRole("R_SA"));

        Assert.isFalse(rbacService.isRole("*"));
        Assert.isFalse(rbacService.isRole(""));
        Assert.isFalse(rbacService.isRole(null));

        Assert.isTrue(rbacService.isPermission("P:SA"));
        Assert.isTrue(rbacService.isPermission("*:*"));
        Assert.isTrue(rbacService.isPermission("*"));


        Assert.isTrue(rbacService.textPatternMatch("A*","ABCD"));
        Assert.isTrue(rbacService.textPatternMatch("*","ABCD"));
        Assert.isTrue(rbacService.textPatternMatch("**","ABCD"));

        Assert.isFalse(rbacService.textPatternMatch("BC","ABCD"));


        Assert.isTrue(rbacService.textPatternMatch("A*D","ABCD"));
        Assert.isFalse(rbacService.textPatternMatch("A*D","ABCDF"));
        Assert.isTrue(rbacService.textPatternMatch("*D","ABCD"));


        Assert.isTrue(rbacService.simpleMatch("D:T:ID:A","**"));

        Assert.isTrue(rbacService.simpleMatch("D:T:ID:A","**:*"));

        Assert.isFalse(rbacService.simpleMatch("D:T:ID:A","**:A"));
        Assert.isTrue(rbacService.simpleMatch("D:T:ID:A","*:*"));

        Assert.isTrue(rbacService.simpleMatch("D:T:IDD:A","*:T:*D:A"));
        Assert.isTrue(rbacService.simpleMatch("D:T:IDD:A","*:T:*D:*"));
        Assert.isFalse(rbacService.simpleMatch("D:T:IDD:A","*:T:*D:DDD"));

        Assert.isTrue(rbacService.simpleMatch("R_DDD","R_D*"));


        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:查询列表","*:T:*D:查询*|更新*"));

        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:更新数据","*:T:*D:查询*|更新*"));

        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:更新数据","*:T:*D:更新数据"));

        Assert.isFalse(rbacService.simpleMatch("D:T:I*D:更新数据","*:查询*|更新"));

        //** 代表可以多个层级
        Assert.isFalse(rbacService.simpleMatch("D:T:I*D:更新数据","**:查询*|更新*"));

        Assert.isFalse(rbacService.simpleMatch("D:T:I*D:更新数据","*:T:*D:删除*"));

        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:查询数据","*:T:*D:查询*"));


        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:更新数据","*:T:*D:*"));

    }

}
