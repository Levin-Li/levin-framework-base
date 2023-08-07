package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.NoticeProcessLog;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.noticeprocesslog.*;
import com.levin.oak.base.services.noticeprocesslog.req.*;
import com.levin.oak.base.services.noticeprocesslog.info.*;

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
 * 通知处理日志测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月7日 下午1:39:21, 代码生成哈希校验码：[7ac13d309c3d79a9684bbef4b6026bd0]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class NoticeProcessLogServiceTest {

    @Autowired private NoticeProcessLogService noticeProcessLogService;

    @Autowired private BizNoticeProcessLogService bizNoticeProcessLogService;

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
    public void createNoticeProcessLogTest() {

        CreateNoticeProcessLogReq req = new CreateNoticeProcessLogReq();

        // req.setOwnerId("这是文本128");// 必填

        // req.setNoticeId("这是文本128");// 必填

        // req.setStatus("这是文本128");//

        // req.setRemark("这是文本512");//

        // req.setTenantId("这是文本128");//

        // req.setOrgId("这是文本128");//

        String id = noticeProcessLogService.create(req);

        log.debug("新增通知处理日志->" + id);

        Assert.isTrue(id != null, "通知处理日志");
    }

    @Test
    public void queryNoticeProcessLogTest() {

        QueryNoticeProcessLogReq req = new QueryNoticeProcessLogReq();

        // req.setId(null);//
        // req.setOwnerId("这是文本128");//
        // req.setNoticeId("这是文本128");//
        // req.setStatus("这是文本128");//
        // req.setRemark("这是文本512");//
        // req.setTenantId("这是文本128");//
        // req.setOrgId("这是文本128");//

        PagingData<NoticeProcessLogInfo> resp = noticeProcessLogService.query(req, null);

        log.debug("查询通知处理日志->" + resp);

        Assert.isTrue(!resp.isEmpty(), "通知处理日志");
    }

    @Test
    public void updateNoticeProcessLogTest() {

        UpdateNoticeProcessLogReq req = new UpdateNoticeProcessLogReq();

        req.setId(id);

        // req.setOwnerId("这是文本128");// 必填
        // req.setNoticeId("这是文本128");// 必填
        // req.setStatus("这是文本128");//
        // req.setRemark("这是文本512");//
        // req.setTenantId("这是文本128");//
        // req.setOrgId("这是文本128");//

        boolean ok = noticeProcessLogService.update(req);

        log.debug("更新通知处理日志-> " + ok);

        Assert.isTrue(ok, "通知处理日志");
    }

    @Test
    public void deleteNoticeProcessLogTest() {

        NoticeProcessLogIdReq req = new NoticeProcessLogIdReq();

        req.setId(id);

        boolean ok = noticeProcessLogService.delete(req);

        log.debug("删除通知处理日志->" + ok);

        Assert.isTrue(ok, "通知处理日志");
    }
}
