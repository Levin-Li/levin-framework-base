package com.levin.oak.base.biz.rbac;

import cn.hutool.core.util.StrUtil;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.RbacUserObject;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.ResPermission;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizRoleService;
import com.levin.oak.base.biz.BizUserService;
import com.levin.oak.base.biz.CaptchaService;
import com.levin.oak.base.biz.SmsCodeService;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.appclient.req.CreateAppClientReq;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.org.req.CreateOrgReq;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.role.req.CreateRoleReq;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.CreateTenantReq;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.services.user.req.CreateUserReq;
import com.levin.oak.base.utils.AmisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.biz.BizUserService.SA_ACCOUNT;

/**
 * Rbac初始化服务
 *
 * @todo 分离web相关部分
 */

@Slf4j
//@org.springframework.context.annotation.Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Service(PLUGIN_PREFIX + "DefaultUserLoadService")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "DefaultUserLoadService", matchIfMissing = true)
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
