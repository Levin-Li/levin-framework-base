package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.repository.SimpleDaoRepository;
import com.levin.commons.plugin.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.*;

//Auto gen by simple-dao-codegen 2021-10-28 16:17:42
//模块插件

@Slf4j
@Component(PLUGIN_PREFIX + "ModulePlugin")
public class ModulePlugin implements Plugin, PluginManagerAware {

    //dao
    @Autowired
    private SimpleDaoRepository simpleDaoRepository;

    @Autowired
    private SimpleDao simpleDao;

    final String pid = ModuleOption.ID;

    private PluginManager pluginManager;


    @Override
    public ResLoader getResLoader() {
        //@todo 返回资源加载器
        return null;
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

    @Override
    public String getId() {
        return pid;
    }

    @Override
    public String getName() {
        return "插件" + pid;
    }


} // end class