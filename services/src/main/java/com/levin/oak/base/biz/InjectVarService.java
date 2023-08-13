package com.levin.oak.base.biz;

import java.util.Map;

/**
 * 注入变量服务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年8月13日 下午4:53:06, 代码生成哈希校验码：[aa1c80c7a3f26b183e90ec6e2ebde373]，请不要修改和删除此行内容。
 */
public interface InjectVarService {

    String INJECT_VAR_CACHE_KEY = InjectVarService.class.getName() + ".INJECT_VAR_CACHE_KEY";

    /** 清除缓存 */
    void clearCache();

    /**
     * 获取默认常规的注入变量
     *
     * @return
     */
    Map<String, ?> getInjectVars();
}
