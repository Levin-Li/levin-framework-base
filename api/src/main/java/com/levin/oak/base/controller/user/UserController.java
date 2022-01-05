package com.levin.oak.base.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.*;
import javax.validation.*;
import java.util.*;

import javax.servlet.http.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import javax.validation.constraints.*;

import com.levin.oak.base.controller.*;
import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.user.*;
import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-1-5 15:29:20

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
@ConditionalOnProperty(value = PLUGIN_PREFIX + "UserController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "user")
//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)
@Slf4j
@Valid
public class UserController extends BaseController{

    private static final String BIZ_NAME = E_User.BIZ_NAME;

    @Autowired
    UserService userService;

    /**
     * 分页查找
     *
     * @param req  QueryUserReq
     * @return  ApiResp<PagingData<UserInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    public ApiResp<PagingData<UserInfo>> query(QueryUserReq req , SimplePaging paging) {
        return ApiResp.ok(userService.query(req,paging));
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
        return ApiResp.ok(userService.batchCreate(reqList));
    }


    /**
    * 查看详情
    *
    * @param req QueryUserByIdReq
    */
    @GetMapping("/retrieve")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<UserInfo> retrieve(@NotNull QueryUserByIdReq req) {

         return ApiResp.ok(userService.findById(req));

         //return ApiResp.ok(userService.findById(id));
     }

    /**
    * 查看详情
    *
    * @param id Long
    */
    @GetMapping("/{id}")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<UserInfo> retrieve(@PathVariable @NotNull Long id) {

         return getSelfProxy(getClass()).retrieve(new QueryUserByIdReq().setId(id));

         //return ApiResp.ok(userService.findById(id));
     }


    /**
     * 更新
     * @param req UpdateUserReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
     public ApiResp<Void> update(@RequestBody UpdateUserReq req) {
         return userService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateUserReq> reqList) {
        return ApiResp.ok(userService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param id Long
     */
    @DeleteMapping({"/{id}"})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    public ApiResp<Integer> delete(@PathVariable @NotNull Long id) {
        return getSelfProxy(getClass()).batchDelete(new DeleteUserReq().setId(id));
    }

    /**
     * 批量删除
     * @param req DeleteUserReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    public ApiResp<Integer> batchDelete(@NotNull DeleteUserReq req) {

        //new DeleteUserReq().setIdList(idList)

        int n = userService.delete(req);

        return  n > 0 ? ApiResp.ok(n) : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }  

}
