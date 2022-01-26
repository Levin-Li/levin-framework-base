package com.levin.oak.base.controller.accesslog;

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
import com.levin.oak.base.services.accesslog.*;
import com.levin.oak.base.services.accesslog.req.*;
import com.levin.oak.base.services.accesslog.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-1-26 17:07:14

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "AccessLogController")
@RequestMapping(API_PATH + "accesslog")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AccessLogController", matchIfMissing = true)

//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_AccessLog.BIZ_NAME, description = E_AccessLog.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class AccessLogController extends BaseController{

    private static final String BIZ_NAME = E_AccessLog.BIZ_NAME;

    @Autowired
    AccessLogService accessLogService;

    /**
     * 分页查找
     *
     * @param req  QueryAccessLogReq
     * @return  ApiResp<PagingData<AccessLogInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    public ApiResp<PagingData<AccessLogInfo>> query(QueryAccessLogReq req , SimplePaging paging) {
        return ApiResp.ok(accessLogService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateAccessLogEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    public ApiResp<Long> create(@RequestBody CreateAccessLogReq req) {
        return ApiResp.ok(accessLogService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateAccessLogEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateAccessLogReq> reqList) {
        return ApiResp.ok(accessLogService.batchCreate(reqList));
    }


    /**
    * 查看详情
    *
    * @param req QueryAccessLogByIdReq
    */
    @GetMapping("")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<AccessLogInfo> retrieve(@NotNull AccessLogIdReq req) {

         return ApiResp.ok(accessLogService.findById(req));

     }

    /**
    * 查看详情
    *
    * @param id Long
    */
    //@GetMapping("/{id}")
    //@Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    //public ApiResp<AccessLogInfo> retrieve(@PathVariable @NotNull Long id) {

    //     return getSelfProxy(getClass()).retrieve(new AccessLogIdReq().setId(id));

    // }


    /**
     * 更新
     * @param req UpdateAccessLogReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
     public ApiResp<Void> update(@RequestBody UpdateAccessLogReq req) {
         return accessLogService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateAccessLogReq> reqList) {
        return ApiResp.ok(accessLogService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param req AccessLogIdReq
     */
    @DeleteMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    public ApiResp<Integer> delete(@NotNull AccessLogIdReq req) {
        return ApiResp.ok(accessLogService.delete(req));
    }

     // /**
     // * 删除
     // * @param id Long
     // */
     // @DeleteMapping({"/{id}"})
     // @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
     // public ApiResp<Integer> delete(@PathVariable @NotNull Long id) {
     //
     //     List<Integer> ns = getSelfProxy(getClass())
     //         .batchDelete(new DeleteAccessLogReq().setIdList(id))
     //         .getData();
     //
     //         return ns != null && !ns.isEmpty() ? ApiResp.ok(ns.get(0)) : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
     //         }

    /**
     * 批量删除
     * @param req DeleteAccessLogReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    public ApiResp<List<Integer>> batchDelete(@NotNull DeleteAccessLogReq req) {
        return ApiResp.ok(accessLogService.batchDelete(req));
    }

}
