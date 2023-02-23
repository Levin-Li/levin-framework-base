package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.SimpleDao;
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
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static com.levin.oak.base.entities.EntityConst.MAINTAIN_ACTION;

@Service(PLUGIN_PREFIX + "BizTenantService")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "BizTenantService", matchIfMissing = true)
@Slf4j
//@Validated
@Tag(name = E_Tenant.BIZ_NAME, description = E_Tenant.BIZ_NAME + MAINTAIN_ACTION)
public class BizTenantServiceImpl
        implements BizTenantService {

    @Autowired
    SimpleDao simpleDao;

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


    final static ThreadLocal<TenantInfo> currentTenant = new ThreadLocal<>();

    final static ThreadLocal<String> currentDomain = new ThreadLocal<>();

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
    public TenantInfo checkAndGetCurrentUserTenant() {

        //获取当前租户
        TenantInfo tenantInfo = getCurrentTenant();

        final String domain = getData(getCurrentDomain(), StringUtils::hasText, () -> request.getServerName());

        //如果当前没有域名，获取域名关联的租户
        if (tenantInfo == null && frameworkProperties.getTenantBindDomain().isEnable()) {
            tenantInfo = setCurrentTenantByDomain(domain);
            log.warn("当前请求的域名[ {} ]未关联租户", domain);
        }

        //当前登录用户
        if (authService.isLogin()) {
            //暂时兼容
            //获取登录信息
            UserInfo userInfo = authService.getUserInfo();

            //加载用户租户信息
            if (StringUtils.hasText(userInfo.getTenantId())) {

                TenantInfo tenantInfo2 = getTenantInfo(userInfo.getTenantId());

                if (tenantInfo2 != null) {

                    if (tenantInfo != null
                            && !tenantInfo.getId().contentEquals(tenantInfo2.getId())) {

                        //如果用户的租户和域名的租户不匹配，则抛出异常
                        throw new AccessDeniedException("当前用户归属的租户和当前访问的域名不匹配");
                    }

                    tenantInfo = tenantInfo2;
                }
            }

            //除了超管，其它用户必须要归属于指定的租户
            if (!userInfo.isSuperAdmin() && tenantInfo == null) {
                throw new AccessDeniedException("非法的无租户用户");
            }
        }

        return tenantInfo;
    }

    /**
     * 获取当前域名
     *
     * @param domain
     * @return
     */
    @Override
    public void setCurrentDomain(String domain) {
        currentDomain.set(domain);
    }

    /**
     * 获取当前域名
     *
     * @return
     */
    @Override
    public String getCurrentDomain() {
        return currentDomain.get();
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

        setCurrentDomain(domain);

        TenantInfo tenantInfo = getTenantByDomain(domain);

        setCurrentTenant(tenantInfo);

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

    @Override
    public TenantInfo setCurrentTenant(TenantInfo tenantInfo) {
        currentTenant.set(tenantInfo);
        return tenantInfo;
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
     * 获取当前的租户信息
     *
     * @return
     */
    @Override
    public TenantInfo getCurrentTenant() {

        TenantInfo tenantInfo = auditTenant(currentTenant.get());

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

        Assert.notNull(tenantInfo, () -> new ServiceException("NullObject", "租户不存在"));

        Assert.isTrue(tenantInfo.getEnable(),
                () -> new ServiceException(tenantInfo.getId().toString(), "租户账户已经冻结"));

        Assert.isTrue(tenantInfo.getLicenseExpire() == null
                        || tenantInfo.getLicenseExpire().getTime() > System.currentTimeMillis(),
                () -> new ServiceException(tenantInfo.getId().toString(), "租户已经过期"));


        return tenantInfo;
    }

    @Override
    public TenantInfo getTenantInfo(String tenantId) {

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
    public TenantInfo getTenantByDomain(String domain) {

        Assert.notBlank(domain, () -> new IllegalArgumentException("domain is blank"));

        TenantInfo tenantInfo = tenantService.findOne(new QueryTenantReq().setContainsDomainList(Arrays.asList(domain)));

        return auditTenant(tenantInfo);
    }

    /**
     * 获取用户的租户ID
     *
     * @param tenantKey
     * @return
     */
    @Override
    public TenantInfo getTenantByTenantKey(String tenantKey) {

        Assert.isTrue(StringUtils.hasText(tenantKey), "租户Key不能为空");

        TenantInfo tenantInfo = tenantService.findOne(new QueryTenantReq().setTenantKey(tenantKey));

        return auditTenant(tenantInfo);
    }

}
