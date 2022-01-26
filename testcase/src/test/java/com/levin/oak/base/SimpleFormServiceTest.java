package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimpleForm;

import com.levin.oak.base.services.simpleform.*;
import com.levin.oak.base.services.simpleform.req.*;
import com.levin.oak.base.services.simpleform.info.*;


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
 *  简单表单测试
 *
 *  @author auto gen by simple-dao-codegen 2022-1-26 17:15:35
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class SimpleFormServiceTest {


    @Autowired
    private SimpleFormService simpleFormService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createSimpleFormTest() {

        CreateSimpleFormReq req = new CreateSimpleFormReq();

            // req.setCommitApi("提交地址_1");//提交地址 

            // req.setCategory("这是文本64");//分类名称 必填

            // req.setGroupName("这是文本64");//分组名称 必填

            // req.setPath("访问路径_1");//访问路径 必填

            // req.setContent("内容_1");//内容 

            // req.setOrgId("这是文本64");//机构ID 

            // req.setTenantId("这是文本64");//租户ID 

            // req.setDomain("这是文本64");//系统子域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = simpleFormService.create(req);

        log.debug("新增简单表单->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void querySimpleFormTest() {

        QuerySimpleFormReq req = new QuerySimpleFormReq();

        // req.setCommitApi("提交地址_1");//提交地址
        // req.setId(null);//id
        // req.setCategory("这是文本64");//分类名称
        // req.setGroupName("这是文本64");//分组名称
        // req.setPath("访问路径_1");//访问路径
        // req.setContent("内容_1");//内容
        // req.setOrgId("这是文本64");//机构ID
        // req.setTenantId("这是文本64");//租户ID
        // req.setDomain("这是文本64");//系统子域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼)
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<SimpleFormInfo> resp = simpleFormService.query(req,null);

        log.debug("查询简单表单->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateSimpleFormTest() {

         UpdateSimpleFormReq req = new UpdateSimpleFormReq();

         req.setId(id);


           // req.setCommitApi("提交地址_1");//提交地址 
           // req.setCategory("这是文本64");//分类名称 必填
           // req.setGroupName("这是文本64");//分组名称 必填
           // req.setPath("访问路径_1");//访问路径 必填
           // req.setContent("内容_1");//内容 
           // req.setOrgId("这是文本64");//机构ID 
           // req.setTenantId("这是文本64");//租户ID 
           // req.setDomain("这是文本64");//系统子域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = simpleFormService.update(req);

          log.debug("更新简单表单-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteSimpleFormTest() {

        SimpleFormIdReq req = new SimpleFormIdReq();

        req.setId(id);

        int n = simpleFormService.delete(req);

        log.debug("删除简单表单->" + n);

        Assert.assertTrue(n > 0);
    }
}
