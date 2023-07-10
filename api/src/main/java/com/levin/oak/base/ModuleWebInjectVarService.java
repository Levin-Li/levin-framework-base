package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.InjectVarService;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.support.VariableInjector;
import com.levin.commons.service.support.VariableResolverManager;
import com.levin.commons.utils.MapUtils;

import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.user.info.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/**
 * web模块注入服务
 * <p>
 * 正常情况下，一个项目只需要一个注入服务，为项目提供注入上下文。
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月6日 下午2:07:08, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[d25caab5a28265c3bb9ff7beb74f1af2], 请不要修改和删除此行内容。
 */

//默认不启用
@Service(PLUGIN_PREFIX + "ModuleWebInjectVarService")
@ConditionalOnMissingBean({InjectVarService.class}) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleWebInjectVarService", matchIfMissing = true)
@Slf4j
public class ModuleWebInjectVarService implements InjectVarService {

    public static final RbacUserInfo anonymous = new RbacUserInfo() {
        @Override
        public String getNickname() {
            return "anonymous";
        }

        @Override
        public String getEmail() {
            return "anonymous@163.com";
        }

        @Override
        public String getTelephone() {
            return null;
        }

        @Override
        public String getAvatar() {
            return "anonymous";
        }

        @Override
        public boolean isSuperAdmin() {
            return false;
        }

        @Override
        public String getName() {
            return "anonymous";
        }

        @Override
        public String getTenantId() {
            return null;
        }

        @Override
        public <ID extends Serializable> ID getId() {
            //throw new IllegalStateException("anonymous user");
            return null;
        }
    };

    @Autowired
    Environment environment;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    VariableResolverManager variableResolverManager;

    @Autowired
    BizTenantService bizTenantService;

    @Autowired
    AuthService baseAuthService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Value("${spring.application.name:}")
    String appModuleName = "";

    @PostConstruct
    public void init() {
        log.info("启用模块Web注入服务...");
        //设置上下文
        variableResolverManager.add(VariableInjector.newResolverByMap(() -> Arrays.asList(getInjectVars())));
    }

    /**
     * 清除缓存
     */
    @Override
    public void clearCache() {
        httpServletRequest.removeAttribute(INJECT_VAR_CACHE_KEY);
    }

    @Override
    public Map<String, ?> getInjectVars() {

        //缓存在请求中
        Map<String, ?> result = (Map<String, ?>) httpServletRequest.getAttribute(INJECT_VAR_CACHE_KEY);

        if (result != null) {
            return result;
        }

        TenantInfo tenantInfo = bizTenantService.checkAndGetCurrentUserTenant();

        MapUtils.Builder<String, Object> builder
                = MapUtils.putFirst("tenantBindDomainEnable", frameworkProperties.getTenantBindDomain().isEnable());

        //当前登录用户
        if (baseAuthService.isLogin()) {
            //暂时兼容
            //获取登录信息
            UserInfo userInfo = baseAuthService.getUserInfo();

            builder.put(InjectConsts.USER_ID, userInfo.getId())
                    .put(InjectConsts.USER_NAME, userInfo.getName())
                    .put(InjectConsts.USER, userInfo)
                    .put(InjectConsts.IS_SUPER_ADMIN, userInfo.isSuperAdmin())
                    .put(InjectConsts.IS_TENANT_ADMIN, userInfo.getRoleList() != null && userInfo.getRoleList().contains(RbacRoleObject.ADMIN_ROLE))
                    .put(InjectConsts.ORG, userInfo.getOrg())
                    .put(InjectConsts.ORG_ID, userInfo.getOrgId());

        } else {
            //匿名用户
            builder.put(InjectConsts.USER, anonymous);
        }

        //租户信息
        if (tenantInfo != null) {
            builder.put(InjectConsts.TENANT, tenantInfo)
                    .put(InjectConsts.TENANT_ID, tenantInfo.getId());
        }

        final Map<String, Object> ctx = builder.build();

        result = ctx;

        //缓存到请求对象重
        httpServletRequest.setAttribute(INJECT_VAR_CACHE_KEY, result);

        if (log.isTraceEnabled()) {
            log.trace("getInjectVars ok");
        }

        return result;
    }
}
