package com.levin.oak.base.controller.i18nres;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_I18nRes;
import com.levin.oak.base.services.i18nres.I18nResService;
import com.levin.oak.base.services.i18nres.info.I18nResInfo;
import com.levin.oak.base.services.i18nres.req.CreateI18nResReq;
import com.levin.oak.base.services.i18nres.req.DeleteI18nResReq;
import com.levin.oak.base.services.i18nres.req.QueryI18nResReq;
import com.levin.oak.base.services.i18nres.req.UpdateI18nResReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.levin.oak.base.ModuleOption.API_PATH;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2021-11-12 9:56:30

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
@ConditionalOnProperty(value = PLUGIN_PREFIX + "I18nResController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "i18nres")
//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_I18nRes.BIZ_NAME, description = E_I18nRes.BIZ_NAME + MAINTAIN_ACTION)
@Slf4j
@Valid
public class I18nResController extends BaseController {

    private static final String BIZ_NAME = E_I18nRes.BIZ_NAME;

    @Autowired
    I18nResService i18nResService;

    /**
     * 分页查找
     *
     * @param req QueryI18nResReq
     * @return ApiResp<PagingData < I18nResInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION + BIZ_NAME)
    public ApiResp<PagingData<I18nResInfo>> query(QueryI18nResReq req, SimplePaging paging) {
        return ApiResp.ok(i18nResService.query(req, paging));
    }

    /**
     * 新增
     *
     * @param req CreateI18nResEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION + BIZ_NAME)
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
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION + BIZ_NAME)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateI18nResReq> reqList) {
        return ApiResp.ok(i18nResService.batchCreate(reqList));
    }

    /**
     * 查看详情
     *
     * @param id Long
     */
    @GetMapping("/{id}")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION + BIZ_NAME)
    public ApiResp<I18nResInfo> retrieve(@PathVariable @NotNull Long id) {
        return ApiResp.ok(i18nResService.findById(id));
    }

    /**
     * 更新
     *
     * @param req UpdateI18nResReq
     */
    @PutMapping({""})
    @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION + BIZ_NAME)
    public ApiResp<Void> update(@RequestBody UpdateI18nResReq req) {
        return i18nResService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION + BIZ_NAME)
    public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateI18nResReq> reqList) {
        return ApiResp.ok(i18nResService.batchUpdate(reqList));
    }

    /**
     * 删除
     *
     * @param id Long
     */
    @DeleteMapping({"/{id}"})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION + BIZ_NAME)
    public ApiResp<Void> delete(@PathVariable @NotNull Long id) {
        return i18nResService.delete(new DeleteI18nResReq().setId(id)) > 0
                ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量删除
     *
     * @param req DeleteI18nResReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION + BIZ_NAME)
    public ApiResp<Void> batchDelete(@NotNull DeleteI18nResReq req) {
        //new DeleteI18nResReq().setIdList(idList)
        return i18nResService.delete(req) > 0 ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }

}
