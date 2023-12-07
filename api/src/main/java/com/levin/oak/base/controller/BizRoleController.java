package com.levin.oak.base.controller;

import com.levin.commons.dao.PagingData;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.validation.annotation.*;

import java.util.stream.Collectors;

//import org.apache.dubbo.config.annotation.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.ui.annotation.*;
import com.levin.commons.rbac.ResAuthorize;

import com.levin.oak.base.controller.base.role.*;

import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.role.req.*;
import com.levin.oak.base.services.role.info.*;

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
 * 角色业务控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月26日 上午8:12:20, 代码生成哈希校验码：[29f8bd4fe2fcb69e174f59d7053b15ca]，请不要修改和删除此行内容。
 */

//生成的控制器
@RestController(PLUGIN_PREFIX + "BizRoleController")
@RequestMapping(API_PATH + "Role") //role

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizRoleController", havingValue = "true", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME + "-" + E_Role.BIZ_NAME)

//类注解，@Tag的name属性关联权限的资源标识
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD

@Slf4j
public class BizRoleController extends RoleController {

    @Autowired
    AuthService authService;

    @Autowired
    RbacService rbacService;

    /**
     * 分页列表查找
     *
     * @param req    QueryRoleReq
     * @param paging
     * @return ApiResp<PagingData < RoleInfo>>
     */
    @Override
    public ApiResp<PagingData<RoleInfo>> list(QueryRoleReq req, SimplePaging paging) {

        req = checkRequest(QUERY_LIST_ACTION, req);

        PagingData<RoleInfo> pagingData = roleService.query(req, paging);

        //只过滤出当前用户完全拥有权限的角色
        if (pagingData.getItems() != null) {
            //@todo 只过滤出当前用户完全拥有权限的角色

            if (!(pagingData instanceof DefaultPagingData)) {
                pagingData = new DefaultPagingData<>(pagingData);
            }

            ((DefaultPagingData) (pagingData)).setItems(
                    pagingData.getItems().stream()
                            .filter(roleInfo -> rbacService.canAssignRole(authService.getUserInfo(), null, roleInfo.getCode(), null))
                            .collect(Collectors.toList())
            );
        }

        return ApiResp.ok(checkResponse(QUERY_LIST_ACTION, pagingData));
    }

    /**
     * 新增
     *
     * @param req CreateRoleEvt
     * @return ApiResp
     */
    @Override
    public ApiResp<String> create(CreateRoleReq req) {

        req = checkRequest(CREATE_ACTION, req);

        return ApiResp.ok(bizRoleService.create(authService.getUserInfo(), req));
    }

    /**
     * 更新
     *
     * @param req UpdateRoleReq
     * @param id
     */
    @Override
    public ApiResp<Boolean> update(UpdateRoleReq req, String id) {

        req.updateIdWhenNotBlank(id);

        req = checkRequest(UPDATE_ACTION, req);

        return ApiResp.ok(bizRoleService.update(authService.getUserInfo(), req));
    }

}
