package com.levin.oak.base.controller;

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

import cn.hutool.core.lang.Assert;

import javax.servlet.http.*;

//import org.apache.dubbo.config.annotation.*;

import com.levin.commons.dao.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.ui.annotation.*;
import com.levin.commons.rbac.ResAuthorize;

import javax.validation.constraints.*;

import com.levin.oak.base.controller.*;
import com.levin.oak.base.controller.base.tenant.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.biz.bo.tenant.*;

import com.levin.oak.base.biz.*;

import com.levin.oak.base.services.tenant.*;
import com.levin.oak.base.services.tenant.req.*;
import com.levin.oak.base.services.tenant.info.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;


// POST: 创建一个新的资源，如用户资源，部门资源; PATCH: 修改资源的某个属性; PUT: 更新资源当中包含的全部属性; DELETE: 删除某项资源; GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。
// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。 所以一般插入新数据的时候使用post方法，更新数据库时用put方法

// Spring mvc 参数验证说明
// @Valid 只能用在controller, @Validated 可以用在其他被spring管理的类上。注意只有 @Valid 才支持对象嵌套验证，示例如下：
// @Valid
// @NotNull(groups = AdvanceInfo.class)
// private UserAddress userAddress;

/**
* 平台租户业务控制器
*
* @author Auto gen by simple-dao-codegen, @time: 2023年12月29日 下午5:42:59, 代码生成哈希校验码：[0c0fc8aa906609a9a7aa99a2acd5a51c]，请不要修改和删除此行内容。
*
*/

//生成的控制器
@RestController(PLUGIN_PREFIX + "BizTenantController")
@RequestMapping(API_PATH + "Tenant") //tenant

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizTenantController", havingValue = "true", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = PLATFORM_TYPE_NAME + "-")

//类注解，@Tag的name属性关联权限的资源标识
@Tag(name = E_Tenant.BIZ_NAME, description = E_Tenant.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD

@Slf4j
public class BizTenantController extends TenantController{

    //允许的操作
    List<String> allowOpList = Arrays.asList(QUERY_LIST_ACTION, CREATE_ACTION, UPDATE_ACTION, DELETE_ACTION, VIEW_DETAIL_ACTION, BATCH_CREATE_ACTION, BATCH_UPDATE_ACTION, BATCH_DELETE_ACTION);

    /**
    * 检查请求
    *
    * @param action
    * @param req
    * @return
    */
    @Override
    protected <T> T checkRequest(String action, T req) {

        Assert.isTrue(allowOpList.contains(action), "不支持的操作-{}", action);

        return super.checkRequest(action, req);
    }

    /**
    * 统计
    *
    * @param req QueryTenantReq
    * @return  ApiResp<StatTenantReq.Result>
    */
    @GetMapping("/stat") //默认开放
    @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
    public ApiResp<StatTenantReq.Result> stat(@Valid StatTenantReq req, SimplePaging paging) {

        req = checkRequest(STAT_ACTION, req);

        return ApiResp.ok(checkResponse(STAT_ACTION, bizTenantService.stat(req, paging)));
    }

}
