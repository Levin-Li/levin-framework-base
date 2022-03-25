package com.levin.oak.base.controller.tenant;

import com.levin.commons.rbac.ResAuthorize;
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
import com.levin.oak.base.services.tenant.*;
import com.levin.oak.base.services.tenant.req.*;
import com.levin.oak.base.services.tenant.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-3-25 18:38:46

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "TenantController")
@RequestMapping(API_PATH + "tenant")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "TenantController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = TYPE_NAME, isAndMode = true, anyRoles = {"SA"})
@Tag(name = E_Tenant.BIZ_NAME, description = E_Tenant.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class TenantController extends BaseController{

    private static final String BIZ_NAME = E_Tenant.BIZ_NAME;

    @Autowired
    TenantService tenantService;

    /**
     * 分页查找
     *
     * @param req  QueryTenantReq
     * @return  ApiResp<PagingData<TenantInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    public ApiResp<PagingData<TenantInfo>> query(QueryTenantReq req , SimplePaging paging) {
        return ApiResp.ok(tenantService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateTenantEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    public ApiResp<String> create(@RequestBody CreateTenantReq req) {
        return ApiResp.ok(tenantService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateTenantEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateTenantReq> reqList) {
        return ApiResp.ok(tenantService.batchCreate(reqList));
    }


    /**
    * 查看详情
    *
    * @param req QueryTenantByIdReq
    */
    @GetMapping("")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<TenantInfo> retrieve(@NotNull TenantIdReq req) {

         return ApiResp.ok(tenantService.findById(req));

     }

    /**
    * 查看详情
    *
    * @param id String
    */
    //@GetMapping("/{id}")
    //@Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    //public ApiResp<TenantInfo> retrieve(@PathVariable @NotNull String id) {

    //     return getSelfProxy(getClass()).retrieve(new TenantIdReq().setId(id));

    // }


    /**
     * 更新
     * @param req UpdateTenantReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
     public ApiResp<Void> update(@RequestBody UpdateTenantReq req) {
         return tenantService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateTenantReq> reqList) {
        return ApiResp.ok(tenantService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param req TenantIdReq
     */
    @DeleteMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    public ApiResp<Integer> delete(@NotNull TenantIdReq req) {
        return ApiResp.ok(tenantService.delete(req));
    }

     // /**
     // * 删除
     // * @param id String
     // */
     // @DeleteMapping({"/{id}"})
     // @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
     // public ApiResp<Integer> delete(@PathVariable @NotNull String id) {
     //
     //   List<Integer> ns = getSelfProxy(getClass())
     //       .batchDelete(new DeleteTenantReq().setIdList(id))
     //       .getData();
     //   
     //       return ns != null && !ns.isEmpty() ? ApiResp.ok(ns.get(0)) : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
     //  }

    /**
     * 批量删除
     * @param req DeleteTenantReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    public ApiResp<List<Integer>> batchDelete(@NotNull DeleteTenantReq req) {
        return ApiResp.ok(tenantService.batchDelete(req));
    }

}
