package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Notice;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.notice.*;
import com.levin.oak.base.services.notice.req.*;
import com.levin.oak.base.services.notice.info.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;
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
 * 通知测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月10日 上午2:41:25, 代码生成哈希校验码：[cd65898d745a347b7bda0d688df64d9b]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class NoticeServiceTest {

    @Autowired private NoticeService noticeService;

    @Autowired private BizNoticeService bizNoticeService;

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
    public void createNoticeTest() {

        CreateNoticeReq req = new CreateNoticeReq();

        // req.setOwnerId("这是文本128");//

        // req.setCategory("这是文本64");//

        // req.setContentType(ContentType.Text);//

        // req.setContent("通知内容_1");//

        // req.setExpiredDate(new Date());//

        // req.setDomain("这是文本128");//

        // req.setName("这是文本64");// 必填

        // req.setOrgId("这是文本128");//

        // req.setTenantId("这是文本128");//

        // req.setOrderCode(1);//

        // req.setEnable(true);// 必填

        // req.setEditable(true);// 必填

        // req.setRemark("这是文本512");//

        String id = noticeService.create(req);

        log.debug("新增通知->" + id);

        Assert.isTrue(id != null, "通知");
    }

    @Test
    public void queryNoticeTest() {

        QueryNoticeReq req = new QueryNoticeReq();

        // req.setId(null);//
        // req.setOwnerId("这是文本128");//
        // req.setCategory("这是文本64");//
        // req.setContentType(ContentType.Text);//
        // req.setContent("通知内容_1");//
        // req.setGteExpiredDate(DateUtils.getZoneHour(new Date()));//最小
        // req.setLteExpiredDate(DateUtils.getEndHour(new Date()));//最大
        // req.setDomain("这是文本128");//
        // req.setName("这是文本64");//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<NoticeInfo> resp = noticeService.query(req, null);

        log.debug("查询通知->" + resp);

        Assert.isTrue(!resp.isEmpty(), "通知");
    }

    @Test
    public void updateNoticeTest() {

        UpdateNoticeReq req = new UpdateNoticeReq();

        req.setId(id);

        // req.setOwnerId("这是文本128");//
        // req.setCategory("这是文本64");//
        // req.setContentType(ContentType.Text);//
        // req.setContent("通知内容_1");//
        // req.setExpiredDate(new Date());//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本64");// 必填
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);// 必填
        // req.setEditable(true);// 必填
        // req.setRemark("这是文本512");//

        boolean ok = noticeService.update(req);

        log.debug("更新通知-> " + ok);

        Assert.isTrue(ok, "通知");
    }

    @Test
    public void deleteNoticeTest() {

        NoticeIdReq req = new NoticeIdReq();

        req.setId(id);

        boolean ok = noticeService.delete(req);

        log.debug("删除通知->" + ok);

        Assert.isTrue(ok, "通知");
    }
}
