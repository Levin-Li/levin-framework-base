package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.services.org.info.OrgInfo;
import com.levin.oak.base.services.role.info.RoleInfo;
import com.levin.oak.base.services.user.info.UserInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BizOrgServiceImplTest {

    BizOrgServiceImpl bizOrgService = new BizOrgServiceImpl() {
        /**
         * 加载租户的部门列表
         *
         * @param tenantId
         * @param parentId
         * @return
         */
        @Override
        public List<OrgInfo> loadOrgList(String tenantId, String parentId) {
            return orgList.stream()
                    .filter(org ->
                            StrUtil.isBlank(tenantId) || org.getTenantId().equals(tenantId)
                                    && (StrUtil.isBlank(parentId) || org.getParentId().equals(parentId)))
                    .collect(Collectors.toList());
        }
    };

    List<OrgInfo> orgList = new ArrayList<>();

    UserInfo SA = new UserInfo().setId("00").setName("SA").setRoleList(Arrays.asList("R_SA"));

    UserInfo TA = new UserInfo().setId("U01").setTenantId("T01").setRoleList(Arrays.asList(RbacRoleObject.ADMIN_ROLE));

    UserInfo user = new UserInfo().setId("U501").setOrgId("501").setTenantId("T02").setRoleList(Arrays.asList("R_USER"));

    RoleInfo R_USER = new RoleInfo().setId("R_USER_01").setName("普通用户").setOrgDataScope(Role.OrgDataScope.MyDeptAndChildren).setCode("R_USER");

    RoleInfo R_USER_2 = new RoleInfo().setId("R_USER_01").setName("普通用户").setOrgDataScope(Role.OrgDataScope.Assigned).setAssignedOrgIdList(Arrays.asList("701")).setCode("R_USER_2");

    @BeforeEach
    void init() {

        orgList.clear();


        for (int i = 1; i < 10; i++) {

            String tenantId = "T0" + (i % 3);

            // 奇数父节点
            orgList.add(new OrgInfo().setTenantId(tenantId).setId(String.valueOf(i)).setParentId(String.valueOf(i - i % 2)));


            for (int j = 1; j < 10; j++) {

                int l2Id = i * 100 + j;

                orgList.add(new OrgInfo().setTenantId(tenantId).setId(String.valueOf(l2Id)).setParentId(String.valueOf(i)));


                for (int k = 1; k < 10; k++) {

                    int l3Id = l2Id * 100 + k;

                    orgList.add(new OrgInfo().setTenantId(tenantId).setId(String.valueOf(l3Id)).setParentId(String.valueOf(l2Id)));

                    for (int l = 1; l < 10; l++) {

                        orgList.add(new OrgInfo().setTenantId(tenantId).setId(String.valueOf(l3Id + l)).setParentId(String.valueOf(l3Id + l-1)));

                    }

                }

            }

        }

        orgList = Collections.unmodifiableList(orgList);

    }


    @Test
    void testLoadOrgList() {


        long start = System.currentTimeMillis();

        List<OrgInfo> tempList = bizOrgService.loadOrgList(() -> SA, u -> Collections.emptyList(), false);

        long takeTime = System.currentTimeMillis() - start;
        System.out.println("1、loadOrgList takeTime:" + takeTime);

        start = System.currentTimeMillis();
        tempList = bizOrgService.loadOrgList(() -> SA, u -> Collections.emptyList(), true, "101");

        takeTime = System.currentTimeMillis()  - start;
        System.out.println("2、loadOrgList takeTime:" + takeTime);

        start = System.currentTimeMillis();
        tempList = bizOrgService.loadOrgList(() -> TA, u -> Collections.emptyList(), true, "7");


        takeTime = System.currentTimeMillis() - start;
        System.out.println("3、loadOrgList takeTime:" + takeTime);

        start = System.currentTimeMillis();
        tempList = bizOrgService.loadOrgList(() -> user, u -> Arrays.asList(R_USER), true, "5");

        takeTime = System.currentTimeMillis() - start;
        System.out.println("4、loadOrgList takeTime:" + takeTime);

        Assert.isTrue(tempList.size()== 1);


        start = System.currentTimeMillis();
        tempList = bizOrgService.loadOrgList(() -> user, u -> Arrays.asList(R_USER, R_USER_2), true);

        takeTime = System.currentTimeMillis() - start;
        System.out.println("4、loadOrgList takeTime:" + takeTime);

        Assert.isTrue(tempList.size()== 1);

    }

}