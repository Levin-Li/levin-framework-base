package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AccessLog;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.accesslog.*;
import com.levin.oak.base.services.accesslog.req.*;
import com.levin.oak.base.services.accesslog.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * 访问日志测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:46, 代码生成哈希校验码：[66d2b7c81a2d519d94022de384274c09]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class AccessLogServiceTest {

    @Autowired
    private AccessLogService accessLogService;

    @Autowired
    private BizAccessLogService bizAccessLogService;

    private Long id;

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
    public void createAccessLogTest() {

        CreateAccessLogReq req = new CreateAccessLogReq();

            // req.setDomain("系统域_1");//归属的子系统 

            // req.setModule("模块_1");//归属的应用或模块 

            // req.setVisitor("这是文本64");// 

            // req.setTitle("标题_1");// 必填

            // req.setLogType("这是文本64");// 

            // req.setDiffModifyData("差异修改数据_1");// 

            // req.setBizKey("业务主键_1");// 

            // req.setBizType("业务类型_1");// 

            // req.setRequestUri("这是文本768");// 

            // req.setRequestMethod("这是文本32");// 

            // req.setHeadInfo("头部信息_1");// 

            // req.setRequestParams("请求参数_1");// 

            // req.setRequestBody("请求体_1");// 

            // req.setResponseBody("响应体_1");// 

            // req.setRemoteAddr("这是文本128");// 

            // req.setAccessRegion("这是文本256");// 

            // req.setServerAddr("这是文本64");// 

            // req.setIsException(true);// 

            // req.setExceptionInfo("异常信息_1");// 

            // req.setUserAgent("这是文本1800");// 

            // req.setDeviceName("这是文本128");// 

            // req.setBrowserName("这是文本128");// 

            // req.setExecuteTime(1L);// 

            // req.setTenantId("这是文本128");// 

            // req.setOrgId("这是文本128");// 


       Long id  = accessLogService.create(req);

        log.debug("新增访问日志->" + id);

        Assert.isTrue(id != null, "访问日志");

    }


    @Test
    public void queryAccessLogTest() {

        QueryAccessLogReq req = new QueryAccessLogReq();

        // req.setId(null);//
        // req.setDomain("系统域_1");//归属的子系统
        // req.setModule("模块_1");//归属的应用或模块
        // req.setVisitor("这是文本64");//
        // req.setTitle("标题_1");//
        // req.setLogType("这是文本64");//
        // req.setDiffModifyData("差异修改数据_1");//
        // req.setBizKey("业务主键_1");//
        // req.setBizType("业务类型_1");//
        // req.setRequestUri("这是文本768");//
        // req.setRequestMethod("这是文本32");//
        // req.setHeadInfo("头部信息_1");//
        // req.setRequestParams("请求参数_1");//
        // req.setRequestBody("请求体_1");//
        // req.setResponseBody("响应体_1");//
        // req.setRemoteAddr("这是文本128");//
        // req.setAccessRegion("这是文本256");//
        // req.setServerAddr("这是文本64");//
        // req.setIsException(true);//
        // req.setExceptionInfo("异常信息_1");//
        // req.setUserAgent("这是文本1800");//
        // req.setDeviceName("这是文本128");//
        // req.setBrowserName("这是文本128");//
        // req.setExecuteTime(1L);//
        // req.setTenantId("这是文本128");//
        // req.setOrgId("这是文本128");//

        PagingData<AccessLogInfo> resp = accessLogService.query(req,null);

        log.debug("查询访问日志->" + resp);

        Assert.isTrue(!resp.isEmpty(), "访问日志");
    }

    @Test
    public void updateAccessLogTest() {

         UpdateAccessLogReq req = new UpdateAccessLogReq();

         req.setId(id);


           // req.setDomain("系统域_1");//归属的子系统 
           // req.setModule("模块_1");//归属的应用或模块 
           // req.setVisitor("这是文本64");// 
           // req.setTitle("标题_1");// 必填
           // req.setLogType("这是文本64");// 
           // req.setDiffModifyData("差异修改数据_1");// 
           // req.setBizKey("业务主键_1");// 
           // req.setBizType("业务类型_1");// 
           // req.setRequestUri("这是文本768");// 
           // req.setRequestMethod("这是文本32");// 
           // req.setHeadInfo("头部信息_1");// 
           // req.setRequestParams("请求参数_1");// 
           // req.setRequestBody("请求体_1");// 
           // req.setResponseBody("响应体_1");// 
           // req.setRemoteAddr("这是文本128");// 
           // req.setAccessRegion("这是文本256");// 
           // req.setServerAddr("这是文本64");// 
           // req.setIsException(true);// 
           // req.setExceptionInfo("异常信息_1");// 
           // req.setUserAgent("这是文本1800");// 
           // req.setDeviceName("这是文本128");// 
           // req.setBrowserName("这是文本128");// 
           // req.setExecuteTime(1L);// 
           // req.setTenantId("这是文本128");// 
           // req.setOrgId("这是文本128");// 

          boolean ok = accessLogService.update(req);

          log.debug("更新访问日志-> " + ok);

          Assert.isTrue(ok, "访问日志");
    }

    @Test
    public void deleteAccessLogTest() {

        AccessLogIdReq req = new AccessLogIdReq();

        req.setId(id);

        boolean ok = accessLogService.delete(req);

        log.debug("删除访问日志->" + ok);

        Assert.isTrue(ok , "访问日志");
    }
}
