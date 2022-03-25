package com.levin.oak.base.controller.simpleform;

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
import com.levin.oak.base.services.simpleform.*;
import com.levin.oak.base.services.simpleform.req.*;
import com.levin.oak.base.services.simpleform.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-3-25 17:01:37

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "SimpleFormController")
@RequestMapping(API_PATH + "simpleform")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SimpleFormController", matchIfMissing = true)

//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_SimpleForm.BIZ_NAME, description = E_SimpleForm.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class SimpleFormController extends BaseController{

    private static final String BIZ_NAME = E_SimpleForm.BIZ_NAME;

    @Autowired
    SimpleFormService simpleFormService;

    /**
     * 分页查找
     *
     * @param req  QuerySimpleFormReq
     * @return  ApiResp<PagingData<SimpleFormInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    public ApiResp<PagingData<SimpleFormInfo>> query(QuerySimpleFormReq req , SimplePaging paging) {
        return ApiResp.ok(simpleFormService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateSimpleFormEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    public ApiResp<Long> create(@RequestBody CreateSimpleFormReq req) {
        return ApiResp.ok(simpleFormService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateSimpleFormEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateSimpleFormReq> reqList) {
        return ApiResp.ok(simpleFormService.batchCreate(reqList));
    }


    /**
    * 查看详情
    *
    * @param req QuerySimpleFormByIdReq
    */
    @GetMapping("")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<SimpleFormInfo> retrieve(@NotNull SimpleFormIdReq req) {

         return ApiResp.ok(simpleFormService.findById(req));

     }

    /**
    * 查看详情
    *
    * @param id Long
    */
    //@GetMapping("/{id}")
    //@Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    //public ApiResp<SimpleFormInfo> retrieve(@PathVariable @NotNull Long id) {

    //     return getSelfProxy(getClass()).retrieve(new SimpleFormIdReq().setId(id));

    // }


    /**
     * 更新
     * @param req UpdateSimpleFormReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
     public ApiResp<Void> update(@RequestBody UpdateSimpleFormReq req) {
         return simpleFormService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateSimpleFormReq> reqList) {
        return ApiResp.ok(simpleFormService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param req SimpleFormIdReq
     */
    @DeleteMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    public ApiResp<Integer> delete(@NotNull SimpleFormIdReq req) {
        return ApiResp.ok(simpleFormService.delete(req));
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
     //       .batchDelete(new DeleteSimpleFormReq().setIdList(id))
     //       .getData();
     //   
     //       return ns != null && !ns.isEmpty() ? ApiResp.ok(ns.get(0)) : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
     //  }

    /**
     * 批量删除
     * @param req DeleteSimpleFormReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    public ApiResp<List<Integer>> batchDelete(@NotNull DeleteSimpleFormReq req) {
        return ApiResp.ok(simpleFormService.batchDelete(req));
    }

}
