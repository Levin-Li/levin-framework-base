package com.levin.oak.base.biz.rbac;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.rbac.RbacUserObject;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

/**
 * Rbac初始化服务
 *
 * @todo 分离web相关部分
 */

@Slf4j
//@org.springframework.context.annotation.Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Service(PLUGIN_PREFIX + "DefaultUserLoadService")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "DefaultUserLoadService", havingValue = "true", matchIfMissing = true)
public class DefaultRbacLoadService
        implements RbacLoadService<Serializable> {

    @Autowired
    ApplicationContext context;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    SimpleDao simpleDao;

    @PostConstruct
    public void init() {

        log.info("默认Rbac初始化服务启用...");

    }

    /**
     * 加载用户
     *
     * @param principal 登录ID
     * @return 用户信息
     */
    @Override
    public RbacUserObject<String> loadUser(Serializable principal) {

        Assert.notNull(principal, "principal must not be null");

        if (principal instanceof RbacUserObject) {
            return (RbacUserObject<String>) principal;
        }

        if (principal instanceof String) {
            Assert.hasText(((String) principal), "principal must not be empty");
            return userService.findById((String) principal);
        } else if (principal instanceof Number) {
            return userService.findById(String.valueOf(principal));
        }

        throw new IllegalArgumentException("unknown principal type: " + principal.getClass().getName());
    }


    /**
     * 获取指定角色的权限列表
     *
     * @param tenantId
     * @param roleCodeList
     * @return
     */
    @Override
    public List<String> loadRolePermissionList(Serializable tenantId, List<String> roleCodeList) {

        //@todo 优化，使用缓存

       // boolean hasTenant = StringUtils.hasText(tenantId);

        return new ArrayList<>(
                //获取指定用户的权限列表
                simpleDao.selectFrom(Role.class)
                        .select("distinct " + E_Role.permissionList)
                        .eq(E_Role.enable, true)
                        .in(E_Role.code, roleCodeList)

                        .or()
                        .eq(E_Role.tenantId, tenantId)
                        .isNull(E_Role.tenantId)

//                        .appendByAnnotations(hasTenant, E_Role.tenantId, tenantId, Eq.class)
//                        .appendByAnnotations(!hasTenant, E_Role.tenantId, null, IsNull.class)
                        .end()

                        .find(String.class)
                        .stream()
                        .filter(StringUtils::hasText)
                        //JSON 转换
                        .flatMap(json -> (JsonStrArrayUtils.<String>parse(json, null, null)).stream())
//                .map(json -> (List<ResPermission>) gson.fromJson(json, resPermissionListType))
                        .filter(StringUtils::hasText)
                        .collect(Collectors.toSet())
        );
    }
}
