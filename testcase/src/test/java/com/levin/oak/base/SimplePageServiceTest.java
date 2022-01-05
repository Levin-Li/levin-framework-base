package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimplePage;

import com.levin.oak.base.services.simplepage.*;
import com.levin.oak.base.services.simplepage.req.*;
import com.levin.oak.base.services.simplepage.info.*;


////////////////////////////////////
//自动导入列表
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
 *  简单页面测试
 *
 *  @author auto gen by simple-dao-codegen 2021-12-18 11:15:49
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

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createSimplePageTest() {

        CreateSimplePageReq req = new CreateSimplePageReq();

            // req.setCategory("这是文本64");//分类名称 必填

            // req.setGroupName("这是文本64");//分组名称 必填

            // req.setPath("访问路径_1");//访问路径 必填

            // req.setContent("内容_1");//内容 

            // req.setDomain("这是文本64");//系统子域 

            // req.setName("这是文本512");//名称 必填

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = simplePageService.create(req);

        log.debug("新增简单页面->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void querySimplePageTest() {

        QuerySimplePageReq req = new QuerySimplePageReq();

        // req.setId(null);//id
        // req.setCategory("这是文本64");//分类名称
        // req.setGroupName("这是文本64");//分组名称
        // req.setPath("访问路径_1");//访问路径
        // req.setContent("内容_1");//内容
        // req.setDomain("这是文本64");//系统子域
        // req.setName("这是文本512");//名称
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<SimplePageInfo> resp = simplePageService.query(req,null);

        log.debug("查询简单页面->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateSimplePageTest() {

         UpdateSimplePageReq req = new UpdateSimplePageReq();

         req.setId(id);


           // req.setCategory("这是文本64");//分类名称 必填
           // req.setGroupName("这是文本64");//分组名称 必填
           // req.setPath("访问路径_1");//访问路径 必填
           // req.setContent("内容_1");//内容 
           // req.setDomain("这是文本64");//系统子域 
           // req.setName("这是文本512");//名称 必填
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = simplePageService.update(req);

          log.debug("更新简单页面-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteSimplePageTest() {

        DeleteSimplePageReq req = new DeleteSimplePageReq();

        req.setId(id);

        int n = simplePageService.delete(req);

        log.debug("删除简单页面->" + n);

        Assert.assertTrue(n > 0);
    }
}
