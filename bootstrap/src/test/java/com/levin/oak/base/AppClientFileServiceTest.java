package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AppClientFile;

import com.levin.oak.base.services.appclientfile.*;
import com.levin.oak.base.services.appclientfile.req.*;
import com.levin.oak.base.services.appclientfile.info.*;


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
 *  客户端文件测试
 *
 *  @author auto gen by simple-dao-codegen 2023-2-5 11:13:19
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class AppClientFileServiceTest {

    @Resource
    private AppClientFileService appClientFileService;

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
    public void createAppClientFileTest() {

        CreateAppClientFileReq req = new CreateAppClientFileReq();

            // req.setClientType("这是文本64");//客户端类型 

            // req.setMimeType("这是文本128");//文件类型 

            // req.setPath("文件路径_1");//文件路径 必填

            // req.setContent("文件内容_1");//文件内容 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = appClientFileService.create(req);

        log.debug("新增客户端文件->" + id);

        Assert.isTrue(id != null, "客户端文件");

    }


    @Test
    public void queryAppClientFileTest() {

        QueryAppClientFileReq req = new QueryAppClientFileReq();

        // req.setId(null);//id
        // req.setClientType("这是文本64");//客户端类型
        // req.setMimeType("这是文本128");//文件类型
        // req.setPath("文件路径_1");//文件路径
        // req.setContent("文件内容_1");//文件内容
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<AppClientFileInfo> resp = appClientFileService.query(req,null);

        log.debug("查询客户端文件->" + resp);

        Assert.isTrue(!resp.isEmpty(), "客户端文件");
    }

    @Test
    public void updateAppClientFileTest() {

         UpdateAppClientFileReq req = new UpdateAppClientFileReq();

         req.setId(id);


           // req.setClientType("这是文本64");//客户端类型 
           // req.setMimeType("这是文本128");//文件类型 
           // req.setPath("文件路径_1");//文件路径 必填
           // req.setContent("文件内容_1");//文件内容 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = appClientFileService.update(req);

          log.debug("更新客户端文件-> " + resp);

          Assert.isTrue(resp > 0, "客户端文件");
    }

    @Test
    public void deleteAppClientFileTest() {

        AppClientFileIdReq req = new AppClientFileIdReq();

        req.setId(id);

        int n = appClientFileService.delete(req);

        log.debug("删除客户端文件->" + n);

        Assert.isTrue(n > 0, "客户端文件");
    }
}
