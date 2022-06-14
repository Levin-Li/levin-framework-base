package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimpleApi;

import com.levin.oak.base.services.simpleapi.*;
import com.levin.oak.base.services.simpleapi.req.*;
import com.levin.oak.base.services.simpleapi.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.SimpleApi.*;
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

import javax.annotation.Resource;

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
 *  简单动态接口测试
 *
 *  @author auto gen by simple-dao-codegen 2022-6-14 9:26:30
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class SimpleApiServiceTest {


    @Resource
    private SimpleApiService simpleApiService;

    private String id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createSimpleApiTest() {

        CreateSimpleApiReq req = new CreateSimpleApiReq();

            // req.setMethods("这是文本16");//http方法 

            // req.setLanguage(Language.Groovy);//脚本语言 必填

            // req.setCategory("这是文本128");//分类名称 必填

            // req.setGroupName("这是文本128");//分组名称 必填

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


       String id  = simpleApiService.create(req);

        log.debug("新增简单动态接口->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void querySimpleApiTest() {

        QuerySimpleApiReq req = new QuerySimpleApiReq();

        // req.setMethods("这是文本16");//http方法
        // req.setLanguage(Language.Groovy);//脚本语言
        // req.setId(null);//id
        // req.setCategory("这是文本128");//分类名称
        // req.setGroupName("这是文本128");//分组名称
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

        PagingData<SimpleApiInfo> resp = simpleApiService.query(req,null);

        log.debug("查询简单动态接口->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateSimpleApiTest() {

         UpdateSimpleApiReq req = new UpdateSimpleApiReq();

         req.setId(id);


           // req.setMethods("这是文本16");//http方法 
           // req.setLanguage(Language.Groovy);//脚本语言 必填
           // req.setCategory("这是文本128");//分类名称 必填
           // req.setGroupName("这是文本128");//分组名称 必填
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

          int resp = simpleApiService.update(req);

          log.debug("更新简单动态接口-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteSimpleApiTest() {

        SimpleApiIdReq req = new SimpleApiIdReq();

        req.setId(id);

        int n = simpleApiService.delete(req);

        log.debug("删除简单动态接口->" + n);

        Assert.assertTrue(n > 0);
    }
}
