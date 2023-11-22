package com.levin.oak.base.biz;

import io.swagger.v3.oas.annotations.tags.*;

import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.tenant.info.*;


////////////////////////////////////
//自动导入列表

////////////////////////////////////

/**
 * 租户-业务服务
 *
 * @author auto gen by simple-dao-codegen 2023年6月26日 下午6:06:01
 * 代码生成哈希校验码：[d12026bde28ae8e55a30e753e27c92ae]
 */

@Tag(name = E_Tenant.BIZ_NAME + "-业务服务", description = "")
public interface BizTenantService {

    /**
     * 清除线程缓存
     */
    void clearThreadCacheData();


    /**
     * 检查和比对当前域名和用户的租户是否正确
     * <p>
     * 用户的租户必须和域名的租户一致
     *
     * @return nullable
     */
    TenantInfo checkCurrentUserTenantInfo();

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
    TenantInfo loadTenant(String tenantId);

    /**
     * 获取租户
     *
     * @return
     */
    TenantInfo loadTenantByDomain(String domain);

    /**
     * 获取租户
     *
     * @return
     */
    TenantInfo loadTenantByTenantKey(String tenantKey);

}
