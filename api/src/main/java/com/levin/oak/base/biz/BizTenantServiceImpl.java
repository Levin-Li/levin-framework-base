package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.support.SimplePaging;
import com.levin.commons.service.domain.DefaultSignatureReq;
import com.levin.commons.service.exception.AccessDeniedException;
import com.levin.commons.service.exception.ServiceException;
import com.levin.commons.utils.SignUtils;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.bo.tenant.StatTenantReq;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.entities.E_Permission;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.listener.ModuleSpringCacheEventListener;
import com.levin.oak.base.listener.ModuleSpringCacheEventListener.Action;
import com.levin.oak.base.services.appclient.AppClientService;
import com.levin.oak.base.services.appclient.info.AppClientInfo;
import com.levin.oak.base.services.appclient.req.QueryAppClientReq;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.MAINTAIN_ACTION;


@Service(PLUGIN_PREFIX + "BizTenantService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizTenantService", havingValue = "true", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Tenant.BIZ_NAME, description = E_Tenant.BIZ_NAME + MAINTAIN_ACTION)

@CacheConfig(cacheNames = {ID + CACHE_DELIM + E_Tenant.SIMPLE_CLASS_NAME}, cacheResolver = PLUGIN_PREFIX + "ModuleSpringCacheResolver")

public class BizTenantServiceImpl implements BizTenantService {

    @Autowired
    AuthService authService;

    @Autowired
    TenantService tenantService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @Autowired
    AppClientService appClientService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    InjectVarService injectVarService;

    final static ThreadLocal<TenantInfo> currentTenant = new ThreadLocal<>();

    @PostConstruct
    void init() {
        frameworkProperties.getSign().friendlyTip(log.isInfoEnabled(), info -> log.info(info));

        //首次启动先清除缓存
        tenantService.clearCache(E_Tenant.SIMPLE_CLASS_NAME);

        //如果缓存有变化，清除列表缓存
        ModuleSpringCacheEventListener.add((ctx, cache, action, key, value) -> cache.evict(E_Tenant.SIMPLE_CLASS_NAME)
                , ID + CACHE_DELIM + E_Tenant.SIMPLE_CLASS_NAME, TenantService.CK_PREFIX + "*", Action.Put, Action.Evict);
    }

    /**
     * 获取所有租户列表，并缓存结果
     *
     * @return
     */
    @Cacheable(unless = "#result == null", key = "'" + E_Tenant.SIMPLE_CLASS_NAME + "'") //
    public List<TenantInfo> getAllTenantList() {
        List<TenantInfo> items = tenantService.query(new QueryTenantReq().setEnable(true), null).getItems();
        return items == null ? Collections.emptyList() : items;
    }

    @Override
    public void clearThreadCacheData() {
        currentTenant.set(null);
    }

    @Override
    public TenantInfo checkCurrentUserTenantInfo() {

        //获取当前租户
        TenantInfo tenantInfo = getCurrentTenant();

        // 获取域名
        final String domain = request.getServerName();

        //如果当前没有域名，获取域名关联的租户
        if (tenantInfo == null
                && frameworkProperties.getTenantBindDomain().isEnable()) {

            //尝试设置租户信息
            tenantInfo = setCurrentTenantByDomain(domain);

            log.warn("当前请求的域名[ {} ]未关联租户, URL:{}, 调用堆栈：{}", domain, request.getRequestURL(), injectVarService.getBizStack());

        }

        //如果还没有登录
        if (!authService.isLogin()) {
            return tenantInfo;
        }

        //当前登录用户
        //暂时兼容
        //获取登录信息
        UserInfo userInfo = authService.getUserInfo();

        Assert.notNull(userInfo, "当前登录用户信息无法加载");

        //超管可以访问所有租户
        if (userInfo.isSuperAdmin()) {

            if (tenantInfo == null && StringUtils.hasText(userInfo.getTenantId())) {
                //加载超管的租户信息，并且设置为当前租户
                tenantInfo = setCurrentTenant(loadTenant(userInfo.getTenantId()));
            }

            return tenantInfo;
        }

        //普通用户必须有归属的租户
        Assert.notBlank(userInfo.getTenantId(), () -> new AccessDeniedException(403, "登录用户无归属的租户"));

        if (tenantInfo != null) {

            if (!tenantInfo.getId().contentEquals(userInfo.getTenantId())) {
                //如果用户的租户和域名的租户不匹配，则抛出异常
                throw new AccessDeniedException(400, "登录用户的租户和当前租户不匹配");
            }

            //当前租户和用户的租户一致
            return tenantInfo;
        }

        //加载用户的租户信息，并且设置为当前租户
        return setCurrentTenant(loadTenant(userInfo.getTenantId()));

    }


    @Override
    public TenantInfo setCurrentTenant(TenantInfo tenantInfo) {
        currentTenant.set(auditTenant(tenantInfo));
        return tenantInfo;
    }

    /**
     * 获取当前的租户信息
     *
     * @return
     */
    @Override
    public TenantInfo getCurrentTenant() {
        return (currentTenant.get());
    }

    /**
     * 获取当前的租户信息
     *
     * @param domain
     * @return
     */
    @Override
    public TenantInfo setCurrentTenantByDomain(String domain) {

        Assert.notBlank(domain, () -> new IllegalArgumentException("domain is blank"));

        TenantInfo tenantInfo = getCurrentTenant();

        if (tenantInfo == null) {
            //自动加载域名
            tenantInfo = setCurrentTenant(loadTenantByDomain(domain));
        }

        FrameworkProperties.Cfg sign = frameworkProperties.getSign();

        if (sign.isEnable()) {

            String path = request.getRequestURI().substring(getLen(request.getContextPath()));

            if (sign.isPathMatched(path)) {
                checkTenantAppSign(tenantInfo);
            }
        }

        return tenantInfo;
    }

    private int getLen(String txt) {
        return txt == null ? 0 : txt.length();
    }


    /**
     * 检查当前租户应用签名
     */
    protected TenantInfo checkTenantAppSign(TenantInfo tenantInfo) {

        if (tenantInfo == null) {
            return tenantInfo;
        }

        String url = request.getRequestURL().toString();

        log.debug("签名验证 url:{}", url);

        String clientId = request.getHeader(DefaultSignatureReq.Fields.clientId);

        //String clientSecret = request.getHeader(DefaultSignatureReq.Fields.clientSecret);

        String timestamp = request.getHeader(DefaultSignatureReq.Fields.timestamp);
        String nonceStr = request.getHeader(DefaultSignatureReq.Fields.nonceStr);
        String channelCode = request.getHeader(DefaultSignatureReq.Fields.channelCode);

        String sign = request.getHeader(DefaultSignatureReq.Fields.sign);

        Assert.notBlank(clientId, "应用ID不能为空");
        Assert.notBlank(channelCode, "渠道编码不能为空");
        Assert.notBlank(sign, "签名不能为空");

//        签名，验签规则:md5(Utf8(应用ID +  渠道编码 + 应用密钥 + timestamp + nonceStr ))

        AppClientInfo clientInfo = appClientService.findOne(
                new QueryAppClientReq()
                        .setAppId(clientId.trim())
                        .setEnable(true)
                        .setTenantId(tenantInfo.getId())
        );

        Assert.notNull(clientInfo, "非法的应用ID-{}", clientId);

        String newSign = SignUtils.md5Utf8Text(clientInfo.getAppId() + channelCode + clientInfo.getAppSecret() + timestamp + nonceStr);

        Assert.isTrue(sign.equals(newSign), "签名验证失败");

        return tenantInfo;
    }


    /**
     * 检查租户状态
     *
     * @param tenantInfo
     * @return
     */
    @Override
    public TenantInfo auditTenant(TenantInfo tenantInfo) {

        //
        if (tenantInfo == null) {
            return tenantInfo;
        }

        Assert.notNull(tenantInfo, () -> new ServiceException(400, "租户不存在"));

        Assert.isTrue(tenantInfo.getEnable(),
                () -> new ServiceException(400, tenantInfo.getId() + "租户账户已经冻结"));

        Assert.isTrue(tenantInfo.getLicenseExpire() == null
                        || tenantInfo.getLicenseExpire().getTime() > System.currentTimeMillis(),
                () -> new ServiceException(400, tenantInfo.getId() + "租户已经过期"));

        return tenantInfo;
    }

    @Override
    public TenantInfo loadTenant(String tenantId) {

        Assert.isTrue(StringUtils.hasText(tenantId), "租户Id不能为空");

        return auditTenant(tenantService.findById(tenantId));
    }

    /**
     * 获取租户
     *
     * @param domain
     * @return
     */
    @Override
    public TenantInfo loadTenantByDomain(String domain) {

        Assert.notBlank(domain, () -> new IllegalArgumentException("domain is blank"));

        //获取租户
        TenantInfo tenantInfo = getSelfProxy().getAllTenantList().stream().filter(t -> t.getDomainList() != null && t.getDomainList().contains(domain)).findFirst().get();
        // tenantService.findUnique(new QueryTenantReq().setDomainList(Arrays.asList(domain)));

        return auditTenant(tenantInfo);
    }

    /**
     * 获取用户的租户ID
     *
     * @param tenantKey
     * @return
     */
    @Override
    public TenantInfo loadTenantByTenantKey(String tenantKey) {

        Assert.notBlank(tenantKey, "要加载的租户Key不能为空");

        TenantInfo tenantInfo = getSelfProxy().getAllTenantList().stream().filter(t -> tenantKey.equals(t.getTenantKey())).findFirst().get();
        // tenantService.findUnique(new QueryTenantReq().setTenantKey(tenantKey));

        return auditTenant(tenantInfo);
    }

    @Override
    public StatTenantReq.Result stat(StatTenantReq req, SimplePaging paging) {
        throw new UnsupportedOperationException("not support");
    }

    @Autowired
    protected ApplicationContext applicationContext;

    protected BizTenantServiceImpl selfProxy = null;

    /**
     * 返回自身的代理
     *
     * @return
     */
    protected BizTenantServiceImpl getSelfProxy() {

        if (selfProxy == null) {
            selfProxy = (BizTenantServiceImpl) applicationContext.getBean(AopProxyUtils.ultimateTargetClass(this));
        }

        return selfProxy;
    }
}
