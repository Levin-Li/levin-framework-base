package com.levin.oak.base.biz.rbac;

/**
 * Rbac 初始服务
 */
@FunctionalInterface
public interface RbacInitService {
    /**
     * 初始化数据
     */
    void initRbacData();
}
