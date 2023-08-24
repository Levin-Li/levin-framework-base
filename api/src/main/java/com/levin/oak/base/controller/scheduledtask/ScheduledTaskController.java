package com.levin.oak.base.controller.scheduledtask;

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

import com.levin.oak.base.services.scheduledtask.*;
import com.levin.oak.base.services.scheduledtask.req.*;
import com.levin.oak.base.services.scheduledtask.info.*;

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

// 生成的控制器
@RestController(PLUGIN_PREFIX + "ScheduledTaskController")
@RequestMapping(API_PATH + "ScheduledTask") // scheduledtask
@Slf4j
@ConditionalOnProperty(
        prefix = PLUGIN_PREFIX,
        name = "ScheduledTaskController",
        matchIfMissing = true)

// 默认需要权限访问，默认从父类继承
// @ResAuthorize(domain = ID, type = ENTITY_TYPE_NAME)

// 类注解

@Tag(name = E_ScheduledTask.BIZ_NAME, description = E_ScheduledTask.BIZ_NAME + MAINTAIN_ACTION)
@Validated // @Valid
@CRUD
/**
 * 调度任务控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月25日 上午2:04:00, 代码生成哈希校验码：[b6341c6f822e4a365603e68dda5648ab]，请不要修改和删除此行内容。
 */
public class ScheduledTaskController extends BaseController {

    protected static final String BIZ_NAME = E_ScheduledTask.BIZ_NAME;

    @DubboReference // @Autowired
    protected ScheduledTaskService scheduledTaskService;

    @DubboReference // @Autowired
    protected BizScheduledTaskService bizScheduledTaskService;

    /**
     * 分页列表查找
     *
     * @param req QueryScheduledTaskReq
     * @return ApiResp<PagingData<ScheduledTaskInfo>>
     */
    @GetMapping("/queryList")
    @Operation(summary = QUERY_LIST_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    @CRUD.ListTable
    public ApiResp<PagingData<ScheduledTaskInfo>> queryList(
            @Form @Valid QueryScheduledTaskReq req, SimplePaging paging) {
        return ApiResp.ok(scheduledTaskService.query(req, paging));
    }

    /**
     * 简单统计
     *
     * @param req QueryScheduledTaskReq
     * @return ApiResp<PagingData<StatScheduledTaskReq.Result>>
     */
    // @GetMapping("/stat") //默认不开放
    @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<StatScheduledTaskReq.Result>> stat(
            @Valid StatScheduledTaskReq req, SimplePaging paging) {
        return ApiResp.ok(scheduledTaskService.stat(req, paging));
    }

    /**
     * 新增
     *
     * @param req CreateScheduledTaskEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.None)
    public ApiResp<String> create(@RequestBody @Valid CreateScheduledTaskReq req) {
        return ApiResp.ok(scheduledTaskService.create(req));
    }

    /**
     * 查看详情
     *
     * @param req QueryScheduledTaskByIdReq
     */
    @GetMapping({"", "{id}"})
    @Operation(summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    @CRUD.Op
    public ApiResp<ScheduledTaskInfo> retrieve(
            @NotNull @Valid ScheduledTaskIdReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return ApiResp.ok(scheduledTaskService.findById(req));
    }

    /**
     * 更新
     *
     * @param req UpdateScheduledTaskReq
     */
    @PutMapping({"", "{id}"})
    @Operation(
            summary = UPDATE_ACTION + "(RequestBody方式)",
            description = UPDATE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> update(
            @RequestBody @Valid UpdateScheduledTaskReq req,
            @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return ApiResp.ok(
                assertTrue(scheduledTaskService.update(req), UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     *
     * @param req ScheduledTaskIdReq
     */
    @DeleteMapping({"", "{id}"})
    @Operation(
            summary = DELETE_ACTION,
            description = DELETE_ACTION + "(Query方式) " + BIZ_NAME + ", 路径变量参数优先")
    @CRUD.Op
    public ApiResp<Boolean> delete(
            @Valid ScheduledTaskIdReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return ApiResp.ok(
                assertTrue(scheduledTaskService.delete(req), DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 删除
     *
     * @param req ScheduledTaskIdReq
     */
    @DeleteMapping(
            value = {"", "{id}"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = DELETE_ACTION + "(RequestBody方式)",
            description = DELETE_ACTION + " " + BIZ_NAME + ", 路径变量参数优先")
    public ApiResp<Boolean> delete2(
            @RequestBody @Valid ScheduledTaskIdReq req, @PathVariable(required = false) String id) {
        req.updateIdWhenNotBlank(id);
        return delete(req, id);
    }

    ////////////////////////////////////// 以下是批量操作//////////////////////////////////////

    /**
     * 批量新增
     *
     * @param reqList List<CreateScheduledTaskEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(
            @RequestBody @Valid List<CreateScheduledTaskReq> reqList) {
        return ApiResp.ok(scheduledTaskService.batchCreate(reqList));
    }

    /** 批量更新 */
    @PutMapping("/batchUpdate")
    @Operation(summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody @Valid List<UpdateScheduledTaskReq> reqList) {
        return ApiResp.ok(
                assertTrue(
                        scheduledTaskService.batchUpdate(reqList),
                        BATCH_UPDATE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除
     *
     * @param req DeleteScheduledTaskReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    @CRUD.Op(recordRefType = CRUD.RecordRefType.Multiple)
    public ApiResp<Integer> batchDelete(@NotNull @Valid DeleteScheduledTaskReq req) {
        return ApiResp.ok(
                assertTrue(
                        scheduledTaskService.batchDelete(req),
                        BATCH_DELETE_ACTION + BIZ_NAME + "失败"));
    }

    /**
     * 批量删除2
     *
     * @param req @RequestBody DeleteScheduledTaskReq
     */
    @DeleteMapping(
            value = {"/batchDelete"},
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete2(@RequestBody @Valid DeleteScheduledTaskReq req) {
        return batchDelete(req);
    }
}
