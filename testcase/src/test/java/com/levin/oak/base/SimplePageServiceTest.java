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
 *  简单页面测试
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
public class SimplePageServiceTest {


    @Resource
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

            // req.setType("这是文本64");//类型 必填

            // req.setCategory("这是文本64");//分类名称 必填

            // req.setGroupName("这是文本64");//分组名称 必填

            // req.setIcon("图标_1");//图标 

            // req.setPath("访问路径_1");//访问路径 必填

            // req.setRequireAuthorizations("这是文本1800");//需要的权限或角色，json数组 

            // req.setContent("内容_1");//内容 

            // req.setOrgId("这是文本128");//机构ID 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

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
        // req.setType("这是文本64");//类型
        // req.setCategory("这是文本64");//分类名称
        // req.setGroupName("这是文本64");//分组名称
        // req.setIcon("图标_1");//图标
        // req.setPath("访问路径_1");//访问路径
        // req.setRequireAuthorizations("这是文本1800");//需要的权限或角色，json数组
        // req.setContent("内容_1");//内容
        // req.setOrgId("这是文本128");//机构ID
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
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


           // req.setType("这是文本64");//类型 必填
           // req.setCategory("这是文本64");//分类名称 必填
           // req.setGroupName("这是文本64");//分组名称 必填
           // req.setIcon("图标_1");//图标 
           // req.setPath("访问路径_1");//访问路径 必填
           // req.setRequireAuthorizations("这是文本1800");//需要的权限或角色，json数组 
           // req.setContent("内容_1");//内容 
           // req.setOrgId("这是文本128");//机构ID 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
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

        SimplePageIdReq req = new SimplePageIdReq();

        req.setId(id);

        int n = simplePageService.delete(req);

        log.debug("删除简单页面->" + n);

        Assert.assertTrue(n > 0);
    }
}
