package com.levin.oak.base;

import com.levin.oak.base.services.dynamicverifycodecfg.*;
import com.levin.oak.base.services.dynamicverifycodecfg.req.*;
import com.levin.oak.base.services.dynamicverifycodecfg.info.*;


////////////////////////////////////
//自动导入列表
////////////////////////////////////

import com.levin.commons.dao.*;

import org.springframework.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 动态验证码配置测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月9日 下午10:46:14, 代码生成哈希校验码：[326d4e3a6dea7ab96e1082309932986b]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class DynamicVerifyCodeCfgServiceTest {

    @Autowired
    private DynamicVerifyCodeCfgService dynamicVerifyCodeCfgService;

    @Autowired
    private BizDynamicVerifyCodeCfgService bizDynamicVerifyCodeCfgService;

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
    public void createDynamicVerifyCodeCfgTest() {

        CreateDynamicVerifyCodeCfgReq req = new CreateDynamicVerifyCodeCfgReq();

            // req.setTitle("这是文本255");// 必填

            // req.setModuleId("这是文本64");// 

            // req.setModuleName("这是文本64");// 

            // req.setDomainList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 

            // req.setRegionList("这是文本1800");//逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符 

            // req.setIpList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 

            // req.setIpExcludeList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 

            // req.setVerifyCodeType(VerifyCodeType.captcha);// 必填

            // req.setVerifyCodeParamName("这是文本16");//默认参数名：verifyCode 

            // req.setTenantId("这是文本128");// 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


       String id  = dynamicVerifyCodeCfgService.create(req);

        log.debug("新增动态验证码配置->" + id);

        Assert.isTrue(id != null, "动态验证码配置");

    }


    @Test
    public void queryDynamicVerifyCodeCfgTest() {

        QueryDynamicVerifyCodeCfgReq req = new QueryDynamicVerifyCodeCfgReq();

        // req.setId(null);//格式：tenantId+@+URL路径,如：tid@/base/sendSms，路径去除域名后的路径(不含参数)，规范的URL路径，中间不能有2个斜杠等
        // req.setTitle("这是文本255");//
        // req.setModuleId("这是文本64");//
        // req.setModuleName("这是文本64");//
        // req.setDomainList("这是文本1800");//逗号或是回车隔开，可以支持*通配符
        // req.setRegionList("这是文本1800");//逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符
        // req.setIpList("这是文本1800");//逗号或是回车隔开，可以支持*通配符
        // req.setIpExcludeList("这是文本1800");//逗号或是回车隔开，可以支持*通配符
        // req.setVerifyCodeType(VerifyCodeType.captcha);//
        // req.setVerifyCodeParamName("这是文本16");//默认参数名：verifyCode
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//
        // req.setOptimisticLock(1);//

        PagingData<DynamicVerifyCodeCfgInfo> resp = dynamicVerifyCodeCfgService.query(req,null);

        log.debug("查询动态验证码配置->" + resp);

        Assert.isTrue(!resp.isEmpty(), "动态验证码配置");
    }

    @Test
    public void updateDynamicVerifyCodeCfgTest() {

         UpdateDynamicVerifyCodeCfgReq req = new UpdateDynamicVerifyCodeCfgReq();

         req.setId(id);


           // req.setTitle("这是文本255");// 必填
           // req.setModuleId("这是文本64");// 
           // req.setModuleName("这是文本64");// 
           // req.setDomainList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 
           // req.setRegionList("这是文本1800");//逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符 
           // req.setIpList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 
           // req.setIpExcludeList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 
           // req.setVerifyCodeType(VerifyCodeType.captcha);// 必填
           // req.setVerifyCodeParamName("这是文本16");//默认参数名：verifyCode 
           // req.setTenantId("这是文本128");// 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

          boolean ok = dynamicVerifyCodeCfgService.update(req);

          log.debug("更新动态验证码配置-> " + ok);

          Assert.isTrue(ok, "动态验证码配置");
    }

    @Test
    public void deleteDynamicVerifyCodeCfgTest() {

        DynamicVerifyCodeCfgIdReq req = new DynamicVerifyCodeCfgIdReq();

        req.setId(id);

        boolean ok = dynamicVerifyCodeCfgService.delete(req);

        log.debug("删除动态验证码配置->" + ok);

        Assert.isTrue(ok , "动态验证码配置");
    }
}
