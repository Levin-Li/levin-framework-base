package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Role;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.role.*;
import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import com.levin.oak.base.entities.Role.*;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

import com.levin.commons.dao.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.service.domain.*;

import org.springframework.util.*;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 角色测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月16日 下午1:12:59, 代码生成哈希校验码：[a06318a089e500b8c87c0f3ad326cab1]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class RoleServiceTest {

    @Autowired private RoleService roleService;

    @Autowired private BizRoleService bizRoleService;

    private String id;

    @BeforeAll
    public static void beforeAll() throws Exception {}

    @AfterAll
    public static void afterAll() throws Exception {}

    @BeforeEach
    public void beforeEach() throws Exception {}

    @AfterEach
    public void afterEach() throws Exception {}

    @Test
    public void createRoleTest() {

        CreateRoleReq req = new CreateRoleReq();

        // req.setCode("这是文本128");// 必填

        // req.setIcon("图标_1");//

        // req.setOrgDataScope(OrgDataScope.All);//参考组织ID列表 必填

        // req.setAssignedOrgIdList("组织ID列表_1");//指定的组织ID列表，Json数组

        // req.setPermissionList("资源权限列表_1");//Json数组

        // req.setDomain("这是文本128");//

        // req.setName("这是文本64");// 必填

        // req.setOptimisticLock(1);//

        // req.setOrgId("这是文本128");//

        // req.setTenantId("这是文本128");//

        // req.setOrderCode(1);//

        // req.setEnable(true);// 必填

        // req.setEditable(true);// 必填

        // req.setRemark("这是文本512");//

        String id = roleService.create(req);

        log.debug("新增角色->" + id);

        Assert.isTrue(id != null, "角色");
    }

    @Test
    public void queryRoleTest() {

        QueryRoleReq req = new QueryRoleReq();

        // req.setId(null);//
        // req.setCode("这是文本128");//
        // req.setIcon("图标_1");//
        // req.setOrgDataScope(OrgDataScope.All);//参考组织ID列表
        // req.setAssignedOrgIdList("组织ID列表_1");//指定的组织ID列表，Json数组
        // req.setPermissionList("资源权限列表_1");//Json数组
        // req.setDomain("这是文本128");//
        // req.setName("这是文本64");//
        // req.setOptimisticLock(1);//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<RoleInfo> resp = roleService.query(req, null);

        log.debug("查询角色->" + resp);

        Assert.isTrue(!resp.isEmpty(), "角色");
    }

    @Test
    public void updateRoleTest() {

        UpdateRoleReq req = new UpdateRoleReq();

        req.setId(id);

        // req.setCode("这是文本128");// 必填
        // req.setIcon("图标_1");//
        // req.setOrgDataScope(OrgDataScope.All);//参考组织ID列表 必填
        // req.setAssignedOrgIdList("组织ID列表_1");//指定的组织ID列表，Json数组
        // req.setPermissionList("资源权限列表_1");//Json数组
        // req.setDomain("这是文本128");//
        // req.setName("这是文本64");// 必填
        // req.setOptimisticLock(1);//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);// 必填
        // req.setEditable(true);// 必填
        // req.setRemark("这是文本512");//

        boolean ok = roleService.update(req);

        log.debug("更新角色-> " + ok);

        Assert.isTrue(ok, "角色");
    }

    @Test
    public void deleteRoleTest() {

        RoleIdReq req = new RoleIdReq();

        req.setId(id);

        boolean ok = roleService.delete(req);

        log.debug("删除角色->" + ok);

        Assert.isTrue(ok, "角色");
    }
}
