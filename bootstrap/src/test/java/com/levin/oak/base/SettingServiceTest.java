package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.services.setting.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
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
 *  系统设置测试
 *
 *  @author auto gen by simple-dao-codegen 2023-2-22 18:58:29
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
    public void createSettingTest() {

        CreateSettingReq req = new CreateSettingReq();

            // req.setCategoryName("这是文本64");//分类名称 必填

            // req.setGroupName("这是文本64");//分组名称 

            // req.setCode("这是文本64");//编码 必填

            // req.setValueType(ValueType.Html);//值类型 必填

            // req.setValue("值_1");//值 

            // req.setNullable(true);//值是否可空 

            // req.setInputPlaceholder("这是文本128");//输入占位提示 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = settingService.create(req);

        log.debug("新增系统设置->" + id);

        Assert.isTrue(id != null, "系统设置");

    }


    @Test
    public void querySettingTest() {

        QuerySettingReq req = new QuerySettingReq();

        // req.setId(null);//id
        // req.setCategoryName("这是文本64");//分类名称
        // req.setGroupName("这是文本64");//分组名称
        // req.setCode("这是文本64");//编码
        // req.setValueType(ValueType.Html);//值类型
        // req.setValue("值_1");//值
        // req.setNullable(true);//值是否可空
        // req.setInputPlaceholder("这是文本128");//输入占位提示
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<SettingInfo> resp = settingService.query(req,null);

        log.debug("查询系统设置->" + resp);

        Assert.isTrue(!resp.isEmpty(), "系统设置");
    }

    @Test
    public void updateSettingTest() {

         UpdateSettingReq req = new UpdateSettingReq();

         req.setId(id);


           // req.setCategoryName("这是文本64");//分类名称 必填
           // req.setGroupName("这是文本64");//分组名称 
           // req.setCode("这是文本64");//编码 必填
           // req.setValueType(ValueType.Html);//值类型 必填
           // req.setValue("值_1");//值 
           // req.setNullable(true);//值是否可空 
           // req.setInputPlaceholder("这是文本128");//输入占位提示 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = settingService.update(req);

          log.debug("更新系统设置-> " + resp);

          Assert.isTrue(resp > 0, "系统设置");
    }

    @Test
    public void deleteSettingTest() {

        SettingIdReq req = new SettingIdReq();

        req.setId(id);

        int n = settingService.delete(req);

        log.debug("删除系统设置->" + n);

        Assert.isTrue(n > 0, "系统设置");
    }
}
