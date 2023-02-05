package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Area;

import com.levin.oak.base.services.area.*;
import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.services.area.info.*;
import java.util.Set;
import com.levin.oak.base.entities.Area.*;
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
 *  区域测试
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
public class AreaServiceTest {

    @Resource
    private AreaService areaService;

    private String code;

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
    public void createAreaTest() {

        CreateAreaReq req = new CreateAreaReq();

            // req.setIcon("图标_1");//图标 

            // req.setParentCode("这是文本64");//父区域ID 

            // req.setType(Type.Nation);//类型 必填

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String code  = areaService.create(req);

        log.debug("新增区域->" + code);

        Assert.isTrue(code != null, "区域");

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
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<AreaInfo> resp = areaService.query(req,null);

        log.debug("查询区域->" + resp);

        Assert.isTrue(!resp.isEmpty(), "区域");
    }

    @Test
    public void updateAreaTest() {

         UpdateAreaReq req = new UpdateAreaReq();

         req.setCode(code);


           // req.setIcon("图标_1");//图标 
           // req.setParentCode("这是文本64");//父区域ID 
           // req.setType(Type.Nation);//类型 必填
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = areaService.update(req);

          log.debug("更新区域-> " + resp);

          Assert.isTrue(resp > 0, "区域");
    }

    @Test
    public void deleteAreaTest() {

        AreaIdReq req = new AreaIdReq();

        req.setCode(code);

        int n = areaService.delete(req);

        log.debug("删除区域->" + n);

        Assert.isTrue(n > 0, "区域");
    }
}
