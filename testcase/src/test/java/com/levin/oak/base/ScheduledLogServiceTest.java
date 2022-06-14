package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.scheduledlog.ScheduledLogService;
import com.levin.oak.base.services.scheduledlog.info.ScheduledLogInfo;
import com.levin.oak.base.services.scheduledlog.req.CreateScheduledLogReq;
import com.levin.oak.base.services.scheduledlog.req.QueryScheduledLogReq;
import com.levin.oak.base.services.scheduledlog.req.ScheduledLogIdReq;
import com.levin.oak.base.services.scheduledlog.req.UpdateScheduledLogReq;
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
 *  调度日志测试
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
public class ScheduledLogServiceTest {


    @Resource
    private ScheduledLogService scheduledLogService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
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

        Assert.assertTrue(id != null);

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

        Assert.assertTrue(!resp.isEmpty());
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

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteScheduledLogTest() {

        ScheduledLogIdReq req = new ScheduledLogIdReq();

        req.setId(id);

        int n = scheduledLogService.delete(req);

        log.debug("删除调度日志->" + n);

        Assert.assertTrue(n > 0);
    }
}
