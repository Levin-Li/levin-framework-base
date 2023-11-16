package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Tenant;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.tenant.*;
import com.levin.oak.base.services.tenant.req.*;
import com.levin.oak.base.services.tenant.info.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.List;
import java.util.Date;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
 * 平台租户测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午9:16:21, 代码生成哈希校验码：[ccf8ccb165dc1a9dfd5925a9cb55a499]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class TenantServiceTest {

    @Autowired private TenantService tenantService;

    @Autowired private BizTenantService bizTenantService;

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
    public void createTenantTest() {

        CreateTenantReq req = new CreateTenantReq();

        // req.setSysName("这是文本128");//

        // req.setSysLogo("系统Logo_1");//

        // req.setLogo("租户Logo_1");//

        // req.setCode("这是文本128");//

        // req.setTenantKey("租户编码_1");// 必填

        // req.setBalance(0.1d);//

        // req.setLicenseCnt(1);//

        // req.setRemainingLicenseCnt(1);//

        // req.setLicenseExpire(new Date());//

        // req.setContractPerson("这是文本32");//

        // req.setContractPhone("这是文本32");//

        // req.setDomainList("这是文本1200");//

        // req.setAppId("这是文本64");//

        // req.setAppSecret("这是文本512");//

        // req.setEncryptKey("这是文本512");//

        // req.setName("这是文本128");// 必填

        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开

        // req.setOrderCode(1);//

        // req.setEnable(true);// 必填

        // req.setEditable(true);// 必填

        // req.setRemark("这是文本512");//

        String id = tenantService.create(req);

        log.debug("新增平台租户->" + id);

        Assert.isTrue(id != null, "平台租户");
    }

    @Test
    public void queryTenantTest() {

        QueryTenantReq req = new QueryTenantReq();

        // req.setId(null);//
        // req.setSysName("这是文本128");//
        // req.setSysLogo("系统Logo_1");//
        // req.setLogo("租户Logo_1");//
        // req.setCode("这是文本128");//
        // req.setTenantKey("租户编码_1");//
        // req.setBalance(0.1d);//
        // req.setLicenseCnt(1);//
        // req.setRemainingLicenseCnt(1);//
        // req.setGteLicenseExpire(DateUtils.getZoneHour(new Date()));//最小
        // req.setLteLicenseExpire(DateUtils.getEndHour(new Date()));//最大
        // req.setContractPerson("这是文本32");//
        // req.setContractPhone("这是文本32");//
        // req.setDomainList("这是文本1200");//
        // req.setAppId("这是文本64");//
        // req.setAppSecret("这是文本512");//
        // req.setEncryptKey("这是文本512");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<TenantInfo> resp = tenantService.query(req, null);

        log.debug("查询平台租户->" + resp);

        Assert.isTrue(!resp.isEmpty(), "平台租户");
    }

    @Test
    public void updateTenantTest() {

        UpdateTenantReq req = new UpdateTenantReq();

        req.setId(id);

        // req.setSysName("这是文本128");//
        // req.setSysLogo("系统Logo_1");//
        // req.setLogo("租户Logo_1");//
        // req.setCode("这是文本128");//
        // req.setTenantKey("租户编码_1");// 必填
        // req.setBalance(0.1d);//
        // req.setLicenseCnt(1);//
        // req.setRemainingLicenseCnt(1);//
        // req.setLicenseExpire(new Date());//
        // req.setContractPerson("这是文本32");//
        // req.setContractPhone("这是文本32");//
        // req.setDomainList("这是文本1200");//
        // req.setAppId("这是文本64");//
        // req.setAppSecret("这是文本512");//
        // req.setEncryptKey("这是文本512");//
        // req.setName("这是文本128");// 必填
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setOrderCode(1);//
        // req.setEnable(true);// 必填
        // req.setEditable(true);// 必填
        // req.setRemark("这是文本512");//

        boolean ok = tenantService.update(req);

        log.debug("更新平台租户-> " + ok);

        Assert.isTrue(ok, "平台租户");
    }

    @Test
    public void deleteTenantTest() {

        TenantIdReq req = new TenantIdReq();

        req.setId(id);

        boolean ok = tenantService.delete(req);

        log.debug("删除平台租户->" + ok);

        Assert.isTrue(ok, "平台租户");
    }
}
