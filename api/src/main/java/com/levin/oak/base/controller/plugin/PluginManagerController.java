package com.levin.oak.base.controller.plugin;

import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.domain.ApiResp;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.plugin.dto.PluginInfo;
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
import java.util.stream.Collectors;

import static com.levin.commons.dao.EntityOpConst.*;
import static com.levin.oak.base.ModuleOption.*;

/**
 * 全局插件控制器
 *
 * @author Auto gen by simple-dao-codegen 2021-10-28 9:46:17
 */
@RestController(PLUGIN_PREFIX + "PluginManagerController")
@ConditionalOnProperty(value = PLUGIN_PREFIX + "PluginManagerController", havingValue = "true", matchIfMissing = true)
@RequestMapping(API_PATH + "PluginManager")

@Slf4j
@Valid
@ResAuthorize(domain = ID, type = PLATFORM_TYPE_NAME + "-")
@Tag(name = "插件管理", description = "插件管理")
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
    @GetMapping("list")
    @Operation(summary = QUERY_LIST_ACTION, description = "获取插件列表")
    public ApiResp<List<PluginInfo>> list() {
        return ApiResp.ok(pluginManager.getInstalledPlugins().stream().map(this::toPluginInfo).map(p -> p.setMenuList(null)).collect(Collectors.toList()));
    }

    /**
     * 插件菜单
     *
     * @param pluginId
     * @return
     */
    @GetMapping("{pluginId}")
    @Operation(summary = VIEW_DETAIL_ACTION, description = "获取插件详情")
    public ApiResp<PluginInfo> plugin(@PathVariable String pluginId) {
        return ApiResp.ok(toPluginInfo(pluginManager.getInstalledPlugin(pluginId)));
    }
    private PluginInfo toPluginInfo(Plugin plugin) {

        if (plugin == null) {
            return null;
        }

        return new PluginInfo()
                .setId(plugin.getId())
                .setVersion(plugin.getVersion())
                .setAuthor(plugin.getAuthor())
                .setRemark(plugin.getRemark())
                .setName(plugin.getName())
                .setPackageName(plugin.getPackageName())
                .setMenuList(plugin.getMenuList());

    }

}
