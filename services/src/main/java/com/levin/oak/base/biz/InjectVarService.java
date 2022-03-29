package com.levin.oak.base.biz;



import com.levin.commons.rbac.RbacUserInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 注入变量服务
 */
public interface InjectVarService {

    /**
     * 获取默认常规的注入变量
     *
     * @return
     */
    List<Map<String, ?>> getInjectVars();

}
