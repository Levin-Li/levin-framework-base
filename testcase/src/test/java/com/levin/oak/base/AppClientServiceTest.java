package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AppClient;

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

import javax.annotation.Resource;

/**
 *  应用接入测试
 *
 *  @author auto gen by simple-dao-codegen 2022-4-3 1:34:17
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class AppClientServiceTest {


    @Resource
    private AppClientService appClientService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createAppClientTest() {

        CreateAppClientReq req = new CreateAppClientReq();

            // req.setAppId("这是文本128");//应用ID 必填

            // req.setAppSecret("这是文本128");//应用密钥 必填

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统子域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = appClientService.create(req);

        log.debug("新增应用接入->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryAppClientTest() {

        QueryAppClientReq req = new QueryAppClientReq();

        // req.setId(null);//id
        // req.setAppId(null);//应用ID
        // req.setAppSecret("这是文本128");//应用密钥
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统子域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼)
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<AppClientInfo> resp = appClientService.query(req,null);

        log.debug("查询应用接入->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateAppClientTest() {

         UpdateAppClientReq req = new UpdateAppClientReq();

         req.setId(id);


           // req.setAppId("这是文本128");//应用ID 必填
           // req.setAppSecret("这是文本128");//应用密钥 必填
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统子域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = appClientService.update(req);

          log.debug("更新应用接入-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteAppClientTest() {

        AppClientIdReq req = new AppClientIdReq();

        req.setId(id);

        int n = appClientService.delete(req);

        log.debug("删除应用接入->" + n);

        Assert.assertTrue(n > 0);
    }
}
