package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.User;

import com.levin.oak.base.services.user.*;
import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.oak.base.entities.User.*;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import java.util.Date;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
import java.util.List;
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
 *  用户测试
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
public class UserServiceTest {


    @Resource
    private UserService userService;

    private String id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createUserTest() {

        CreateUserReq req = new CreateUserReq();

            // req.setTenantId("这是文本64");//租户ID 

            // req.setTelephone("这是文本20");//手机号-可做为登录帐号 

            // req.setEmail("这是文本32");//邮箱-可做为登录帐号 

            // req.setPassword("这是文本256");//登录密码 

            // req.setNickname("这是文本32");//昵称 

            // req.setAvatar("头像_1");//头像 

            // req.setSex(Sex.Man);//性别 

            // req.setTagList("这是文本1800");//标签列表（json数组） 

            // req.setCategory(Category.Staff);//帐号类型 

            // req.setExpiredDate(new Date());//过期时间 

            // req.setState(State.Normal);//帐号状态 必填

            // req.setStaffNo("这是文本32");//工号 

            // req.setJobPostCode("这是文本128");//岗位职级 

            // req.setRoleList("这是文本1800");//角色列表(Json数组) 

            // req.setWxOpenId("这是文本64");//微信 OpendId 

            // req.setAliOpenId("这是文本64");//阿里 OpendId 

            // req.setOrgId("这是文本128");//机构ID 

            // req.setTenantId("这是文本128");//租户ID 

            // req.setDomain("这是文本128");//系统域 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       String id  = userService.create(req);

        log.debug("新增用户->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryUserTest() {

        QueryUserReq req = new QueryUserReq();

        // req.setId(null);//id
        // req.setTenantId("这是文本64");//租户ID
        // req.setTelephone("这是文本20");//手机号-可做为登录帐号
        // req.setEmail("这是文本32");//邮箱-可做为登录帐号
        // req.setPassword("这是文本256");//登录密码
        // req.setNickname("这是文本32");//昵称
        // req.setAvatar("头像_1");//头像
        // req.setSex(Sex.Man);//性别
        // req.setTagList("这是文本1800");//标签列表（json数组）
        // req.setCategory(Category.Staff);//帐号类型
        // req.setGteExpiredDate(DateUtils.getZoneHour(new Date()));//最小过期时间
        // req.setLteExpiredDate(DateUtils.getEndHour(new Date()));//最大过期时间
        // req.setState(State.Normal);//帐号状态
        // req.setStaffNo("这是文本32");//工号
        // req.setJobPostCode("这是文本128");//岗位职级
        // req.setRoleList("这是文本1800");//角色列表(Json数组)
        // req.setLoadOrg(true);//加载所属部门
        // req.setWxOpenId("这是文本64");//微信 OpendId
        // req.setAliOpenId("这是文本64");//阿里 OpendId
        // req.setOrgId("这是文本128");//机构ID
        // req.setTenantId("这是文本128");//租户ID
        // req.setDomain("这是文本128");//系统域
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<UserInfo> resp = userService.query(req,null);

        log.debug("查询用户->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateUserTest() {

         UpdateUserReq req = new UpdateUserReq();

         req.setId(id);


           // req.setTenantId("这是文本64");//租户ID 
           // req.setTelephone("这是文本20");//手机号-可做为登录帐号 
           // req.setEmail("这是文本32");//邮箱-可做为登录帐号 
           // req.setPassword("这是文本256");//登录密码 
           // req.setNickname("这是文本32");//昵称 
           // req.setAvatar("头像_1");//头像 
           // req.setSex(Sex.Man);//性别 
           // req.setTagList("这是文本1800");//标签列表（json数组） 
           // req.setCategory(Category.Staff);//帐号类型 
           // req.setExpiredDate(new Date());//过期时间 
           // req.setState(State.Normal);//帐号状态 必填
           // req.setStaffNo("这是文本32");//工号 
           // req.setJobPostCode("这是文本128");//岗位职级 
           // req.setRoleList("这是文本1800");//角色列表(Json数组) 
           // req.setWxOpenId("这是文本64");//微信 OpendId 
           // req.setAliOpenId("这是文本64");//阿里 OpendId 
           // req.setOrgId("这是文本128");//机构ID 
           // req.setTenantId("这是文本128");//租户ID 
           // req.setDomain("这是文本128");//系统域 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = userService.update(req);

          log.debug("更新用户-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteUserTest() {

        UserIdReq req = new UserIdReq();

        req.setId(id);

        int n = userService.delete(req);

        log.debug("删除用户->" + n);

        Assert.assertTrue(n > 0);
    }
}
