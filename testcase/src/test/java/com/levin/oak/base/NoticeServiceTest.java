package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Notice;

import com.levin.oak.base.services.notice.*;
import com.levin.oak.base.services.notice.req.*;
import com.levin.oak.base.services.notice.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Notice.*;
import java.util.Date;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
 *  通知测试
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
public class NoticeServiceTest {


    @Resource
    private NoticeService noticeService;

    private String id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createNoticeTest() {

        CreateNoticeReq req = new CreateNoticeReq();

            // req.setOwnerId("这是文本128");//所有者ID 

            // req.setCategory("这是文本64");//通知类别 

            // req.setContentType(ContentType.Text);//通知内容类型 

            // req.setContent("通知内容_1");//通知内容 

            // req.setExpiredDate(new Date());//过期时间 

            // req.setOrgId("这是文本64");//机构ID 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = noticeService.create(req);

        log.debug("新增通知->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryNoticeTest() {

        QueryNoticeReq req = new QueryNoticeReq();

        // req.setId(null);//id
        // req.setOwnerId("这是文本128");//所有者ID
        // req.setCategory("这是文本64");//通知类别
        // req.setContentType(ContentType.Text);//通知内容类型
        // req.setContent("通知内容_1");//通知内容
        // req.setGteExpiredDate(DateUtils.getZoneHour(new Date()));//最小过期时间
        // req.setLteExpiredDate(DateUtils.getEndHour(new Date()));//最大过期时间
        // req.setOrgId("这是文本64");//机构ID
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<NoticeInfo> resp = noticeService.query(req,null);

        log.debug("查询通知->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateNoticeTest() {

         UpdateNoticeReq req = new UpdateNoticeReq();

         req.setId(id);


           // req.setOwnerId("这是文本128");//所有者ID 
           // req.setCategory("这是文本64");//通知类别 
           // req.setContentType(ContentType.Text);//通知内容类型 
           // req.setContent("通知内容_1");//通知内容 
           // req.setExpiredDate(new Date());//过期时间 
           // req.setOrgId("这是文本64");//机构ID 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = noticeService.update(req);

          log.debug("更新通知-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteNoticeTest() {

        NoticeIdReq req = new NoticeIdReq();

        req.setId(id);

        int n = noticeService.delete(req);

        log.debug("删除通知->" + n);

        Assert.assertTrue(n > 0);
    }
}
