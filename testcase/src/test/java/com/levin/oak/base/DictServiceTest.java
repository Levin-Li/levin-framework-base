package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Dict;

import com.levin.oak.base.services.dict.*;
import com.levin.oak.base.services.dict.req.*;
import com.levin.oak.base.services.dict.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Dict.*;
import java.util.List;
import com.levin.commons.service.support.DefaultJsonConverter;
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
 *  字典测试
 *
 *  @author auto gen by simple-dao-codegen 2022-3-29 23:06:04
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class DictServiceTest {


    @Autowired
    private DictService dictService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createDictTest() {

        CreateDictReq req = new CreateDictReq();

            // req.setType(Type.System);//类型 必填

            // req.setCode("这是文本64");//编码 必填

            // req.setItemList("编码项_1");//编码项 

            // req.setTenantId("这是文本64");//租户ID 

            // req.setDomain("这是文本64");//系统子域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = dictService.create(req);

        log.debug("新增字典->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryDictTest() {

        QueryDictReq req = new QueryDictReq();

        // req.setId(null);//id
        // req.setType(Type.System);//类型
        // req.setCode("这是文本64");//编码
        // req.setItemList("编码项_1");//编码项
        // req.setTenantId("这是文本64");//租户ID
        // req.setDomain("这是文本64");//系统子域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼)
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<DictInfo> resp = dictService.query(req,null);

        log.debug("查询字典->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateDictTest() {

         UpdateDictReq req = new UpdateDictReq();

         req.setId(id);


           // req.setType(Type.System);//类型 必填
           // req.setCode("这是文本64");//编码 必填
           // req.setItemList("编码项_1");//编码项 
           // req.setTenantId("这是文本64");//租户ID 
           // req.setDomain("这是文本64");//系统子域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = dictService.update(req);

          log.debug("更新字典-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteDictTest() {

        DictIdReq req = new DictIdReq();

        req.setId(id);

        int n = dictService.delete(req);

        log.debug("删除字典->" + n);

        Assert.assertTrue(n > 0);
    }
}
