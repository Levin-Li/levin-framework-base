package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.ScheduledLog;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.scheduledlog.*;
import com.levin.oak.base.services.scheduledlog.req.*;
import com.levin.oak.base.services.scheduledlog.info.*;

////////////////////////////////////
// 自动导入列表
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
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月7日 下午1:39:21, 代码生成哈希校验码：[12bc908985e7c79aff6beda38e53c4d6]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class ScheduledLogServiceTest {

    @Autowired private ScheduledLogService scheduledLogService;

    @Autowired private BizScheduledLogService bizScheduledLogService;

    private String id;

    @BeforeAll
    public static void beforeAll() throws Exception {}

    @AfterAll
    public static void afterAll() throws Exception {}

    @BeforeEach
    public void beforeEach() throws Exception {}

    @AfterEach
    public void afterEach() throws Exception {}

    @Test
    public void createScheduledLogTest() {

        CreateScheduledLogReq req = new CreateScheduledLogReq();

        // req.setTaskId("这是文本64");// 必填

        // req.setInvokeCycle("这是文本128");//

        // req.setInvokeSnapshot("指向内容快照_1");//包括调度表达式，执行脚本，执行参数等

        // req.setIsError(true);//

        // req.setInvokeResult("执行结果_1");//

        // req.setTenantId("这是文本128");//

        // req.setOrgId("这是文本128");//

        String id = scheduledLogService.create(req);

        log.debug("新增调度日志->" + id);

        Assert.isTrue(id != null, "调度日志");
    }

    @Test
    public void queryScheduledLogTest() {

        QueryScheduledLogReq req = new QueryScheduledLogReq();

        // req.setId(null);//
        // req.setTaskId("这是文本64");//
        // req.setInvokeCycle("这是文本128");//
        // req.setInvokeSnapshot("指向内容快照_1");//包括调度表达式，执行脚本，执行参数等
        // req.setIsError(true);//
        // req.setInvokeResult("执行结果_1");//
        // req.setTenantId("这是文本128");//
        // req.setOrgId("这是文本128");//

        PagingData<ScheduledLogInfo> resp = scheduledLogService.query(req, null);

        log.debug("查询调度日志->" + resp);

        Assert.isTrue(!resp.isEmpty(), "调度日志");
    }

    @Test
    public void updateScheduledLogTest() {

        UpdateScheduledLogReq req = new UpdateScheduledLogReq();

        req.setId(id);

        // req.setTaskId("这是文本64");// 必填
        // req.setInvokeCycle("这是文本128");//
        // req.setInvokeSnapshot("指向内容快照_1");//包括调度表达式，执行脚本，执行参数等
        // req.setIsError(true);//
        // req.setInvokeResult("执行结果_1");//
        // req.setTenantId("这是文本128");//
        // req.setOrgId("这是文本128");//

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

        Assert.isTrue(ok, "调度日志");
    }
}
