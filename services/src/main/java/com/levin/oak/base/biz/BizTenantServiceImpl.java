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

    @Resource
    SimpleDao simpleDao;

    @Resource
    AuthService authService;

    @Resource
    TenantService tenantService;

    @Resource
    HttpServletRequest request;

    @Resource
    HttpServletResponse response;

    @Resource
    AppClientService appClientService;

    @Resource
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

        //??????????????????
        TenantInfo tenantInfo = getCurrentTenant();

        final String domain = getData(getCurrentDomain(), StringUtils::hasText, () -> request.getServerName());

        //??????????????????????????????????????????????????????
        if (tenantInfo == null && frameworkProperties.getTenantBindDomain().isEnable()) {
            tenantInfo = setCurrentTenantByDomain(domain);
            log.warn("?????????????????????[ {} ]???????????????", domain);
        }

        //??????????????????
        if (authService.isLogin()) {
            //????????????
            //??????????????????
            UserInfo userInfo = authService.getUserInfo();

            //????????????????????????
            if (StringUtils.hasText(userInfo.getTenantId())) {

                TenantInfo tenantInfo2 = getTenantInfo(userInfo.getTenantId());

                if (tenantInfo2 != null) {

                    if (tenantInfo != null
                            && !tenantInfo.getId().contentEquals(tenantInfo2.getId())) {

                        //??????????????????????????????????????????????????????????????????
                        throw new AccessDeniedException("????????????????????????????????????????????????????????????");
                    }

                    tenantInfo = tenantInfo2;
                }
            }

            //????????????????????????????????????????????????????????????
            if (!userInfo.isSuperAdmin() && tenantInfo == null) {
                throw new AccessDeniedException("????????????????????????");
            }
        }

        return tenantInfo;
    }

    /**
     * ??????????????????
     *
     * @param domain
     * @return
     */
    @Override
    public void setCurrentDomain(String domain) {
        currentDomain.set(domain);
    }

    /**
     * ??????????????????
     *
     * @return
     */
    @Override
    public String getCurrentDomain() {
        return currentDomain.get();
    }

    /**
     * ???????????????????????????
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

        if (frameworkProperties.getSign().isEnable()) {
            checkTenantAppSign(tenantInfo);
        }

        return tenantInfo;
    }

    @Override
    public TenantInfo setCurrentTenant(TenantInfo tenantInfo) {
        currentTenant.set(tenantInfo);
        return tenantInfo;
    }

    /**
     * ??????????????????????????????
     */
    protected TenantInfo checkTenantAppSign(TenantInfo tenantInfo) {

        if (tenantInfo == null) {
            return tenantInfo;
        }

        String url = request.getRequestURL().toString();

        log.debug("???????????? url:{}", url);

        String appId = request.getHeader(SignatureReq.Fields.appId);
        String channelCode = request.getHeader(SignatureReq.Fields.channelCode);
        String sign = request.getHeader(SignatureReq.Fields.sign);

        Assert.notBlank(appId, "??????ID????????????");
        Assert.notBlank(channelCode, "????????????????????????");
        Assert.notBlank(sign, "????????????????????????");

//        ?????????????????????:md5(Utf8(??????ID +  ???????????? + ???????????? + ?????????????????????/(45 * 1000) ))

        AppClientInfo clientInfo = appClientService.findOne(
                new QueryAppClientReq()
                        .setAppId(appId.trim())
                        .setEnable(true)
                        .setTenantId(tenantInfo.getId())
        );

        Assert.notNull(clientInfo, "???????????????ID-{}", appId);

        String newSign = SignUtils.md5Utf8Text(clientInfo.getAppId() + channelCode + clientInfo.getAppSecret() + System.currentTimeMillis() / (45 * 1000));

        Assert.isTrue(sign.equals(newSign), "??????????????????");

        return tenantInfo;
    }


    /**
     * ???????????????????????????
     *
     * @return
     */
    @Override
    public TenantInfo getCurrentTenant() {

        TenantInfo tenantInfo = auditTenant(currentTenant.get());

        return tenantInfo;
    }

    /**
     * ??????????????????
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

        Assert.notNull(tenantInfo, () -> new ServiceException("NullObject", "???????????????"));

        Assert.isTrue(tenantInfo.getEnable(),
                () -> new ServiceException(tenantInfo.getId().toString(), "????????????????????????"));

        Assert.isTrue(tenantInfo.getLicenseExpire() == null
                        || tenantInfo.getLicenseExpire().getTime() > System.currentTimeMillis(),
                () -> new ServiceException(tenantInfo.getId().toString(), "??????????????????"));


        return tenantInfo;
    }

    @Override
    public TenantInfo getTenantInfo(String tenantId) {

        Assert.isTrue(StringUtils.hasText(tenantId), "??????Id????????????");

        return auditTenant(tenantService.findById(tenantId));
    }

    /**
     * ????????????
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
     * ?????????????????????ID
     *
     * @param tenantKey
     * @return
     */
    @Override
    public TenantInfo getTenantByTenantKey(String tenantKey) {

        Assert.isTrue(StringUtils.hasText(tenantKey), "??????Key????????????");

        TenantInfo tenantInfo = tenantService.findOne(new QueryTenantReq().setTenantKey(tenantKey));

        return auditTenant(tenantInfo);
    }

}
