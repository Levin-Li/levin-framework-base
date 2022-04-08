package com.levin.oak.base.controller.scheduledlog;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_ScheduledLog;
import com.levin.oak.base.services.scheduledlog.ScheduledLogService;
import com.levin.oak.base.services.scheduledlog.info.ScheduledLogInfo;
import com.levin.oak.base.services.scheduledlog.req.*;
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

//Auto gen by simple-dao-codegen 2022-4-2 19:44:58

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "ScheduledLogController")
//@RequestMapping(API_PATH + "scheduledlog")
@RequestMapping(API_PATH + "ScheduledLog")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ScheduledLogController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Tag(name = E_ScheduledLog.BIZ_NAME, description = E_ScheduledLog.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class ScheduledLogController extends BaseController {

    private static final String BIZ_NAME = E_ScheduledLog.BIZ_NAME;

    @Autowired
    ScheduledLogService scheduledLogService;

    /**
     * 分页查找
     *
     * @param req QueryScheduledLogReq
     * @return ApiResp<PagingData < ScheduledLogInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<ScheduledLogInfo>> query(QueryScheduledLogReq req, SimplePaging paging) {
        return ApiResp.ok(scheduledLogService.query(req, paging));
    }

    /**
     * 新增
     *
     * @param req CreateScheduledLogEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Long> create(@RequestBody CreateScheduledLogReq req) {
        return ApiResp.ok(scheduledLogService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateScheduledLogEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateScheduledLogReq> reqList) {
        return ApiResp.ok(scheduledLogService.batchCreate(reqList));
    }

    /**
     * 查看详情
     *
     * @param req QueryScheduledLogByIdReq
     */
    @GetMapping("")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<ScheduledLogInfo> retrieve(@NotNull ScheduledLogIdReq req) {
        return ApiResp.ok(scheduledLogService.findById(req));
    }

    /**
     * 更新
     *
     * @param req UpdateScheduledLogReq
     */
    @PutMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> update(@RequestBody UpdateScheduledLogReq req) {
        return ApiResp.ok(checkResult(scheduledLogService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateScheduledLogReq> reqList) {
        return ApiResp.ok(checkResult(scheduledLogService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req ScheduledLogIdReq
     */
    @DeleteMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(@NotNull ScheduledLogIdReq req) {
        return ApiResp.ok(checkResult(scheduledLogService.delete(req), DELETE_ACTION));
    }

    /**
     * 批量删除
     *
     * @param req DeleteScheduledLogReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteScheduledLogReq req) {
        return ApiResp.ok(checkResult(scheduledLogService.batchDelete(req), BATCH_DELETE_ACTION));
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
