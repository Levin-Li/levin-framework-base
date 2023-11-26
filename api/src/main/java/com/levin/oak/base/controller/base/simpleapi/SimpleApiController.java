package com.levin.oak.base.controller.base.simpleapi;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.validation.annotation.*;
import org.springframework.util.*;
import javax.validation.*;
import java.util.*;
import javax.annotation.*;

import javax.servlet.http.*;

//import org.apache.dubbo.config.annotation.*;

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

import com.levin.oak.base.services.simpleapi.*;
import com.levin.oak.base.services.simpleapi.req.*;
import com.levin.oak.base.services.simpleapi.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;


// POST: 创建一个新的资源，如用户资源，部门资源; PATCH: 修改资源的某个属性; PUT: 更新资源当中包含的全部属性; DELETE: 删除某项资源; GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。
// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。 所以一般插入新数据的时候使用post方法，更新数据库时用put方法

// Spring mvc 参数验证说明
// @Valid 只能用在controller, @Validated 可以用在其他被spring管理的类上。注意只有 @Valid 才支持对象嵌套验证，示例如下：
// @Valid
// @NotNull(groups = AdvanceInfo.class)
// private UserAddress userAddress;

/**
* 简单动态接口控制器
*
* @author Auto gen by simple-dao-codegen, @time: 2023年11月26日 上午9:43:50, 代码生成哈希校验码：[eb48f5db7c4c9b7961f481a2c24e176b]，请不要修改和删除此行内容。
*
*/

//生成的控制器
//@RestController(PLUGIN_PREFIX + "SimpleApiController")
//@RequestMapping(API_PATH + "SimpleApi") //simpleapi

//@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SimpleApiController", havingValue = "true",  matchIfMissing = true)

//默认需要权限访问，默认从父类继承
@ResAuthorize(domain = ID, type = PLATFORM_TYPE_NAME)

//类注解
//默认生成控制器类，@Tag的name属性关联权限的资源标识
@Tag(name = E_SimpleApi.BIZ_NAME, description = E_SimpleApi.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD

@Slf4j
public abstract class SimpleApiController extends BaseController{

    protected static final String BIZ_NAME = E_SimpleApi.BIZ_NAME;

    @Autowired
    protected SimpleApiService simpleApiService;

    @Autowired
    protected BizSimpleApiService bizsimpleApiService;

    /**
     * 分页列表查找
     *
     * @param req QuerySimpleApiReq
     * @return  ApiResp<PagingData<SimpleApiInfo>>
     */
    @GetMapping("/list")
    @Operation(summary = QUERY_LIST_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    @CRUD.ListTable
    public ApiResp<PagingData<SimpleApiInfo>> list(@Form @Valid QuerySimpleApiReq req, SimplePaging paging) {

        req = checkRequest(QUERY_LIST_ACTION, req);

        return ApiResp.ok(checkResponse(QUERY_LIST_ACTION, simpleApiService.query(req,paging)));
    }

     /**
      * 简单统计
      *
      * @param req QuerySimpleApiReq
      * @return  ApiResp<PagingData<StatSimpleApiReq.Result>>
      */
     //@GetMapping("/stat") //默认不开放
     @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
     public ApiResp<PagingData<StatSimpleApiReq.Result>> stat(@Valid StatSimpleApiReq req, SimplePaging paging) {

         req = checkRequest(STAT_ACTION, req);

         return ApiResp.ok(checkResponse(STAT_ACTION, simpleApiService.stat(req,paging)));
     }

    /**
     * 新增
     *
     * @param req CreateSimpleApiEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.None)
    public ApiResp<String> create(@RequestBody @Valid CreateSimpleApiReq req) {

        req = checkRequest(CREATE_ACTION, req);

        return ApiResp.ok(simpleApiService.create(req));
    }

    /**
     * 查看详情
     *
     * @param req QuerySimpleApiByIdReq
     */
    @GetMapping({"","{id}"})
    @Operation(summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    @CRUD.Op
    public ApiResp<SimpleApiInfo> retrieve(@NotNull @Valid SimpleApiIdReq req, @PathVariable(required = false) String id) {

         req.updateIdWhenNotBlank(id);

         req = checkRequest(VIEW_DETAIL_ACTION, req);

         SimpleApiInfo info = simpleApiService.findById(req);
         Assert.notNull(info, "记录不存在");
         // 租户校验，因为数据可能是从缓存加载的
         Assert.isTrue(!StringUtils.hasText(req.getTenantId()) || req.getTenantId().equals(info.getTenantId()), "非法访问，租户不匹配");

         return ApiResp.ok(checkResponse(VIEW_DETAIL_ACTION, info));
     }

    /**
     * 更新
     * @param req UpdateSimpleApiReq
     */
    @PutMapping({"","{id}"})
    @Operation(summary = UPDATE_ACTION + "(RequestBody方式)", description = UPDATE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> update(@RequestBody @Valid UpdateSimpleApiReq req, @PathVariable(required = false) String id) {

        req.updateIdWhenNotBlank(id);

        req = checkRequest(UPDATE_ACTION, req);

        return ApiResp.ok(assertTrue(simpleApiService.update(req), UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req SimpleApiIdReq
     */
    @DeleteMapping({"","{id}"})
    @Operation(summary = DELETE_ACTION, description = DELETE_ACTION  + "(Query方式) " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> delete(@Valid SimpleApiIdReq req, @PathVariable(required = false) String id) {

        req.updateIdWhenNotBlank(id);

        req = checkRequest(DELETE_ACTION, req);

        return ApiResp.ok(assertTrue(simpleApiService.delete(req), DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req SimpleApiIdReq
     */
    @DeleteMapping(value = {"","{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE_ACTION + "(RequestBody方式)", description = DELETE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    public ApiResp<Boolean> delete2(@RequestBody @Valid SimpleApiIdReq req, @PathVariable(required = false) String id) {

        req.updateIdWhenNotBlank(id);

        req = checkRequest(DELETE_ACTION, req);

        return delete(req, id);
    }

    //////////////////////////////////////以下是批量操作//////////////////////////////////////

    /**
     * 批量新增
     *
     * @param reqList List<CreateSimpleApiEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody @Valid List<CreateSimpleApiReq> reqList) {

        reqList = checkRequest(BATCH_CREATE_ACTION, reqList);

        return ApiResp.ok(simpleApiService.batchCreate(reqList));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody @Valid List<UpdateSimpleApiReq> reqList) {

        reqList = checkRequest(BATCH_UPDATE_ACTION, reqList);

        return ApiResp.ok(assertTrue(simpleApiService.batchUpdate(reqList), BATCH_UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除
     * @param req DeleteSimpleApiReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.Multiple)
    public ApiResp<Integer> batchDelete(@NotNull @Valid DeleteSimpleApiReq req) {

        req = checkRequest(BATCH_DELETE_ACTION, req);

        return ApiResp.ok(assertTrue(simpleApiService.batchDelete(req), BATCH_DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除2
     * @param req @RequestBody DeleteSimpleApiReq
     */
    @DeleteMapping(value = {"/batchDelete"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete2(@RequestBody @Valid DeleteSimpleApiReq req) {

        req = checkRequest(BATCH_DELETE_ACTION, req);

        return batchDelete(req);
    }
}
