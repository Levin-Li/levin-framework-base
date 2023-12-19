package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.AppClientFile;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.appclientfile.*;
import com.levin.oak.base.services.appclientfile.req.*;
import com.levin.oak.base.services.appclientfile.info.*;


////////////////////////////////////
//自动导入列表
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
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
 * 客户端文件测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:28, 代码生成哈希校验码：[58e25763cb646c8d244f005502af73fd]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class AppClientFileServiceTest {

    @Autowired
    private AppClientFileService appClientFileService;

    @Autowired
    private BizAppClientFileService bizAppClientFileService;

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
    public void createAppClientFileTest() {

        CreateAppClientFileReq req = new CreateAppClientFileReq();

            // req.setClientType("这是文本64");// 

            // req.setMimeType("这是文本128");// 

            // req.setPath("这是文本255");//本地路径或是网络路径 必填

            // req.setDomain("这是文本128");//归属的子系统或应用 

            // req.setName("这是文本64");// 必填

            // req.setOrgId("这是文本128");// 

            // req.setTenantId("这是文本128");// 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


       String id  = appClientFileService.create(req);

        log.debug("新增客户端文件->" + id);

        Assert.isTrue(id != null, "客户端文件");

    }


    @Test
    public void queryAppClientFileTest() {

        QueryAppClientFileReq req = new QueryAppClientFileReq();

        // req.setId(null);//
        // req.setClientType("这是文本64");//
        // req.setMimeType("这是文本128");//
        // req.setPath("这是文本255");//本地路径或是网络路径
        // req.setContent(null);//一般不建议存储在数据库
        // req.setDomain("这是文本128");//归属的子系统或应用
        // req.setName("这是文本64");//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//
        // req.setOptimisticLock(1);//

        PagingData<AppClientFileInfo> resp = appClientFileService.query(req,null);

        log.debug("查询客户端文件->" + resp);

        Assert.isTrue(!resp.isEmpty(), "客户端文件");
    }

    @Test
    public void updateAppClientFileTest() {

         UpdateAppClientFileReq req = new UpdateAppClientFileReq();

         req.setId(id);


           // req.setClientType("这是文本64");// 
           // req.setMimeType("这是文本128");// 
           // req.setPath("这是文本255");//本地路径或是网络路径 必填
           // req.setDomain("这是文本128");//归属的子系统或应用 
           // req.setName("这是文本64");// 必填
           // req.setOrgId("这是文本128");// 
           // req.setTenantId("这是文本128");// 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

          boolean ok = appClientFileService.update(req);

          log.debug("更新客户端文件-> " + ok);

          Assert.isTrue(ok, "客户端文件");
    }

    @Test
    public void deleteAppClientFileTest() {

        AppClientFileIdReq req = new AppClientFileIdReq();

        req.setId(id);

        boolean ok = appClientFileService.delete(req);

        log.debug("删除客户端文件->" + ok);

        Assert.isTrue(ok , "客户端文件");
    }
}
