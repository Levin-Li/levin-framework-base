package com.levin.oak.base.biz;

import cn.hutool.core.lang.Assert;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.service.exception.ServiceException;
import com.levin.commons.utils.JsonStrArrayUtils;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.services.tenant.TenantService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import com.levin.oak.base.services.tenant.req.QueryTenantReq;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BizTenantServiceImpl
        implements BizTenantService {

    @Resource
    SimpleDao simpleDao;

    @Resource
    TenantService tenantService;


    final static ThreadLocal<TenantInfo> domainTenant = new ThreadLocal<>();
    final static ThreadLocal<String> domains = new ThreadLocal<>();

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

        TenantInfo tenantInfo = simpleDao.selectFrom(Tenant.class)
                .contains(E_Tenant.domainList, JsonStrArrayUtils.getLikeQueryStr(domain))
                .findOne(TenantInfo.class);

        tenantInfo = checkStatus(tenantInfo);

        domainTenant.set(tenantInfo);

        return tenantInfo;
    }


    /**
     * 获取当前的租户信息
     *
     * @return
     */
    @Override
    public TenantInfo getCurrentTenant() {

        TenantInfo tenantInfo = checkStatus(domainTenant.get());

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
    public TenantInfo checkStatus(TenantInfo tenantInfo) {

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

        return checkStatus(tenantService.findById(Long.parseLong(tenantId)));
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

        if (tenantInfo != null) {
            return checkStatus(tenantInfo);
        }

        return checkStatus(tenantInfo);
    }

}