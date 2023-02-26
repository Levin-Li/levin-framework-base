package com.levin.oak.base.controller.simplepage;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_SimplePage;
import com.levin.oak.base.services.simplepage.SimplePageService;
import com.levin.oak.base.services.simplepage.info.SimplePageInfo;
import com.levin.oak.base.services.simplepage.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

@RestController(PLUGIN_PREFIX + "SimplePageController")
//@RequestMapping(API_PATH + "simplepage")
@RequestMapping(API_PATH + "SimplePage")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SimplePageController", matchIfMissing = true)

//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_SimplePage.BIZ_NAME, description = E_SimplePage.BIZ_NAME + MAINTAIN_ACTION)
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Valid
public class SimplePageController extends BaseController {

    private static final String BIZ_NAME = E_SimplePage.BIZ_NAME;

    @Autowired
    SimplePageService simplePageService;

    /**
     * 分页查找
     *
     * @param req QuerySimplePageReq
     * @return ApiResp<PagingData < SimplePageInfo>>
     */
    @GetMapping("/query")
    @Operation( summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<SimplePageInfo>> query(QuerySimplePageReq req, SimplePaging paging) {
        return ApiResp.ok(simplePageService.query(req, paging));
    }

    /**
     * 新增
     *
     * @param req CreateSimplePageEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation( summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<String> create(@RequestBody CreateSimplePageReq req) {
        return ApiResp.ok(simplePageService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateSimplePageEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation( summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateSimplePageReq> reqList) {
        return ApiResp.ok(simplePageService.batchCreate(reqList));
    }

    /**
     * 查看详情
     *
     * @param req QuerySimplePageByIdReq
     */
    @GetMapping("")
    @Operation( summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<SimplePageInfo> retrieve(@NotNull SimplePageIdReq req) {
        return ApiResp.ok(simplePageService.findById(req));
    }

    /**
     * 更新
     *
     * @param req UpdateSimplePageReq
     */
    @PutMapping({""})
    @Operation( summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> update(@RequestBody UpdateSimplePageReq req) {
        return ApiResp.ok(checkResult(simplePageService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation( summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateSimplePageReq> reqList) {
        return ApiResp.ok(checkResult(simplePageService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req SimplePageIdReq
     */
    @DeleteMapping({""})
    @Operation( summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(@NotNull SimplePageIdReq req) {
        return ApiResp.ok(checkResult(simplePageService.delete(req), DELETE_ACTION));
    }

    /**
     * 批量删除
     *
     * @param req DeleteSimplePageReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation( summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteSimplePageReq req) {
        return ApiResp.ok(checkResult(simplePageService.batchDelete(req), BATCH_DELETE_ACTION));
    }

    /**
     * 检查结果
     *
     * @param n
     * @param action
     * @return
     */
    protected int checkResult(int n, String action) {
        Assert.isTrue(n > 0, action + BIZ_NAME + "失败");
        return n;
    }
}
