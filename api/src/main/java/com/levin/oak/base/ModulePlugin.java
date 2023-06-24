package com.levin.oak.base;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.plugin.*;
import com.levin.commons.rbac.MenuItem;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.service.domain.SimpleIdentifiable;
import com.levin.commons.utils.MapUtils;
import com.levin.oak.base.entities.EntityConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

//Auto gen by simple-dao-codegen 2022-4-21 14:12:07
//模块插件

@Slf4j
@Component(PLUGIN_PREFIX + "ModulePlugin")
public class ModulePlugin implements Plugin, PluginManagerAware {

    @Autowired
    ApplicationContext context;

    @Autowired
    SimpleDao simpleDao;

    private PluginManager pluginManager;

    @Override
    public String getId() {
        return getPackageName();
    }

    @Override
    public String getPackageName() {
        return ModuleOption.PACKAGE_NAME;
    }

    @Override
    public String getName() {
        return ModuleOption.NAME;
    }

    @Override
    public String getVersion() {
        return ModuleOption.VERSION;
    }

    @Override
    public Map<String, String> getAuthor() {
        return MapUtils.put("name", "Levin").put("email", "99668980@qq.com").build();
    }

    @Override
    public ResLoader getResLoader() {
        //@todo 返回资源加载器
        return resLoader;
    }

    @Override
    public <M extends MenuItem> List<M> getMenuList() {
        return (List<M>) RbacUtils.getMenuItemByController(context, ModuleOption.ID, EntityConst.QUERY_ACTION);
    }

    @Override
    public boolean onEvent(Object... objects) {
        //log.debug(getDescription() + " onEvent " + Arrays.asList(objects));
        //@todo
        return false;
    }

    @Override
    public void setPluginManager(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @PostConstruct
    public void init() {
        log.info("plugin init...");
    }

    @Override
    public void destroy() throws PluginException {
    }

    /**
     * 资源加载器
     */
    private final ResLoader resLoader = new ResLoader() {

        final List<SimpleIdentifiable> types = new ArrayList<>();

        final LinkedMultiValueMap<String, Res> resMap = new LinkedMultiValueMap<>();

        @Override
        public List<SimpleIdentifiable> getResTypes() {
            synchronized (types) {
                if (types.isEmpty()) {
                    types.addAll(RbacUtils.loadResTypeFromSpringCtx(context, getPackageName(), null));
                }
            }
            return types;
        }

        @Override
        public <R extends Res> List<R> getResItems(String resType, int loadDeep) {

            Assert.hasText(resType, "资源类型没有指定");

            if (!resMap.containsKey(resType)) {
                resMap.put(resType, RbacUtils.loadResFromSpringCtx(context, getPackageName(), resType));
            }

            return (List<R>) resMap.get(resType);
        }

        @Override
        public <R extends Res> Collection<R> getSubItems(String resType, String resId, int loadDeep) {
            throw new UnsupportedOperationException("getSubItems");
        }

    };

} // end class
