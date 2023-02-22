package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimplePage;

import com.levin.oak.base.services.simplepage.*;
import com.levin.oak.base.services.simplepage.req.*;
import com.levin.oak.base.services.simplepage.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
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
 *  简单页面测试
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
public class SimplePageServiceTest {

    @Resource
    private SimplePageService simplePageService;

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
    public void createSimplePageTest() {

        CreateSimplePageReq req = new CreateSimplePageReq();

            // req.setType("这是文本128");//类型 必填

            // req.setCategory("这是文本128");//分类名称 必填

            // req.setGroupName("这是文本128");//分组名称 必填

            // req.setIcon("图标_1");//图标 

            // req.setPath("访问路径_1");//访问路径 必填

            // req.setRequireAuthorizations("这是文本1800");//需要的权限或角色，json数组 

            // req.setContent("内容_1");//内容 

            // req.setOrgId("这是文本64");//机构ID 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = simplePageService.create(req);

        log.debug("新增简单页面->" + id);

        Assert.isTrue(id != null, "简单页面");

    }


    @Test
    public void querySimplePageTest() {

        QuerySimplePageReq req = new QuerySimplePageReq();

        // req.setId(null);//id
        // req.setType("这是文本128");//类型
        // req.setCategory("这是文本128");//分类名称
        // req.setGroupName("这是文本128");//分组名称
        // req.setIcon("图标_1");//图标
        // req.setPath("访问路径_1");//访问路径
        // req.setRequireAuthorizations("这是文本1800");//需要的权限或角色，json数组
        // req.setContent("内容_1");//内容
        // req.setOrgId("这是文本64");//机构ID
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<SimplePageInfo> resp = simplePageService.query(req,null);

        log.debug("查询简单页面->" + resp);

        Assert.isTrue(!resp.isEmpty(), "简单页面");
    }

    @Test
    public void updateSimplePageTest() {

         UpdateSimplePageReq req = new UpdateSimplePageReq();

         req.setId(id);


           // req.setType("这是文本128");//类型 必填
           // req.setCategory("这是文本128");//分类名称 必填
           // req.setGroupName("这是文本128");//分组名称 必填
           // req.setIcon("图标_1");//图标 
           // req.setPath("访问路径_1");//访问路径 必填
           // req.setRequireAuthorizations("这是文本1800");//需要的权限或角色，json数组 
           // req.setContent("内容_1");//内容 
           // req.setOrgId("这是文本64");//机构ID 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = simplePageService.update(req);

          log.debug("更新简单页面-> " + resp);

          Assert.isTrue(resp > 0, "简单页面");
    }

    @Test
    public void deleteSimplePageTest() {

        SimplePageIdReq req = new SimplePageIdReq();

        req.setId(id);

        int n = simplePageService.delete(req);

        log.debug("删除简单页面->" + n);

        Assert.isTrue(n > 0, "简单页面");
    }
}
