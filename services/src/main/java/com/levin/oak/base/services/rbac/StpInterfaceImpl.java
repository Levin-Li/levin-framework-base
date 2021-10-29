package com.levin.oak.base.services.rbac;

import cn.dev33.satoken.stp.StpInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

/**
 * 参考文档
 * https://sa-token.dev33.cn/doc/index.html#/use/jur-auth
 * <p>
 * <p>
 * 自定义权限验证接口扩展
 */
@Service(PLUGIN_PREFIX + "StpInterface")
@Slf4j
@ConditionalOnMissingBean(StpInterface.class)
public class StpInterfaceImpl
        implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return null;
    }
}
