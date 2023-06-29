package com.levin.oak.base.biz;

import java.util.Map;

/**
 * 注入变量服务
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:10, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[0f757e8bf27d7b6b228bc9679740e73f], 请不要修改和删除此行内容。
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
