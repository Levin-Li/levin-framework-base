package com.levin.oak.base.controller.user;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.biz.BizUserService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.services.user.info.UserInfo;
import com.levin.oak.base.services.user.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;
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
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class UserController extends BaseController {

    private static final String BIZ_NAME = E_User.BIZ_NAME;

//    @DubboReference
    @Autowired
    BizUserService bizUserService;

    @Autowired
    AuthService authService;

    @Autowired
    RbacService rbacService;

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

        boolean ok = rbacService.canAssignRole(authService.getLoginId(), targetUserId, roleList, (roleCode, info) -> {
            throw new AuthorizationException("分配角色(" + roleCode + ")失败，请检查是否拥有角色所需的权限, " + info);
        });

        if (!ok) {
            throw new AuthorizationException("分配角色失败，请检查是否拥有角色所需的权限");
        }
    }

    /**
     * 分页查找
     *
     * @param req QueryUserReq
     * @return ApiResp<PagingData < UserInfo>>
     */
    @GetMapping({"/list"})
    @Operation(summary = QUERY_LIST_ACTION)
    public ApiResp<PagingData<UserInfo>> query(QueryUserReq req, SimplePaging paging) {

        PagingData<UserInfo> pagingData = bizUserService.query(req, paging);

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
    @Operation(summary = CREATE_ACTION)
    public ApiResp<String> create(@RequestBody CreateUserReq req) {
        checkCurrentUserCreateOrUpdateUserRole(null, req.getRoleList());
        return ApiResp.ok(bizUserService.create(req));
    }


    /**
     * 查看详情
     *
     * @param req QueryUserByIdReq
     */
    @GetMapping("")
    @Operation(summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<UserInfo> retrieve(@NotNull UserIdReq req) {
        return ApiResp.ok(desensitize(bizUserService.findById(req)));
    }

    /**
     * 更新
     *
     * @param req UpdateUserReq
     */
    @PutMapping({""})
    @Operation(summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Boolean> update(@RequestBody UpdateUserReq req) {
        checkCurrentUserCreateOrUpdateUserRole(req.getId(), req.getRoleList());
        return ApiResp.ok(assertTrue(bizUserService.update(req), UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req UserIdReq
     */
    @DeleteMapping({""})
    @Operation(summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Boolean> delete(@NotNull UserIdReq req) {
        return ApiResp.ok(assertTrue(bizUserService.delete(req), DELETE_ACTION));
    }

}
