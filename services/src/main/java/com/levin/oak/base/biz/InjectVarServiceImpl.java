package com.levin.oak.base.biz;


import com.levin.commons.dao.SimpleDao;
import com.levin.commons.service.exception.AccessDeniedException;
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.services.rbac.AuthService;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.user.info.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Service(PLUGIN_PREFIX + "InjectVarService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "InjectVarService", matchIfMissing = true)
@Slf4j
public class InjectVarServiceImpl implements InjectVarService {

    @Resource
    AuthService baseAuthService;

    @Resource
    TenantService tenantService;

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

    final List<Map<String, ?>> defaultCtx = Collections.emptyList();

    @PostConstruct
    public void init() {
        log.info("Http请求变量注入服务启用...");
    }

    @Override
    public List<Map<String, ?>> getInjectVars() {

        TenantInfo tenantInfo = bizTenantService.getCurrentTenant();

        //如果当前没有域名
        if (tenantInfo == null) {
            //设置当前登录的域名
            bizTenantService.setCurrentTenantByDomain(httpServletRequest.getServerName());
        }

        tenantInfo = bizTenantService.getCurrentTenant();

        MapUtils.Builder<String, Object> builder = MapUtils.putFirst(InjectConsts.TENANT, tenantInfo);

        //当前登录用户
        if (baseAuthService.isLogin()) {
            //暂时兼容
            //获取登录信息
            UserInfo userInfo = baseAuthService.getUserInfo();

            //加载用户租户信息
            if (StringUtils.hasText(userInfo.getTenantId())) {

                TenantInfo tenantInfo2 = tenantService.findById(userInfo.getTenantId());

                if (tenantInfo2 != null) {

                    if (tenantInfo != null
                            && !tenantInfo.getId().contentEquals(tenantInfo2.getId())) {

                        //如果用户的租户和域名的租户不匹配，则抛出异常
                        throw new AccessDeniedException("当前用户归属的租户和当前访问的域名不匹配");
                    }

                    tenantInfo = tenantInfo2;
                }
            }

            builder.put(InjectConsts.USER_ID, userInfo.getId())
                    .put(InjectConsts.USER_NAME, userInfo.getName())
                    .put(InjectConsts.USER, userInfo)
                    .put(InjectConsts.ORG_ID, userInfo.getOrgId());

        } else {

            //匿名用户


        }

        //租户信息
        if (tenantInfo != null) {
            builder.put(InjectConsts.TENANT, tenantInfo)
                    .put(InjectConsts.TENANT_ID, tenantInfo.getId());
        }

        return Arrays.asList(builder.build());
    }

}
