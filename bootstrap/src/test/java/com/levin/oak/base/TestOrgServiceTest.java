package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.TestOrg;

import com.levin.oak.base.services.testorg.*;
import com.levin.oak.base.services.testorg.req.*;
import com.levin.oak.base.services.testorg.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.TestOrg.*;
import com.levin.oak.base.entities.TestOrg;
import com.levin.oak.base.services.testorg.info.*;
import java.util.Set;
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
 *  机构测试
 *
 *  @author auto gen by simple-dao-codegen 2023-2-5 11:13:19
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class TestOrgServiceTest {

    @Resource
    private TestOrgService testOrgService;

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
    public void createTestOrgTest() {

        CreateTestOrgReq req = new CreateTestOrgReq();

            // req.setParentId("这是文本64");//父ID 

            // req.setTenantId("这是文本64");//租户ID 

            // req.setCode("这是文本64");//编码 

            // req.setIcon("图标_1");//图标 

            // req.setState(State.Normal);//状态 必填

            // req.setType(Type.LegalPerson);//类型 必填

            // req.setIndustries("这是文本64");//所属行业 

            // req.setAreaCode("这是文本64");//区域编码 必填

            // req.setLevel("这是文本128");//机构级别 

            // req.setCategory("这是文本128");//机构类别 必填

            // req.setIsExternal(true);//是否外部机构 必填

            // req.setContacts("这是文本64");//联系人 

            // req.setPhones("这是文本20");//联系电话 

            // req.setEmails("这是文本32");//联系邮箱 

            // req.setAddress("联系地址_1");//联系地址 

            // req.setZipCode("这是文本32");//邮政编码 

            // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = testOrgService.create(req);

        log.debug("新增机构->" + id);

        Assert.isTrue(id != null, "机构");

    }


    @Test
    public void queryTestOrgTest() {

        QueryTestOrgReq req = new QueryTestOrgReq();

        // req.setId(null);//id
        // req.setParentId("这是文本64");//父ID
        // req.setTenantId("这是文本64");//租户ID
        // req.setCode("这是文本64");//编码
        // req.setIcon("图标_1");//图标
        // req.setState(State.Normal);//状态
        // req.setType(Type.LegalPerson);//类型
        // req.setIndustries("这是文本64");//所属行业
        // req.setAreaCode("这是文本64");//区域编码
        // req.setLevel("这是文本128");//机构级别
        // req.setCategory("这是文本128");//机构类别
        // req.setIsExternal(true);//是否外部机构
        // req.setContacts("这是文本64");//联系人
        // req.setPhones("这是文本20");//联系电话
        // req.setEmails("这是文本32");//联系邮箱
        // req.setAddress("联系地址_1");//联系地址
        // req.setZipCode("这是文本32");//邮政编码
        // req.setLoadParent(true);//加载父对象
        // req.setLoadChildren(true);//加载子节点
        // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15|
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<TestOrgInfo> resp = testOrgService.query(req,null);

        log.debug("查询机构->" + resp);

        Assert.isTrue(!resp.isEmpty(), "机构");
    }

    @Test
    public void updateTestOrgTest() {

         UpdateTestOrgReq req = new UpdateTestOrgReq();

         req.setId(id);


           // req.setParentId("这是文本64");//父ID 
           // req.setTenantId("这是文本64");//租户ID 
           // req.setCode("这是文本64");//编码 
           // req.setIcon("图标_1");//图标 
           // req.setState(State.Normal);//状态 必填
           // req.setType(Type.LegalPerson);//类型 必填
           // req.setIndustries("这是文本64");//所属行业 
           // req.setAreaCode("这是文本64");//区域编码 必填
           // req.setLevel("这是文本128");//机构级别 
           // req.setCategory("这是文本128");//机构类别 必填
           // req.setIsExternal(true);//是否外部机构 必填
           // req.setContacts("这是文本64");//联系人 
           // req.setPhones("这是文本20");//联系电话 
           // req.setEmails("这是文本32");//联系邮箱 
           // req.setAddress("联系地址_1");//联系地址 
           // req.setZipCode("这是文本32");//邮政编码 
           // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = testOrgService.update(req);

          log.debug("更新机构-> " + resp);

          Assert.isTrue(resp > 0, "机构");
    }

    @Test
    public void deleteTestOrgTest() {

        TestOrgIdReq req = new TestOrgIdReq();

        req.setId(id);

        int n = testOrgService.delete(req);

        log.debug("删除机构->" + n);

        Assert.isTrue(n > 0, "机构");
    }
}
