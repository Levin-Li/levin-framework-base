package com.levin.oak.base.controller.rbac;

import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.rbac.AuthService;
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

import javax.validation.Valid;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.TYPE_NAME;


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
@ConditionalOnProperty(value = PLUGIN_PREFIX + "AuthController", matchIfMissing = true)
@RequestMapping(API_PATH + "rbac")
@Tag(name = "授权管理", description = "授权管理")
@Slf4j
@Valid
@ResAuthorize(domain = ID, type = TYPE_NAME, onlyRequireAuthenticated = true)
public class RbacController extends BaseController {

    @Autowired
    RoleService roleService;

    @Autowired
    RbacService rbacService;

    @Autowired
    AuthService authService;

    /**
     * 登录
     *
     * @param req
     * @return ApiResp
     */
    @PostMapping("login")
    @Operation(tags = {"授权管理"}, summary = "用户登录")
    @ResAuthorize(domain = ID, type = TYPE_NAME, ignored = true)
    public ApiResp<String> login(LoginReq req, @RequestHeader(value = "user-agent", required = false) String ua) {
        return ApiResp.ok(authService.auth(req.getTenantId(), req.getAccount(), req.getPassword(), ua));
    }


    @PostMapping("userInfo")
    @Operation(tags = {"授权管理"}, summary = "用户信息")
    public ApiResp<RbacUserInfo<String>> getUserInfo() {
        return ApiResp.ok(authService.getUserInfo());
    }

    /**
     * 登出
     *
     * @return ApiResp
     */
    @GetMapping("logout")
    @Operation(tags = {"授权管理"}, summary = "用户登出")
    public ApiResp logout() {
        authService.logout();
        return ApiResp.ok();
    }

    /**
     * 获取可分配的权限资源
     *
     * @return ApiResp
     */
    @GetMapping("authorizedResList")
    @Operation(tags = {"授权管理"}, summary = "获取可分配的权限资源")
    public ApiResp<List<ModuleInfo>> getAuthorizedResList() {
        return ApiResp.ok(rbacService.getAuthorizedResList(authService.getLoginUserId()));
    }

    /**
     * 获取菜单列表
     *
     * @return ApiResp
     */
    @GetMapping("authorizedMenuList")
    @Operation(tags = {"授权管理"}, summary = "获取菜单列表")
    public ApiResp<List<MenuResInfo>> getAuthorizedMenuList(boolean isShowNotPermissionMenu) {
        return ApiResp.ok(rbacService.getAuthorizedMenuList(isShowNotPermissionMenu, authService.getLoginUserId()));
    }

}
