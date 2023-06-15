package com.levin.oak.base.controller.role;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.info.RoleInfo;
import com.levin.oak.base.services.role.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-4-2 19:44:58

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "RoleController")
//@RequestMapping(API_PATH + "role")
@RequestMapping(API_PATH + "Role")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "RoleController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class RoleController extends BaseController {

    private static final String BIZ_NAME = E_Role.BIZ_NAME;

    @Autowired
    RoleService roleService;

    @Autowired
    AuthService authService;

    @Autowired
    RbacService rbacService;

    /**
     * 分页查找
     *
     * @param req QueryRoleReq
     * @return ApiResp<PagingData < RoleInfo>>
     */
    @GetMapping("/query")
    @Operation( summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<RoleInfo>> query(QueryRoleReq req, SimplePaging paging) {

        PagingData<RoleInfo> pagingData = roleService.query(req, paging);

        //只过滤出当前用户完全拥有权限的角色
        if (pagingData.getItems() != null) {
            //@todo 只过滤出当前用户完全拥有权限的角色
            pagingData.setItems(
                    pagingData.getItems().stream()
                            .filter(roleInfo -> rbacService.canAssignRole(null, roleInfo.getCode(), null))
                            .collect(Collectors.toList())
            );

        }

        return ApiResp.ok(pagingData);
    }

    /**
     * 新增
     *
     * @param req CreateRoleEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation( summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<String> create(@RequestBody CreateRoleReq req) {
        checkCurrentUserCreateOrUpdateRolePermissions(req.getCode(), req.getPermissionList());
        return ApiResp.ok(roleService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateRoleEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation( summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateRoleReq> reqList) {
        reqList.stream().forEach(req -> checkCurrentUserCreateOrUpdateRolePermissions(req.getCode(), req.getPermissionList()));
        return ApiResp.ok(roleService.batchCreate(reqList));
    }

    /**
     * 查看详情
     *
     * @param req QueryRoleByIdReq
     */
    @GetMapping("")
    @Operation( summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<RoleInfo> retrieve(@NotNull RoleIdReq req) {
        return ApiResp.ok(roleService.findById(req));
    }

    /**
     * 更新
     *
     * @param req UpdateRoleReq
     */
    @PutMapping({""})
    @Operation( summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> update(@RequestBody UpdateRoleReq req) {
        checkCurrentUserCreateOrUpdateRolePermissions(null, req.getPermissionList());
        return ApiResp.ok(checkResult(roleService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation( summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateRoleReq> reqList) {
        reqList.stream().forEach(req -> checkCurrentUserCreateOrUpdateRolePermissions(null, req.getPermissionList()));
        return ApiResp.ok(checkResult(roleService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req RoleIdReq
     */
    @DeleteMapping({"", "{id}"})
    @Operation( summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(RoleIdReq req, @PathVariable(required = false) String id) {
        return ApiResp.ok(checkResult(roleService.delete(req), DELETE_ACTION));
    }

    /**
     * 批量删除
     *
     * @param req DeleteRoleReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation( summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteRoleReq req) {
        return ApiResp.ok(checkResult(roleService.batchDelete(req), BATCH_DELETE_ACTION));
    }

    /**
     * 检查当前用户是否有权限使用指定的权限
     *
     * @param permissionList
     */
    protected void checkCurrentUserCreateOrUpdateRolePermissions(String roleCode, List<String> permissionList) {

        Assert.hasText(roleCode, "角色编码不能为空");

        Assert.isTrue(roleCode.startsWith("R_"), "角色编码必须以 R_ 开头");
        Assert.isTrue(!roleCode.equals(RbacRoleObject.SA_ROLE), "角色编码 R_SA 不可使用");

        if (StringUtils.hasText(roleCode)) {
            //@todo 检查角色编码是否已经存在
        }

        if (permissionList == null || permissionList.isEmpty()) {
            return;
        }

//        Object loginUserId = authService.getLoginUserId();

        boolean isAuthorized = rbacService.isAuthorized(true, permissionList, (rp, info) -> {
            throw new AuthorizationException("role-" + roleCode, "未授权的资源：" + rp);
        });

        if (!isAuthorized) {
            throw new AuthorizationException("role-" + roleCode, "角色非法使用未授权的资源");
        }
    }

    /**
     * 检查结果
     *
     * @param n
     * @param action
     * @return
     */
    protected int checkResult(int n, String action) {
        Assert.isTrue(n > 0, action + BIZ_NAME + "失败");
        return n;
    }
}
