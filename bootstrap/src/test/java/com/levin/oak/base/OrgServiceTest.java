package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Org;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.org.*;
import com.levin.oak.base.services.org.req.*;
import com.levin.oak.base.services.org.info.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Org.*;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
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
 * 机构测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:46, 代码生成哈希校验码：[a3097c4fe422861e97167b5a343d4b60]，请不要修改和删除此行内容。
 */

// @ActiveProfiles("test")
// @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
// @Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class OrgServiceTest {

    @Autowired private OrgService orgService;

    @Autowired private BizOrgService bizorgService;

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
    public void createOrgTest() {

        CreateOrgReq req = new CreateOrgReq();

        // req.setTenantId("这是文本64");//

        // req.setParentId("这是文本64");//

        // req.setCode("这是文本64");//对于公司是统一信用码

        // req.setIcon("图标_1");//

        // req.setState(State.Normal);// 必填

        // req.setType(Type.LegalPerson);// 必填

        // req.setIndustries("这是文本64");//

        // req.setAreaCode("这是文本64");//

        // req.setLevel("这是文本128");//使用字典值配置

        // req.setCategory("这是文本128");//使用字典值配置 必填

        // req.setIsExternal(true);//是否外部机构，是相对于上级节点来说 必填

        // req.setContacts("这是文本64");//

        // req.setPhones("这是文本20");//

        // req.setEmails("这是文本32");//

        // req.setAddress("联系地址_1");//

        // req.setZipCode("这是文本32");//

        // req.setExtInfo("机构扩展信息_1");//

        // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15|

        // req.setName("这是文本128");// 必填

        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开

        // req.setOrderCode(1);//

        // req.setEnable(true);// 必填

        // req.setEditable(true);// 必填

        // req.setRemark("这是文本512");//

        String id = orgService.create(req);

        log.debug("新增机构->" + id);

        Assert.isTrue(id != null, "机构");
    }

    @Test
    public void queryOrgTest() {

        QueryOrgReq req = new QueryOrgReq();

        // req.setId(null);//
        // req.setTenantId("这是文本64");//
        // req.setParentId("这是文本64");//
        // req.setCode("这是文本64");//对于公司是统一信用码
        // req.setIcon("图标_1");//
        // req.setState(State.Normal);//
        // req.setType(Type.LegalPerson);//
        // req.setIndustries("这是文本64");//
        // req.setAreaCode("这是文本64");//
        // req.setLoadArea(true);//加载
        // req.setLevel("这是文本128");//使用字典值配置
        // req.setCategory("这是文本128");//使用字典值配置
        // req.setIsExternal(true);//是否外部机构，是相对于上级节点来说
        // req.setContacts("这是文本64");//
        // req.setPhones("这是文本20");//
        // req.setEmails("这是文本32");//
        // req.setAddress("联系地址_1");//
        // req.setZipCode("这是文本32");//
        // req.setExtInfo("机构扩展信息_1");//
        // req.setLoadParent(true);//加载
        // req.setLoadChildren(true);//加载
        // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15|
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<OrgInfo> resp = orgService.query(req, null);

        log.debug("查询机构->" + resp);

        Assert.isTrue(!resp.isEmpty(), "机构");
    }

    @Test
    public void updateOrgTest() {

        UpdateOrgReq req = new UpdateOrgReq();

        req.setId(id);

        // req.setTenantId("这是文本64");//
        // req.setParentId("这是文本64");//
        // req.setCode("这是文本64");//对于公司是统一信用码
        // req.setIcon("图标_1");//
        // req.setState(State.Normal);// 必填
        // req.setType(Type.LegalPerson);// 必填
        // req.setIndustries("这是文本64");//
        // req.setAreaCode("这是文本64");//
        // req.setLevel("这是文本128");//使用字典值配置
        // req.setCategory("这是文本128");//使用字典值配置 必填
        // req.setIsExternal(true);//是否外部机构，是相对于上级节点来说 必填
        // req.setContacts("这是文本64");//
        // req.setPhones("这是文本20");//
        // req.setEmails("这是文本32");//
        // req.setAddress("联系地址_1");//
        // req.setZipCode("这是文本32");//
        // req.setExtInfo("机构扩展信息_1");//
        // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15|
        // req.setName("这是文本128");// 必填
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setOrderCode(1);//
        // req.setEnable(true);// 必填
        // req.setEditable(true);// 必填
        // req.setRemark("这是文本512");//

        boolean ok = orgService.update(req);

        log.debug("更新机构-> " + ok);

        Assert.isTrue(ok, "机构");
    }

    @Test
    public void deleteOrgTest() {

        OrgIdReq req = new OrgIdReq();

        req.setId(id);

        boolean ok = orgService.delete(req);

        log.debug("删除机构->" + ok);

        Assert.isTrue(ok, "机构");
    }
}
