package com.levin.oak.base.controller.jobpost;

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
import com.levin.oak.base.services.jobpost.*;
import com.levin.oak.base.services.jobpost.req.*;
import com.levin.oak.base.services.jobpost.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2021-12-17 11:53:24

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "JobPostController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "JobPostController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "jobpost")
//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_JobPost.BIZ_NAME, description = E_JobPost.BIZ_NAME + MAINTAIN_ACTION)
@Slf4j
@Valid
public class JobPostController extends BaseController{

    private static final String BIZ_NAME = E_JobPost.BIZ_NAME;

    @Autowired
    JobPostService jobPostService;

    /**
     * 分页查找
     *
     * @param req  QueryJobPostReq
     * @return  ApiResp<PagingData<JobPostInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    public ApiResp<PagingData<JobPostInfo>> query(QueryJobPostReq req , SimplePaging paging) {
        return ApiResp.ok(jobPostService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateJobPostEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    public ApiResp<Long> create(@RequestBody CreateJobPostReq req) {
        return ApiResp.ok(jobPostService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateJobPostEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateJobPostReq> reqList) {
        return ApiResp.ok(jobPostService.batchCreate(reqList));
    }

    /**
    * 查看详情
    *
    * @param id Long
    */
    @GetMapping("/{id}")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<JobPostInfo> retrieve(@PathVariable @NotNull Long id) {
         return ApiResp.ok(jobPostService.findById(id));
     }

    /**
     * 更新
     * @param req UpdateJobPostReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
     public ApiResp<Void> update(@RequestBody UpdateJobPostReq req) {
         return jobPostService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateJobPostReq> reqList) {
        return ApiResp.ok(jobPostService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param id Long
     */
    @DeleteMapping({"/{id}"})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    public ApiResp<Void> delete(@PathVariable @NotNull Long id) {
        return jobPostService.delete(new DeleteJobPostReq().setId(id)) > 0
                                                ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量删除
     * @param req DeleteJobPostReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    public ApiResp<Void> batchDelete(@NotNull DeleteJobPostReq req) {
        //new DeleteJobPostReq().setIdList(idList)
        return jobPostService.delete(req) > 0 ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }  

}
