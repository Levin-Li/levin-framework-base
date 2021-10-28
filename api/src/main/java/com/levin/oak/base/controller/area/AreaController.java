package com.levin.oak.base.controller.area;

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
import com.levin.oak.base.services.area.*;
import com.levin.oak.base.services.area.req.*;
import com.levin.oak.base.services.area.info.*;

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

@RestController(PLUGIN_PREFIX + "AreaController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "AreaController", havingValue = "false", matchIfMissing = true)
@RequestMapping(API_PATH + "area")

@Tag(name = "区域", description = "区域管理")
@Slf4j @Valid
public class AreaController {

    private static final String ENTITY_NAME ="区域";

    //请求级别变量
    @Autowired
    HttpServletResponse httpResponse;

    //请求级别变量
    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    AreaService areaService;

    /**
     * 分页查找
     *
     * @param req  QueryAreaReq
     * @return  ApiResp<PagingData<AreaInfo>>
     */
    @GetMapping("/query")
    @Operation(tags = {ENTITY_NAME}, summary = "分页查找" + ENTITY_NAME)
    public ApiResp<PagingData<AreaInfo>> query(QueryAreaReq req , SimplePaging paging) {
        return ApiResp.ok(areaService.query(req,paging));
    }

    /**
     * 新增
     *
     * @param req CreateAreaEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(tags = {ENTITY_NAME}, summary = "新增" + ENTITY_NAME)
    public ApiResp<String> create(@RequestBody CreateAreaReq req) {
        return ApiResp.ok(areaService.create(req));
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateAreaEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation(tags = {ENTITY_NAME}, summary = "批量新增" + ENTITY_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateAreaReq> reqList) {
        return ApiResp.ok(areaService.batchCreate(reqList));
    }

    /**
    * 查看详情
    *
    * @param code String
    */
    @GetMapping("/{code}")
    @Operation(tags = {ENTITY_NAME}, summary = "通过ID找回" + ENTITY_NAME)
    public ApiResp<AreaInfo> retrieve(@PathVariable @NotNull String code) {
         return ApiResp.ok(areaService.findById(code));
     }

    /**
     * 更新
     * @param code String
     */
     @PutMapping({"" , "/{code}"})
     @Operation(tags = {ENTITY_NAME}, summary = "更新" + ENTITY_NAME)
     public ApiResp<Void> update(@PathVariable String code , @RequestBody UpdateAreaReq req) {
         //路径参数优先使用
         if (isNotEmpty(code)) { req.setCode(code); }
         return areaService.update(req) > 0 ? ApiResp.ok() : ApiResp.error("更新" + ENTITY_NAME + "失败");
    }

    /**
     * 批量更新
     */
     @PutMapping("/batchUpdate")
     @Operation(tags = {ENTITY_NAME}, summary = "批量更新" + ENTITY_NAME)
     public ApiResp<List<Integer>> batchUpdate(@RequestBody List<UpdateAreaReq> reqList) {
        return ApiResp.ok(areaService.batchUpdate(reqList));
    }

    /**
     * 删除
     * @param code String
     */
    @DeleteMapping({"" , "/{code}"})
    @Operation(tags = {ENTITY_NAME}, summary = "删除" + ENTITY_NAME)
    public ApiResp<Void> delete(@PathVariable String code , DeleteAreaReq req) {
        //路径参数优先使用
        if (isNotEmpty(code)) { req.setCode(code); }
        return areaService.delete(req) > 0 ? ApiResp.ok() : ApiResp.error("删除" + ENTITY_NAME + "失败");
    }

    protected boolean isNotEmpty(Object value) {
        return value != null
        && (!(value instanceof CharSequence) || StringUtils.hasText((CharSequence) value));
    }

}
