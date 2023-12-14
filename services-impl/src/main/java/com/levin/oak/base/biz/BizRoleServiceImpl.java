package com.levin.oak.base.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.PagingData;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.support.DefaultPagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.RbacUserObject;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.oak.base.biz.rbac.RbacLoadService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.E_SimpleApi;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.info.RoleInfo;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.role.req.QueryRoleReq;
import com.levin.oak.base.services.role.req.RoleIdReq;
import com.levin.oak.base.services.role.req.UpdateRoleReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.levin.commons.dao.EntityOpConst.DELETE_ACTION;
import static com.levin.commons.dao.EntityOpConst.UPDATE_ACTION;
import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.MAINTAIN_ACTION;


@Service(PLUGIN_PREFIX + "BizRoleService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizRoleService", havingValue = "true", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Role.BIZ_NAME, description = E_Role.BIZ_NAME + MAINTAIN_ACTION)

@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_SimpleApi.SIMPLE_CLASS_NAME})

public class BizRoleServiceImpl implements BizRoleService<Serializable> {

    @Autowired
    SimpleDao simpleDao;

    @Autowired
    RoleService roleService;

    @Autowired
    RbacLoadService<Serializable> rbacLoadService;

    @Autowired
    RbacService<Serializable> rbacService;

    @Override
    public PagingData<RoleInfo> list(Serializable userPrincipal, QueryRoleReq req, SimplePaging paging) {

        PagingData<RoleInfo> pagingData = roleService.query(req, paging);

        RbacUserObject<String> user = rbacLoadService.loadUser(userPrincipal);

        //只过滤出当前用户完全拥有权限的角色
        if (!user.isSuperAdmin()
                && pagingData.getItems() != null) {

            //@todo 只过滤出当前用户完全拥有权限的角色

            if (!(pagingData instanceof DefaultPagingData)) {
                pagingData = new DefaultPagingData<>(pagingData);
            }

            ((DefaultPagingData) (pagingData)).setItems(
                    pagingData.getItems().stream()
                            .filter(roleInfo -> rbacService.canAssignRole(userPrincipal, null, roleInfo.getCode(), null))
                            .collect(Collectors.toList())
            );

        }

        return pagingData;
    }

    /**
     * 创建记录，返回主键ID
     *
     * @param userPrincipal
     * @param req
     * @return pkId 主键ID
     */
    public String create(Serializable userPrincipal, CreateRoleReq req) {

        checkCode(req.getCode());

        checkPermissions(userPrincipal, req.getPermissionList());

        RbacUserObject<String> user = rbacLoadService.loadUser(userPrincipal);

        if (!user.isSuperAdmin()) {

            req.setTenantId(user.getId());

            Assert.notBlank(req.getTenantId(), "租户ID不能为空");

            if (!user.isTenantAdmin()) {
                req.setOrgId(user.getOrgId());
                Assert.notBlank(req.getOrgId(), "机构ID不能为空");
            }
        }

        return roleService.create(req);
    }

    /**
     * 更新记录，并返回更新是否成功
     *
     * @param userPrincipal
     * @param req
     * @return boolean 是否成功
     */
    public boolean update(Serializable userPrincipal, UpdateRoleReq req) {

        loadAndCheck(userPrincipal, BeanUtil.copyProperties(req, RoleIdReq.class), DELETE_ACTION);

        // 忽略code，不允许修改 code
        req.setCode(null);

        return roleService.update(req);
    }

    /**
     * 删除记录，并返回删除是否成功
     *
     * @param userPrincipal
     * @param req
     * @return boolean 删除是否成功
     */
    @Override
    public boolean delete(Serializable userPrincipal, RoleIdReq req) {

        //只允许用户删除自己创建的角色，或者超级管理员，或者租户管理员

        loadAndCheck(userPrincipal, req, DELETE_ACTION);

        return roleService.delete(req);
    }

    /**
     * 加载并检查当前用户是否有权限使用指定的权限
     *
     * @param userPrincipal
     * @param req
     * @param action
     * @return RoleInfo
     */
    protected RoleInfo loadAndCheck(Serializable userPrincipal, RoleIdReq req, String action) {

        RoleInfo info = roleService.findById(req);

        Assert.notNull(info, "角色不存在");

        checkCode(info.getCode());

        RbacUserObject<String> user = rbacLoadService.loadUser(userPrincipal);

        if (!user.isSuperAdmin()) {

            Assert.notBlank(info.getTenantId(), "租户ID不能为空");

            if (!user.isTenantAdmin()) {
                Assert.notBlank(info.getOrgId(), "机构ID不能为空");
            }

            //只能操作自己租户的角色
            Assert.isTrue(user.getTenantId().equals(info.getTenantId()), "不能{}该角色{}", action, info.getId());
        }

        checkPermissions(user, info.getPermissionList());

        return info;
    }


    /**
     * 检查当前用户是否有权限使用指定的权限
     *
     * @param permissionList
     */
    protected void checkPermissions(Serializable userPrincipal, List<String> permissionList) {

        if (permissionList == null || permissionList.isEmpty()) {
            return;
        }

        boolean isAuthorized = rbacService.isAuthorized(userPrincipal, true, permissionList, (rp, info) -> {
            throw new AuthorizationException("未授权的资源：" + rp + "-" + info);
        });

        if (!isAuthorized) {
            throw new AuthorizationException("权限列表中不能存在未拥有的权限");
        }

    }

    protected void checkCode(String roleCode) {

        Assert.notBlank(roleCode, "角色编码不能为空");

        Assert.isTrue(roleCode.startsWith("R_"), "角色编码必须以 R_ 开头");

        Assert.isTrue(!roleCode.equalsIgnoreCase(RbacRoleObject.SA_ROLE), "角色编码 R_SA 不可使用");
    }

}
