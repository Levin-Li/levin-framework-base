package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.dict.DictService;
import com.levin.oak.base.services.dict.info.DictInfo;
import com.levin.oak.base.services.dict.req.CreateDictReq;
import com.levin.oak.base.services.dict.req.DictIdReq;
import com.levin.oak.base.services.dict.req.QueryDictReq;
import com.levin.oak.base.services.dict.req.UpdateDictReq;
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
 *  字典测试
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
public class DictServiceTest {


    @Resource
    private DictService dictService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createDictTest() {

        CreateDictReq req = new CreateDictReq();

            // req.setType(Type.System);//类型 必填

            // req.setCode("这是文本256");//编码 必填

            // req.setItemList("编码项_1");//编码项 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = dictService.create(req);

        log.debug("新增字典->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryDictTest() {

        QueryDictReq req = new QueryDictReq();

        // req.setId(null);//id
        // req.setType(Type.System);//类型
        // req.setCode("这是文本256");//编码
        // req.setItemList("编码项_1");//编码项
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<DictInfo> resp = dictService.query(req,null);

        log.debug("查询字典->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateDictTest() {

         UpdateDictReq req = new UpdateDictReq();

         req.setId(id);


           // req.setType(Type.System);//类型 必填
           // req.setCode("这是文本256");//编码 必填
           // req.setItemList("编码项_1");//编码项 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = dictService.update(req);

          log.debug("更新字典-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteDictTest() {

        DictIdReq req = new DictIdReq();

        req.setId(id);

        int n = dictService.delete(req);

        log.debug("删除字典->" + n);

        Assert.assertTrue(n > 0);
    }
}
