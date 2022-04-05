package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.service.domain.SignatureReq;
import com.levin.commons.service.exception.ServiceException;
import com.levin.commons.utils.SignUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.services.appclient.AppClientService;
import com.levin.oak.base.services.appclient.info.AppClientInfo;
import com.levin.oak.base.services.appclient.req.QueryAppClientReq;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

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
    TenantService tenantService;

    @Resource
    HttpServletRequest request;

    @Resource
    HttpServletResponse response;

    @Resource
    AppClientService appClientService;

    @Resource
    FrameworkProperties frameworkProperties;


    final static ThreadLocal<TenantInfo> domainTenant = new ThreadLocal<>();
    final static ThreadLocal<String> domains = new ThreadLocal<>();

    @PostConstruct
    void init() {

        frameworkProperties.getSign().friendlyTip(log.isInfoEnabled(),info -> log.info(info));

    }

    /**
     * 获取当前域名
     *
     * @param domain
     * @return
     */
    @Override
    public void setCurrentDomain(String domain) {
        domains.set(domain);
    }

    /**
     * 获取当前域名
     *
     * @return
     */
    @Override
    public String getCurrentDomain() {
        return domains.get();
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

        domainTenant.set(tenantInfo);

        if (frameworkProperties.getSign().isEnable()) {
            checkTenantAppSign(tenantInfo);
        }

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

        TenantInfo tenantInfo = auditTenant(domainTenant.get());

        return tenantInfo;
    }


    /**
     * @param tenantInfo
     * @return
     */
    @Override
    public String getDomain(TenantInfo tenantInfo) {

        List<String> list = tenantInfo.getDomainList(); //JsonStrArrayUtils.parse(tenantInfo.getDomainList(), StringUtils::hasText, null);

        return list.get(0);
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
