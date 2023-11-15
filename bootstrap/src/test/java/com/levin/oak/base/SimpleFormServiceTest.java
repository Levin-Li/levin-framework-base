package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.SimpleForm;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.simpleform.*;
import com.levin.oak.base.services.simpleform.req.*;
import com.levin.oak.base.services.simpleform.info.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * 简单表单测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月15日 下午6:26:12, 代码生成哈希校验码：[512bbe7b04245b4deb8d2f8c900b6238]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class SimpleFormServiceTest {

    @Autowired private SimpleFormService simpleFormService;

    @Autowired private BizSimpleFormService bizSimpleFormService;

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
    public void createSimpleFormTest() {

        CreateSimpleFormReq req = new CreateSimpleFormReq();

        // req.setCommitApi("提交地址_1");//

        // req.setType("这是文本128");// 必填

        // req.setCategory("这是文本128");// 必填

        // req.setGroupName("这是文本128");// 必填

        // req.setIcon("图标_1");//

        // req.setPath("这是文本800");// 必填

        // req.setRequireAuthorizations("这是文本1800");//

        // req.setContent("内容_1");//

        // req.setDomain("这是文本128");//归属的子系统或应用

        // req.setName("这是文本64");// 必填

        // req.setOptimisticLock(1);//

        // req.setOrgId("这是文本128");//

        // req.setTenantId("这是文本128");//

        // req.setOrderCode(1);//

        // req.setEnable(true);// 必填

        // req.setEditable(true);// 必填

        // req.setRemark("这是文本512");//

        String id = simpleFormService.create(req);

        log.debug("新增简单表单->" + id);

        Assert.isTrue(id != null, "简单表单");
    }

    @Test
    public void querySimpleFormTest() {

        QuerySimpleFormReq req = new QuerySimpleFormReq();

        // req.setCommitApi("提交地址_1");//
        // req.setId(null);//
        // req.setType("这是文本128");//
        // req.setCategory("这是文本128");//
        // req.setGroupName("这是文本128");//
        // req.setIcon("图标_1");//
        // req.setPath("这是文本800");//
        // req.setRequireAuthorizations("这是文本1800");//
        // req.setContent("内容_1");//
        // req.setDomain("这是文本128");//归属的子系统或应用
        // req.setName("这是文本64");//
        // req.setOptimisticLock(1);//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<SimpleFormInfo> resp = simpleFormService.query(req, null);

        log.debug("查询简单表单->" + resp);

        Assert.isTrue(!resp.isEmpty(), "简单表单");
    }

    @Test
    public void updateSimpleFormTest() {

        UpdateSimpleFormReq req = new UpdateSimpleFormReq();

        req.setId(id);

        // req.setCommitApi("提交地址_1");//
        // req.setType("这是文本128");// 必填
        // req.setCategory("这是文本128");// 必填
        // req.setGroupName("这是文本128");// 必填
        // req.setIcon("图标_1");//
        // req.setPath("这是文本800");// 必填
        // req.setRequireAuthorizations("这是文本1800");//
        // req.setContent("内容_1");//
        // req.setDomain("这是文本128");//归属的子系统或应用
        // req.setName("这是文本64");// 必填
        // req.setOptimisticLock(1);//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);// 必填
        // req.setEditable(true);// 必填
        // req.setRemark("这是文本512");//

        boolean ok = simpleFormService.update(req);

        log.debug("更新简单表单-> " + ok);

        Assert.isTrue(ok, "简单表单");
    }

    @Test
    public void deleteSimpleFormTest() {

        SimpleFormIdReq req = new SimpleFormIdReq();

        req.setId(id);

        boolean ok = simpleFormService.delete(req);

        log.debug("删除简单表单->" + ok);

        Assert.isTrue(ok, "简单表单");
    }
}
