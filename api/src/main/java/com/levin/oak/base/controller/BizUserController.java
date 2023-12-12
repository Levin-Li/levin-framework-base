package com.levin.oak.base.controller;

import com.levin.commons.service.exception.AuthorizationException;
import com.levin.oak.base.biz.bo.tenantapp.StatTenantAppReq;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
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

import javax.servlet.http.*;

//import org.apache.dubbo.config.annotation.*;

import com.levin.commons.dao.*;
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import com.levin.commons.ui.annotation.*;
import com.levin.commons.rbac.ResAuthorize;

import javax.validation.constraints.*;

import com.levin.oak.base.controller.*;
import com.levin.oak.base.controller.base.user.*;

import com.levin.oak.base.*;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.biz.*;

import com.levin.oak.base.services.user.*;
import com.levin.oak.base.services.user.req.*;
import com.levin.oak.base.services.user.info.*;

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
 * 用户业务控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月26日 上午8:12:21, 代码生成哈希校验码：[418ba6133de554435b1c8c77c1677bfb]，请不要修改和删除此行内容。
 */

//生成的控制器
@RestController(PLUGIN_PREFIX + "BizUserController")
@RequestMapping(API_PATH + "User") //user

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizUserController", havingValue = "true", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME + "-")

//类注解，@Tag的name属性关联权限的资源标识
@Tag(name = E_User.BIZ_NAME, description = E_User.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD

@Slf4j
public class BizUserController extends BaseController {

    private static final String BIZ_NAME = E_User.BIZ_NAME;

    @Autowired
    BizUserService bizUserService;

    @Autowired
    AuthService authService;

    @Autowired
    RbacService rbacService;

    /**
     * 信息脱敏
     *
     * @param info
     * @return
     */
    UserInfo desensitize(UserInfo info) {

        if (info != null) {
            info.setPassword(null);
        }

        return info;
    }

    /**
     * 检查用户角色
     * <p>
     * 保证当前用户分配的给其它用户的角色必须是自己已经拥有的角色
     *
     * @param targetUserId
     * @param roleList
     */
    protected void checkCurrentUserCreateOrUpdateUserRole(String targetUserId, List<String> roleList) {

        if (CollectionUtils.isEmpty(roleList)) {
            return;
        }

        boolean ok = rbacService.canAssignRole(authService.getLoginId(), targetUserId, roleList, (roleCode, info) -> {
            throw new AuthorizationException("分配角色(" + roleCode + ")失败，请检查是否拥有角色所需的权限, " + info);
        });

        if (!ok) {
            throw new AuthorizationException("分配角色失败，请检查是否拥有角色所需的权限");
        }
    }

    /**
     * 分页查找
     *
     * @param req QueryUserReq
     * @return ApiResp<PagingData < UserInfo>>
     */
    @GetMapping({"/list"})
    @Operation(summary = QUERY_LIST_ACTION)
    public ApiResp<PagingData<UserInfo>> query(QueryUserReq req, SimplePaging paging) {

        PagingData<UserInfo> pagingData = bizUserService.query(authService.getUserInfo(), req, paging);

        //清楚密码
        if (pagingData.getItems() != null) {
            pagingData.getItems().forEach(this::desensitize);
        }

        return ApiResp.ok(pagingData);
    }


    /**
     * 新增
     *
     * @param req CreateUserEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation(summary = CREATE_ACTION)
    public ApiResp<String> create(@RequestBody CreateUserReq req) {

        checkCurrentUserCreateOrUpdateUserRole(null, req.getRoleList());

        if(!StringUtils.hasText(req.getOrgId())){
            req.setOrgId(authService.getUserInfo().getOrgId());
        }

        return ApiResp.ok(bizUserService.create(authService.getUserInfo(), req));

    }


    /**
     * 查看详情
     *
     * @param req QueryUserByIdReq
     */
    @GetMapping("")
    @Operation(summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<UserInfo> retrieve(@NotNull UserIdReq req) {
        return ApiResp.ok(desensitize(bizUserService.findById(authService.getUserInfo(), req)));
    }

    /**
     * 更新
     *
     * @param req UpdateUserReq
     */
    @PutMapping({""})
    @Operation(summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Boolean> update(@RequestBody UpdateUserReq req) {
        checkCurrentUserCreateOrUpdateUserRole(req.getId(), req.getRoleList());
        return ApiResp.ok(assertTrue(bizUserService.update(authService.getUserInfo(), req), UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req UserIdReq
     */
    @DeleteMapping({""})
    @Operation(summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Boolean> delete(@NotNull UserIdReq req) {
        return ApiResp.ok(assertTrue(bizUserService.delete(authService.getUserInfo(), req), DELETE_ACTION));
    }

}
