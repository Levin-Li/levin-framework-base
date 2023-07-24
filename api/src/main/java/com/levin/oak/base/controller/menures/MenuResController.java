package com.levin.oak.base.controller.menures;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.*;
import javax.validation.*;
import java.util.*;
import javax.annotation.*;

import javax.servlet.http.*;

import org.apache.dubbo.config.annotation.*;

import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.dao.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.ui.annotation.*;
import javax.validation.constraints.*;

import com.levin.oak.base.controller.*;
import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.biz.*;

import com.levin.oak.base.services.menures.*;
import com.levin.oak.base.services.menures.req.*;
import com.levin.oak.base.services.menures.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

//生成的控制器
@RestController(PLUGIN_PREFIX + "MenuResController")
@RequestMapping(API_PATH + "MenuRes") //menures

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "MenuResController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = ENTITY_TYPE_NAME)

//类注解
@Tag(name = E_MenuRes.BIZ_NAME, description = E_MenuRes.BIZ_NAME + MAINTAIN_ACTION)
@Valid
@CRUD
/**
 * 菜单控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月24日 15:26:17, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[da818e6b9513f3997a5e8ba854391cdb], 请不要修改和删除此行内容。
 */
public class MenuResController extends BaseController{

    protected static final String BIZ_NAME = E_MenuRes.BIZ_NAME;

    //@Autowired
    @DubboReference
    protected MenuResService menuResService;

    //@Autowired
    @DubboReference
    protected BizMenuResService bizMenuResService;

    /**
     * 分页列表查找
     *
     * @param req QueryMenuResReq
     * @return  ApiResp<PagingData<MenuResInfo>>
     */
    @GetMapping("/queryList")
    @Operation(summary = QUERY_LIST_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    @CRUD.ListTable
    public ApiResp<PagingData<MenuResInfo>> queryList(@Form QueryMenuResReq req, SimplePaging paging) {
        return ApiResp.ok(menuResService.query(req,paging));
    }

     /**
      * 简单统计
      *
      * @param req QueryMenuResReq
      * @return  ApiResp<PagingData<StatMenuResReq.Result>>
      */
     //@GetMapping("/stat") //默认不开放
     @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
     public ApiResp<PagingData<StatMenuResReq.Result>> stat(StatMenuResReq req, SimplePaging paging) {
         return ApiResp.ok(menuResService.stat(req,paging));
     }

    /**
     * 新增
     *
     * @param req CreateMenuResEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.None)
    public ApiResp<String> create(@RequestBody CreateMenuResReq req) {
        return ApiResp.ok(menuResService.create(req));
    }

    /**
     * 查看详情
     *
     * @param req QueryMenuResByIdReq
     */
    @GetMapping({"","{id}"})
    @Operation(summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    @CRUD.Op
    public ApiResp<MenuResInfo> retrieve(@NotNull MenuResIdReq req, @PathVariable(required = false) String id) {
         req.updateIdWhenNotBlank(id);
         return ApiResp.ok(menuResService.findById(req));
     }

    /**
     * 更新
     * @param req UpdateMenuResReq
     */
    @PutMapping({"","{id}"})
    @Operation(summary = UPDATE_ACTION + "(RequestBody方式)", description = UPDATE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> update(@RequestBody UpdateMenuResReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return ApiResp.ok(checkResult(menuResService.update(req), UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req MenuResIdReq
     */
    @DeleteMapping({"","{id}"})
    @Operation(summary = DELETE_ACTION, description = DELETE_ACTION  + "(Query方式) " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> delete(MenuResIdReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return ApiResp.ok(checkResult(menuResService.delete(req), DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req MenuResIdReq
     */
    @DeleteMapping(value = {"","{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE_ACTION + "(RequestBody方式)", description = DELETE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    public ApiResp<Boolean> delete2(@RequestBody MenuResIdReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return delete(req, id);
    }

    //////////////////////////////////////以下是批量操作//////////////////////////////////////

    /**
     * 批量新增
     *
     * @param reqList List<CreateMenuResEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateMenuResReq> reqList) {
        return ApiResp.ok(menuResService.batchCreate(reqList));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateMenuResReq> reqList) {
        return ApiResp.ok(checkResult(menuResService.batchUpdate(reqList), BATCH_UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除
     * @param req DeleteMenuResReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.Multiple)
    public ApiResp<Integer> batchDelete(@NotNull DeleteMenuResReq req) {
        return ApiResp.ok(checkResult(menuResService.batchDelete(req), BATCH_DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除2
     * @param req @RequestBody DeleteMenuResReq
     */
    @DeleteMapping(value = {"/batchDelete"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete2(@RequestBody DeleteMenuResReq req) {
        return batchDelete(req);
    }
}
