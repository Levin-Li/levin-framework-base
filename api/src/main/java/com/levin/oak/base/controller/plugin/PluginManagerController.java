package com.levin.oak.base.controller.plugin;

import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;

/**
 * 全局插件控制器
 *
 * @author Auto gen by simple-dao-codegen 2021-10-28 9:46:17
 */
@RestController(PLUGIN_PREFIX + "PluginManagerController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "PluginManagerController", matchIfMissing = true)
@RequestMapping(API_PATH + "PluginManager")
@Tag(name = "插件管理", description = "插件管理")
@Slf4j
@Valid
@ResAuthorize(domain = ID, type = "系统插件")
public class PluginManagerController extends BaseController {

    @Autowired
    PluginManager pluginManager;

    @PostConstruct
    public void init() {
    }

    /**
     * 插件列表
     *
     * @return
     */
    @GetMapping("/list")
    @Operation(tags = "插件管理", summary = "获取插件列表", description = "获取插件列表")
    public ApiResp<List<Plugin>> list() {
        return ApiResp.ok(pluginManager.getInstalledPlugins());
    }

    /**
     * 插件菜单
     *
     * @param pluginId
     * @return
     */
    @GetMapping("/{pluginId}")
    @Operation(tags = "插件", summary = "获取插件详情", description = "获取插件详情")
    public ApiResp<Plugin> plugin(@PathVariable String pluginId) {
        return ApiResp.ok(pluginManager.getInstalledPlugin(pluginId));
    }

}
