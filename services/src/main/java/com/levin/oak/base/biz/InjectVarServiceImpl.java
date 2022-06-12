package com.levin.oak.base.biz;


import com.levin.commons.dao.DaoContext;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.user.info.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "InjectVarService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "InjectVarService", matchIfMissing = true)
@Slf4j
public class InjectVarServiceImpl implements InjectVarService {

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

    @Resource
    AuthService baseAuthService;

    @Resource
    Environment environment;

    @Value("${spring.application.name:}")
    String appModuleName = "";

    @Resource
    SimpleDao dao;

    @Resource
    BizTenantService bizTenantService;

    @Resource
    HttpServletRequest httpServletRequest;

    @Resource
    FrameworkProperties frameworkProperties;

    @PostConstruct
    public void init() {
        log.info("Http请求变量注入服务启用...");
    }

    /**
     *
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

        MapUtils.Builder<String, Object> builder = MapUtils.putFirst("tenantBindDomainEnable", frameworkProperties.getTenantBindDomain().isEnable());

        //当前登录用户
        if (baseAuthService.isLogin()) {
            //暂时兼容
            //获取登录信息
            UserInfo userInfo = baseAuthService.getUserInfo();
            builder.put(InjectConsts.USER_ID, userInfo.getId())
                    .put(InjectConsts.USER_NAME, userInfo.getName())
                    .put(InjectConsts.USER, userInfo)
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

        //设置注入变量到Dao上下文中
        DaoContext.threadContext.putAll(ctx);

        //缓存到请求对象重
        httpServletRequest.setAttribute(INJECT_VAR_CACHE_KEY, result);

        if (log.isTraceEnabled()) {
            log.trace("getInjectVars ok");
        }

        return result;
    }

}
