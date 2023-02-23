package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.MenuRes;

import com.levin.oak.base.services.menures.*;
import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.rbac.MenuItem.*;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.services.menures.info.*;
import java.util.Set;
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
 *  菜单测试
 *
 *  @author auto gen by simple-dao-codegen 2023-2-22 18:58:29
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class MenuResServiceTest {

    @Autowired
    private MenuResService menuResService;

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
    public void createMenuResTest() {

        CreateMenuResReq req = new CreateMenuResReq();

            // req.setParentId("这是文本64");//父ID 

            // req.setTenantId("这是文本64");//租户ID 

            // req.setDomain("这是文本128");//子系统 

            // req.setRequireAuthorizations("这是文本1800");//需要的授权，权限或角色，json数组 

            // req.setAlwaysShow(true);//无权限时是否展示 必填

            // req.setTarget("这是文本64");//目标 

            // req.setActionType(ActionType.Default);//打开方式 

            // req.setIcon("图标_1");//图标 

            // req.setPath("路径/链接_1");//路径/链接 

            // req.setParams("这是文本1800");//参数 

            // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = menuResService.create(req);

        log.debug("新增菜单->" + id);

        Assert.isTrue(id != null, "菜单");

    }


    @Test
    public void queryMenuResTest() {

        QueryMenuResReq req = new QueryMenuResReq();

        // req.setId(null);//id
        // req.setParentId("这是文本64");//父ID
        // req.setTenantId("这是文本64");//租户ID
        // req.setDomain("这是文本128");//子系统
        // req.setRequireAuthorizations("这是文本1800");//需要的授权，权限或角色，json数组
        // req.setAlwaysShow(true);//无权限时是否展示
        // req.setTarget("这是文本64");//目标
        // req.setActionType(ActionType.Default);//打开方式
        // req.setIcon("图标_1");//图标
        // req.setPath("路径/链接_1");//路径/链接
        // req.setParams("这是文本1800");//参数
        // req.setLoadParent(true);//加载父对象
        // req.setLoadChildren(true);//加载子节点
        // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15|
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<MenuResInfo> resp = menuResService.query(req,null);

        log.debug("查询菜单->" + resp);

        Assert.isTrue(!resp.isEmpty(), "菜单");
    }

    @Test
    public void updateMenuResTest() {

         UpdateMenuResReq req = new UpdateMenuResReq();

         req.setId(id);


           // req.setParentId("这是文本64");//父ID 
           // req.setTenantId("这是文本64");//租户ID 
           // req.setDomain("这是文本128");//子系统 
           // req.setRequireAuthorizations("这是文本1800");//需要的授权，权限或角色，json数组 
           // req.setAlwaysShow(true);//无权限时是否展示 必填
           // req.setTarget("这是文本64");//目标 
           // req.setActionType(ActionType.Default);//打开方式 
           // req.setIcon("图标_1");//图标 
           // req.setPath("路径/链接_1");//路径/链接 
           // req.setParams("这是文本1800");//参数 
           // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = menuResService.update(req);

          log.debug("更新菜单-> " + resp);

          Assert.isTrue(resp > 0, "菜单");
    }

    @Test
    public void deleteMenuResTest() {

        MenuResIdReq req = new MenuResIdReq();

        req.setId(id);

        int n = menuResService.delete(req);

        log.debug("删除菜单->" + n);

        Assert.isTrue(n > 0, "菜单");
    }
}
