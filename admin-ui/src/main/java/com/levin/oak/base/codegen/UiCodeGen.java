package com.levin.oak.base.codegen;

import java.util.Map;

public interface UiCodeGen {

    /**
     * 是否支持指定的页面
     *
     * @param uiPageType
     * @return
     */
    boolean isSupport(String uiPageType);

    /**
     * 生成代码
     *
     * @param path
     * @param contexts
     * @return
     */
    String genCode(String path, Map<String, Object>... contexts);

}
