package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Role;

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
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  角色测试
 *
 *  @author auto gen by simple-dao-codegen 2022-6-14 9:26:30
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class RoleServiceTest {


    @Resource
    private RoleService roleService;

    private String id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createRoleTest() {

        CreateRoleReq req = new CreateRoleReq();

            // req.setCode("这是文本128");//编码 必填

            // req.setIcon("图标_1");//图标 

            // req.setOrgDataScope(OrgDataScope.All);//部门数据权限 必填

            // req.setAssignedOrgIdList("指定的部门列表_1");//指定的部门列表 

            // req.setPermissionList("资源权限列表_1");//资源权限列表 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = roleService.create(req);

        log.debug("新增角色->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryRoleTest() {

        QueryRoleReq req = new QueryRoleReq();

        // req.setId(null);//id
        // req.setCode("这是文本128");//编码
        // req.setIcon("图标_1");//图标
        // req.setOrgDataScope(OrgDataScope.All);//部门数据权限
        // req.setAssignedOrgIdList("指定的部门列表_1");//指定的部门列表
        // req.setPermissionList("资源权限列表_1");//资源权限列表
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<RoleInfo> resp = roleService.query(req,null);

        log.debug("查询角色->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateRoleTest() {

         UpdateRoleReq req = new UpdateRoleReq();

         req.setId(id);


           // req.setCode("这是文本128");//编码 必填
           // req.setIcon("图标_1");//图标 
           // req.setOrgDataScope(OrgDataScope.All);//部门数据权限 必填
           // req.setAssignedOrgIdList("指定的部门列表_1");//指定的部门列表 
           // req.setPermissionList("资源权限列表_1");//资源权限列表 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = roleService.update(req);

          log.debug("更新角色-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteRoleTest() {

        RoleIdReq req = new RoleIdReq();

        req.setId(id);

        int n = roleService.delete(req);

        log.debug("删除角色->" + n);

        Assert.assertTrue(n > 0);
    }
}
