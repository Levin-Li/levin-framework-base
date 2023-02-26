package com.levin.oak.base.controller.tenant;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.entities.E_User;
import com.levin.oak.base.entities.User;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.*;
import com.levin.oak.base.services.user.req.CreateUserReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

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

@RestController(PLUGIN_PREFIX + "TenantController")
//@RequestMapping(API_PATH + "tenant")
@RequestMapping(API_PATH + "Tenant")

@Slf4j
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "TenantController", matchIfMissing = true)

//默认需要权限访问

@ResAuthorize(domain = ID, type = "平台" + TYPE_NAME, isAndMode = true, anyRoles = {RbacRoleObject.SA_ROLE})
@Tag(name = E_Tenant.BIZ_NAME, description = E_Tenant.BIZ_NAME + MAINTAIN_ACTION)

@Valid
public class TenantController extends BaseController {

    private static final String BIZ_NAME = E_Tenant.BIZ_NAME;

    @Autowired
    TenantService tenantService;

    @Autowired
    SimpleDao simpleDao;

    @Autowired
    AuthService authService;

    /**
     * 分页查找
     *
     * @param req QueryTenantReq
     * @return ApiResp<PagingData < TenantInfo>>
     */
    @GetMapping("/query")
    @Operation( summary = QUERY_ACTION, description = QUERY_ACTION + " " + BIZ_NAME)
    public ApiResp<PagingData<TenantInfo>> query(QueryTenantReq req, SimplePaging paging) {
        return ApiResp.ok(tenantService.query(req, paging));
    }

    /**
     * 新增
     *
     * @param req CreateTenantEvt
     * @return ApiResp
     */
    @PostMapping
    @Operation( summary = CREATE_ACTION, description = CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<String> create(@RequestBody CreateTenantReq req) {

        String tenantId = tenantService.create(req);

        //创建租户后，自动创建租户用户
        if (simpleDao.selectFrom(User.class)
                .eq(E_User.tenantId, tenantId)
                .count() < 1) {
            simpleDao.create(new CreateUserReq()
                    .setEmail("admin")
                    .setPassword(authService.encryptPassword("123456"))
                    .setName("管理员")
                    .setStaffNo("0000")
                    .setRoleList(Arrays.asList(RbacRoleObject.ADMIN_ROLE))
                    .setTenantId(tenantId)
            );
        }

        return ApiResp.ok(tenantId);
    }

    /**
     * 批量新增
     *
     * @param reqList List<CreateTenantEvt>
     * @return ApiResp
     */
    @PostMapping("/batchCreate")
    @Operation( summary = BATCH_CREATE_ACTION, description = BATCH_CREATE_ACTION + " " + BIZ_NAME)
    public ApiResp<List<String>> batchCreate(@RequestBody List<CreateTenantReq> reqList) {
        return ApiResp.ok(tenantService.batchCreate(reqList));
    }

    /**
     * 查看详情
     *
     * @param req QueryTenantByIdReq
     */
    @GetMapping("")
    @Operation( summary = VIEW_DETAIL_ACTION, description = VIEW_DETAIL_ACTION + " " + BIZ_NAME)
    public ApiResp<TenantInfo> retrieve(@NotNull TenantIdReq req) {
        return ApiResp.ok(tenantService.findById(req));
    }

    /**
     * 更新
     *
     * @param req UpdateTenantReq
     */
    @PutMapping({""})
    @Operation( summary = UPDATE_ACTION, description = UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> update(@RequestBody UpdateTenantReq req) {
        return ApiResp.ok(checkResult(tenantService.update(req), UPDATE_ACTION));
    }

    /**
     * 批量更新
     */
    @PutMapping("/batchUpdate")
    @Operation( summary = BATCH_UPDATE_ACTION, description = BATCH_UPDATE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchUpdate(@RequestBody List<UpdateTenantReq> reqList) {
        return ApiResp.ok(checkResult(tenantService.batchUpdate(reqList), BATCH_UPDATE_ACTION));
    }

    /**
     * 删除
     *
     * @param req TenantIdReq
     */
    @DeleteMapping({""})
    @Operation( summary = DELETE_ACTION, description = DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> delete(@NotNull TenantIdReq req) {
        return ApiResp.ok(checkResult(tenantService.delete(req), DELETE_ACTION));
    }

    /**
     * 批量删除
     *
     * @param req DeleteTenantReq
     */
    @DeleteMapping({"/batchDelete"})
    @Operation( summary = BATCH_DELETE_ACTION, description = BATCH_DELETE_ACTION + " " + BIZ_NAME)
    public ApiResp<Integer> batchDelete(@NotNull DeleteTenantReq req) {
        return ApiResp.ok(checkResult(tenantService.batchDelete(req), BATCH_DELETE_ACTION));
    }

    /**
     * 检查结果
     *
     * @param n
     * @param action
     * @return
     */
    protected int checkResult(int n, String action) {
        Assert.isTrue(n > 0, action + BIZ_NAME + "失败");
        return n;
    }
}
