package com.levin.oak.base.biz;

import java.util.List;
import java.util.Map;

/**
 * 注入变量服务
 */
public interface InjectVarService {

    String INJECT_VAR_CACHE_KEY = InjectVarService.class.getName() + ".INJECT_VAR_CACHE_KEY";

    /**
     *
     */
    void clearCache();

    /**
     * 获取默认常规的注入变量
     *
     * @return
     */
    List<Map<String, ?>> getInjectVars();

}
