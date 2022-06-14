package com.levin.oak.base;

import com.levin.commons.dao.support.PagingData;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.menures.req.CreateMenuResReq;
import com.levin.oak.base.services.menures.req.MenuResIdReq;
import com.levin.oak.base.services.menures.req.QueryMenuResReq;
import com.levin.oak.base.services.menures.req.UpdateMenuResReq;
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
 *  菜单测试
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
public class MenuResServiceTest {


    @Resource
    private MenuResService menuResService;

    private Long id;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void createMenuResTest() {

        CreateMenuResReq req = new CreateMenuResReq();

            // req.setTenantId("租户ID_1");//租户ID 

            // req.setDomain("这是文本128");//子系统 

            // req.setRequireAuthorizations("这是文本1800");//需要的授权，权限或角色，json数组 

            // req.setAlwaysShow(true);//无权限时是否展示 必填

            // req.setTarget("这是文本64");//目标 

            // req.setActionType(ActionType.Default);//打开方式 

            // req.setIcon("图标_1");//图标 

            // req.setPath("路径/链接_1");//路径/链接 

            // req.setParams("这是文本1800");//参数 

            // req.setParentId(null);//父ID 

            // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 

            // req.setName("这是文本128");//名称 必填

            // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 

            // req.setOrderCode(1);//排序代码 

            // req.setEnable(true);//是否允许 必填

            // req.setEditable(true);//是否可编辑 必填

            // req.setRemark("这是文本512");//备注 


       Long id  = menuResService.create(req);

        log.debug("新增菜单->" + id);

        Assert.assertTrue(id != null);

    }


    @Test
    public void queryMenuResTest() {

        QueryMenuResReq req = new QueryMenuResReq();

        // req.setId(null);//id
        // req.setTenantId("租户ID_1");//租户ID
        // req.setDomain("这是文本128");//子系统
        // req.setRequireAuthorizations("这是文本1800");//需要的授权，权限或角色，json数组
        // req.setAlwaysShow(true);//无权限时是否展示
        // req.setTarget("这是文本64");//目标
        // req.setActionType(ActionType.Default);//打开方式
        // req.setIcon("图标_1");//图标
        // req.setPath("路径/链接_1");//路径/链接
        // req.setParams("这是文本1800");//参数
        // req.setParentId(null);//父ID
        // req.setLoadParent(true);//加载父对象
        // req.setLoadChildren(true);//加载子节点
        // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15|
        // req.setName("这是文本128");//名称
        // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼]
        // req.setCreator("这是文本128");//创建者
        // req.setOrderCode(1);//排序代码
        // req.setEnable(true);//是否允许
        // req.setEditable(true);//是否可编辑
        // req.setRemark("这是文本512");//备注

        PagingData<MenuResInfo> resp = menuResService.query(req,null);

        log.debug("查询菜单->" + resp);

        Assert.assertTrue(!resp.isEmpty());
    }

    @Test
    public void updateMenuResTest() {

         UpdateMenuResReq req = new UpdateMenuResReq();

         req.setId(id);


           // req.setTenantId("租户ID_1");//租户ID 
           // req.setDomain("这是文本128");//子系统 
           // req.setRequireAuthorizations("这是文本1800");//需要的授权，权限或角色，json数组 
           // req.setAlwaysShow(true);//无权限时是否展示 必填
           // req.setTarget("这是文本64");//目标 
           // req.setActionType(ActionType.Default);//打开方式 
           // req.setIcon("图标_1");//图标 
           // req.setPath("路径/链接_1");//路径/链接 
           // req.setParams("这是文本1800");//参数 
           // req.setParentId(null);//父ID 
           // req.setIdPath("这是文本1800");//id路径， 使用|包围，如|1|3|15| 
           // req.setName("这是文本128");//名称 必填
           // req.setPinyinName("这是文本128");//拼音，格式Json数组：[全拼,简拼] 
           // req.setOrderCode(1);//排序代码 
           // req.setEnable(true);//是否允许 必填
           // req.setEditable(true);//是否可编辑 必填
           // req.setRemark("这是文本512");//备注 

          int resp = menuResService.update(req);

          log.debug("更新菜单-> " + resp);

          Assert.assertTrue(resp > 0);
    }

    @Test
    public void deleteMenuResTest() {

        MenuResIdReq req = new MenuResIdReq();

        req.setId(id);

        int n = menuResService.delete(req);

        log.debug("删除菜单->" + n);

        Assert.assertTrue(n > 0);
    }
}
