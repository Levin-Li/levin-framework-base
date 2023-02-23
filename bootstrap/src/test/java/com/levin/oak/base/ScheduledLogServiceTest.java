package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.ScheduledLog;

import com.levin.oak.base.services.scheduledlog.*;
import com.levin.oak.base.services.scheduledlog.req.*;
import com.levin.oak.base.services.scheduledlog.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
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
 *  调度日志测试
 *
 *  @author auto gen by simple-dao-codegen 2023-2-22 18:58:29
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class ScheduledLogServiceTest {

    @Autowired
    private ScheduledLogService scheduledLogService;

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
    public void createScheduledLogTest() {

        CreateScheduledLogReq req = new CreateScheduledLogReq();

            // req.setTenantId("这是文本64");//租户ID 

            // req.setOrgId("这是文本64");//归属组织 必填

            // req.setTaskId("这是文本64");//任务ID 必填

            // req.setInvokeCycle("这是文本128");//执行周期 

            // req.setIsError(true);//是否错误 

            // req.setInvokeResult("执行结果_1");//执行结果 


       Long id  = scheduledLogService.create(req);

        log.debug("新增调度日志->" + id);

        Assert.isTrue(id != null, "调度日志");

    }


    @Test
    public void queryScheduledLogTest() {

        QueryScheduledLogReq req = new QueryScheduledLogReq();

        // req.setId(null);//id
        // req.setTenantId("这是文本64");//租户ID
        // req.setOrgId("这是文本64");//归属组织
        // req.setTaskId("这是文本64");//任务ID
        // req.setInvokeCycle("这是文本128");//执行周期
        // req.setIsError(true);//是否错误
        // req.setInvokeResult("执行结果_1");//执行结果

        PagingData<ScheduledLogInfo> resp = scheduledLogService.query(req,null);

        log.debug("查询调度日志->" + resp);

        Assert.isTrue(!resp.isEmpty(), "调度日志");
    }

    @Test
    public void updateScheduledLogTest() {

         UpdateScheduledLogReq req = new UpdateScheduledLogReq();

         req.setId(id);


           // req.setTenantId("这是文本64");//租户ID 
           // req.setOrgId("这是文本64");//归属组织 必填
           // req.setTaskId("这是文本64");//任务ID 必填
           // req.setInvokeCycle("这是文本128");//执行周期 
           // req.setIsError(true);//是否错误 
           // req.setInvokeResult("执行结果_1");//执行结果 

          int resp = scheduledLogService.update(req);

          log.debug("更新调度日志-> " + resp);

          Assert.isTrue(resp > 0, "调度日志");
    }

    @Test
    public void deleteScheduledLogTest() {

        ScheduledLogIdReq req = new ScheduledLogIdReq();

        req.setId(id);

        int n = scheduledLogService.delete(req);

        log.debug("删除调度日志->" + n);

        Assert.isTrue(n > 0, "调度日志");
    }
}
