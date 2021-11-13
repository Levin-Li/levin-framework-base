package com.levin.oak.base;

import com.levin.commons.dao.SimpleDao;
import com.levin.commons.dao.repository.SimpleDaoRepository;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginException;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.plugin.PluginManagerAware;
import com.levin.commons.rbac.MenuItem;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.Res;
import com.levin.commons.rbac.ResLoader;
import com.levin.commons.service.domain.Identifiable;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.services.i18nres.I18nResService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

//Auto gen by simple-dao-codegen 2021-11-3 15:08:04
//模块插件

@Slf4j
@Component(PLUGIN_PREFIX + "ModulePlugin")
public class ModulePlugin implements Plugin, PluginManagerAware {

    //dao
    @Autowired
    SimpleDaoRepository simpleDaoRepository;

    @Autowired
    SimpleDao simpleDao;

    @Resource
    ApplicationContext context;

    @Resource
    I18nResService i18nResService;

    final String pid = ModuleOption.ID;

    private PluginManager pluginManager;

    private final ResLoader resLoader = new ResLoader() {

        final List<Identifiable> types = new LinkedList<>();

        final List<Res> pluginResList = new LinkedList<>();

        @Override
        public List<Identifiable> getResTypes() {
            synchronized (types) {
                if (types.isEmpty()) {
                    types.addAll(RbacUtils.loadResTypeFromSpringCtx(context, getId(), null));
                }
            }
            return types;
        }


        @Override
        public <R extends Res> Collection<R> getResItems(String resType, int loadDeep) {

            Assert.hasText(resType, "资源类型没有指定");

            synchronized (pluginResList) {
                if (pluginResList.isEmpty()) {
                    pluginResList.addAll(RbacUtils.loadResFromSpringCtx(context, getId(), resType));
                }
            }

            return (Collection<R>) pluginResList.parallelStream()
                    .filter(res -> resType.equals(res.getType()))
                    .collect(Collectors.toList());
        }

        @Override
        public <R extends Res> Collection<R> getSubItems(String resType, String resId, int loadDeep) {

            return null;
        }

    };

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

        List<Identifiable> resTypes = getResLoader().getResTypes();

        for (Identifiable resType : resTypes) {
            log.debug(" plugin {} types:{}", getId(), getResLoader().getResItems(resType.getId(), 0));
        }

    }

    @Override
    public void destroy() throws PluginException {
    }

    @Override
    public String getId() {
        return pid;
    }

    @Override
    public String getName() {
        return "系统管理";
    }

    @Override
    public String getType() {
        return "系统";
    }


} // end class
