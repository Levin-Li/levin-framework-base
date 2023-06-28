package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.ScheduledLog;

import com.levin.oak.base.biz.*;
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
 * 调度日志测试
 *
 * Auto gen by simple-dao-codegen 2023年6月28日 上午11:30:58
 * 代码生成哈希校验码：[4cf09426604ddc2422dd7a1016b12316]
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

    @Autowired
    private BizScheduledLogService bizscheduledLogService;

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

            // req.setTenantId("这是文本64");// 

            // req.setOrgId("这是文本64");// 必填

            // req.setTaskId("这是文本64");// 必填

            // req.setInvokeCycle("这是文本128");// 

            // req.setIsError(true);// 

            // req.setInvokeResult("执行结果_1");// 


       Long id  = scheduledLogService.create(req);

        log.debug("新增调度日志->" + id);

        Assert.isTrue(id != null, "调度日志");

    }


    @Test
    public void queryScheduledLogTest() {

        QueryScheduledLogReq req = new QueryScheduledLogReq();

        // req.setId(null);//
        // req.setTenantId("这是文本64");//
        // req.setOrgId("这是文本64");//
        // req.setTaskId("这是文本64");//
        // req.setInvokeCycle("这是文本128");//
        // req.setIsError(true);//
        // req.setInvokeResult("执行结果_1");//

        PagingData<ScheduledLogInfo> resp = scheduledLogService.query(req,null);

        log.debug("查询调度日志->" + resp);

        Assert.isTrue(!resp.isEmpty(), "调度日志");
    }

    @Test
    public void updateScheduledLogTest() {

         UpdateScheduledLogReq req = new UpdateScheduledLogReq();

         req.setId(id);


           // req.setTenantId("这是文本64");// 
           // req.setOrgId("这是文本64");// 必填
           // req.setTaskId("这是文本64");// 必填
           // req.setInvokeCycle("这是文本128");// 
           // req.setIsError(true);// 
           // req.setInvokeResult("执行结果_1");// 

          boolean ok = scheduledLogService.update(req);

          log.debug("更新调度日志-> " + ok);

          Assert.isTrue(ok, "调度日志");
    }

    @Test
    public void deleteScheduledLogTest() {

        ScheduledLogIdReq req = new ScheduledLogIdReq();

        req.setId(id);

        boolean ok = scheduledLogService.delete(req);

        log.debug("删除调度日志->" + ok);

        Assert.isTrue(ok , "调度日志");
    }
}
