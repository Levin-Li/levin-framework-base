package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.Dict;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.dict.*;
import com.levin.oak.base.services.dict.req.*;
import com.levin.oak.base.services.dict.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.Dict.*;
import java.util.List;
import com.levin.commons.service.support.DefaultJsonConverter;
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
 * 字典测试
 *
 * Auto gen by simple-dao-codegen 2023年6月28日 上午9:18:58
 * 代码生成哈希校验码：[487a2207ecc759a45d49246d9fa2b51d]
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class DictServiceTest {

    @Autowired
    private DictService dictService;

    @Autowired
    private BizDictService bizdictService;

    private String id;

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
    public void createDictTest() {

        CreateDictReq req = new CreateDictReq();

            // req.setType(Type.System);// 必填

            // req.setCode("这是文本256");// 必填

            // req.setItemList("编码项_1");//Json 存储 

            // req.setTenantId("这是文本128");// 

            // req.setDomain("这是文本128");// 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String id  = dictService.create(req);

        log.debug("新增字典->" + id);

        Assert.isTrue(id != null, "字典");

    }


    @Test
    public void queryDictTest() {

        QueryDictReq req = new QueryDictReq();

        // req.setId(null);//
        // req.setType(Type.System);//
        // req.setCode("这是文本256");//
        // req.setItemList("编码项_1");//Json 存储
        // req.setTenantId("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<DictInfo> resp = dictService.query(req,null);

        log.debug("查询字典->" + resp);

        Assert.isTrue(!resp.isEmpty(), "字典");
    }

    @Test
    public void updateDictTest() {

         UpdateDictReq req = new UpdateDictReq();

         req.setId(id);


           // req.setType(Type.System);// 必填
           // req.setCode("这是文本256");// 必填
           // req.setItemList("编码项_1");//Json 存储 
           // req.setTenantId("这是文本128");// 
           // req.setDomain("这是文本128");// 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = dictService.update(req);

          log.debug("更新字典-> " + ok);

          Assert.isTrue(ok, "字典");
    }

    @Test
    public void deleteDictTest() {

        DictIdReq req = new DictIdReq();

        req.setId(id);

        boolean ok = dictService.delete(req);

        log.debug("删除字典->" + ok);

        Assert.isTrue(ok , "字典");
    }
}
