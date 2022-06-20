package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.NoticeProcessLog;

import com.levin.oak.base.services.noticeprocesslog.*;
import com.levin.oak.base.services.noticeprocesslog.req.*;
import com.levin.oak.base.services.noticeprocesslog.info.*;


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
 *  通知处理日志测试
 *
 *  @author auto gen by simple-dao-codegen 2022-6-20 16:50:12
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class NoticeProcessLogServiceTest {


    @Resource
    private NoticeProcessLogService noticeProcessLogService;

    private String id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createNoticeProcessLogTest() {

        CreateNoticeProcessLogReq req = new CreateNoticeProcessLogReq();

            // req.setTenantId("这是文本128");//租户ID 

            // req.setOrgId("这是文本128");//部门ID 

            // req.setOwnerId("这是文本128");//用户ID 必填

            // req.setNoticeId("这是文本128");//消息ID 必填

            // req.setStatus("这是文本128");//处理状态 

            // req.setRemark("这是文本512");//备注 


       String id  = noticeProcessLogService.create(req);

        log.debug("新增通知处理日志->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryNoticeProcessLogTest() {

        QueryNoticeProcessLogReq req = new QueryNoticeProcessLogReq();

        // req.setId(null);//id
        // req.setTenantId("这是文本128");//租户ID
        // req.setOrgId("这是文本128");//部门ID
        // req.setOwnerId("这是文本128");//用户ID
        // req.setNoticeId("这是文本128");//消息ID
        // req.setStatus("这是文本128");//处理状态
        // req.setRemark("这是文本512");//备注

        PagingData<NoticeProcessLogInfo> resp = noticeProcessLogService.query(req,null);

        log.debug("查询通知处理日志->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateNoticeProcessLogTest() {

         UpdateNoticeProcessLogReq req = new UpdateNoticeProcessLogReq();

         req.setId(id);


           // req.setTenantId("这是文本128");//租户ID 
           // req.setOrgId("这是文本128");//部门ID 
           // req.setOwnerId("这是文本128");//用户ID 必填
           // req.setNoticeId("这是文本128");//消息ID 必填
           // req.setStatus("这是文本128");//处理状态 
           // req.setRemark("这是文本512");//备注 

          int resp = noticeProcessLogService.update(req);

          log.debug("更新通知处理日志-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteNoticeProcessLogTest() {

        NoticeProcessLogIdReq req = new NoticeProcessLogIdReq();

        req.setId(id);

        int n = noticeProcessLogService.delete(req);

        log.debug("删除通知处理日志->" + n);

        Assert.assertTrue(n > 0);
    }
}
