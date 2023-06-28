package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Role;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.role.*;
import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Role.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
 * Auto gen by simple-dao-codegen 2023年6月28日 上午11:30:58
 * 代码生成哈希校验码：[6cff056e65ff7f0ea1b2be0888496406]
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private BizRoleService bizroleService;

    private String id;

    @BeforeAll
    public static void beforeAll() throws Exception {
    }

    @AfterAll
    public static void afterAll() throws Exception {
    }

    @BeforeEach
    public void beforeEach() throws Exception {
    }

    @AfterEach
    public void afterEach() throws Exception {
    }

    @Test
    public void createRoleTest() {

        CreateRoleReq req = new CreateRoleReq();

            // req.setCode("这是文本128");// 必填

            // req.setIcon("图标_1");// 

            // req.setOrgDataScope(OrgDataScope.All);// 必填

            // req.setAssignedOrgIdList("指定的部门列表_1");//Json数组 

            // req.setPermissionList("资源权限列表_1");//Json数组 

            // req.setTenantId("这是文本128");// 

            // req.setDomain("这是文本128");// 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String id  = roleService.create(req);

        log.debug("新增角色->" + id);

        Assert.isTrue(id != null, "角色");

    }


    @Test
    public void queryRoleTest() {

        QueryRoleReq req = new QueryRoleReq();

        // req.setId(null);//
        // req.setCode("这是文本128");//
        // req.setIcon("图标_1");//
        // req.setOrgDataScope(OrgDataScope.All);//
        // req.setAssignedOrgIdList("指定的部门列表_1");//Json数组
        // req.setPermissionList("资源权限列表_1");//Json数组
        // req.setTenantId("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<RoleInfo> resp = roleService.query(req,null);

        log.debug("查询角色->" + resp);

        Assert.isTrue(!resp.isEmpty(), "角色");
    }

    @Test
    public void updateRoleTest() {

         UpdateRoleReq req = new UpdateRoleReq();

         req.setId(id);


           // req.setCode("这是文本128");// 必填
           // req.setIcon("图标_1");// 
           // req.setOrgDataScope(OrgDataScope.All);// 必填
           // req.setAssignedOrgIdList("指定的部门列表_1");//Json数组 
           // req.setPermissionList("资源权限列表_1");//Json数组 
           // req.setTenantId("这是文本128");// 
           // req.setDomain("这是文本128");// 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
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

        Assert.isTrue(ok , "角色");
    }
}
