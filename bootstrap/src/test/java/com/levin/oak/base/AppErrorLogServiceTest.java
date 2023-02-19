package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AppErrorLog;

import com.levin.oak.base.services.apperrorlog.*;
import com.levin.oak.base.services.apperrorlog.req.*;
import com.levin.oak.base.services.apperrorlog.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
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
 *  应用错误日志测试
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
public class AppErrorLogServiceTest {

    @Resource
    private AppErrorLogService appErrorLogService;

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
    public void createAppErrorLogTest() {

        CreateAppErrorLogReq req = new CreateAppErrorLogReq();

            // req.setTenantId("租户ID_1");//租户ID 

            // req.setModuleId("这是文本64");//模块ID 

            // req.setOccurTime(new Date());//发生时间 必填

            // req.setTitle("这是文本768");//标题 必填

            // req.setErrorLevel("错误级别_1");//错误级别 

            // req.setRootExceptionType("根异常类型_1");//根异常类型 

            // req.setExceptionFullInfo("完整异常堆栈_1");//完整异常堆栈 


       Long id  = appErrorLogService.create(req);

        log.debug("新增应用错误日志->" + id);

        Assert.isTrue(id != null, "应用错误日志");

    }


    @Test
    public void queryAppErrorLogTest() {

        QueryAppErrorLogReq req = new QueryAppErrorLogReq();

        // req.setId(null);//id
        // req.setTenantId("租户ID_1");//租户ID
        // req.setModuleId("这是文本64");//模块ID
        // req.setGteOccurTime(DateUtils.getZoneHour(new Date()));//最小发生时间
        // req.setLteOccurTime(DateUtils.getEndHour(new Date()));//最大发生时间
        // req.setTitle("这是文本768");//标题
        // req.setErrorLevel("错误级别_1");//错误级别
        // req.setRootExceptionType("根异常类型_1");//根异常类型
        // req.setExceptionFullInfo("完整异常堆栈_1");//完整异常堆栈

        PagingData<AppErrorLogInfo> resp = appErrorLogService.query(req,null);

        log.debug("查询应用错误日志->" + resp);

        Assert.isTrue(!resp.isEmpty(), "应用错误日志");
    }

    @Test
    public void updateAppErrorLogTest() {

         UpdateAppErrorLogReq req = new UpdateAppErrorLogReq();

         req.setId(id);


           // req.setTenantId("租户ID_1");//租户ID 
           // req.setModuleId("这是文本64");//模块ID 
           // req.setOccurTime(new Date());//发生时间 必填
           // req.setTitle("这是文本768");//标题 必填
           // req.setErrorLevel("错误级别_1");//错误级别 
           // req.setRootExceptionType("根异常类型_1");//根异常类型 
           // req.setExceptionFullInfo("完整异常堆栈_1");//完整异常堆栈 

          int resp = appErrorLogService.update(req);

          log.debug("更新应用错误日志-> " + resp);

          Assert.isTrue(resp > 0, "应用错误日志");
    }

    @Test
    public void deleteAppErrorLogTest() {

        AppErrorLogIdReq req = new AppErrorLogIdReq();

        req.setId(id);

        int n = appErrorLogService.delete(req);

        log.debug("删除应用错误日志->" + n);

        Assert.isTrue(n > 0, "应用错误日志");
    }
}