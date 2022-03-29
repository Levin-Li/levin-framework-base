package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Tenant;

import com.levin.oak.base.services.tenant.*;
import com.levin.oak.base.services.tenant.req.*;
import com.levin.oak.base.services.tenant.info.*;


////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
 *  租户测试
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
public class TenantServiceTest {


    @Autowired
    private TenantService tenantService;

    private String id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createTenantTest() {

        CreateTenantReq req = new CreateTenantReq();

            // req.setLogo("租户头像_1");//租户头像 

            // req.setCode("企业信用编码_1");//企业信用编码 

            // req.setTenantKey("租户编码_1");//租户编码 必填

            // req.setBalance(0.1d);//帐号余额 

            // req.setLicenseCnt(1);//总坐席数 

            // req.setRemainingLicenseCnt(1);//剩余坐席数 

            // req.setLicenseExpire(new Date());//到期时间 

            // req.setContractPerson("联系人_1");//联系人 

            // req.setContractPhone("联系电话_1");//联系电话 

            // req.setDomainList("域名列表_1");//域名列表 

            // req.setAppId("这是文本32");//appId 

            // req.setAppSecret("这是文本256");//appSecret 

            // req.setEncryptKey("EncryptKey_1");//EncryptKey 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = tenantService.create(req);

        log.debug("新增租户->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryTenantTest() {

        QueryTenantReq req = new QueryTenantReq();

        // req.setId(null);//ID
        // req.setLogo("租户头像_1");//租户头像
        // req.setCode("企业信用编码_1");//企业信用编码
        // req.setTenantKey("租户编码_1");//租户编码
        // req.setBalance(0.1d);//帐号余额
        // req.setLicenseCnt(1);//总坐席数
        // req.setRemainingLicenseCnt(1);//剩余坐席数
        // req.setGteLicenseExpire(DateUtils.getZoneHour(new Date()));//最小到期时间
        // req.setLteLicenseExpire(DateUtils.getEndHour(new Date()));//最大到期时间
        // req.setContractPerson("联系人_1");//联系人
        // req.setContractPhone("联系电话_1");//联系电话
        // req.setDomainList("域名列表_1");//域名列表
        // req.setAppId("这是文本32");//appId
        // req.setAppSecret("这是文本256");//appSecret
        // req.setEncryptKey("EncryptKey_1");//EncryptKey
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼)
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<TenantInfo> resp = tenantService.query(req,null);

        log.debug("查询租户->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateTenantTest() {

         UpdateTenantReq req = new UpdateTenantReq();

         req.setId(id);


           // req.setLogo("租户头像_1");//租户头像 
           // req.setCode("企业信用编码_1");//企业信用编码 
           // req.setTenantKey("租户编码_1");//租户编码 必填
           // req.setBalance(0.1d);//帐号余额 
           // req.setLicenseCnt(1);//总坐席数 
           // req.setRemainingLicenseCnt(1);//剩余坐席数 
           // req.setLicenseExpire(new Date());//到期时间 
           // req.setContractPerson("联系人_1");//联系人 
           // req.setContractPhone("联系电话_1");//联系电话 
           // req.setDomainList("域名列表_1");//域名列表 
           // req.setAppId("这是文本32");//appId 
           // req.setAppSecret("这是文本256");//appSecret 
           // req.setEncryptKey("EncryptKey_1");//EncryptKey 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式：全拼(简拼) 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = tenantService.update(req);

          log.debug("更新租户-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteTenantTest() {

        TenantIdReq req = new TenantIdReq();

        req.setId(id);

        int n = tenantService.delete(req);

        log.debug("删除租户->" + n);

        Assert.assertTrue(n > 0);
    }
}
