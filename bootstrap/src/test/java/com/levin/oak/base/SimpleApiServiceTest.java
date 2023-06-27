package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimpleApi;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.simpleapi.*;
import com.levin.oak.base.services.simpleapi.req.*;
import com.levin.oak.base.services.simpleapi.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.SimpleApi.*;
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
 * 简单动态接口测试
 *
 * @author auto gen by simple-dao-codegen 2023年6月28日 上午12:45:57
 * 代码生成哈希校验码：[599c8dccf2d39199fbf4679f6ae060a9]
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class SimpleApiServiceTest {

    @Autowired
    private SimpleApiService simpleApiService;

    @Autowired
    private BizSimpleApiService bizsimpleApiService;

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
    public void createSimpleApiTest() {

        CreateSimpleApiReq req = new CreateSimpleApiReq();

            // req.setMethods("这是文本16");//逗号隔开 

            // req.setLanguage(Language.Groovy);// 必填

            // req.setType("这是文本128");// 必填

            // req.setCategory("这是文本128");// 必填

            // req.setGroupName("这是文本128");// 必填

            // req.setIcon("图标_1");// 

            // req.setPath("访问路径_1");// 必填

            // req.setRequireAuthorizations("这是文本1800");// 

            // req.setContent("内容_1");// 

            // req.setOrgId("这是文本64");// 

            // req.setTenantId("这是文本128");// 

            // req.setDomain("这是文本128");// 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String id  = simpleApiService.create(req);

        log.debug("新增简单动态接口->" + id);

        Assert.isTrue(id != null, "简单动态接口");

    }


    @Test
    public void querySimpleApiTest() {

        QuerySimpleApiReq req = new QuerySimpleApiReq();

        // req.setMethods("这是文本16");//逗号隔开
        // req.setLanguage(Language.Groovy);//
        // req.setId(null);//
        // req.setType("这是文本128");//
        // req.setCategory("这是文本128");//
        // req.setGroupName("这是文本128");//
        // req.setIcon("图标_1");//
        // req.setPath("访问路径_1");//
        // req.setRequireAuthorizations("这是文本1800");//
        // req.setContent("内容_1");//
        // req.setOrgId("这是文本64");//
        // req.setTenantId("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<SimpleApiInfo> resp = simpleApiService.query(req,null);

        log.debug("查询简单动态接口->" + resp);

        Assert.isTrue(!resp.isEmpty(), "简单动态接口");
    }

    @Test
    public void updateSimpleApiTest() {

         UpdateSimpleApiReq req = new UpdateSimpleApiReq();

         req.setId(id);


           // req.setMethods("这是文本16");//逗号隔开 
           // req.setLanguage(Language.Groovy);// 必填
           // req.setType("这是文本128");// 必填
           // req.setCategory("这是文本128");// 必填
           // req.setGroupName("这是文本128");// 必填
           // req.setIcon("图标_1");// 
           // req.setPath("访问路径_1");// 必填
           // req.setRequireAuthorizations("这是文本1800");// 
           // req.setContent("内容_1");// 
           // req.setOrgId("这是文本64");// 
           // req.setTenantId("这是文本128");// 
           // req.setDomain("这是文本128");// 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = simpleApiService.update(req);

          log.debug("更新简单动态接口-> " + ok);

          Assert.isTrue(ok, "简单动态接口");
    }

    @Test
    public void deleteSimpleApiTest() {

        SimpleApiIdReq req = new SimpleApiIdReq();

        req.setId(id);

        boolean ok = simpleApiService.delete(req);

        log.debug("删除简单动态接口->" + ok);

        Assert.isTrue(ok , "简单动态接口");
    }
}
