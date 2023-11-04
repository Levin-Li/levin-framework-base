package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.TenantApp;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.tenantapp.*;
import com.levin.oak.base.services.tenantapp.req.*;
import com.levin.oak.base.services.tenantapp.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
 * 租户应用测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:46, 代码生成哈希校验码：[dd992b7f034b3e3728edc8bdc064e218]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class TenantAppServiceTest {

    @Autowired
    private TenantAppService tenantAppService;

    @Autowired
    private BizTenantAppService bizTenantAppService;

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
    public void createTenantAppTest() {

        CreateTenantAppReq req = new CreateTenantAppReq();

            // req.setName("这是文本64");// 必填

            // req.setModules("这是文本1800");// 

            // req.setTenantId("这是文本128");// 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String id  = tenantAppService.create(req);

        log.debug("新增租户应用->" + id);

        Assert.isTrue(id != null, "租户应用");

    }


    @Test
    public void queryTenantAppTest() {

        QueryTenantAppReq req = new QueryTenantAppReq();

        // req.setId(null);//
        // req.setName("这是文本64");//
        // req.setModules("这是文本1800");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<TenantAppInfo> resp = tenantAppService.query(req,null);

        log.debug("查询租户应用->" + resp);

        Assert.isTrue(!resp.isEmpty(), "租户应用");
    }

    @Test
    public void updateTenantAppTest() {

         UpdateTenantAppReq req = new UpdateTenantAppReq();

         req.setId(id);


           // req.setName("这是文本64");// 必填
           // req.setModules("这是文本1800");// 
           // req.setTenantId("这是文本128");// 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = tenantAppService.update(req);

          log.debug("更新租户应用-> " + ok);

          Assert.isTrue(ok, "租户应用");
    }

    @Test
    public void deleteTenantAppTest() {

        TenantAppIdReq req = new TenantAppIdReq();

        req.setId(id);

        boolean ok = tenantAppService.delete(req);

        log.debug("删除租户应用->" + ok);

        Assert.isTrue(ok , "租户应用");
    }
}
