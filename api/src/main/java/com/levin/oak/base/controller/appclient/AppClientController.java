package com.levin.oak.base.controller.appclient;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_AppClient;
import com.levin.oak.base.services.appclient.AppClientService;
import com.levin.oak.base.services.appclient.info.AppClientInfo;
import com.levin.oak.base.services.appclient.req.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-4-3 0:55:04

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "AppClientController")
//@RequestMapping(API_PATH + "appclient")
@RequestMapping(API_PATH + "AppClient")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AppClientController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Tag(name = E_AppClient.BIZ_NAME, description = E_AppClient.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class AppClientController extends BaseController {

    private static final String BIZ_NAME = E_AppClient.BIZ_NAME;

    @Autowired
    AppClientService appClientService;

    /**
     * 信息脱敏
     *
     * @param info
     * @return
     */
    AppClientInfo desensitize(AppClientInfo info) {
        if (info != null) {
            // info.setAppSecret(null);
        }
        return info;
    }

    /**
     * 分页查找
     *
     * @param req QueryAppClientReq
     * @return ApiResp<PagingData < AppClientInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<AppClientInfo>> query(QueryAppClientReq req, SimplePaging paging) {

        PagingData<AppClientInfo> pagingData = appClientService.query(req, paging);

        if (pagingData.getItems() != null) {
            pagingData.getItems().forEach(this::desensitize);
        }

        return ApiResp.ok(pagingData);
    }

    /**
     * 新增
     *
     * @param req CreateAppClientEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<String> create(@RequestBody CreateAppClientReq req) {
        return ApiResp.ok(appClientService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateAppClientEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateAppClientReq> reqList) {
        return ApiResp.ok(appClientService.batchCreate(reqList));
    }

    /**
     * 查看详情
     *
     * @param req QueryAppClientByIdReq
     */
    @GetMapping("")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<AppClientInfo> retrieve(@NotNull AppClientIdReq req) {
        return ApiResp.ok(desensitize(appClientService.findById(req)));
    }


    /**
     * 更新
     *
     * @param req UpdateAppClientReq
     */
    @PutMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> update(@RequestBody UpdateAppClientReq req) {
        return ApiResp.ok(checkResult(appClientService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateAppClientReq> reqList) {
        return ApiResp.ok(checkResult(appClientService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req AppClientIdReq
     */
    @DeleteMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(@NotNull AppClientIdReq req) {
        return ApiResp.ok(checkResult(appClientService.delete(req), DELETE_ACTION));
    }

    /**
     * 批量删除
     *
     * @param req DeleteAppClientReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteAppClientReq req) {
        return ApiResp.ok(checkResult(appClientService.batchDelete(req), BATCH_DELETE_ACTION));
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
