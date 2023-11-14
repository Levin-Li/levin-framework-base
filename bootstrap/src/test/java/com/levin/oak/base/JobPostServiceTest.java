package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.JobPost;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.jobpost.*;
import com.levin.oak.base.services.jobpost.req.*;
import com.levin.oak.base.services.jobpost.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.oak.base.entities.JobPost.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * 工作岗位测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月14日 下午3:54:13, 代码生成哈希校验码：[2403cc986953cd45465024873bc71046]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class JobPostServiceTest {

    @Autowired
    private JobPostService jobPostService;

    @Autowired
    private BizJobPostService bizJobPostService;

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
    public void createJobPostTest() {

        CreateJobPostReq req = new CreateJobPostReq();

            // req.setCode("这是文本64");// 必填

            // req.setType(Type.Manager);// 必填

            // req.setDomain("这是文本128");//归属的子系统或应用 

            // req.setName("这是文本64");// 必填

            // req.setOptimisticLock(1);// 

            // req.setOrgId("这是文本128");// 

            // req.setTenantId("这是文本128");// 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 


       String id  = jobPostService.create(req);

        log.debug("新增工作岗位->" + id);

        Assert.isTrue(id != null, "工作岗位");

    }


    @Test
    public void queryJobPostTest() {

        QueryJobPostReq req = new QueryJobPostReq();

        // req.setId(null);//
        // req.setCode("这是文本64");//
        // req.setType(Type.Manager);//
        // req.setDomain("这是文本128");//归属的子系统或应用
        // req.setName("这是文本64");//
        // req.setOptimisticLock(1);//
        // req.setOrgId("这是文本128");//
        // req.setTenantId("这是文本128");//
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//

        PagingData<JobPostInfo> resp = jobPostService.query(req,null);

        log.debug("查询工作岗位->" + resp);

        Assert.isTrue(!resp.isEmpty(), "工作岗位");
    }

    @Test
    public void updateJobPostTest() {

         UpdateJobPostReq req = new UpdateJobPostReq();

         req.setId(id);


           // req.setCode("这是文本64");// 必填
           // req.setType(Type.Manager);// 必填
           // req.setDomain("这是文本128");//归属的子系统或应用 
           // req.setName("这是文本64");// 必填
           // req.setOptimisticLock(1);// 
           // req.setOrgId("这是文本128");// 
           // req.setTenantId("这是文本128");// 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 

          boolean ok = jobPostService.update(req);

          log.debug("更新工作岗位-> " + ok);

          Assert.isTrue(ok, "工作岗位");
    }

    @Test
    public void deleteJobPostTest() {

        JobPostIdReq req = new JobPostIdReq();

        req.setId(id);

        boolean ok = jobPostService.delete(req);

        log.debug("删除工作岗位->" + ok);

        Assert.isTrue(ok , "工作岗位");
    }
}
