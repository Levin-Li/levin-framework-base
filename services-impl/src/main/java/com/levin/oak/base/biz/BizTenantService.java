package com.levin.oak.base.biz;


import com.levin.oak.base.services.tenant.info.TenantInfo;
import org.springframework.lang.Nullable;

/**
 * 租户-服务接口
 */
public interface BizTenantService {

    /**
     * 检查和比对当前域名和用户的租户是否正确
     *
     * @return nullable
     */
    @Nullable
    TenantInfo checkAndGetCurrentUserTenant();

    /**
     * 获取当前的租户信息
     *
     * @return
     */
    TenantInfo setCurrentTenantByDomain(String domain);


    /**
     * 获取当前的租户信息
     *
     * @return
     */
    TenantInfo setCurrentTenant(TenantInfo tenantInfo);

    /**
     * 获取当前域名
     *
     * @return
     */
    void setCurrentDomain(String domain);

    /**
     * 获取当前域名
     *
     * @return
     */
    String getCurrentDomain();

    /**
     * 获取当前的租户信息
     *
     * @return
     */
    TenantInfo getCurrentTenant();

    /**
     * 检查租户状态
     *
     * @param tenantInfo
     * @return
     */
    TenantInfo auditTenant(TenantInfo tenantInfo);

    /**
     * 租户信息
     *
     * @param tenantId
     * @return
     */
    TenantInfo getTenantInfo(String tenantId);

    /**
     * 获取租户
     *
     * @return
     */
    TenantInfo getTenantByDomain(String domain);

    /**
     * 获取租户
     *
     * @return
     */
    TenantInfo getTenantByTenantKey(String tenantKey);


}
