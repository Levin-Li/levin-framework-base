package com.levin.oak.base.biz;

import java.util.Map;

/**
 * 注入变量服务
 * Auto gen by simple-dao-codegen 2023年6月28日 下午4:18:30
 * 代码生成哈希校验码：[f7706b36f9121cd3fce5d556266ad333]
 */
public interface InjectVarService {

    String INJECT_VAR_CACHE_KEY = InjectVarService.class.getName() + ".INJECT_VAR_CACHE_KEY";

    /**
     * 清除缓存
     */
    void clearCache();

    /**
     * 获取默认常规的注入变量
     *
     * @return
     */
    Map<String, ?> getInjectVars();

}
