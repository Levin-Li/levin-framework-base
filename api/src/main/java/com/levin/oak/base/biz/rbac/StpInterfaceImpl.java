package com.levin.oak.base.biz.rbac;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

/**
 * 参考文档
 * https://sa-token.dev33.cn/doc/index.html#/use/jur-auth
 * <p>
 * <p>
 * 自定义权限验证接口扩展
 */
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@Service(PLUGIN_PREFIX + "StpInterface")
@Slf4j
@ConditionalOnClass(StpInterface.class)
@ConditionalOnProperty(value = PLUGIN_PREFIX + "StpInterface", havingValue = "true", matchIfMissing = true)
public class StpInterfaceImpl
        implements StpInterface {

    @Autowired
    RbacPermissionService<Object> rbacPermissionService;

    @PostConstruct
    void init() {
        // SaStrategy.me.setHasElement();
        log.info("StpInterface init...");
        SaManager.setStpInterface(this);
    }

    @Override
    public List<String> getPermissionList(Object principal, String loginType) {
        return rbacPermissionService.getPermissionList(principal.toString());
    }

    @Override
    public List<String> getRoleList(Object principal, String loginType) {
        return rbacPermissionService.getRoleList(principal.toString());
    }

}
