package com.levin.oak.base.biz;


import com.levin.oak.base.services.tenant.info.TenantInfo;

/**
 * 租户-服务接口
 * <p>
 * 企业标识，两种获取方式：
 * 1. 企业开通应用时由平台方推送给应用，具体可参考首次启用应用
 * 2. 用户登录时返回，具体可参考获取用户身份访问凭证
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
    TenantInfo checkStatus(TenantInfo tenantInfo);

    /**
     * 租户信息
     *
     * @param tenantId
     * @return
     */
    TenantInfo getTenantInfo(String tenantId);

    /**
     * 获取用户的租户ID
     *
     * @return
     */
    TenantInfo getTenantByTenantKey(String tenantKey);


}
