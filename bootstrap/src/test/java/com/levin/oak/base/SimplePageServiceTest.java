package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimplePage;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.simplepage.*;
import com.levin.oak.base.services.simplepage.req.*;
import com.levin.oak.base.services.simplepage.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * 简单页面测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月14日 下午3:54:13, 代码生成哈希校验码：[7f1c77bf343149768026c81b51b7a131]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class SimplePageServiceTest {

    @Autowired
    private SimplePageService simplePageService;

    @Autowired
    private BizSimplePageService bizSimplePageService;

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

            // req.setType("这是文本128");// 必填

            // req.setCategory("这是文本128");// 必填

            // req.setGroupName("这是文本128");// 必填

            // req.setIcon("图标_1");// 

            // req.setPath("这是文本800");// 必填

            // req.setRequireAuthorizations("这是文本1800");// 

            // req.setContent("内容_1");// 

            // req.setDomain("这是文本128");//归属的子系统或应用 

            // req.setName("这是文本64");// 必填

            // req.setOptimisticLock(1);// 

            // req.setOrgId("这是文本128");// 

            // req.setTenantId("这是文本128");// 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String id  = simplePageService.create(req);

        log.debug("新增简单页面->" + id);

        Assert.isTrue(id != null, "简单页面");

    }


    @Test
    public void querySimplePageTest() {

        QuerySimplePageReq req = new QuerySimplePageReq();

        // req.setId(null);//
        // req.setType("这是文本128");//
        // req.setCategory("这是文本128");//
        // req.setGroupName("这是文本128");//
        // req.setIcon("图标_1");//
        // req.setPath("这是文本800");//
        // req.setRequireAuthorizations("这是文本1800");//
        // req.setContent("内容_1");//
        // req.setDomain("这是文本128");//归属的子系统或应用
        // req.setName("这是文本64");//
        // req.setOptimisticLock(1);//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<SimplePageInfo> resp = simplePageService.query(req,null);

        log.debug("查询简单页面->" + resp);

        Assert.isTrue(!resp.isEmpty(), "简单页面");
    }

    @Test
    public void updateSimplePageTest() {

         UpdateSimplePageReq req = new UpdateSimplePageReq();

         req.setId(id);


           // req.setType("这是文本128");// 必填
           // req.setCategory("这是文本128");// 必填
           // req.setGroupName("这是文本128");// 必填
           // req.setIcon("图标_1");// 
           // req.setPath("这是文本800");// 必填
           // req.setRequireAuthorizations("这是文本1800");// 
           // req.setContent("内容_1");// 
           // req.setDomain("这是文本128");//归属的子系统或应用 
           // req.setName("这是文本64");// 必填
           // req.setOptimisticLock(1);// 
           // req.setOrgId("这是文本128");// 
           // req.setTenantId("这是文本128");// 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = simplePageService.update(req);

          log.debug("更新简单页面-> " + ok);

          Assert.isTrue(ok, "简单页面");
    }

    @Test
    public void deleteSimplePageTest() {

        SimplePageIdReq req = new SimplePageIdReq();

        req.setId(id);

        boolean ok = simplePageService.delete(req);

        log.debug("删除简单页面->" + ok);

        Assert.isTrue(ok , "简单页面");
    }
}
