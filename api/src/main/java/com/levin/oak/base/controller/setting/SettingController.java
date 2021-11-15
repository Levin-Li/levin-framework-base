package com.levin.oak.base.controller.setting;

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
import com.levin.oak.base.services.setting.*;
import com.levin.oak.base.services.setting.req.*;
import com.levin.oak.base.services.setting.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2021-11-15 15:01:48

// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

@RestController(PLUGIN_PREFIX + "SettingController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "SettingController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "setting")
//默认需要权限访问
//@ResAuthorize(domain = ID, type = TYPE_NAME)
@Tag(name = E_Setting.BIZ_NAME, description = E_Setting.BIZ_NAME + MAINTAIN_ACTION)
@Slf4j
@Valid
public class SettingController extends BaseController{

    private static final String BIZ_NAME = E_Setting.BIZ_NAME;

    @Autowired
    SettingService settingService;

    /**
     * 分页查找
     *
     * @param req  QuerySettingReq
     * @return  ApiResp<PagingData<SettingInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {BIZ_NAME}, summary = QUERY_ACTION)
    public ApiResp<PagingData<SettingInfo>> query(QuerySettingReq req , SimplePaging paging) {
        return ApiResp.ok(settingService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateSettingEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {BIZ_NAME}, summary = CREATE_ACTION)
    public ApiResp<Long> create(@RequestBody CreateSettingReq req) {
        return ApiResp.ok(settingService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateSettingEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {BIZ_NAME}, summary = BATCH_CREATE_ACTION)
    public ApiResp<List<Long>> batchCreate(@RequestBody List<CreateSettingReq> reqList) {
        return ApiResp.ok(settingService.batchCreate(reqList));
    }

    /**
    * 查看详情
    *
    * @param id Long
    */
    @GetMapping("/{id}")
    @Operation(tags = {BIZ_NAME}, summary = VIEW_DETAIL_ACTION)
    public ApiResp<SettingInfo> retrieve(@PathVariable @NotNull Long id) {
         return ApiResp.ok(settingService.findById(id));
     }

    /**
     * 更新
     * @param req UpdateSettingReq
     */
     @PutMapping({""})
     @Operation(tags = {BIZ_NAME}, summary = UPDATE_ACTION)
     public ApiResp<Void> update(@RequestBody UpdateSettingReq req) {
         return settingService.update(req) > 0 ? ApiResp.ok() : ApiResp.error(UPDATE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {BIZ_NAME}, summary = BATCH_UPDATE_ACTION)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateSettingReq> reqList) {
        return ApiResp.ok(settingService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param id Long
     */
    @DeleteMapping({"/{id}"})
    @Operation(tags = {BIZ_NAME}, summary = DELETE_ACTION)
    public ApiResp<Void> delete(@PathVariable @NotNull Long id) {
        return settingService.delete(new DeleteSettingReq().setId(id)) > 0
                                                ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }

    /**
     * 批量删除
     * @param req DeleteSettingReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation(tags = {BIZ_NAME}, summary = BATCH_DELETE_ACTION)
    public ApiResp<Void> batchDelete(@NotNull DeleteSettingReq req) {
        //new DeleteSettingReq().setIdList(idList)
        return settingService.delete(req) > 0 ? ApiResp.ok() : ApiResp.error(DELETE_ACTION + BIZ_NAME + "失败");
    }  

}
