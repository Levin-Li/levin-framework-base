package com.levin.oak.base.controller.i18nres;

import com.levin.commons.rbac.ResAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import com.levin.oak.base.services.i18nres.*;
import com.levin.oak.base.services.i18nres.req.*;
import com.levin.oak.base.services.i18nres.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-4-2 19:44:59

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "I18nResController")
//@RequestMapping(API_PATH + "i18nres")
@RequestMapping(API_PATH + "I18nRes")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "I18nResController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Tag(name = E_I18nRes.BIZ_NAME, description = E_I18nRes.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class I18nResController extends BaseController{

    private static final String BIZ_NAME = E_I18nRes.BIZ_NAME;

    @Autowired
    I18nResService i18nResService;

    /**
     * 分页查找
     *
     * @param req  QueryI18nResReq
     * @return  ApiResp<PagingData<I18nResInfo>>
     */
    @GetMapping("/query")
    @Operation( summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<I18nResInfo>> query(QueryI18nResReq req , SimplePaging paging) {
        return ApiResp.ok(i18nResService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateI18nResEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation( summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Long> create(@RequestBody CreateI18nResReq req) {
        return ApiResp.ok(i18nResService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateI18nResEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation( summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateI18nResReq> reqList) {
        return ApiResp.ok(i18nResService.batchCreate(reqList));
    }

    /**
    * 查看详情
    *
    * @param req QueryI18nResByIdReq
    */
    @GetMapping("")
    @Operation( summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<I18nResInfo> retrieve(@NotNull I18nResIdReq req) {
         return ApiResp.ok(i18nResService.findById(req));
     }

    /**
     * 更新
     * @param req UpdateI18nResReq
     */
     @PutMapping({""})
     @Operation( summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
     public ApiResp<Integer> update(@RequestBody UpdateI18nResReq req) {
         return ApiResp.ok(checkResult(i18nResService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation( summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
     public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateI18nResReq> reqList) {
        return ApiResp.ok(checkResult(i18nResService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     * @param req I18nResIdReq
     */
    @DeleteMapping({""})
    @Operation( summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(@NotNull I18nResIdReq req) {
        return ApiResp.ok(checkResult(i18nResService.delete(req), DELETE_ACTION));
    }

    /**
     * 删除
     * @param req I18nResIdReq
     */
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation( summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete2(@RequestBody I18nResIdReq req) {
        return delete(req);
    }

    /**
     * 批量删除
     * @param req DeleteI18nResReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation( summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteI18nResReq req) {
        return ApiResp.ok(checkResult(i18nResService.batchDelete(req), BATCH_DELETE_ACTION));
    }

    /**
     * 检查结果
     * @param n
     * @param action
     * @return
     */
    protected int checkResult(int n, String action) {
        Assert.isTrue(n > 0, action + BIZ_NAME + "失败");
        return n;
    }
}
