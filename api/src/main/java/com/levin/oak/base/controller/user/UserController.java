package com.levin.oak.base.controller.user;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.biz.rbac.AuthService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-3-25 17:01:36

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
@RequestMapping(API_PATH + "user")

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

    @Resource
    AuthService authService;

    /**
     * 分页查找
     *
     * @param req QueryUserReq
     * @return ApiResp<PagingData < UserInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    public ApiResp<PagingData<UserInfo>> query(QueryUserReq req, SimplePaging paging) {

        PagingData<UserInfo> pagingData = userService.query(req, paging);

        //清楚密码
        if (pagingData.getItems() != null) {
            pagingData.getItems().forEach(userInfo -> userInfo.setPassword(null));
        }

        return ApiResp.ok(pagingData);
    }

    protected void checkSARole(List<String> roleList, String errorInfo) {

        //如果有超级角色，需要检查当前用户，是否是超管
        if (roleList != null
                && roleList.stream().map(StringUtils::trimAllWhitespace)
                .anyMatch(name -> RbacRoleObject.SA_ROLE.equalsIgnoreCase(name))) {

            if (errorInfo == null) {
                errorInfo = "当前用户未拥有超管角色";
            }

            Assert.isTrue(authService.getUserInfo().isSuperAdmin(), errorInfo);
        }
    }

    /**
     * 新增
     *
     * @param req CreateUserEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    public ApiResp<Long> create(@RequestBody CreateUserReq req) {
        checkSARole(req.getRoleList(), null);
        return ApiResp.ok(userService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateUserEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateUserReq> reqList) {

        reqList.forEach(req -> checkSARole(req.getRoleList(),null));

        return ApiResp.ok(userService.batchCreate(reqList));
    }


    /**
     * 查看详情
     *
     * @param req QueryUserByIdReq
     */
    @GetMapping("")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<UserInfo> retrieve(@NotNull UserIdReq req) {
        return ApiResp.ok(userService.findById(req).setPassword(null));
    }

    /**
     * 查看详情
     *
     * @param id Long
     */
    //@GetMapping("/{id}")
    //@Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    //public ApiResp<UserInfo> retrieve(@PathVariable @NotNull Long id) {

    //     return getSelfProxy(getClass()).retrieve(new UserIdReq().setId(id));

    // }


    /**
     * 更新
     *
     * @param req UpdateUserReq
     */
    @PutMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
    public ApiResp<Void> update(@RequestBody UpdateUserReq req) {
        checkSARole(req.getRoleList(), null);
        return userService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
    public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateUserReq> reqList) {
        reqList.forEach(req -> checkSARole(req.getRoleList(),null));
        return ApiResp.ok(userService.batchUpdate(reqList));
    }

    /**
     * 删除
     *
     * @param req UserIdReq
     */
    @DeleteMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    public ApiResp<Integer> delete(@NotNull UserIdReq req) {
        return ApiResp.ok(userService.delete(req));
    }

    // /**
    // * 删除
    // * @param id Long
    // */
    // @DeleteMapping({"/{id}"})
    // @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    // public ApiResp<Integer> delete(@PathVariable @NotNull Long id) {
    //
    //   List<Integer> ns = getSelfProxy(getClass())
    //       .batchDelete(new DeleteUserReq().setIdList(id))
    //       .getData();
    //
    //       return ns != null && !ns.isEmpty() ? ApiResp.ok(ns.get(0)) : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    //  }

    /**
     * 批量删除
     *
     * @param req DeleteUserReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    public ApiResp<List<Integer>> batchDelete(@NotNull DeleteUserReq req) {
        return ApiResp.ok(userService.batchDelete(req));
    }

}
