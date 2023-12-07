package com.levin.oak.base.controller.commons;

import cn.hutool.core.lang.ClassScanner;
import cn.hutool.core.util.ClassUtil;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacResService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.commons.dto.EnumInfo;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.levin.oak.base.ModuleOption.*;


// POST: 创建一个新的资源，如用户资源，部门资源
// PATCH: 修改资源的某个属性
// PUT: 更新资源当中包含的全部属性
// DELETE: 删除某项资源
// GET: 获取某个资源的详情

// 在数学计算或者计算机科学中，幂等性（idempotence）是指相同操作或资源在一次或多次请求中具有同样效果的作用。幂等性是在分布式系统设计中具有十分重要的地位。

// http协议明确规定，put、get、delete请求都是具有幂等性的，而post为非幂等性的。
// 所以一般插入新数据的时候使用post方法，更新数据库时用put方法
// @Valid只能用在controller。@Validated可以用在其他被spring管理的类上。

/**
 * 一次性返回所有的枚举变量
 */
@RestController(PLUGIN_PREFIX + "EnumController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "EnumController", matchIfMissing = true)
@RequestMapping(API_PATH + "enums")
@Tag(name = "业务枚举类", description = "业务枚举类服务")
@Slf4j
@Valid
@ResAuthorize(domain = ID, type = EntityConst.COMMON_TYPE_NAME + "-枚举", onlyRequireAuthenticated = true)
@MenuResTag(false)
public class EnumController extends BaseController {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    RbacResService rbacResService;

    @Autowired
    AuthService authService;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    PluginManager pluginManager;

    //扫描类
//    final ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
//            false) {
//        @Override
//        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
//            return true;
//        }
//    };

    final Map<String, EnumInfo> enumCacheMap = new ConcurrentHashMap<>();

    @GetMapping("")
    @Operation(summary = "枚举列表", description = "一次性返回所有的枚举")
    public ApiResp<Map<String, EnumInfo>> enums() {

        synchronized (enumCacheMap) {
            if (enumCacheMap.isEmpty()) {
                pluginManager.getInstalledPlugins()
                        .parallelStream()
                        .flatMap(plugin -> ClassScanner.scanPackage(plugin.getPackageName(), clazz -> clazz.isEnum()).stream())
                        .forEach(clazz -> {
                            enumCacheMap.put(clazz.getName(), toEnumInfo((Class<Enum>) clazz));
                        });
            }
        }

        return ApiResp.ok(enumCacheMap);
    }

    @GetMapping("{enumName}")
    @Operation(summary = "单个枚举")
    public ApiResp<EnumInfo> enums(@PathVariable String enumName) {
        return ApiResp.ok(MapUtils.getAndAutoPut(enumCacheMap, enumName, null, () -> toEnumInfo(ClassUtil.loadClass(enumName))));
    }

    protected static EnumInfo toEnumInfo(Class<Enum> enumClass) {

        EnumInfo enumInfo = new EnumInfo().setFullName(enumClass.getName())
                .setName(enumClass.getSimpleName())
                .setLabel(enumClass.getSimpleName());

        for (Enum anEnum : enumClass.getEnumConstants()) {

            enumInfo.getValues().add(new EnumInfo.Item()
                    .setOrdinal(anEnum.ordinal())
                    .setDetail(anEnum)
                    .setValue(anEnum.name())
                    .setLabel(EnumDesc.getDesc(anEnum)));
        }

        return enumInfo;
    }

}
