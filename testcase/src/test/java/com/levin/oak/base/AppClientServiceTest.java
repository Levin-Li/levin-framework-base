package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.appclient.AppClientService;
import com.levin.oak.base.services.appclient.info.AppClientInfo;
import com.levin.oak.base.services.appclient.req.AppClientIdReq;
import com.levin.oak.base.services.appclient.req.CreateAppClientReq;
import com.levin.oak.base.services.appclient.req.QueryAppClientReq;
import com.levin.oak.base.services.appclient.req.UpdateAppClientReq;
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
 *  应用接入测试
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
public class AppClientServiceTest {


    @Resource
    private AppClientService appClientService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createAppClientTest() {

        CreateAppClientReq req = new CreateAppClientReq();

            // req.setAppId("这是文本128");//应用ID 必填

            // req.setAppSecret("这是文本128");//应用密钥 必填

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = appClientService.create(req);

        log.debug("新增应用接入->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryAppClientTest() {

        QueryAppClientReq req = new QueryAppClientReq();

        // req.setId(null);//id
        // req.setAppId(null);//应用ID
        // req.setAppSecret("这是文本128");//应用密钥
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<AppClientInfo> resp = appClientService.query(req,null);

        log.debug("查询应用接入->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateAppClientTest() {

         UpdateAppClientReq req = new UpdateAppClientReq();

         req.setId(id);


           // req.setAppId("这是文本128");//应用ID 必填
           // req.setAppSecret("这是文本128");//应用密钥 必填
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = appClientService.update(req);

          log.debug("更新应用接入-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteAppClientTest() {

        AppClientIdReq req = new AppClientIdReq();

        req.setId(id);

        int n = appClientService.delete(req);

        log.debug("删除应用接入->" + n);

        Assert.assertTrue(n > 0);
    }
}
