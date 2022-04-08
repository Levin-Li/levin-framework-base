package com.levin.oak.base.controller.scheduledtask;

import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.ResAuthorize;
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
import com.levin.oak.base.services.scheduledtask.*;
import com.levin.oak.base.services.scheduledtask.req.*;
import com.levin.oak.base.services.scheduledtask.info.*;

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

@RestController(PLUGIN_PREFIX + "ScheduledTaskController")
//@RequestMapping(API_PATH + "scheduledtask")
@RequestMapping(API_PATH + "ScheduledTask")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ScheduledTaskController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Tag(name = E_ScheduledTask.BIZ_NAME, description = E_ScheduledTask.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class ScheduledTaskController extends BaseController{

    private static final String BIZ_NAME = E_ScheduledTask.BIZ_NAME;

    @Autowired
    ScheduledTaskService scheduledTaskService;

    /**
     * 分页查找
     *
     * @param req  QueryScheduledTaskReq
     * @return  ApiResp<PagingData<ScheduledTaskInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<ScheduledTaskInfo>> query(QueryScheduledTaskReq req , SimplePaging paging) {
        return ApiResp.ok(scheduledTaskService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateScheduledTaskEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Long> create(@RequestBody CreateScheduledTaskReq req) {
        return ApiResp.ok(scheduledTaskService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateScheduledTaskEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateScheduledTaskReq> reqList) {
        return ApiResp.ok(scheduledTaskService.batchCreate(reqList));
    }

    /**
    * 查看详情
    *
    * @param req QueryScheduledTaskByIdReq
    */
    @GetMapping("")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<ScheduledTaskInfo> retrieve(@NotNull ScheduledTaskIdReq req) {
         return ApiResp.ok(scheduledTaskService.findById(req));
     }

    /**
     * 更新
     * @param req UpdateScheduledTaskReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
     public ApiResp<Integer> update(@RequestBody UpdateScheduledTaskReq req) {
         return ApiResp.ok(checkResult(scheduledTaskService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
     public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateScheduledTaskReq> reqList) {
        return ApiResp.ok(checkResult(scheduledTaskService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     * @param req ScheduledTaskIdReq
     */
    @DeleteMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(@NotNull ScheduledTaskIdReq req) {
        return ApiResp.ok(checkResult(scheduledTaskService.delete(req), DELETE_ACTION));
    }

    /**
     * 批量删除
     * @param req DeleteScheduledTaskReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteScheduledTaskReq req) {
        return ApiResp.ok(checkResult(scheduledTaskService.batchDelete(req), BATCH_DELETE_ACTION));
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
