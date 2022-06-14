package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.org.OrgService;
import com.levin.oak.base.services.org.info.OrgInfo;
import com.levin.oak.base.services.org.req.CreateOrgReq;
import com.levin.oak.base.services.org.req.OrgIdReq;
import com.levin.oak.base.services.org.req.QueryOrgReq;
import com.levin.oak.base.services.org.req.UpdateOrgReq;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

////////////////////////////////////
//自动导入列表
////////////////////////////////////
//import org.junit.jupiter.api.Test;
import javax.annotation.Resource;

/**
 *  机构测试
 *
 *  @author auto gen by simple-dao-codegen 2022-6-13 19:41:50
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class OrgServiceTest {


    @Resource
    private OrgService orgService;

    private String id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createOrgTest() {

        CreateOrgReq req = new CreateOrgReq();

            // req.setTenantId("租户ID_1");//租户ID 

            // req.setCode("这是文本128");//编码 

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

            // req.setParentId(null);//父ID 

            // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = orgService.create(req);

        log.debug("新增机构->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryOrgTest() {

        QueryOrgReq req = new QueryOrgReq();

        // req.setId(null);//id
        // req.setTenantId("租户ID_1");//租户ID
        // req.setCode("这是文本128");//编码
        // req.setIcon("图标_1");//图标
        // req.setState(State.Normal);//状态
        // req.setType(Type.LegalPerson);//类型
        // req.setIndustries("这是文本64");//所属行业
        // req.setAreaCode("这是文本64");//区域编码
        // req.setLoadArea(true);//加载所属区域
        // req.setLevel("这是文本128");//机构级别
        // req.setCategory("这是文本128");//机构类别
        // req.setIsExternal(true);//是否外部机构
        // req.setContacts("这是文本64");//联系人
        // req.setPhones("这是文本20");//联系电话
        // req.setEmails("这是文本32");//联系邮箱
        // req.setAddress("联系地址_1");//联系地址
        // req.setZipCode("这是文本32");//邮政编码
        // req.setParentId(null);//父ID
        // req.setLoadParent(true);//加载父对象
        // req.setLoadChildren(true);//加载子节点
        // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15|
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<OrgInfo> resp = orgService.query(req,null);

        log.debug("查询机构->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateOrgTest() {

         UpdateOrgReq req = new UpdateOrgReq();

         req.setId(id);


           // req.setTenantId("租户ID_1");//租户ID 
           // req.setCode("这是文本128");//编码 
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
           // req.setParentId(null);//父ID 
           // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = orgService.update(req);

          log.debug("更新机构-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteOrgTest() {

        OrgIdReq req = new OrgIdReq();

        req.setId(id);

        int n = orgService.delete(req);

        log.debug("删除机构->" + n);

        Assert.assertTrue(n > 0);
    }
}
