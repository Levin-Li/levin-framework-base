package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.apperrorlog.AppErrorLogService;
import com.levin.oak.base.services.apperrorlog.info.AppErrorLogInfo;
import com.levin.oak.base.services.apperrorlog.req.AppErrorLogIdReq;
import com.levin.oak.base.services.apperrorlog.req.CreateAppErrorLogReq;
import com.levin.oak.base.services.apperrorlog.req.QueryAppErrorLogReq;
import com.levin.oak.base.services.apperrorlog.req.UpdateAppErrorLogReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

////////////////////////////////////
//自动导入列表
////////////////////////////////////
//import org.junit.jupiter.api.Test;
import javax.annotation.Resource;

/**
 *  应用错误日志测试
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
public class AppErrorLogServiceTest {


    @Resource
    private AppErrorLogService appErrorLogService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
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

        Assert.assertTrue(id != null);

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

        Assert.assertTrue(!resp.isEmpty());
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

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteAppErrorLogTest() {

        AppErrorLogIdReq req = new AppErrorLogIdReq();

        req.setId(id);

        int n = appErrorLogService.delete(req);

        log.debug("删除应用错误日志->" + n);

        Assert.assertTrue(n > 0);
    }
}
