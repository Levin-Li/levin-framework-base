package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Area;

import com.levin.oak.base.biz.*;
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
 * 区域测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:13, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[646012cd2b935c1f355bf21e60a3fa16], 请不要修改和删除此行内容。
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class AreaServiceTest {

    @Autowired
    private AreaService areaService;

    @Autowired
    private BizAreaService bizareaService;

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

            // req.setIcon("图标_1");// 

            // req.setParentCode("这是文本64");// 

            // req.setType(Type.Nation);// 必填

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String code  = areaService.create(req);

        log.debug("新增区域->" + code);

        Assert.isTrue(code != null, "区域");

    }


    @Test
    public void queryAreaTest() {

        QueryAreaReq req = new QueryAreaReq();

        // req.setCode(null);//
        // req.setIcon("图标_1");//
        // req.setParentCode("这是文本64");//
        // req.setLoadParent(true);//加载
        // req.setLoadChildren(true);//加载
        // req.setType(Type.Nation);//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<AreaInfo> resp = areaService.query(req,null);

        log.debug("查询区域->" + resp);

        Assert.isTrue(!resp.isEmpty(), "区域");
    }

    @Test
    public void updateAreaTest() {

         UpdateAreaReq req = new UpdateAreaReq();

         req.setCode(code);


           // req.setIcon("图标_1");// 
           // req.setParentCode("这是文本64");// 
           // req.setType(Type.Nation);// 必填
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = areaService.update(req);

          log.debug("更新区域-> " + ok);

          Assert.isTrue(ok, "区域");
    }

    @Test
    public void deleteAreaTest() {

        AreaIdReq req = new AreaIdReq();

        req.setCode(code);

        boolean ok = areaService.delete(req);

        log.debug("删除区域->" + ok);

        Assert.isTrue(ok , "区域");
    }
}
