package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.entities.User;

import com.levin.oak.base.services.user.*;
import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;


////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.User.*;
import java.util.Date;
import java.util.List;
import com.levin.oak.base.services.org.info.*;
import com.levin.oak.base.entities.Org;
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
 *  @author auto gen by simple-dao-codegen 2021-12-18 11:15:49
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

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createUserTest() {

        CreateUserReq req = new CreateUserReq();

            // req.setLoginName("这是文本64");//登录名 

            // req.setPassword("这是文本256");//登录密码 

            // req.setTelephone("这是文本20");//手机号 

            // req.setEmail("这是文本32");//邮箱 

            // req.setName("这是文本64");//名称 

            // req.setNickname("这是文本32");//昵称 

            // req.setAvatar("头像_1");//头像 

            // req.setSex(Sex.Man);//性别 

            // req.setTags("这是文本1800");//帐号标签 

            // req.setCategory(Category.Staff);//帐号类型 

            // req.setExpiredDate(new Date());//过期时间 

            // req.setState(State.Normal);//帐号状态 必填

            // req.setStaffNo("这是文本32");//工号 

            // req.setJobPostCode("这是文本128");//岗位职级 

            // req.setRoles("这是文本1800");//角色列表 

            // req.setWxOpenId("这是文本128");//微信 OpendId 

            // req.setAliOpenId("这是文本128");//阿里 OpendId 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = userService.create(req);

        log.debug("新增用户->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryUserTest() {

        QueryUserReq req = new QueryUserReq();

        // req.setId(null);//id
        // req.setLoginName("这是文本64");//登录名
        // req.setPassword("这是文本256");//登录密码
        // req.setTelephone("这是文本20");//手机号
        // req.setEmail("这是文本32");//邮箱
        // req.setName("这是文本64");//名称
        // req.setNickname("这是文本32");//昵称
        // req.setAvatar("头像_1");//头像
        // req.setSex(Sex.Man);//性别
        // req.setTags("这是文本1800");//帐号标签
        // req.setCategory(Category.Staff);//帐号类型
        // req.setGteExpiredDate(DateUtils.getZoneHour(new Date()));//最小过期时间
        // req.setLteExpiredDate(DateUtils.getEndHour(new Date()));//最大过期时间
        // req.setState(State.Normal);//帐号状态
        // req.setStaffNo("这是文本32");//工号
        // req.setJobPostCode("这是文本128");//岗位职级
        // req.setRoles("这是文本1800");//角色列表
        // req.setRoleList(null);//角色列表
        // req.setLoadOrg(true);//加载所属部门
        // req.setWxOpenId("这是文本128");//微信 OpendId
        // req.setAliOpenId("这是文本128");//阿里 OpendId
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


           // req.setLoginName("这是文本64");//登录名 
           // req.setPassword("这是文本256");//登录密码 
           // req.setTelephone("这是文本20");//手机号 
           // req.setEmail("这是文本32");//邮箱 
           // req.setName("这是文本64");//名称 
           // req.setNickname("这是文本32");//昵称 
           // req.setAvatar("头像_1");//头像 
           // req.setSex(Sex.Man);//性别 
           // req.setTags("这是文本1800");//帐号标签 
           // req.setCategory(Category.Staff);//帐号类型 
           // req.setExpiredDate(new Date());//过期时间 
           // req.setState(State.Normal);//帐号状态 必填
           // req.setStaffNo("这是文本32");//工号 
           // req.setJobPostCode("这是文本128");//岗位职级 
           // req.setRoles("这是文本1800");//角色列表 
           // req.setWxOpenId("这是文本128");//微信 OpendId 
           // req.setAliOpenId("这是文本128");//阿里 OpendId 
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

        DeleteUserReq req = new DeleteUserReq();

        req.setId(id);

        int n = userService.delete(req);

        log.debug("删除用户->" + n);

        Assert.assertTrue(n > 0);
    }
}
