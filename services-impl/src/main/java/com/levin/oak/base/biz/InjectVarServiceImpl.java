package com.levin.oak.base.biz;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import com.levin.commons.dao.DaoContext;
import com.levin.commons.dao.SimpleDao;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.commons.rbac.RbacUserInfo;
import com.levin.commons.service.support.InjectConst;
import com.levin.commons.service.support.VariableInjector;
import com.levin.commons.service.support.VariableResolverManager;
import com.levin.commons.utils.MapUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 注入服务
 * 正常情况下该服务不需要应用，注入操作在web控制器中完成。
 * 正常情况下，一个项目只需要一个注入服务，为项目提供注入上下文。
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月6日 下午3:13:28, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[85874579771b0a114fb0ab09f87404f1], 请不要修改和删除此行内容。
 */

//默认不启用
//@Service(PLUGIN_PREFIX + "InjectVarService")
//@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "InjectVarService", matchIfMissing = true)
@Slf4j
public class InjectVarServiceImpl implements InjectVarService {

    public static final RbacUserInfo anonymous = new RbacUserInfo() {
        @Override
        public String getNickname() {
            return "anonymous";
        }

        @Override
        public String getEmail() {
            return "anonymous@163.com";
        }

        @Override
        public String getTelephone() {
            return null;
        }

        @Override
        public String getAvatar() {
            return "anonymous";
        }

        @Override
        public boolean isSuperAdmin() {
            return false;
        }

        @Override
        public String getName() {
            return "anonymous";
        }

        @Override
        public String getTenantId() {
            return null;
        }

        @Override
        public <ID extends Serializable> ID getOrgId() {
            return null;
        }

        @Override
        public <ID extends Serializable> ID getId() {
            //throw new IllegalStateException("anonymous user");
            return null;
        }
    };

    @Autowired
    Environment environment;

    @Autowired
    SimpleDao dao;

    @Autowired
    VariableResolverManager variableResolverManager;

    @Autowired
    PluginManager pluginManager;

    private static ThreadLocal<Map<String, ?>> varCache = new ThreadLocal<>();

    @PostConstruct
    public void init() {
        log.info("启用模块注入服务...");
        //设置上下文
        variableResolverManager.addSuppliers(() -> VariableInjector.newResolverByMap(() -> Arrays.asList(getInjectVars())));
    }


    public List<String> getBizStack(Thread thread) {

        if (thread == null) {
            thread = Thread.currentThread();
        }

        List<Plugin> plugins = pluginManager.getInstalledPlugins();
        return Stream.of(thread.getStackTrace())

                //过滤自己
                .filter(e -> !e.getClassName().startsWith(getClass().getName()))
                .filter(e -> !e.getClassName().startsWith(InjectVarService.class.getName()))

                //只过滤出业务类
                .filter(e -> plugins.stream().anyMatch(plugin -> e.getClassName().startsWith(plugin.getPackageName())))

                .map(e -> e.getClassName() + ":" + e.getMethodName() + "(" + e.getFileName() + ":" + e.getLineNumber() + ")")

                .collect(Collectors.toList());
    }

    /**
     *
     */
    @Override
    public void clearCache() {
        varCache.set(null);
    }

    @Override
    public Map<String, ?> getInjectVars() {

        //缓存在请求中
        Map<String, ?> result = varCache.get();

        if (result != null) {
            return result;
        }

        MapUtils.Builder<String, Object> builder = MapUtils.newBuilder();

        //@todo  获取当前登录用户
        RbacUserInfo userInfo = null;

        //当前登录用户
        if (userInfo != null) {

            //暂时兼容
            //获取登录信息
            builder.put(InjectConst.USER_ID, userInfo.getId())
                    .put(InjectConst.USER_NAME, userInfo.getName())
                    .put(InjectConst.USER, userInfo)
                    .put(InjectConst.IS_SUPER_ADMIN, userInfo.isSuperAdmin())
                    .put(InjectConst.IS_TENANT_ADMIN, userInfo.getRoleList() != null && userInfo.getRoleList().contains(RbacRoleObject.ADMIN_ROLE))
//                    .put(InjectConst.ORG, userInfo.getOrg())
//                    .put(InjectConst.ORG_ID, userInfo.getOrgId())
            ;

        } else {
            //匿名用户
            builder.put(InjectConst.USER, anonymous);
        }

        final Map<String, Object> ctx = builder.build();

        result = ctx;

        //缓存到请求对象重
        varCache.set(result);

        if (log.isTraceEnabled()) {
            log.trace("getInjectVars ok");
        }

        return result;
    }

}
