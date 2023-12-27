package com.levin.oak.base.controller;

import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.PagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.ui.annotation.CRUD;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.controller.base.org.OrgController;
import com.levin.oak.base.entities.E_Org;
import com.levin.oak.base.services.org.info.OrgInfo;
import com.levin.oak.base.services.org.req.CreateOrgReq;
import com.levin.oak.base.services.org.req.OrgIdReq;
import com.levin.oak.base.services.org.req.QueryOrgReq;
import com.levin.oak.base.services.org.req.UpdateOrgReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
 * 机构业务控制器
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月12日 下午11:15:26, 代码生成哈希校验码：[0c99c1d0138f1566af23380c080e968f]，请不要修改和删除此行内容。
 */

//生成的控制器
@RestController(PLUGIN_PREFIX + "BizOrgController")
@RequestMapping(API_PATH + "Org") //org

@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizOrgController", havingValue = "true", matchIfMissing = true)

//默认需要权限访问
@ResAuthorize(domain = ID, type = SYS_TYPE_NAME + "-")

//类注解，@Tag的name属性关联权限的资源标识
@Tag(name = E_Org.BIZ_NAME, description = E_Org.BIZ_NAME + MAINTAIN_ACTION)
@Validated //@Valid
@CRUD

@Slf4j
public class BizOrgController extends OrgController {

    @Autowired
    AuthService authService;

    List<String> allowOpList = Arrays.asList(CREATE_ACTION, UPDATE_ACTION, DELETE_ACTION, VIEW_DETAIL_ACTION);

    /**
     * 检查请求
     *
     * @param action
     * @param req
     * @return
     */
    @Override
    protected <T> T checkRequest(String action, T req) {

        Assert.isTrue(allowOpList.contains(action), "不支持的操作{}", action);

        return super.checkRequest(action, req);
    }

    /**
     * 分页列表查找
     *
     * @param req    QueryOrgReq
     * @param paging
     * @return ApiResp<PagingData < OrgInfo>>
     */
    @Override
    public ApiResp<PagingData<OrgInfo>> list(QueryOrgReq req, SimplePaging paging) {

        RbacUserInfo<String> userInfo = authService.getUserInfo();

        bizOrgService.checkAccessible(userInfo, req.getTenantId(), req.getParentId(), req.getId());

        if (!bizOrgService.canAccessAllOrg(userInfo)) {

        }

        return super.list(req, paging);
    }

    /**
     * 新增
     *
     * @param req CreateOrgEvt
     * @return ApiResp
     */
    @Override
    public ApiResp<String> create(CreateOrgReq req) {

        bizOrgService.checkAccessible(authService.getUserInfo(), req.getTenantId(), req.getParentId(), null);

        return super.create(req);
    }

    /**
     * 查看详情
     *
     * @param req QueryOrgByIdReq
     * @param id
     */
    @Override
    public ApiResp<OrgInfo> retrieve(OrgIdReq req, String id) {

        req.updateIdWhenNotBlank(id);
        bizOrgService.checkAccessible(authService.getUserInfo(), req.getTenantId(), null, req.getId());

        return super.retrieve(req, id);
    }

    /**
     * 更新
     *
     * @param req UpdateOrgReq
     * @param id
     */
    @Override
    public ApiResp<Boolean> update(UpdateOrgReq req, String id) {
        req.updateIdWhenNotBlank(id);
        bizOrgService.checkAccessible(authService.getUserInfo(), req.getTenantId(), req.getParentId(), req.getId());
        return super.update(req, id);
    }

    /**
     * 删除
     *
     * @param req OrgIdReq
     * @param id
     */
    @Override
    public ApiResp<Boolean> delete(OrgIdReq req, String id) {

        req.updateIdWhenNotBlank(id);
        bizOrgService.checkAccessible(authService.getUserInfo(), req.getTenantId(), null, req.getId());

        return super.delete(req, id);
    }
}
