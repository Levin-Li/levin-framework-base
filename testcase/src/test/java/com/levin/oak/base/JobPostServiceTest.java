package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.JobPost;

import com.levin.oak.base.services.jobpost.*;
import com.levin.oak.base.services.jobpost.req.*;
import com.levin.oak.base.services.jobpost.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.JobPost.*;
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



import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  工作岗位测试
 *
 *  @author auto gen by simple-dao-codegen 2021-12-18 11:15:49
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

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createJobPostTest() {

        CreateJobPostReq req = new CreateJobPostReq();

            // req.setCode("这是文本64");//编码 必填

            // req.setType(Type.Manager);//类型 必填

            // req.setDomain("这是文本64");//系统子域 

            // req.setName("这是文本512");//名称 必填

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = jobPostService.create(req);

        log.debug("新增工作岗位->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryJobPostTest() {

        QueryJobPostReq req = new QueryJobPostReq();

        // req.setId(null);//id
        // req.setCode("这是文本64");//编码
        // req.setType(Type.Manager);//类型
        // req.setDomain("这是文本64");//系统子域
        // req.setName("这是文本512");//名称
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<JobPostInfo> resp = jobPostService.query(req,null);

        log.debug("查询工作岗位->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateJobPostTest() {

         UpdateJobPostReq req = new UpdateJobPostReq();

         req.setId(id);


           // req.setCode("这是文本64");//编码 必填
           // req.setType(Type.Manager);//类型 必填
           // req.setDomain("这是文本64");//系统子域 
           // req.setName("这是文本512");//名称 必填
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = jobPostService.update(req);

          log.debug("更新工作岗位-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteJobPostTest() {

        DeleteJobPostReq req = new DeleteJobPostReq();

        req.setId(id);

        int n = jobPostService.delete(req);

        log.debug("删除工作岗位->" + n);

        Assert.assertTrue(n > 0);
    }
}
