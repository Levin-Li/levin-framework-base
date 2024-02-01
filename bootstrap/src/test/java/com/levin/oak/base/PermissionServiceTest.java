package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Permission;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.permission.*;
import com.levin.oak.base.services.permission.req.*;
import com.levin.oak.base.services.permission.info.*;


////////////////////////////////////
//自动导入列表
import java.io.Serializable;
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
 * 权限清单测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年1月27日 上午11:48:08, 代码生成哈希校验码：[b186214770785505f3f85eb9fad712cf]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private BizPermissionService bizPermissionService;

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
    public void createPermissionTest() {

        CreatePermissionReq req = new CreatePermissionReq();

            // req.setIcon("图标_1");// 

            // req.setDomain("这是文本128");// 

            // req.setType("这是文本128");// 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


       String id  = permissionService.create(req);

        log.debug("新增权限清单->" + id);

        Assert.isTrue(id != null, "权限清单");

    }


    @Test
    public void queryPermissionTest() {

        QueryPermissionReq req = new QueryPermissionReq();

        // req.setId(null);//
        // req.setIcon("图标_1");//
        // req.setDomain("这是文本128");//
        // req.setType("这是文本128");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//
        // req.setOptimisticLock(1);//

        PagingData<PermissionInfo> resp = permissionService.query(req,null);

        log.debug("查询权限清单->" + resp);

        Assert.isTrue(!resp.isEmpty(), "权限清单");
    }

    @Test
    public void updatePermissionTest() {

         UpdatePermissionReq req = new UpdatePermissionReq();

         req.setId(id);


           // req.setIcon("图标_1");// 
           // req.setDomain("这是文本128");// 
           // req.setType("这是文本128");// 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

          boolean ok = permissionService.update(req);

          log.debug("更新权限清单-> " + ok);

          Assert.isTrue(ok, "权限清单");
    }

    @Test
    public void deletePermissionTest() {

        PermissionIdReq req = new PermissionIdReq();

        req.setId(id);

        boolean ok = permissionService.delete(req);

        log.debug("删除权限清单->" + ok);

        Assert.isTrue(ok , "权限清单");
    }
}
