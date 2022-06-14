package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.area.AreaService;
import com.levin.oak.base.services.area.info.AreaInfo;
import com.levin.oak.base.services.area.req.AreaIdReq;
import com.levin.oak.base.services.area.req.CreateAreaReq;
import com.levin.oak.base.services.area.req.QueryAreaReq;
import com.levin.oak.base.services.area.req.UpdateAreaReq;
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
 *  区域测试
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
public class AreaServiceTest {


    @Resource
    private AreaService areaService;

    private String code;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createAreaTest() {

        CreateAreaReq req = new CreateAreaReq();

            // req.setIcon("图标_1");//图标 

            // req.setParentCode("这是文本64");//父区域ID 

            // req.setType(Type.Nation);//类型 必填

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String code  = areaService.create(req);

        log.debug("新增区域->" + code);

        Assert.assertTrue(code != null);

    }


    @Test
    public void queryAreaTest() {

        QueryAreaReq req = new QueryAreaReq();

        // req.setCode(null);//编码
        // req.setIcon("图标_1");//图标
        // req.setParentCode("这是文本64");//父区域ID
        // req.setLoadParent(true);//加载父区域
        // req.setLoadChildren(true);//加载子区域
        // req.setType(Type.Nation);//类型
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<AreaInfo> resp = areaService.query(req,null);

        log.debug("查询区域->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateAreaTest() {

         UpdateAreaReq req = new UpdateAreaReq();

         req.setCode(code);


           // req.setIcon("图标_1");//图标 
           // req.setParentCode("这是文本64");//父区域ID 
           // req.setType(Type.Nation);//类型 必填
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = areaService.update(req);

          log.debug("更新区域-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteAreaTest() {

        AreaIdReq req = new AreaIdReq();

        req.setCode(code);

        int n = areaService.delete(req);

        log.debug("删除区域->" + n);

        Assert.assertTrue(n > 0);
    }
}
