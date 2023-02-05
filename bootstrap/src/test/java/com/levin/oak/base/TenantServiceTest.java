package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Tenant;

import com.levin.oak.base.services.tenant.*;
import com.levin.oak.base.services.tenant.req.*;
import com.levin.oak.base.services.tenant.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
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
 *  租户测试
 *
 *  @author auto gen by simple-dao-codegen 2023-2-5 11:13:19
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class TenantServiceTest {

    @Resource
    private TenantService tenantService;

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
    public void createTenantTest() {

        CreateTenantReq req = new CreateTenantReq();

            // req.setSysName("这是文本128");//系统名称 

            // req.setSysLogo("系统Logo_1");//系统Logo 

            // req.setLogo("租户Logo_1");//租户Logo 

            // req.setCode("这是文本128");//企业信用编码 

            // req.setTenantKey("租户编码_1");//租户编码 必填

            // req.setBalance(0.1d);//帐号余额 

            // req.setLicenseCnt(1);//总许可数 

            // req.setRemainingLicenseCnt(1);//剩余许可数 

            // req.setLicenseExpire(new Date());//到期时间 

            // req.setContractPerson("这是文本32");//联系人 

            // req.setContractPhone("这是文本32");//联系电话 

            // req.setDomainList("这是文本1200");//域名列表 

            // req.setAppId("这是文本64");//appId 

            // req.setAppSecret("这是文本512");//appSecret 

            // req.setEncryptKey("这是文本512");//encryptKey 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = tenantService.create(req);

        log.debug("新增租户->" + id);

        Assert.isTrue(id != null, "租户");

    }


    @Test
    public void queryTenantTest() {

        QueryTenantReq req = new QueryTenantReq();

        // req.setId(null);//ID
        // req.setSysName("这是文本128");//系统名称
        // req.setSysLogo("系统Logo_1");//系统Logo
        // req.setLogo("租户Logo_1");//租户Logo
        // req.setCode("这是文本128");//企业信用编码
        // req.setTenantKey("租户编码_1");//租户编码
        // req.setBalance(0.1d);//帐号余额
        // req.setLicenseCnt(1);//总许可数
        // req.setRemainingLicenseCnt(1);//剩余许可数
        // req.setGteLicenseExpire(DateUtils.getZoneHour(new Date()));//最小到期时间
        // req.setLteLicenseExpire(DateUtils.getEndHour(new Date()));//最大到期时间
        // req.setContractPerson("这是文本32");//联系人
        // req.setContractPhone("这是文本32");//联系电话
        // req.setDomainList("这是文本1200");//域名列表
        // req.setAppId("这是文本64");//appId
        // req.setAppSecret("这是文本512");//appSecret
        // req.setEncryptKey("这是文本512");//encryptKey
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<TenantInfo> resp = tenantService.query(req,null);

        log.debug("查询租户->" + resp);

        Assert.isTrue(!resp.isEmpty(), "租户");
    }

    @Test
    public void updateTenantTest() {

         UpdateTenantReq req = new UpdateTenantReq();

         req.setId(id);


           // req.setSysName("这是文本128");//系统名称 
           // req.setSysLogo("系统Logo_1");//系统Logo 
           // req.setLogo("租户Logo_1");//租户Logo 
           // req.setCode("这是文本128");//企业信用编码 
           // req.setTenantKey("租户编码_1");//租户编码 必填
           // req.setBalance(0.1d);//帐号余额 
           // req.setLicenseCnt(1);//总许可数 
           // req.setRemainingLicenseCnt(1);//剩余许可数 
           // req.setLicenseExpire(new Date());//到期时间 
           // req.setContractPerson("这是文本32");//联系人 
           // req.setContractPhone("这是文本32");//联系电话 
           // req.setDomainList("这是文本1200");//域名列表 
           // req.setAppId("这是文本64");//appId 
           // req.setAppSecret("这是文本512");//appSecret 
           // req.setEncryptKey("这是文本512");//encryptKey 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = tenantService.update(req);

          log.debug("更新租户-> " + resp);

          Assert.isTrue(resp > 0, "租户");
    }

    @Test
    public void deleteTenantTest() {

        TenantIdReq req = new TenantIdReq();

        req.setId(id);

        int n = tenantService.delete(req);

        log.debug("删除租户->" + n);

        Assert.isTrue(n > 0, "租户");
    }
}
