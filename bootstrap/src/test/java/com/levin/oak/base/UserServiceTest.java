package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.User;

import com.levin.oak.base.biz.*;
import com.levin.oak.base.services.user.*;
import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.User.*;
import java.util.Date;
import com.levin.commons.dao.domain.OrganizedObject;
import java.io.Serializable;
import com.levin.commons.dao.domain.StatefulObject;
import com.levin.commons.service.support.InjectConst;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.oak.base.entities.Org;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
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
 * 用户测试
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月2日 下午4:32:07, 代码生成哈希校验码：[332fc100247edf871f01de3560a235e4]，请不要修改和删除此行内容。
 *
 */

//@ActiveProfiles("test")
//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Transactional(rollbackFor = {Throwable.class})
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BizUserService bizUserService;

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
    public void createUserTest() {

        CreateUserReq req = new CreateUserReq();

            // req.setOrgId("这是文本128");// 

            // req.setTelephone("这是文本20");//可做为登录帐号 

            // req.setEmail("这是文本32");//可做为登录帐号 

            // req.setPassword("这是文本256");// 

            // req.setNickname("这是文本32");// 

            // req.setAvatar("头像_1");// 

            // req.setSex(Sex.Man);// 

            // req.setTagList("这是文本1800");// 

            // req.setCategory(Category.Staff);// 

            // req.setLoginFailedCount(1);// 

            // req.setLockExpiredTime(new Date());// 

            // req.setExpiredDate(new Date());// 

            // req.setState(State.Normal);// 必填

            // req.setStaffNo("这是文本32");// 

            // req.setJobPostCode("这是文本128");// 

            // req.setRoleList("这是文本1800");// 

            // req.setMfaSecretKey("这是文本64");// 

            // req.setWxOpenId("这是文本64");// 

            // req.setAliOpenId("这是文本64");// 

            // req.setTenantId("这是文本128");// 

            // req.setDomain("这是文本128");// 

            // req.setName("这是文本128");// 必填

            // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 

            // req.setOrderCode(1);// 

            // req.setEnable(true);// 必填

            // req.setEditable(true);// 必填

            // req.setRemark("这是文本512");// 

            // req.setOptimisticLock(1);// 


       String id  = userService.create(req);

        log.debug("新增用户->" + id);

        Assert.isTrue(id != null, "用户");

    }


    @Test
    public void queryUserTest() {

        QueryUserReq req = new QueryUserReq();

        // req.setId(null);//
        // req.setOrgId("这是文本128");//
        // req.setTelephone("这是文本20");//可做为登录帐号
        // req.setEmail("这是文本32");//可做为登录帐号
        // req.setPassword("这是文本256");//
        // req.setNickname("这是文本32");//
        // req.setAvatar("头像_1");//
        // req.setSex(Sex.Man);//
        // req.setTagList("这是文本1800");//
        // req.setCategory(Category.Staff);//
        // req.setLoginFailedCount(1);//
        // req.setGteLockExpiredTime(DateUtils.getZoneHour(new Date()));//最小
        // req.setLteLockExpiredTime(DateUtils.getEndHour(new Date()));//最大
        // req.setGteExpiredDate(DateUtils.getZoneHour(new Date()));//最小
        // req.setLteExpiredDate(DateUtils.getEndHour(new Date()));//最大
        // req.setState(State.Normal);//
        // req.setStaffNo("这是文本32");//
        // req.setJobPostCode("这是文本128");//
        // req.setRoleList("这是文本1800");//
        // req.setLoadOrg(true);//加载
        // req.setMfaSecretKey("这是文本64");//
        // req.setWxOpenId("这是文本64");//
        // req.setAliOpenId("这是文本64");//
        // req.setTenantId("这是文本128");//
        // req.setDomain("这是文本128");//
        // req.setName("这是文本128");//
        // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开
        // req.setCreator("这是文本128");//
        // req.setOrderCode(1);//
        // req.setEnable(true);//
        // req.setEditable(true);//
        // req.setRemark("这是文本512");//
        // req.setOptimisticLock(1);//

        PagingData<UserInfo> resp = userService.query(req,null);

        log.debug("查询用户->" + resp);

        Assert.isTrue(!resp.isEmpty(), "用户");
    }

    @Test
    public void updateUserTest() {

         UpdateUserReq req = new UpdateUserReq();

         req.setId(id);


           // req.setOrgId("这是文本128");// 
           // req.setTelephone("这是文本20");//可做为登录帐号 
           // req.setEmail("这是文本32");//可做为登录帐号 
           // req.setPassword("这是文本256");// 
           // req.setNickname("这是文本32");// 
           // req.setAvatar("头像_1");// 
           // req.setSex(Sex.Man);// 
           // req.setTagList("这是文本1800");// 
           // req.setCategory(Category.Staff);// 
           // req.setLoginFailedCount(1);// 
           // req.setLockExpiredTime(new Date());// 
           // req.setExpiredDate(new Date());// 
           // req.setState(State.Normal);// 必填
           // req.setStaffNo("这是文本32");// 
           // req.setJobPostCode("这是文本128");// 
           // req.setRoleList("这是文本1800");// 
           // req.setMfaSecretKey("这是文本64");// 
           // req.setWxOpenId("这是文本64");// 
           // req.setAliOpenId("这是文本64");// 
           // req.setTenantId("这是文本128");// 
           // req.setDomain("这是文本128");// 
           // req.setName("这是文本128");// 必填
           // req.setPinyinName("这是文本128");//简拼或全拼，逗号隔开 
           // req.setOrderCode(1);// 
           // req.setEnable(true);// 必填
           // req.setEditable(true);// 必填
           // req.setRemark("这是文本512");// 
           // req.setOptimisticLock(1);// 

          boolean ok = userService.update(req);

          log.debug("更新用户-> " + ok);

          Assert.isTrue(ok, "用户");
    }

    @Test
    public void deleteUserTest() {

        UserIdReq req = new UserIdReq();

        req.setId(id);

        boolean ok = userService.delete(req);

        log.debug("删除用户->" + ok);

        Assert.isTrue(ok , "用户");
    }
}
