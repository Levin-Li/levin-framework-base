package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import com.levin.commons.service.domain.SignatureReq;
import com.levin.commons.service.exception.AccessDeniedException;
import com.levin.commons.service.exception.ServiceException;
import com.levin.commons.utils.SignUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.services.appclient.AppClientService;
import com.levin.oak.base.services.appclient.info.AppClientInfo;
import com.levin.oak.base.services.appclient.req.QueryAppClientReq;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import com.levin.oak.base.services.user.info.UserInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.entities.EntityConst.MAINTAIN_ACTION;


@Service(PLUGIN_PREFIX + "BizTenantService")
//@ConditionalOnMissingBean({BizTenantService.class}) //默认只有在无对应服务才启用
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizTenantService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Tenant.BIZ_NAME, description = E_Tenant.BIZ_NAME + MAINTAIN_ACTION)
public class BizTenantServiceImpl
        implements BizTenantService {

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
    }

    private static <T> T getData(T main, Predicate<T> filter, Supplier<T>... supplier) {

        if (filter == null) {
            filter = Objects::nonNull;
        }

        return filter.test(main) ? main :
                Arrays.stream(supplier)
                        .map(Supplier::get)
                        .filter(filter)
                        .findFirst()
                        .orElse(null);
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

        String appId = request.getHeader(SignatureReq.Fields.appId);
        String channelCode = request.getHeader(SignatureReq.Fields.channelCode);
        String sign = request.getHeader(SignatureReq.Fields.sign);

        Assert.notBlank(appId, "应用ID不能为空");
        Assert.notBlank(channelCode, "渠道编码不能为空");
        Assert.notBlank(sign, "应用签名不能为空");

//        签名，验签规则:md5(Utf8(应用ID +  渠道编码 + 应用密钥 + 当前时间毫秒数/(45 * 1000) ))

        AppClientInfo clientInfo = appClientService.findOne(
                new QueryAppClientReq()
                        .setAppId(appId.trim())
                        .setEnable(true)
                        .setTenantId(tenantInfo.getId())
        );

        Assert.notNull(clientInfo, "非法的应用ID-{}", appId);

        String newSign = SignUtils.md5Utf8Text(clientInfo.getAppId() + channelCode + clientInfo.getAppSecret() + System.currentTimeMillis() / (45 * 1000));

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

        TenantInfo tenantInfo = tenantService.findUnique(new QueryTenantReq().setDomainList(Arrays.asList(domain)));

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

        Assert.isTrue(StringUtils.hasText(tenantKey), "租户Key不能为空");

        TenantInfo tenantInfo = tenantService.findUnique(new QueryTenantReq().setTenantKey(tenantKey));

        return auditTenant(tenantInfo);
    }

}
