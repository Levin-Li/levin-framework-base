package com.levin.oak.base.controller.rbac;

import cn.dev33.satoken.stp.StpUtil;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.rbac.RbacService;
import com.levin.oak.base.services.rbac.info.ModuleInfo;
import com.levin.oak.base.services.rbac.req.LoginReq;
import com.levin.oak.base.services.role.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

import static com.levin.oak.base.ModuleOption.API_PATH;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;


// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "AuthController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "AuthController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "rbac")
@Tag(name = "权限认证", description = "权限管理")
@Slf4j
@Valid
public class RbacController {

    //请求级别变量
    @Autowired
    HttpServletResponse httpResponse;

    //请求级别变量
    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    RoleService roleService;

    @Autowired
    RbacService rbacService;

    /**
     * 登录
     *
     * @param req
     * @return ApiResp
     */
    @PostMapping("login")
    @Operation(tags = {"权限认证"}, summary = "用户登录")
    public ApiResp<String> login(LoginReq req, @RequestHeader(value = "user-agent", required = false) String ua) {
        return ApiResp.ok(rbacService.login(req.setUa(ua)));
    }

    /**
     * 登出
     *
     * @return ApiResp
     */
    @PostMapping("logout")
    @Operation(tags = {"权限认证"}, summary = "用户登出")
    public ApiResp logout() {
        rbacService.logout();
        return ApiResp.ok();
    }

    /**
     * 获取可分配的权限资源
     *
     * @return ApiResp
     */
    @GetMapping("authorizedResList")
    @Operation(tags = {"权限认证"}, summary = "获取可分配的权限资源")
    public List<ModuleInfo> getAuthorizedResList() {
        return rbacService.getAuthorizedResList(StpUtil.getLoginId());
    }


    /**
     * 获取菜单列表
     *
     * @return ApiResp
     */
    @GetMapping("authorizedMenuList")
    @Operation(tags = {"权限认证"}, summary = "获取菜单列表")
    public List<MenuResInfo> getAuthorizedMenuList() {
        return rbacService.getAuthorizedMenuList(StpUtil.getLoginId());
    }


}
