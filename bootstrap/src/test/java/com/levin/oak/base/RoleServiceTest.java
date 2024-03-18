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
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.Role.*;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import java.util.Date;
import com.levin.commons.dao.domain.TreeObject;
import java.util.Set;
import java.io.Serializable;
import com.levin.commons.service.support.InjectConst;
import java.util.List;
import com.levin.oak.base.services.role.info.*;
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
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月18日 下午3:08:58, 代码生成哈希校验码：[31053f6eeef957a2f13a4dc6f6189507]，请不要修改和删除此行内容。
 *
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
    private BizRoleService bizRoleService;

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

            // req.setModuleId("这是文本128");//归属的子系统或应用 

            // req.setTenantId("这是文本128");// 

            // req.setParentId("这是文本128");// 

            // req.setExtendable(true);// 

            // req.setMutex(true);// 

            // req.setUserLimit(1);// 

            // req.setPrecondition("这是文本1800");//支持括号优先级、&&和||操作，如：(R_SA || R_ADMIN) && R_STAFF 

            // req.setCode("这是文本128");// 必填

            // req.setExpiredDate(new Date());// 

            // req.setIcon("图标_1");// 

            // req.setOrgDataScope(OrgDataScope.Inherited);//参考组织ID列表 必填

            // req.setAssignedOrgIdList("组织ID列表_1");//指定的组织ID列表，Json数组 

            // req.setPermissionList("资源权限列表_1");//Json数组 

            // req.setNodePath("这是文本1800");//建议使用|包围节点ID，如|1|3|15| 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


       String id  = roleService.create(req);

        log.debug("新增角色->" + id);

        Assert.isTrue(id != null, "角色");

    }


    @Test
    public void queryRoleTest() {

        QueryRoleReq req = new QueryRoleReq();

        // req.setId(null);//
        // req.setModuleId("这是文本128");//归属的子系统或应用
        // req.setTenantId("这是文本128");//
        // req.setParentId("这是文本128");//
        // req.setExtendable(true);//
        // req.setMutex(true);//
        // req.setUserLimit(1);//
        // req.setPrecondition("这是文本1800");//支持括号优先级、&&和||操作，如：(R_SA || R_ADMIN) && R_STAFF
        // req.setCode("这是文本128");//
        // req.setGteExpiredDate(DateUtils.getZoneHour(new Date()));//最小
        // req.setLteExpiredDate(DateUtils.getEndHour(new Date()));//最大
        // req.setIcon("图标_1");//
        // req.setOrgDataScope(OrgDataScope.Inherited);//参考组织ID列表
        // req.setAssignedOrgIdList("组织ID列表_1");//指定的组织ID列表，Json数组
        // req.setPermissionList("资源权限列表_1");//Json数组
        // req.setLoadParent(true);//加载
        // req.setLoadChildren(true);//加载
        // req.setNodePath("这是文本1800");//建议使用|包围节点ID，如|1|3|15|
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//
        // req.setOptimisticLock(1);//

        PagingData<RoleInfo> resp = roleService.query(req,null);

        log.debug("查询角色->" + resp);

        Assert.isTrue(!resp.isEmpty(), "角色");
    }

    @Test
    public void updateRoleTest() {

         UpdateRoleReq req = new UpdateRoleReq();

         req.setId(id);


           // req.setModuleId("这是文本128");//归属的子系统或应用 
           // req.setTenantId("这是文本128");// 
           // req.setParentId("这是文本128");// 
           // req.setExtendable(true);// 
           // req.setMutex(true);// 
           // req.setUserLimit(1);// 
           // req.setPrecondition("这是文本1800");//支持括号优先级、&&和||操作，如：(R_SA || R_ADMIN) && R_STAFF 
           // req.setCode("这是文本128");// 必填
           // req.setExpiredDate(new Date());// 
           // req.setIcon("图标_1");// 
           // req.setOrgDataScope(OrgDataScope.Inherited);//参考组织ID列表 必填
           // req.setAssignedOrgIdList("组织ID列表_1");//指定的组织ID列表，Json数组 
           // req.setPermissionList("资源权限列表_1");//Json数组 
           // req.setNodePath("这是文本1800");//建议使用|包围节点ID，如|1|3|15| 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

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
