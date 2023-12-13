package com.levin.oak.base.biz;

import cn.hutool.core.bean.BeanUtil;
import com.levin.commons.conditional.ConditionalOn;
import com.levin.commons.dao.PagingData;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.IsNull;
import com.levin.commons.dao.support.DefaultPagingData;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.RbacUserObject;
import com.levin.commons.service.exception.AuthorizationException;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.oak.base.biz.rbac.RbacLoadService;
import com.levin.oak.base.biz.rbac.RbacMethodService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.E_SimpleApi;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.info.RoleInfo;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.role.req.QueryRoleReq;
import com.levin.oak.base.services.role.req.RoleIdReq;
import com.levin.oak.base.services.role.req.UpdateRoleReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.levin.commons.conditional.ConditionalOn.Action.OnMissingBean;
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

        //只过滤出当前用户完全拥有权限的角色
        if (pagingData.getItems() != null) {

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

        RoleInfo info = roleService.findById(BeanUtil.copyProperties(req, RoleIdReq.class));

        Assert.notNull(info, "角色不存在");

        checkCode(info.getCode());

        // 忽略code，不允许修改 code
        req.setCode(null);

        checkPermissions(userPrincipal, req.getPermissionList());

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

        RoleInfo info = roleService.findById(req);

        Assert.notNull(info, "角色不存在");

        checkCode(info.getCode());

        RbacUserObject<String> user = rbacLoadService.loadUser(userPrincipal);

        //只允许用户删除自己创建的角色，或者超级管理员，或者租户管理员
        Assert.isTrue(user.getId().equals(info.getCreator())
                || user.isSuperAdmin()
                || (user.isTenantAdmin() && user.getTenantId().equals(info.getTenantId())), "当前用户没有权限删除该角色");

        checkPermissions(userPrincipal, info.getPermissionList());

        return roleService.delete(req);

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

        Assert.hasText(roleCode, "角色编码不能为空");

        Assert.isTrue(roleCode.startsWith("R_"), "角色编码必须以 R_ 开头");

        Assert.isTrue(!roleCode.equalsIgnoreCase(RbacRoleObject.SA_ROLE), "角色编码 R_SA 不可使用");
    }

}
