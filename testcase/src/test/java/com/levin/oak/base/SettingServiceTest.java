package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.services.setting.req.CreateSettingReq;
import com.levin.oak.base.services.setting.req.QuerySettingReq;
import com.levin.oak.base.services.setting.req.SettingIdReq;
import com.levin.oak.base.services.setting.req.UpdateSettingReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

////////////////////////////////////
//自动导入列表
////////////////////////////////////
//import org.junit.jupiter.api.Test;
import javax.annotation.Resource;

/**
 *  系统设置测试
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
public class SettingServiceTest {


    @Resource
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

            // req.setValueType(ValueType.Css);//值类型 必填

            // req.setValue("值_1");//值 

            // req.setNullable(true);//值是否可空 

            // req.setInputPlaceholder("这是文本64");//输入占位提示 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

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
        // req.setValueType(ValueType.Css);//值类型
        // req.setValue("值_1");//值
        // req.setNullable(true);//值是否可空
        // req.setInputPlaceholder("这是文本64");//输入占位提示
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
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
           // req.setValueType(ValueType.Css);//值类型 必填
           // req.setValue("值_1");//值 
           // req.setNullable(true);//值是否可空 
           // req.setInputPlaceholder("这是文本64");//输入占位提示 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
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

        SettingIdReq req = new SettingIdReq();

        req.setId(id);

        int n = settingService.delete(req);

        log.debug("删除系统设置->" + n);

        Assert.assertTrue(n > 0);
    }
}
