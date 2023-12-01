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
import java.util.List;
import com.levin.oak.base.entities.Dict.*;
import com.levin.commons.service.support.DefaultJsonConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
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
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月28日 下午2:37:41, 代码生成哈希校验码：[407289d1b334153b2299b9fb7edf58b5]，请不要修改和删除此行内容。
 *
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
    private BizDictService bizDictService;

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

            // req.setDomain("这是文本128");//归属的子系统或应用 

            // req.setName("这是文本64");// 必填

            // req.setOrgId("这是文本128");// 

            // req.setTenantId("这是文本128");// 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


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
        // req.setDomain("这是文本128");//归属的子系统或应用
        // req.setName("这是文本64");//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//
        // req.setOptimisticLock(1);//

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
           // req.setDomain("这是文本128");//归属的子系统或应用 
           // req.setName("这是文本64");// 必填
           // req.setOrgId("这是文本128");// 
           // req.setTenantId("这是文本128");// 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

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
