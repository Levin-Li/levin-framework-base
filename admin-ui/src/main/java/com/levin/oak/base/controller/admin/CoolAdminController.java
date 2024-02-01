package com.levin.oak.base.controller.admin;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.LRUCache;
import com.alibaba.fastjson2.JSONObject;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.commons.service.support.SpringContextHolder;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizSimplePageService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacResService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.services.menures.MenuResService;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.simplepage.SimplePageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.*;

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

@Tag(name = "CoolAdmin支持", description = "CoolAdmin服务")
@RestController(PLUGIN_PREFIX + "CoolAdminController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "CoolAdminController", matchIfMissing = true)
@RequestMapping(API_PATH + "CoolAdmin")
@Slf4j
@Valid
@MenuResTag(false)
@ResAuthorize(domain = ID, type = EntityConst.COMMON_TYPE_NAME + "-CoolAdmin", onlyRequireAuthenticated = true)
public class CoolAdminController extends BaseController {

    @Data
    @Accessors(chain = true)
    public static class SubModuleItem implements Serializable {

        @Data
        @Accessors(chain = true)
        static class Api implements Serializable {

            //小写get post get
            String method;
            String path;
            String summary;

            //对象{ parameters:[{name:xxx, required:true,type:xxx, description:xxx}]}
            JSONObject dts;

            String tag;

            @Schema(title = "路径前缀")
            String prefix;

            @Schema(title = "是否不要登录")
            boolean ignoreToken;
        }

        @Data
        @Accessors(chain = true)
        static class Column implements Serializable {

            String propertyName;
            String type;
            String length;
            String comment;
            boolean nullable;
        }

        @Data
        @Accessors(chain = true)
        static class Info implements Serializable {

            @Data
            @Accessors(chain = true)
            static class Type implements Serializable {

                @Schema(title = "类型名称")
                String name;

                @Schema(title = "描述")
                String description;
            }

            @Schema(title = "类型")
            Type type;
        }


        @Schema(title = "归属的模块")
        String module;

        @Schema(title = "实体名称", description = "建议类名")
        String name;

        @Schema(title = "模块ID")
        Info info;

        @Schema(title = "api列表")
        List<Api> api;

        @Schema(title = "实体列名清单")
        List<Column> columns;

        @Schema(title = "访问路径", description = "api前缀")
        String prefix;

    }

    @Autowired
    PluginManager pluginManager;


    @Autowired
    ApplicationContext context;

    @PostConstruct
    public void init() {
        log.info("默认cool-admin服务支持启用...");
    }

    /**
     * 获取菜单列表
     *
     * @return ApiResp
     */
    @RequestMapping(value = "schema", method = {RequestMethod.GET, RequestMethod.POST})
    @Operation(summary = "cool-admin")
    public ApiResp<Map<String, List<SubModuleItem>>> getCoolAdminSchema() {

        final Map<String, List<SubModuleItem>> result = new HashMap<>();

        pluginManager.getInstalledPlugins().forEach(plugin -> {

            //查找模块的所有控制器
            List<Object> controllers = SpringContextHolder.findBeanByPkgName(context, Controller.class, plugin.getPackageName());

            List<SubModuleItem> subModuleItems = result.computeIfAbsent(plugin.getId(), key -> new ArrayList<>());

            controllers.forEach(controller -> {

                final Class<?> beanType = AopProxyUtils.ultimateTargetClass(controller.getClass());

                RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(beanType, RequestMapping.class);

                String basePath = requestMapping.value()[0];

                String entityName = basePath.substring(basePath.lastIndexOf('/') + 1);


                Map<Method, ResAuthorize> methodResAuthorizeMap = RbacUtils.loadClassResAuthorize(controller, true);

                SubModuleItem subModuleItem = new SubModuleItem()
                        .setModule(plugin.getId())
                        .setName(entityName)
                        .setPrefix(basePath)
                        .setInfo(new SubModuleItem.Info().setType(new SubModuleItem.Info.Type().setName(entityName)));

                ArrayList<SubModuleItem.Column> columns = new ArrayList<>();
                subModuleItem.setColumns(columns);


                subModuleItems.add(subModuleItem);

                methodResAuthorizeMap.forEach((method, resAuthorize) -> {

                    RequestMapping mapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);

                    String path = mapping.value()[0];

                });

            });


        });


        return ApiResp.ok(result);
    }

}
