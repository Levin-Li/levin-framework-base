package com.levin.oak.base.biz.rbac;

import com.levin.commons.rbac.RbacUserObject;


/**
 * 用户加载服务
 */
@FunctionalInterface
public interface UserLoadService<U>{
    /**
     * 加载用户
     * @param principal 登录ID
     * @return 用户信息
     */
    RbacUserObject<String> loadUser(U principal);
}
