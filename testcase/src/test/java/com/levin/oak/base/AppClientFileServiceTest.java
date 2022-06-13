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
 *  客户端文件测试
 *
 *  @author auto gen by simple-dao-codegen 2022-6-13 19:41:50
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

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createAppClientFileTest() {

        CreateAppClientFileReq req = new CreateAppClientFileReq();

            // req.setClientType("这是文本64");//客户端类型 

            // req.setMimeType("这是文本64");//文件类型 

            // req.setPath("文件路径_1");//文件路径 必填

            // req.setContent("文件内容_1");//文件内容 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = appClientFileService.create(req);

        log.debug("新增客户端文件->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryAppClientFileTest() {

        QueryAppClientFileReq req = new QueryAppClientFileReq();

        // req.setId(null);//id
        // req.setClientType("这是文本64");//客户端类型
        // req.setMimeType("这是文本64");//文件类型
        // req.setPath("文件路径_1");//文件路径
        // req.setContent("文件内容_1");//文件内容
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<AppClientFileInfo> resp = appClientFileService.query(req,null);

        log.debug("查询客户端文件->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateAppClientFileTest() {

         UpdateAppClientFileReq req = new UpdateAppClientFileReq();

         req.setId(id);


           // req.setClientType("这是文本64");//客户端类型 
           // req.setMimeType("这是文本64");//文件类型 
           // req.setPath("文件路径_1");//文件路径 必填
           // req.setContent("文件内容_1");//文件内容 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = appClientFileService.update(req);

          log.debug("更新客户端文件-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteAppClientFileTest() {

        AppClientFileIdReq req = new AppClientFileIdReq();

        req.setId(id);

        int n = appClientFileService.delete(req);

        log.debug("删除客户端文件->" + n);

        Assert.assertTrue(n > 0);
    }
}
