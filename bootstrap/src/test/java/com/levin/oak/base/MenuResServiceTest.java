package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.MenuRes;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.menures.*;
import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import com.levin.oak.base.entities.MenuRes;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.rbac.MenuItem.*;
import java.util.Set;
import com.levin.oak.base.services.menures.info.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
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
 * 菜单测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:07, 代码生成哈希校验码：[2da3a72d0aed5396ea74bd8cb77be706]，请不要修改和删除此行内容。
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

    @Autowired
    private BizMenuResService bizMenuResService;

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

            // req.setParentId("这是文本64");// 

            // req.setTenantId("这是文本64");// 

            // req.setDomain("系统域_1");//归属的子系统 

            // req.setRequireAuthorizations("这是文本1800");// 

            // req.setAlwaysShow(true);// 必填

            // req.setTarget("这是文本64");// 

            // req.setActionType(ActionType.Default);// 

            // req.setIcon("图标_1");// 

            // req.setPath("路径/链接_1");// 

            // req.setParams("这是文本1800");// 

            // req.setNodePath("这是文本1800");//建议使用|包围节点ID，如|1|3|15| 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


       String id  = menuResService.create(req);

        log.debug("新增菜单->" + id);

        Assert.isTrue(id != null, "菜单");

    }


    @Test
    public void queryMenuResTest() {

        QueryMenuResReq req = new QueryMenuResReq();

        // req.setId(null);//
        // req.setParentId("这是文本64");//
        // req.setTenantId("这是文本64");//
        // req.setDomain("系统域_1");//归属的子系统
        // req.setRequireAuthorizations("这是文本1800");//
        // req.setAlwaysShow(true);//
        // req.setTarget("这是文本64");//
        // req.setActionType(ActionType.Default);//
        // req.setIcon("图标_1");//
        // req.setPath("路径/链接_1");//
        // req.setParams("这是文本1800");//
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

        PagingData<MenuResInfo> resp = menuResService.query(req,null);

        log.debug("查询菜单->" + resp);

        Assert.isTrue(!resp.isEmpty(), "菜单");
    }

    @Test
    public void updateMenuResTest() {

         UpdateMenuResReq req = new UpdateMenuResReq();

         req.setId(id);


           // req.setParentId("这是文本64");// 
           // req.setTenantId("这是文本64");// 
           // req.setDomain("系统域_1");//归属的子系统 
           // req.setRequireAuthorizations("这是文本1800");// 
           // req.setAlwaysShow(true);// 必填
           // req.setTarget("这是文本64");// 
           // req.setActionType(ActionType.Default);// 
           // req.setIcon("图标_1");// 
           // req.setPath("路径/链接_1");// 
           // req.setParams("这是文本1800");// 
           // req.setNodePath("这是文本1800");//建议使用|包围节点ID，如|1|3|15| 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

          boolean ok = menuResService.update(req);

          log.debug("更新菜单-> " + ok);

          Assert.isTrue(ok, "菜单");
    }

    @Test
    public void deleteMenuResTest() {

        MenuResIdReq req = new MenuResIdReq();

        req.setId(id);

        boolean ok = menuResService.delete(req);

        log.debug("删除菜单->" + ok);

        Assert.isTrue(ok , "菜单");
    }
}
