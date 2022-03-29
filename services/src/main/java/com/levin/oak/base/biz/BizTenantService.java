package com.levin.oak.base.biz;


import com.levin.oak.base.services.tenant.info.TenantInfo;

/**
 * 租户-服务接口
 */
public interface BizTenantService {

    /**
     * 获取当前的租户信息
     *
     * @return
     */
    TenantInfo setCurrentTenantByDomain(String domain);

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
     * @param tenantInfo
     * @return
     */
    String getDomain(TenantInfo tenantInfo);


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
