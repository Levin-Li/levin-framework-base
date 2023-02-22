package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.ScheduledTask;

import com.levin.oak.base.services.scheduledtask.*;
import com.levin.oak.base.services.scheduledtask.req.*;
import com.levin.oak.base.services.scheduledtask.info.*;


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
 *  调度任务测试
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
public class ScheduledTaskServiceTest {

    @Resource
    private ScheduledTaskService scheduledTaskService;

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
    public void createScheduledTaskTest() {

        CreateScheduledTaskReq req = new CreateScheduledTaskReq();

            // req.setCategory("这是文本128");//任务分类 必填

            // req.setGroupName("这是文本128");//任务组 必填

            // req.setCron("调度表达式_1");//调度表达式 必填

            // req.setInvokeExpr("执行表达式_1");//执行表达式 

            // req.setParallelInvoke(true);//允许并发执行 

            // req.setLastInvokedTime(new Date());//最后一次时间 

            // req.setNextInvokeTime(new Date());//下一次时间 

            // req.setOrgId("这是文本64");//机构ID 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = scheduledTaskService.create(req);

        log.debug("新增调度任务->" + id);

        Assert.isTrue(id != null, "调度任务");

    }


    @Test
    public void queryScheduledTaskTest() {

        QueryScheduledTaskReq req = new QueryScheduledTaskReq();

        // req.setId(null);//id
        // req.setCategory("这是文本128");//任务分类
        // req.setGroupName("这是文本128");//任务组
        // req.setCron("调度表达式_1");//调度表达式
        // req.setInvokeExpr("执行表达式_1");//执行表达式
        // req.setParallelInvoke(true);//允许并发执行
        // req.setGteLastInvokedTime(DateUtils.getZoneHour(new Date()));//最小最后一次时间
        // req.setLteLastInvokedTime(DateUtils.getEndHour(new Date()));//最大最后一次时间
        // req.setGteNextInvokeTime(DateUtils.getZoneHour(new Date()));//最小下一次时间
        // req.setLteNextInvokeTime(DateUtils.getEndHour(new Date()));//最大下一次时间
        // req.setOrgId("这是文本64");//机构ID
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<ScheduledTaskInfo> resp = scheduledTaskService.query(req,null);

        log.debug("查询调度任务->" + resp);

        Assert.isTrue(!resp.isEmpty(), "调度任务");
    }

    @Test
    public void updateScheduledTaskTest() {

         UpdateScheduledTaskReq req = new UpdateScheduledTaskReq();

         req.setId(id);


           // req.setCategory("这是文本128");//任务分类 必填
           // req.setGroupName("这是文本128");//任务组 必填
           // req.setCron("调度表达式_1");//调度表达式 必填
           // req.setInvokeExpr("执行表达式_1");//执行表达式 
           // req.setParallelInvoke(true);//允许并发执行 
           // req.setLastInvokedTime(new Date());//最后一次时间 
           // req.setNextInvokeTime(new Date());//下一次时间 
           // req.setOrgId("这是文本64");//机构ID 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = scheduledTaskService.update(req);

          log.debug("更新调度任务-> " + resp);

          Assert.isTrue(resp > 0, "调度任务");
    }

    @Test
    public void deleteScheduledTaskTest() {

        ScheduledTaskIdReq req = new ScheduledTaskIdReq();

        req.setId(id);

        int n = scheduledTaskService.delete(req);

        log.debug("删除调度任务->" + n);

        Assert.isTrue(n > 0, "调度任务");
    }
}
