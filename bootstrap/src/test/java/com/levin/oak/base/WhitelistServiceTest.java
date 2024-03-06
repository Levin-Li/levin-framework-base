package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Whitelist;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.whitelist.*;
import com.levin.oak.base.services.whitelist.req.*;
import com.levin.oak.base.services.whitelist.info.*;


////////////////////////////////////
//自动导入列表
import java.io.Serializable;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
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
 * 白名单测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月6日 下午2:17:35, 代码生成哈希校验码：[28cec74e3a2b8048109fdb71e2193b3c]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class WhitelistServiceTest {

    @Autowired
    private WhitelistService whitelistService;

    @Autowired
    private BizWhitelistService bizWhitelistService;

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
    public void createWhitelistTest() {

        CreateWhitelistReq req = new CreateWhitelistReq();

            // req.setModuleId("这是文本255");// 必填

            // req.setModuleName("这是文本64");// 

            // req.setTitle("这是文本255");// 必填

            // req.setDomainList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 

            // req.setRegionList("这是文本1800");//逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符 

            // req.setIpList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 

            // req.setOsList("操作系统列表_1");//逗号或是回车隔开,可以支持*通配符 

            // req.setBrowserList("浏览器列表_1");//逗号或是回车隔开,可以支持*通配符 

            // req.setMethodList("这是文本64");//逗号或是回车隔开，可以支持*通配符 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


       String id  = whitelistService.create(req);

        log.debug("新增白名单->" + id);

        Assert.isTrue(id != null, "白名单");

    }


    @Test
    public void queryWhitelistTest() {

        QueryWhitelistReq req = new QueryWhitelistReq();

        // req.setId(null);//去除域名后的路径(不含参数)，规范的URL路径，中间不能有2个斜杠等
        // req.setModuleId("这是文本255");//
        // req.setModuleName("这是文本64");//
        // req.setTitle("这是文本255");//
        // req.setDomainList("这是文本1800");//逗号或是回车隔开，可以支持*通配符
        // req.setRegionList("这是文本1800");//逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符
        // req.setIpList("这是文本1800");//逗号或是回车隔开，可以支持*通配符
        // req.setOsList("操作系统列表_1");//逗号或是回车隔开,可以支持*通配符
        // req.setBrowserList("浏览器列表_1");//逗号或是回车隔开,可以支持*通配符
        // req.setMethodList("这是文本64");//逗号或是回车隔开，可以支持*通配符
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//
        // req.setOptimisticLock(1);//

        PagingData<WhitelistInfo> resp = whitelistService.query(req,null);

        log.debug("查询白名单->" + resp);

        Assert.isTrue(!resp.isEmpty(), "白名单");
    }

    @Test
    public void updateWhitelistTest() {

         UpdateWhitelistReq req = new UpdateWhitelistReq();

         req.setId(id);


           // req.setModuleId("这是文本255");// 必填
           // req.setModuleName("这是文本64");// 
           // req.setTitle("这是文本255");// 必填
           // req.setDomainList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 
           // req.setRegionList("这是文本1800");//逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符 
           // req.setIpList("这是文本1800");//逗号或是回车隔开，可以支持*通配符 
           // req.setOsList("操作系统列表_1");//逗号或是回车隔开,可以支持*通配符 
           // req.setBrowserList("浏览器列表_1");//逗号或是回车隔开,可以支持*通配符 
           // req.setMethodList("这是文本64");//逗号或是回车隔开，可以支持*通配符 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

          boolean ok = whitelistService.update(req);

          log.debug("更新白名单-> " + ok);

          Assert.isTrue(ok, "白名单");
    }

    @Test
    public void deleteWhitelistTest() {

        WhitelistIdReq req = new WhitelistIdReq();

        req.setId(id);

        boolean ok = whitelistService.delete(req);

        log.debug("删除白名单->" + ok);

        Assert.isTrue(ok , "白名单");
    }
}
