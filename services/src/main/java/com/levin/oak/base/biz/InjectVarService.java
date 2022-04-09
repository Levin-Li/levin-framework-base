package com.levin.oak.base.biz;

import java.util.List;
import java.util.Map;

/**
 * 注入变量服务
 */
public interface InjectVarService {

    String TENANT_BIND_DOMAIN_ENABLE ="tenantBindDomainEnable";


    /**
     *
     */
    void clearThreadCache();

    /**
     * 获取默认常规的注入变量
     *
     * @return
     */
    List<Map<String, ?>> getInjectVars();

}
