package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.InjectVarService;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.service.support.InjectConst;
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
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//@ConditionalOnMissingBean(InjectVarService.class) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleWebInjectVarService", matchIfMissing = true)
@Slf4j
public class ModuleWebInjectVarServiceImpl implements InjectVarService {

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
        public <ID extends Serializable> ID getOrgId() {
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

    @Autowired
    PluginManager pluginManager;


    @PostConstruct
    public void init() {

        log.info("启用模块Web注入服务...");

        //重要逻辑点

        //1、设置上下文
        variableResolverManager.addSuppliers(() -> VariableInjector.newResolverByMap(() -> Arrays.asList(getInjectVars())));

        //2、动态加入用户相关的动态的会变的数据，如用户能访问的机构列表，用户权限列表

        //@todo 重要待实现
    }

    /**
     * 清除缓存
     */
    @Override
    public void clearCache() {
        httpServletRequest.removeAttribute(INJECT_VAR_CACHE_KEY);
    }

    public List<String> getBizStack(Thread thread) {

        if (thread == null) {
            thread = Thread.currentThread();
        }

        List<Plugin> plugins = pluginManager.getInstalledPlugins();
        return Stream.of(thread.getStackTrace())

                //过滤自己
                .filter(e -> !e.getClassName().startsWith(getClass().getName()))
                .filter(e -> !e.getClassName().startsWith(InjectVarService.class.getName()))

                //只过滤出业务类
                .filter(e -> plugins.stream().anyMatch(plugin -> e.getClassName().startsWith(plugin.getPackageName())))

                .map(e -> e.getClassName() + ":" + e.getMethodName() + "(" + e.getFileName() + ":" + e.getLineNumber() + ")")

                .collect(Collectors.toList());
    }

    @Override
    public Map<String, ?> getInjectVars() {

        //如果当前不是web请求，则不注入
        if (RequestContextHolder.getRequestAttributes() == null) {
            if (log.isDebugEnabled()) {
                log.debug("当前不是web请求");
            }
            return Collections.emptyMap();
        }

        //缓存在请求中
        Map<String, ?> result = (Map<String, ?>) httpServletRequest.getAttribute(INJECT_VAR_CACHE_KEY);

        if (result != null) {
            return result;
        }

        TenantInfo tenantInfo = bizTenantService.checkCurrentUserTenantInfo();

        MapUtils.Builder<String, Object> builder
                = MapUtils.putFirst("tenantBindDomainEnable", frameworkProperties.getTenantBindDomain().isEnable());

        builder.put(InjectConst.IS_WEB_CONTEXT, true);

        //当前登录用户
        if (baseAuthService.isLogin()) {

            //暂时兼容
            //获取登录信息
            UserInfo userInfo = baseAuthService.getUserInfo();

            builder.put(InjectConst.USER_ID, userInfo.getId())
                    .put(InjectConst.USER_NAME, userInfo.getName())
                    .put(InjectConst.USER, userInfo)

                    .put(InjectConst.IS_SUPER_ADMIN, userInfo.isSuperAdmin())
                    .put(InjectConst.IS_TENANT_ADMIN, userInfo.isTenantAdmin())

                    .put(InjectConst.ORG, userInfo.getOrg())
                    .put(InjectConst.ORG_ID, userInfo.getOrgId());

            if (log.isDebugEnabled()) {
                log.debug("当前登录用户：{}, {}", userInfo, getBizStack());
            }

        } else {
            //匿名用户
            builder.put(InjectConst.USER_ID, anonymous.getId())
                    .put(InjectConst.USER_NAME, anonymous.getName())
                    .put(InjectConst.USER, anonymous)

                    .put(InjectConst.IS_SUPER_ADMIN, false)
                    .put(InjectConst.IS_TENANT_ADMIN, false)

                    .put(InjectConst.ORG, null)
                    .put(InjectConst.ORG_ID_LIST, null)
                    .put(InjectConst.ORG_ID, null);

            log.debug("当前登录用户未登录");
        }

        //租户信息
        if (tenantInfo != null) {
            builder.put(InjectConst.TENANT, tenantInfo)
                    .put(InjectConst.TENANT_ID, tenantInfo.getId());
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
