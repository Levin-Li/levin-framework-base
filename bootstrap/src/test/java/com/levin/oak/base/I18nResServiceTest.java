package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.I18nRes;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.i18nres.*;
import com.levin.oak.base.services.i18nres.req.*;
import com.levin.oak.base.services.i18nres.info.*;


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
 * 国际化资源测试
 *
 * Auto gen by simple-dao-codegen 2023年6月28日 上午9:18:58
 * 代码生成哈希校验码：[dbf1139252390ee7ef6df12b042cc81e]
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class I18nResServiceTest {

    @Autowired
    private I18nResService i18nResService;

    @Autowired
    private BizI18nResService bizi18nResService;

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
    public void createI18nResTest() {

        CreateI18nResReq req = new CreateI18nResReq();

            // req.setCategory("这是文本128");// 必填

            // req.setLang("这是文本64");// 必填

            // req.setLabel("这是文本768");// 必填

            // req.setTenantId("这是文本128");// 

            // req.setDomain("这是文本128");// 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       Long id  = i18nResService.create(req);

        log.debug("新增国际化资源->" + id);

        Assert.isTrue(id != null, "国际化资源");

    }


    @Test
    public void queryI18nResTest() {

        QueryI18nResReq req = new QueryI18nResReq();

        // req.setId(null);//
        // req.setCategory("这是文本128");//
        // req.setLang("这是文本64");//
        // req.setLabel("这是文本768");//
        // req.setTenantId("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<I18nResInfo> resp = i18nResService.query(req,null);

        log.debug("查询国际化资源->" + resp);

        Assert.isTrue(!resp.isEmpty(), "国际化资源");
    }

    @Test
    public void updateI18nResTest() {

         UpdateI18nResReq req = new UpdateI18nResReq();

         req.setId(id);


           // req.setCategory("这是文本128");// 必填
           // req.setLang("这是文本64");// 必填
           // req.setLabel("这是文本768");// 必填
           // req.setTenantId("这是文本128");// 
           // req.setDomain("这是文本128");// 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = i18nResService.update(req);

          log.debug("更新国际化资源-> " + ok);

          Assert.isTrue(ok, "国际化资源");
    }

    @Test
    public void deleteI18nResTest() {

        I18nResIdReq req = new I18nResIdReq();

        req.setId(id);

        boolean ok = i18nResService.delete(req);

        log.debug("删除国际化资源->" + ok);

        Assert.isTrue(ok , "国际化资源");
    }
}
