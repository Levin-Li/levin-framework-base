package com.levin.oak.base.controller.noticeprocesslog;

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

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import javax.validation.constraints.*;

import com.levin.oak.base.controller.*;
import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.noticeprocesslog.*;
import com.levin.oak.base.services.noticeprocesslog.req.*;
import com.levin.oak.base.services.noticeprocesslog.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2022-6-20 16:50:12

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

//@RestController(PLUGIN_PREFIX + "NoticeProcessLogController")
//@RequestMapping(API_PATH + "noticeprocesslog")
@RequestMapping(API_PATH + "NoticeProcessLog")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "NoticeProcessLogController", matchIfMissing = true)

//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_NoticeProcessLog.BIZ_NAME, description = E_NoticeProcessLog.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class NoticeProcessLogController extends BaseController{

    private static final String BIZ_NAME = E_NoticeProcessLog.BIZ_NAME;

    @Resource
    NoticeProcessLogService noticeProcessLogService;

    /**
     * 分页查找
     *
     * @param req QueryNoticeProcessLogReq
     * @return  ApiResp<PagingData<NoticeProcessLogInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<NoticeProcessLogInfo>> query(QueryNoticeProcessLogReq req, SimplePaging paging) {
        return ApiResp.ok(noticeProcessLogService.query(req,paging));
    }

     /**
      * 简单统计
      *
      * @param req QueryNoticeProcessLogReq
      * @return  ApiResp<PagingData<StatNoticeProcessLogReq.Result>>
      */
     //@GetMapping("/stat") //默认不开放
     @Operation(tags = {BIZ_NAME}, summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
     public ApiResp<PagingData<StatNoticeProcessLogReq.Result>> stat(StatNoticeProcessLogReq req, SimplePaging paging) {
         return ApiResp.ok(noticeProcessLogService.stat(req,paging));
     }

    /**
     * 新增
     *
     * @param req CreateNoticeProcessLogEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<String> create(@RequestBody CreateNoticeProcessLogReq req) {
        return ApiResp.ok(noticeProcessLogService.create(req));
    }

    /**
    * 查看详情
    *
    * @param req QueryNoticeProcessLogByIdReq
    */
    @GetMapping({"","{id}"})
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<NoticeProcessLogInfo> retrieve(@NotNull NoticeProcessLogIdReq req, @PathVariable(required = false) String id) {
         req.setIdOnNotBlank(id);
         return ApiResp.ok(noticeProcessLogService.findById(req));
     }

    /**
     * 更新
     * @param req UpdateNoticeProcessLogReq
     */
     @PutMapping({"","{id}"})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
     public ApiResp<Integer> update(@RequestBody UpdateNoticeProcessLogReq req, @PathVariable(required = false) String id) {
         req.setIdOnNotBlank(id);
         return ApiResp.ok(checkResult(noticeProcessLogService.update(req), UPDATE_ACTION));
    }

    /**
     * 删除
     * @param req NoticeProcessLogIdReq
     */
    @DeleteMapping({"","{id}"})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(NoticeProcessLogIdReq req, @PathVariable(required = false) String id) {
        req.setIdOnNotBlank(id);
        return ApiResp.ok(checkResult(noticeProcessLogService.delete(req), DELETE_ACTION));
    }

    /**
     * 删除
     * @param req NoticeProcessLogIdReq
     */
    @DeleteMapping(value = {"","{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete2(@RequestBody NoticeProcessLogIdReq req, @PathVariable(required = false) String id) {
        //req.setIdOnNotBlank(id);
        return delete(req, id);
    }

    //////////////////////////////////////以下是批量操作//////////////////////////////////////

    /**
     * 批量新增
     *
     * @param reqList List<CreateNoticeProcessLogEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateNoticeProcessLogReq> reqList) {
        return ApiResp.ok(noticeProcessLogService.batchCreate(reqList));
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
     public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateNoticeProcessLogReq> reqList) {
        return ApiResp.ok(checkResult(noticeProcessLogService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 批量删除
     * @param req DeleteNoticeProcessLogReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteNoticeProcessLogReq req) {
        return ApiResp.ok(checkResult(noticeProcessLogService.batchDelete(req), BATCH_DELETE_ACTION));
    }

     /**
     * 批量删除2
     * @param req @RequestBody DeleteNoticeProcessLogReq
     */
    @DeleteMapping(value = {"/batchDelete"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete2(@RequestBody DeleteNoticeProcessLogReq req) {
        return batchDelete(req);
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
