package com.levin.oak.base.biz;

import java.util.Map;

/**
 * 注入变量服务
 * Auto gen by simple-dao-codegen 2023年6月28日 上午12:45:53
 * 代码生成哈希校验码：[b03a2b724bef309db80bac8b2fe301e0]
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
