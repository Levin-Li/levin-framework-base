package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.services.setting.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Setting.*;
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
 *  系统设置测试
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
public class SettingServiceTest {


    @Autowired
    private SettingService settingService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createSettingTest() {

        CreateSettingReq req = new CreateSettingReq();

            // req.setCategoryName("这是文本64");//分类名称 必填

            // req.setGroupName("这是文本64");//分组名称 

            // req.setCode("这是文本64");//编码 必填

            // req.setValueType(ValueType.Text);//值类型 必填

            // req.setValue("值_1");//值 

            // req.setNullable(true);//值是否可空 

            // req.setInputPlaceholder("这是文本64");//输入占位提示 

            // req.setDomain("这是文本64");//系统子域 

            // req.setName("这是文本512");//名称 必填

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = settingService.create(req);

        log.debug("新增系统设置->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void querySettingTest() {

        QuerySettingReq req = new QuerySettingReq();

        // req.setId(null);//id
        // req.setCategoryName("这是文本64");//分类名称
        // req.setGroupName("这是文本64");//分组名称
        // req.setCode("这是文本64");//编码
        // req.setValueType(ValueType.Text);//值类型
        // req.setValue("值_1");//值
        // req.setNullable(true);//值是否可空
        // req.setInputPlaceholder("这是文本64");//输入占位提示
        // req.setDomain("这是文本64");//系统子域
        // req.setName("这是文本512");//名称
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<SettingInfo> resp = settingService.query(req,null);

        log.debug("查询系统设置->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateSettingTest() {

         UpdateSettingReq req = new UpdateSettingReq();

         req.setId(id);


           // req.setCategoryName("这是文本64");//分类名称 必填
           // req.setGroupName("这是文本64");//分组名称 
           // req.setCode("这是文本64");//编码 必填
           // req.setValueType(ValueType.Text);//值类型 必填
           // req.setValue("值_1");//值 
           // req.setNullable(true);//值是否可空 
           // req.setInputPlaceholder("这是文本64");//输入占位提示 
           // req.setDomain("这是文本64");//系统子域 
           // req.setName("这是文本512");//名称 必填
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = settingService.update(req);

          log.debug("更新系统设置-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteSettingTest() {

        DeleteSettingReq req = new DeleteSettingReq();

        req.setId(id);

        int n = settingService.delete(req);

        log.debug("删除系统设置->" + n);

        Assert.assertTrue(n > 0);
    }
}
