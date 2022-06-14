package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.i18nres.I18nResService;
import com.levin.oak.base.services.i18nres.info.I18nResInfo;
import com.levin.oak.base.services.i18nres.req.CreateI18nResReq;
import com.levin.oak.base.services.i18nres.req.I18nResIdReq;
import com.levin.oak.base.services.i18nres.req.QueryI18nResReq;
import com.levin.oak.base.services.i18nres.req.UpdateI18nResReq;
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
 *  国际化资源测试
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
public class I18nResServiceTest {


    @Resource
    private I18nResService i18nResService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createI18nResTest() {

        CreateI18nResReq req = new CreateI18nResReq();

            // req.setCategory("这是文本64");//分类 必填

            // req.setLang("这是文本32");//语言 必填

            // req.setLabel("这是文本768");//标签 必填

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = i18nResService.create(req);

        log.debug("新增国际化资源->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryI18nResTest() {

        QueryI18nResReq req = new QueryI18nResReq();

        // req.setId(null);//id
        // req.setCategory("这是文本64");//分类
        // req.setLang("这是文本32");//语言
        // req.setLabel("这是文本768");//标签
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<I18nResInfo> resp = i18nResService.query(req,null);

        log.debug("查询国际化资源->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateI18nResTest() {

         UpdateI18nResReq req = new UpdateI18nResReq();

         req.setId(id);


           // req.setCategory("这是文本64");//分类 必填
           // req.setLang("这是文本32");//语言 必填
           // req.setLabel("这是文本768");//标签 必填
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = i18nResService.update(req);

          log.debug("更新国际化资源-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteI18nResTest() {

        I18nResIdReq req = new I18nResIdReq();

        req.setId(id);

        int n = i18nResService.delete(req);

        log.debug("删除国际化资源->" + n);

        Assert.assertTrue(n > 0);
    }
}
