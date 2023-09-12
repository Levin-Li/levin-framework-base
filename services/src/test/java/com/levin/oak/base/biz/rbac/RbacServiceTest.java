package com.levin.oak.base.biz.rbac;


import cn.hutool.core.lang.Assert;
import com.levin.commons.plugin.Res;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiConsumer;

class RbacServiceTest {

    RbacService rbacService = new RbacService() {
        @Override
        public RbacUserInfo<String> getUserInfo(Object userId) {
            return null;
        }

        @Override
        public List<String> getRoleList(Object userId) {
            return null;
        }

        @Override
        public List<String> getPermissionList(Object userId) {
            return null;
        }

        @Override
        public List<String> getCanAcccessOrgIdList(Object userId) {
            return null;
        }

        @Override
        public boolean isAuthorized(Object userId, ResAuthorize resAuthorize) {
            return false;
        }

        @Override
        public boolean isAuthorized(Object userId, String resPrefix, Res.Action action) {
            return false;
        }

        @Override
        public boolean canAssignRole(Object sourceUserId, Object targetUserId, String requireRoleCode, BiConsumer<String, String> matchErrorConsumer) {
            return false;
        }

        @Override
        public boolean isAuthorized(Object userId, boolean isRequireAllPermission, List<String> requirePermissionList, BiConsumer<String, String> matchErrorConsumer) {
            return false;
        }

        @Override
        public boolean isAuthorized(List<String> ownerRoleList, List<String> ownerPermissionList, String requirePermission, BiConsumer<String, String> matchErrorConsumer) {
            return false;
        }
    };

    @Test
    public void testMatch() {


        Assert.isTrue(rbacService.isPattern("A*B"));
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

        Assert.isFalse(rbacService.isPermission("**"));


        Assert.isTrue(rbacService.textPatternMatch("A*","ABCD"));
        Assert.isTrue(rbacService.textPatternMatch("*","ABCD"));
        Assert.isTrue(rbacService.textPatternMatch("**","ABCD"));

        Assert.isFalse(rbacService.textPatternMatch("BC","ABCD"));


        Assert.isTrue(rbacService.textPatternMatch("A*D","ABCD"));
        Assert.isFalse(rbacService.textPatternMatch("A*D","ABCDF"));
        Assert.isTrue(rbacService.textPatternMatch("*D","ABCD"));


        Assert.isTrue(rbacService.simpleMatch("D:T:ID:A","*"));

        Assert.isTrue(rbacService.simpleMatch("D:T:ID:A","*:*"));
        Assert.isTrue(rbacService.simpleMatch("D:T:ID:A","*:"));

        Assert.isTrue(rbacService.simpleMatch("D:T:IDD:A","*:T:*D:A"));
        Assert.isTrue(rbacService.simpleMatch("D:T:IDD:A","*:T:*D:*"));
        Assert.isFalse(rbacService.simpleMatch("D:T:IDD:A","*:T:*D:DDD"));


        Assert.isTrue(rbacService.simpleMatch("R_DDD","R_D*"));



        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:查询列表","*:T:*D:查询*"));

        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:查询数据","*:T:*D:查询*"));

        Assert.isFalse(rbacService.simpleMatch("D:T:I*D:更新数据","*:T:*D:查询*"));

        Assert.isTrue(rbacService.simpleMatch("D:T:I*D:更新数据","*:T:*D:*"));

    }

}