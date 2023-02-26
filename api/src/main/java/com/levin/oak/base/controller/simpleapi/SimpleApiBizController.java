package com.levin.oak.base.controller.simpleapi;

import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_SimpleApi;
import com.levin.oak.base.services.simpleapi.SimpleApiService;
import com.levin.oak.base.services.simpleapi.info.SimpleApiInfo;
import com.levin.oak.base.services.simpleapi.req.SimpleApiIdReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.SYS_TYPE_NAME;
import static com.levin.oak.base.entities.EntityConst.VIEW_DETAIL_ACTION;

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

@RestController(PLUGIN_PREFIX + "SimpleApiBizController")

@RequestMapping(API_PATH + "SimpleApi")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "SimpleApiBizController", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME)
@Tag(name = E_SimpleApi.BIZ_NAME, description = E_SimpleApi.BIZ_NAME + "服务")
@MenuResTag(false)
@Valid
public class SimpleApiBizController extends BaseController {

    private static final String BIZ_NAME = E_SimpleApi.BIZ_NAME;

    @Autowired
    SimpleApiService simpleApiService;

    /**
     * 查看详情
     *
     * @param req QuerySimpleApiByIdReq
     */
    @PostMapping("/service")
    @Operation( summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<?> service(@NotNull SimpleApiIdReq req) {

        SimpleApiInfo info = simpleApiService.findById(req);
        //执行脚本

        return ApiResp.ok(info);
    }

}
