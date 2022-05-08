package com.levin.oak.base.controller.rbac;

import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.biz.rbac.info.ModuleInfo;
import com.levin.oak.base.biz.rbac.req.LoginReq;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.rbac.dto.LoginInfo;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.req.UpdateUserPwdReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;


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
@ResAuthorize(domain = ID, type = EntityConst.COMMON_TYPE_NAME, onlyRequireAuthenticated = true)
@MenuResTag(false)
public class RbacController extends BaseController {

    @Resource
    RoleService roleService;

    @Resource
    UserService userService;

    @Resource
    RbacService rbacService;

    @Resource
    AuthService authService;

    @Resource
    BizTenantService bizTenantService;


    /**
     * 登录
     *
     * @param req
     * @return ApiResp
     */
    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(tags = {"授权管理"}, summary = "用户登录")
    @ResAuthorize(ignored = true)
    public ApiResp<LoginInfo> login2(@RequestBody LoginReq req) {
        return login(req);
    }

    /**
     * 登录
     *
     * @param req
     * @return ApiResp
     */
    @PostMapping("login")
    @Operation(tags = {"授权管理"}, summary = "用户登录")
    @ResAuthorize(ignored = true)
    public ApiResp<LoginInfo> login(@NotNull LoginReq req) {
        String accessToken = authService.auth(req.getTenantId(), req.getAccount(), req.getPassword(), req.getUa());
        return ApiResp.ok(new LoginInfo()
                .setAccessToken(accessToken)
                .setUserInfo(authService.getUserInfo())
        );
    }

    @PostMapping("{tenantId}/login")
    @Operation(tags = {"授权管理"}, summary = "用户登录-租户登录入口")
    @ResAuthorize(ignored = true)
    public ApiResp<LoginInfo> loginByTenantId(String account, String password, @PathVariable String tenantId, @RequestHeader(value = "user-agent") String ua) {

        Assert.hasText(account, "帐号不能为空");
        Assert.hasText(password, "密码不能为空");
        Assert.hasText(tenantId, "租户不能为空");

        TenantInfo tenantInfo = bizTenantService.getTenantInfo(tenantId);

        Assert.notNull(tenantInfo, "租户不存在");

        return login(new LoginReq()
                .setAccount(account)
                .setPassword(password)
                .setUa(ua)
                .setTenantId(tenantId)
        );
    }

    /**
     * 登出
     *
     * @return ApiResp
     */
    @GetMapping("logout")
    @Operation(tags = {"授权管理"}, summary = "用户登出")
    public ApiResp<Void> logout() {
        authService.logout();
        return ApiResp.ok();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @PostMapping("userInfo")
    @Operation(tags = {"授权管理"}, summary = "用户信息")
    public ApiResp<RbacUserInfo<String>> getUserInfo() {
        return ApiResp.ok(authService.getUserInfo());
    }

    /**
     * 获取域名对应的租户信息
     */
    @GetMapping("tenantInfo")
    @Operation(tags = {"授权管理"}, summary = "获取租户信息")
    public ApiResp<TenantInfo> getTenantInfo() {
        return ApiResp.ok(bizTenantService.getTenantInfo(authService.getUserInfo().getTenantId()));
    }

    /**
     * 修改密码
     *
     * @param req UpdateUserReq
     */
    @PutMapping({"updatePwd"})
    @Operation(tags = {"授权管理"}, summary = "修改密码")
    public ApiResp<Void> updatePwd(@RequestBody UpdateUserPwdReq req) {
        return userService.update(req) > 0 ? ApiResp.ok() : ApiResp.error("修改密码失败");
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
    @Operation(tags = {"授权管理"}, summary = "获取授权的菜单列表")
    public ApiResp<List<MenuResInfo>> getAuthorizedMenuList(boolean isShowNotPermissionMenu) {
        return ApiResp.ok(rbacService.getAuthorizedMenuList(isShowNotPermissionMenu, authService.getLoginUserId()));
    }

}
