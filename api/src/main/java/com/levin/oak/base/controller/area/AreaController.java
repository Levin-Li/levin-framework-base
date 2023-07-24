package com.levin.oak.base.controller.area;


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

import com.levin.oak.base.services.area.*;
import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;

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
@RestController(PLUGIN_PREFIX + "AreaController")
@RequestMapping(API_PATH + "Area") //area

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AreaController", matchIfMissing = true)

//默认需要权限访问
//@ResAuthorize(domain = ID, type = ENTITY_TYPE_NAME)

//类注解
@Tag(name = E_Area.BIZ_NAME, description = E_Area.BIZ_NAME + MAINTAIN_ACTION)
@Valid
@CRUD
/**
 * 区域控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月24日 15:26:17, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[5b8f350822a46da8030cd80cd971ef87], 请不要修改和删除此行内容。
 */
public class AreaController extends BaseController{

    protected static final String BIZ_NAME = E_Area.BIZ_NAME;

    //@Autowired
    @DubboReference
    protected AreaService areaService;

    //@Autowired
    @DubboReference
    protected BizAreaService bizAreaService;

    /**
     * 分页列表查找
     *
     * @param req QueryAreaReq
     * @return  ApiResp<PagingData<AreaInfo>>
     */
    @GetMapping("/queryList")
    @Operation(summary = QUERY_LIST_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    @CRUD.ListTable
    public ApiResp<PagingData<AreaInfo>> queryList(@Form QueryAreaReq req, SimplePaging paging) {
        return ApiResp.ok(areaService.query(req,paging));
    }

     /**
      * 简单统计
      *
      * @param req QueryAreaReq
      * @return  ApiResp<PagingData<StatAreaReq.Result>>
      */
     //@GetMapping("/stat") //默认不开放
     @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
     public ApiResp<PagingData<StatAreaReq.Result>> stat(StatAreaReq req, SimplePaging paging) {
         return ApiResp.ok(areaService.stat(req,paging));
     }

    /**
     * 新增
     *
     * @param req CreateAreaEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.None)
    public ApiResp<String> create(@RequestBody CreateAreaReq req) {
        return ApiResp.ok(areaService.create(req));
    }

    /**
     * 查看详情
     *
     * @param req QueryAreaByIdReq
     */
    @GetMapping({"","{code}"})
    @Operation(summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    @CRUD.Op
    public ApiResp<AreaInfo> retrieve(@NotNull AreaIdReq req, @PathVariable(required = false) String code) {
         req.updateCodeWhenNotBlank(code);
         return ApiResp.ok(areaService.findById(req));
     }

    /**
     * 更新
     * @param req UpdateAreaReq
     */
    @PutMapping({"","{code}"})
    @Operation(summary = UPDATE_ACTION + "(RequestBody方式)", description = UPDATE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> update(@RequestBody UpdateAreaReq req, @PathVariable(required = false) String code) {
        req.updateCodeWhenNotBlank(code);
        return ApiResp.ok(checkResult(areaService.update(req), UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req AreaIdReq
     */
    @DeleteMapping({"","{code}"})
    @Operation(summary = DELETE_ACTION, description = DELETE_ACTION  + "(Query方式) " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> delete(AreaIdReq req, @PathVariable(required = false) String code) {
        req.updateCodeWhenNotBlank(code);
        return ApiResp.ok(checkResult(areaService.delete(req), DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req AreaIdReq
     */
    @DeleteMapping(value = {"","{code}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE_ACTION + "(RequestBody方式)", description = DELETE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    public ApiResp<Boolean> delete2(@RequestBody AreaIdReq req, @PathVariable(required = false) String code) {
        req.updateCodeWhenNotBlank(code);
        return delete(req, code);
    }

    //////////////////////////////////////以下是批量操作//////////////////////////////////////

    /**
     * 批量新增
     *
     * @param reqList List<CreateAreaEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateAreaReq> reqList) {
        return ApiResp.ok(areaService.batchCreate(reqList));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateAreaReq> reqList) {
        return ApiResp.ok(checkResult(areaService.batchUpdate(reqList), BATCH_UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除
     * @param req DeleteAreaReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.Multiple)
    public ApiResp<Integer> batchDelete(@NotNull DeleteAreaReq req) {
        return ApiResp.ok(checkResult(areaService.batchDelete(req), BATCH_DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除2
     * @param req @RequestBody DeleteAreaReq
     */
    @DeleteMapping(value = {"/batchDelete"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete2(@RequestBody DeleteAreaReq req) {
        return batchDelete(req);
    }
}
