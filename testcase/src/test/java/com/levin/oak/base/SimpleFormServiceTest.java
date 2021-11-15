package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimpleForm;

import com.levin.oak.base.services.simpleform.*;
import com.levin.oak.base.services.simpleform.req.*;
import com.levin.oak.base.services.simpleform.info.*;


////////////////////////////////////
//自动导入列表
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

/**
 *  动态API测试
 *
 *  @author auto gen by simple-dao-codegen 2021-11-15 15:01:49
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

            // req.setCategory("分类名称_1");//分类名称 必填

            // req.setGroupName("分组名称_1");//分组名称 必填

            // req.setPath("路径_1");//路径 必填

            // req.setFormText("表单文本_1");//表单文本 

            // req.setOrgId("机构ID_1");//机构ID 

            // req.setDomain("子域_1");//子域 

            // req.setTenantId("租户ID_1");//租户ID 

            // req.setDomain("子域，模块或是自系统_1");//子域，模块或是自系统 

            // req.setName("这是文本512");//名称 必填

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本1800");//备注 


       Long id  = simpleFormService.create(req);

        log.debug("新增动态API->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void querySimpleFormTest() {

        QuerySimpleFormReq req = new QuerySimpleFormReq();

        // req.setId(null);//id
        // req.setCategory("分类名称_1");//分类名称
        // req.setGroupName("分组名称_1");//分组名称
        // req.setPath("路径_1");//路径
        // req.setFormText("表单文本_1");//表单文本
        // req.setOrgId("机构ID_1");//机构ID
        // req.setDomain("子域_1");//子域
        // req.setTenantId("租户ID_1");//租户ID
        // req.setDomain("子域，模块或是自系统_1");//子域，模块或是自系统
        // req.setName("这是文本512");//名称
        // req.setCreator("这是文本512");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本1800");//备注

        PagingData<SimpleFormInfo> resp = simpleFormService.query(req,null);

        log.debug("查询动态API->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateSimpleFormTest() {

         UpdateSimpleFormReq req = new UpdateSimpleFormReq();

         req.setId(id);


           // req.setCategory("分类名称_1");//分类名称 必填
           // req.setGroupName("分组名称_1");//分组名称 必填
           // req.setPath("路径_1");//路径 必填
           // req.setFormText("表单文本_1");//表单文本 
           // req.setOrgId("机构ID_1");//机构ID 
           // req.setDomain("子域_1");//子域 
           // req.setTenantId("租户ID_1");//租户ID 
           // req.setDomain("子域，模块或是自系统_1");//子域，模块或是自系统 
           // req.setName("这是文本512");//名称 必填
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本1800");//备注 

          int resp = simpleFormService.update(req);

          log.debug("更新动态API-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteSimpleFormTest() {

        DeleteSimpleFormReq req = new DeleteSimpleFormReq();

        req.setId(id);

        int n = simpleFormService.delete(req);

        log.debug("删除动态API->" + n);

        Assert.assertTrue(n > 0);
    }
}
