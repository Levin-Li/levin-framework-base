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
import com.levin.oak.base.controller.base.i18nres.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.biz.bo.i18nres.*;

import com.levin.oak.base.biz.*;

import com.levin.oak.base.services.i18nres.*;
import com.levin.oak.base.services.i18nres.req.*;
import com.levin.oak.base.services.i18nres.info.*;

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
* 国际化资源业务控制器
*
* @author Auto gen by simple-dao-codegen, @time: 2024年2月19日 上午10:56:10, 代码生成哈希校验码：[8d3d2b9b3aeaf10d68d635f58e8499b6]，请不要修改和删除此行内容。
*
*/

//生成的控制器
@RestController(PLUGIN_PREFIX + "BizI18nResController")
@RequestMapping(API_PATH + "I18nRes") //i18nres

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizI18nResController", havingValue = "true", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME + "-")

//类注解，@Tag的name属性关联权限的资源标识
@Tag(name = E_I18nRes.BIZ_NAME, description = E_I18nRes.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD

@Slf4j
public class BizI18nResController extends I18nResController{

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
    * @param req QueryI18nResReq
    * @return  ApiResp<StatI18nResReq.Result>
    */
    @GetMapping("stat") //默认开放
    @Operation(summary = STAT_ACTION, description = STAT_ACTION + " " + BIZ_NAME)
    public ApiResp<StatI18nResReq.Result> stat(@Valid StatI18nResReq req, SimplePaging paging) {

        req = checkRequest(STAT_ACTION, req);

        return ApiResp.ok(checkResponse(STAT_ACTION, bizI18nResService.stat(req, paging)));
    }

}