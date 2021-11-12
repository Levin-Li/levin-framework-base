package com.levin.oak.base.controller.role;

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
import com.levin.oak.base.services.role.*;
import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2021-11-12 9:56:30

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
@ConditionalOnProperty(value = PLUGIN_PREFIX + "RoleController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "role")
//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)
@Slf4j
@Valid
public class RoleController extends BaseController{

    private static final String BIZ_NAME = E_Role.BIZ_NAME;

    @Autowired
    RoleService roleService;

    /**
     * 分页查找
     *
     * @param req  QueryRoleReq
     * @return  ApiResp<PagingData<RoleInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION + BIZ_NAME)
    public ApiResp<PagingData<RoleInfo>> query(QueryRoleReq req , SimplePaging paging) {
        return ApiResp.ok(roleService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateRoleEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION + BIZ_NAME)
    public ApiResp<Long> create(@RequestBody CreateRoleReq req) {
        return ApiResp.ok(roleService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateRoleEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION + BIZ_NAME)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateRoleReq> reqList) {
        return ApiResp.ok(roleService.batchCreate(reqList));
    }

    /**
    * 查看详情
    *
    * @param id Long
    */
    @GetMapping("/{id}")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION + BIZ_NAME)
    public ApiResp<RoleInfo> retrieve(@PathVariable @NotNull Long id) {
         return ApiResp.ok(roleService.findById(id));
     }

    /**
     * 更新
     * @param req UpdateRoleReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION + BIZ_NAME)
     public ApiResp<Void> update(@RequestBody UpdateRoleReq req) {
         return roleService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION + BIZ_NAME)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateRoleReq> reqList) {
        return ApiResp.ok(roleService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param id Long
     */
    @DeleteMapping({"/{id}"})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION + BIZ_NAME)
    public ApiResp<Void> delete(@PathVariable @NotNull Long id) {
        return roleService.delete(new DeleteRoleReq().setId(id)) > 0
                                                ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量删除
     * @param req DeleteRoleReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION + BIZ_NAME)
    public ApiResp<Void> batchDelete(@NotNull DeleteRoleReq req) {
        //new DeleteRoleReq().setIdList(idList)
        return roleService.delete(req) > 0 ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }  

}
