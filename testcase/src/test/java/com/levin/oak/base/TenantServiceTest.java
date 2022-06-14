package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.CreateTenantReq;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import com.levin.oak.base.services.tenant.req.TenantIdReq;
import com.levin.oak.base.services.tenant.req.UpdateTenantReq;
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
 *  租户测试
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
public class TenantServiceTest {


    @Resource
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

            // req.setDomainList("域名列表_1");//域名列表 

            // req.setAppId("这是文本32");//appId 

            // req.setAppSecret("这是文本128");//appSecret 

            // req.setEncryptKey("这是文本128");//encryptKey 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

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
        // req.setDomainList("域名列表_1");//域名列表
        // req.setAppId("这是文本32");//appId
        // req.setAppSecret("这是文本128");//appSecret
        // req.setEncryptKey("这是文本128");//encryptKey
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
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
           // req.setDomainList("域名列表_1");//域名列表 
           // req.setAppId("这是文本32");//appId 
           // req.setAppSecret("这是文本128");//appSecret 
           // req.setEncryptKey("这是文本128");//encryptKey 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
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
