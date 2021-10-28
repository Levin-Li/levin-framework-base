package com.levin.oak.base.controller.scheduledtask;

import static com.levin.oak.base.ModuleOption.*;

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

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.scheduledtask.*;
import com.levin.oak.base.services.scheduledtask.req.*;
import com.levin.oak.base.services.scheduledtask.info.*;

import static com.levin.oak.base.ModuleOption.*;

//Auto gen by simple-dao-codegen 2021-10-28 9:46:17

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
@ConditionalOnProperty(value = PLUGIN_PREFIX + "ScheduledTaskController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "scheduledtask")

@Tag(name = "调度任务", description = "调度任务管理")
@Slf4j @Valid
public class ScheduledTaskController {

    private static final String ENTITY_NAME ="调度任务";

    //请求级别变量
    @Autowired
    HttpServletResponse httpResponse;

    //请求级别变量
    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    ScheduledTaskService scheduledTaskService;

    /**
     * 分页查找
     *
     * @param req  QueryScheduledTaskReq
     * @return  ApiResp<PagingData<ScheduledTaskInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
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
    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
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
    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateScheduledTaskReq> reqList) {
        return ApiResp.ok(scheduledTaskService.batchCreate(reqList));
    }

    /**
    * 查看详情
    *
    * @param id Long
    */
    @GetMapping("/{id}")
    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    public ApiResp<ScheduledTaskInfo> retrieve(@PathVariable @NotNull Long id) {
         return ApiResp.ok(scheduledTaskService.findById(id));
     }

    /**
     * 更新
     * @param id Long
     */
     @PutMapping({"" , "/{id}"})
     @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
     public ApiResp<Void> update(@PathVariable Long id , @RequestBody UpdateScheduledTaskReq req) {
         //路径参数优先使用
         if (isNotEmpty(id)) { req.setId(id); }
         return scheduledTaskService.update(req) > 0 ? ApiResp.ok() : ApiResp.error("更新" + ENTITY_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateScheduledTaskReq> reqList) {
        return ApiResp.ok(scheduledTaskService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param id Long
     */
    @DeleteMapping({"" , "/{id}"})
    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    public ApiResp<Void> delete(@PathVariable Long id , DeleteScheduledTaskReq req) {
        //路径参数优先使用
        if (isNotEmpty(id)) { req.setId(id); }
        return scheduledTaskService.delete(req) > 0 ? ApiResp.ok() : ApiResp.error("删除" + ENTITY_NAME + "失败");
    }

    protected boolean isNotEmpty(Object value) {
        return value != null
        && (!(value instanceof CharSequence) || StringUtils.hasText((CharSequence) value));
    }

}
