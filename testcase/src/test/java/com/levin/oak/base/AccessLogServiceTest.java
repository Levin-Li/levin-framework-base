package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AccessLog;

import com.levin.oak.base.services.accesslog.*;
import com.levin.oak.base.services.accesslog.req.*;
import com.levin.oak.base.services.accesslog.info.*;


////////////////////////////////////
//自动导入列表
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
 *  访问日志测试
 *
 *  @author auto gen by simple-dao-codegen 2021-11-12 9:56:47
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

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createAccessLogTest() {

        CreateAccessLogReq req = new CreateAccessLogReq();

            // req.setTenantId(null);//租户ID 

            // req.setDomain("请求的域名_1");//请求的域名 

            // req.setVisitor("访问者_1");//访问者 

            // req.setTitle("标题_1");//标题 必填

            // req.setLogType("日志类型_1");//日志类型 

            // req.setDiffModifyData("差异修改数据_1");//差异修改数据 

            // req.setBizKey("业务主键_1");//业务主键 

            // req.setBizType("业务类型_1");//业务类型 

            // req.setRequestUri("请求URI_1");//请求URI 

            // req.setRequestMethod("请求方法_1");//请求方法 

            // req.setRequestParams("请求参数_1");//请求参数 

            // req.setResponseData("响应数据_1");//响应数据 

            // req.setRemoteAddr("操作IP地址_1");//操作IP地址 

            // req.setServerAddr("服务器地址_1");//服务器地址 

            // req.setIsException("是否有异常_1");//是否有异常 

            // req.setExceptionInfo("异常信息_1");//异常信息 

            // req.setUserAgent("用户代理_1");//用户代理 

            // req.setDeviceName("设备名称/操作系统_1");//设备名称/操作系统 

            // req.setBrowserName("浏览器名称_1");//浏览器名称 

            // req.setExecuteTime(1L);//执行时间(ms) 


       Long id  = accessLogService.create(req);

        log.debug("新增访问日志->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryAccessLogTest() {

        QueryAccessLogReq req = new QueryAccessLogReq();

        // req.setId(null);//id
        // req.setTenantId(null);//租户ID
        // req.setDomain("请求的域名_1");//请求的域名
        // req.setVisitor("访问者_1");//访问者
        // req.setTitle("标题_1");//标题
        // req.setLogType("日志类型_1");//日志类型
        // req.setDiffModifyData("差异修改数据_1");//差异修改数据
        // req.setBizKey("业务主键_1");//业务主键
        // req.setBizType("业务类型_1");//业务类型
        // req.setRequestUri("请求URI_1");//请求URI
        // req.setRequestMethod("请求方法_1");//请求方法
        // req.setRequestParams("请求参数_1");//请求参数
        // req.setResponseData("响应数据_1");//响应数据
        // req.setRemoteAddr("操作IP地址_1");//操作IP地址
        // req.setServerAddr("服务器地址_1");//服务器地址
        // req.setIsException("是否有异常_1");//是否有异常
        // req.setExceptionInfo("异常信息_1");//异常信息
        // req.setUserAgent("用户代理_1");//用户代理
        // req.setDeviceName("设备名称/操作系统_1");//设备名称/操作系统
        // req.setBrowserName("浏览器名称_1");//浏览器名称
        // req.setExecuteTime(1L);//执行时间(ms)

        PagingData<AccessLogInfo> resp = accessLogService.query(req,null);

        log.debug("查询访问日志->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateAccessLogTest() {

         UpdateAccessLogReq req = new UpdateAccessLogReq();

         req.setId(id);


           // req.setTenantId(null);//租户ID 
           // req.setDomain("请求的域名_1");//请求的域名 
           // req.setVisitor("访问者_1");//访问者 
           // req.setTitle("标题_1");//标题 必填
           // req.setLogType("日志类型_1");//日志类型 
           // req.setDiffModifyData("差异修改数据_1");//差异修改数据 
           // req.setBizKey("业务主键_1");//业务主键 
           // req.setBizType("业务类型_1");//业务类型 
           // req.setRequestUri("请求URI_1");//请求URI 
           // req.setRequestMethod("请求方法_1");//请求方法 
           // req.setRequestParams("请求参数_1");//请求参数 
           // req.setResponseData("响应数据_1");//响应数据 
           // req.setRemoteAddr("操作IP地址_1");//操作IP地址 
           // req.setServerAddr("服务器地址_1");//服务器地址 
           // req.setIsException("是否有异常_1");//是否有异常 
           // req.setExceptionInfo("异常信息_1");//异常信息 
           // req.setUserAgent("用户代理_1");//用户代理 
           // req.setDeviceName("设备名称/操作系统_1");//设备名称/操作系统 
           // req.setBrowserName("浏览器名称_1");//浏览器名称 
           // req.setExecuteTime(1L);//执行时间(ms) 

          int resp = accessLogService.update(req);

          log.debug("更新访问日志-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteAccessLogTest() {

        DeleteAccessLogReq req = new DeleteAccessLogReq();

        req.setId(id);

        int n = accessLogService.delete(req);

        log.debug("删除访问日志->" + n);

        Assert.assertTrue(n > 0);
    }
}
