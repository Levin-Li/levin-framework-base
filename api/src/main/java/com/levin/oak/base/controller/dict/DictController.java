package com.levin.oak.base.controller.dict;


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

import com.levin.oak.base.services.dict.*;
import com.levin.oak.base.services.dict.req.*;
import com.levin.oak.base.services.dict.info.*;

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

// Spring mvc 参数验证说明
// @Valid 只能用在controller
// @Validated 可以用在其他被spring管理的类上
// 注意 只有 @Valid 才支持对象嵌套验证，示例如下：
// @Valid
// @NotNull(groups = AdvanceInfo.class)
// private UserAddress useraddress;

//生成的控制器
@RestController(PLUGIN_PREFIX + "DictController")
@RequestMapping(API_PATH + "Dict") //dict

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "DictController", havingValue = "true",  matchIfMissing = true)

//默认需要权限访问，默认从父类继承
//@ResAuthorize(domain = ID, type = ENTITY_TYPE_NAME)

//类注解

@Tag(name = E_Dict.BIZ_NAME, description = E_Dict.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD
/**
 * 字典控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月25日 下午1:50:23, 代码生成哈希校验码：[ca79a8dbcf73648ab228af8ffb54c673]，请不要修改和删除此行内容。
 *
 */
public class DictController extends BaseController{

    protected static final String BIZ_NAME = E_Dict.BIZ_NAME;

    @Autowired
    protected DictService dictService;

    @Autowired
    protected BizDictService bizdictService;

    /**
     * 分页列表查找
     *
     * @param req QueryDictReq
     * @return  ApiResp<PagingData<DictInfo>>
     */
    @GetMapping("/list")
    @Operation(summary = QUERY_LIST_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    @CRUD.ListTable
    public ApiResp<PagingData<DictInfo>> list(@Form @Valid QueryDictReq req, SimplePaging paging) {
        return ApiResp.ok(dictService.query(req,paging));
    }

     /**
      * 简单统计
      *
      * @param req QueryDictReq
      * @return  ApiResp<PagingData<StatDictReq.Result>>
      */
     //@GetMapping("/stat") //默认不开放
     @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
     public ApiResp<PagingData<StatDictReq.Result>> stat(@Valid StatDictReq req, SimplePaging paging) {
         return ApiResp.ok(dictService.stat(req,paging));
     }

    /**
     * 新增
     *
     * @param req CreateDictEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.None)
    public ApiResp<String> create(@RequestBody @Valid CreateDictReq req) {
        return ApiResp.ok(dictService.create(req));
    }

    /**
     * 查看详情
     *
     * @param req QueryDictByIdReq
     */
    @GetMapping({"","{id}"})
    @Operation(summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    @CRUD.Op
    public ApiResp<DictInfo> retrieve(@NotNull @Valid DictIdReq req, @PathVariable(required = false) String id) {
         req.updateIdWhenNotBlank(id);

         DictInfo info = dictService.findById(req);
         Assert.notNull(info, "记录不存在");
         // 租户校验，因为数据可能是从缓存加载的
         Assert.isTrue(!StringUtils.hasText(req.getTenantId()) || req.getTenantId().equals(info.getTenantId()), "非法访问，租户不匹配");

         return ApiResp.ok(info);
     }

    /**
     * 更新
     * @param req UpdateDictReq
     */
    @PutMapping({"","{id}"})
    @Operation(summary = UPDATE_ACTION + "(RequestBody方式)", description = UPDATE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> update(@RequestBody @Valid UpdateDictReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return ApiResp.ok(assertTrue(dictService.update(req), UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req DictIdReq
     */
    @DeleteMapping({"","{id}"})
    @Operation(summary = DELETE_ACTION, description = DELETE_ACTION  + "(Query方式) " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> delete(@Valid DictIdReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return ApiResp.ok(assertTrue(dictService.delete(req), DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     * @param req DictIdReq
     */
    @DeleteMapping(value = {"","{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = DELETE_ACTION + "(RequestBody方式)", description = DELETE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    public ApiResp<Boolean> delete2(@RequestBody @Valid DictIdReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return delete(req, id);
    }

    //////////////////////////////////////以下是批量操作//////////////////////////////////////

    /**
     * 批量新增
     *
     * @param reqList List<CreateDictEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody @Valid List<CreateDictReq> reqList) {
        return ApiResp.ok(dictService.batchCreate(reqList));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody @Valid List<UpdateDictReq> reqList) {
        return ApiResp.ok(assertTrue(dictService.batchUpdate(reqList), BATCH_UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除
     * @param req DeleteDictReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.Multiple)
    public ApiResp<Integer> batchDelete(@NotNull @Valid DeleteDictReq req) {
        return ApiResp.ok(assertTrue(dictService.batchDelete(req), BATCH_DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除2
     * @param req @RequestBody DeleteDictReq
     */
    @DeleteMapping(value = {"/batchDelete"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete2(@RequestBody @Valid DeleteDictReq req) {
        return batchDelete(req);
    }
}
