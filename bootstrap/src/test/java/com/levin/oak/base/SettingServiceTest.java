package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.setting.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.Setting.*;
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
 * 系统设置测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月10日 上午2:41:25, 代码生成哈希校验码：[e45426fc7ae5df221780b7b4d4aa2369]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class SettingServiceTest {

    @Autowired private SettingService settingService;

    @Autowired private BizSettingService bizSettingService;

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
    public void createSettingTest() {

        CreateSettingReq req = new CreateSettingReq();

        // req.setCategoryName("这是文本64");// 必填

        // req.setGroupName("这是文本64");//

        // req.setCode("这是文本64");// 必填

        // req.setValueType(ValueType.Html);// 必填

        // req.setValueContent("值_1");//

        // req.setNullable(true);//

        // req.setInputPlaceholder("这是文本128");//

        // req.setDomain("这是文本128");//

        // req.setName("这是文本64");// 必填

        // req.setOrgId("这是文本128");//

        // req.setTenantId("这是文本128");//

        // req.setOrderCode(1);//

        // req.setEnable(true);// 必填

        // req.setEditable(true);// 必填

        // req.setRemark("这是文本512");//

        String id = settingService.create(req);

        log.debug("新增系统设置->" + id);

        Assert.isTrue(id != null, "系统设置");
    }

    @Test
    public void querySettingTest() {

        QuerySettingReq req = new QuerySettingReq();

        // req.setId(null);//
        // req.setCategoryName("这是文本64");//
        // req.setGroupName("这是文本64");//
        // req.setCode("这是文本64");//
        // req.setValueType(ValueType.Html);//
        // req.setValueContent("值_1");//
        // req.setNullable(true);//
        // req.setInputPlaceholder("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本64");//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<SettingInfo> resp = settingService.query(req, null);

        log.debug("查询系统设置->" + resp);

        Assert.isTrue(!resp.isEmpty(), "系统设置");
    }

    @Test
    public void updateSettingTest() {

        UpdateSettingReq req = new UpdateSettingReq();

        req.setId(id);

        // req.setCategoryName("这是文本64");// 必填
        // req.setGroupName("这是文本64");//
        // req.setCode("这是文本64");// 必填
        // req.setValueType(ValueType.Html);// 必填
        // req.setValueContent("值_1");//
        // req.setNullable(true);//
        // req.setInputPlaceholder("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本64");// 必填
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);// 必填
        // req.setEditable(true);// 必填
        // req.setRemark("这是文本512");//

        boolean ok = settingService.update(req);

        log.debug("更新系统设置-> " + ok);

        Assert.isTrue(ok, "系统设置");
    }

    @Test
    public void deleteSettingTest() {

        SettingIdReq req = new SettingIdReq();

        req.setId(id);

        boolean ok = settingService.delete(req);

        log.debug("删除系统设置->" + ok);

        Assert.isTrue(ok, "系统设置");
    }
}
