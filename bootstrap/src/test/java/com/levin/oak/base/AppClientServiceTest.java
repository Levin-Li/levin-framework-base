package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AppClient;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.appclient.*;
import com.levin.oak.base.services.appclient.req.*;
import com.levin.oak.base.services.appclient.info.*;


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
 * 应用接入测试
 *
 * Auto gen by simple-dao-codegen 2023年6月28日 上午11:30:58
 * 代码生成哈希校验码：[f2f41dc4f87d6ae6e88aec57eff5b414]
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class AppClientServiceTest {

    @Autowired
    private AppClientService appClientService;

    @Autowired
    private BizAppClientService bizappClientService;

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
    public void createAppClientTest() {

        CreateAppClientReq req = new CreateAppClientReq();

            // req.setAppId("这是文本64");// 必填

            // req.setAppSecret("这是文本512");// 必填

            // req.setTenantId("这是文本128");// 

            // req.setDomain("这是文本128");// 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String id  = appClientService.create(req);

        log.debug("新增应用接入->" + id);

        Assert.isTrue(id != null, "应用接入");

    }


    @Test
    public void queryAppClientTest() {

        QueryAppClientReq req = new QueryAppClientReq();

        // req.setId(null);//
        // req.setAppId(null);//
        // req.setAppSecret("这是文本512");//
        // req.setTenantId("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<AppClientInfo> resp = appClientService.query(req,null);

        log.debug("查询应用接入->" + resp);

        Assert.isTrue(!resp.isEmpty(), "应用接入");
    }

    @Test
    public void updateAppClientTest() {

         UpdateAppClientReq req = new UpdateAppClientReq();

         req.setId(id);


           // req.setAppId("这是文本64");// 必填
           // req.setAppSecret("这是文本512");// 必填
           // req.setTenantId("这是文本128");// 
           // req.setDomain("这是文本128");// 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = appClientService.update(req);

          log.debug("更新应用接入-> " + ok);

          Assert.isTrue(ok, "应用接入");
    }

    @Test
    public void deleteAppClientTest() {

        AppClientIdReq req = new AppClientIdReq();

        req.setId(id);

        boolean ok = appClientService.delete(req);

        log.debug("删除应用接入->" + ok);

        Assert.isTrue(ok , "应用接入");
    }
}
