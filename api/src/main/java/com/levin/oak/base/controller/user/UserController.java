package com.levin.oak.base.controller.user;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.AuthorizationException;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.ModuleOption.API_PATH;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-4-2 20:07:02

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "UserController")
//@RequestMapping(API_PATH + "user")
@RequestMapping(API_PATH + "User")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "UserController", matchIfMissing = true)

//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class UserController extends BaseController {

    private static final String BIZ_NAME = E_User.BIZ_NAME;

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @Autowired
    RbacService rbacService;

    @Autowired
    BizRoleService bizRoleService;

    @Autowired
    FrameworkProperties frameworkProperties;

    /**
     * 信息脱敏
     *
     * @param info
     * @return
     */
    UserInfo desensitize(UserInfo info) {

        if (info != null) {
            info.setPassword(null);
        }

        return info;
    }

    /**
     * 检查用户角色
     * <p>
     * 保证当前用户分配的给其它用户的角色必须是自己已经拥有的角色
     *
     * @param targetUserId
     * @param roleList
     */
    protected void checkCurrentUserCreateOrUpdateUserRole(String targetUserId, List<String> roleList) {

        boolean ok = rbacService.canAssignRole(targetUserId, roleList, (roleCode, info) -> {
            throw new AuthorizationException("role-" + roleCode, "分配角色(" + roleCode + ")失败，请检查是否拥有角色所需的权限," + info);
        });

        if (!ok) {
            throw new AuthorizationException("role", "分配角色失败，请检查是否拥有角色所需的权限");
        }
    }

    /**
     * 分页查找
     *
     * @param req QueryUserReq
     * @return ApiResp<PagingData < UserInfo>>
     */
    @GetMapping("/query")
    @Operation( summary = QUERY_ACTION)
    public ApiResp<PagingData<UserInfo>> query(QueryUserReq req, SimplePaging paging) {

        PagingData<UserInfo> pagingData = userService.query(req, paging);

        //清楚密码
        if (pagingData.getItems() != null) {
            pagingData.getItems().forEach(this::desensitize);
        }

        return ApiResp.ok(pagingData);
    }


    /**
     * 新增
     *
     * @param req CreateUserEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation( summary = CREATE_ACTION)
    public ApiResp<String> create(@RequestBody CreateUserReq req) {
        checkCurrentUserCreateOrUpdateUserRole(null, req.getRoleList());
        return ApiResp.ok(userService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateUserEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation( summary = BATCH_CREATE_ACTION)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateUserReq> reqList) {
        reqList.forEach(req -> checkCurrentUserCreateOrUpdateUserRole(null, req.getRoleList()));
        return ApiResp.ok(userService.batchCreate(reqList));
    }


    /**
     * 查看详情
     *
     * @param req QueryUserByIdReq
     */
    @GetMapping("")
    @Operation( summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<UserInfo> retrieve(@NotNull UserIdReq req) {
        return ApiResp.ok(desensitize(userService.findById(req)));
    }

    /**
     * 更新
     *
     * @param req UpdateUserReq
     */
    @PutMapping({""})
    @Operation( summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> update(@RequestBody UpdateUserReq req) {
        checkCurrentUserCreateOrUpdateUserRole(req.getId(), req.getRoleList());
        return ApiResp.ok(checkResult(userService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation( summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateUserReq> reqList) {
        reqList.forEach(req -> checkCurrentUserCreateOrUpdateUserRole(req.getId(), req.getRoleList()));
        return ApiResp.ok(checkResult(userService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req UserIdReq
     */
    @DeleteMapping({""})
    @Operation( summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(@NotNull UserIdReq req) {
        return ApiResp.ok(checkResult(userService.delete(req), DELETE_ACTION));
    }

    /**
     * 批量删除
     *
     * @param req DeleteUserReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation( summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteUserReq req) {
        return ApiResp.ok(checkResult(userService.batchDelete(req), BATCH_DELETE_ACTION));
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
