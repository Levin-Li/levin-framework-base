package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserObject;
import com.levin.oak.base.services.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

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
public class DefaultUserLoadService
        implements UserLoadService<Object> {

    @Autowired
    ApplicationContext context;

    @Autowired //@DubboReference
    UserService userService;

//    @DubboReference
//    RoleService roleService;
//
//    @DubboReference
//    BizUserService bizUserService;
//
//    @DubboReference
//    BizRoleService bizRoleService;


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
    public RbacUserObject<String> loadUser(Object principal) {

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
}
