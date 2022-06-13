package com.levin.oak.base.codegen.amis;

import com.levin.oak.base.codegen.UiCodeGen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

/**
 * Amis UI 生成器
 */
@Service(PLUGIN_PREFIX + "AmisUiCodeGen")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AmisUiCodeGen", matchIfMissing = true)
@Slf4j
public class AmisUiCodeGenImpl
        implements UiCodeGen {

    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    @Resource
    protected HttpServletRequest httpRequest;

    @Resource
    protected HttpServletResponse httpResponse;

    @Resource
    protected ApplicationContext applicationContext;

    @PostConstruct
    void init(){

        this.handlerMapping.getHandlerMethods();

    }

    @Override
    public boolean isSupport(String uiPageType) {
        return "amis".equalsIgnoreCase(uiPageType);
    }

    @Override
    public String genCode(String path, Map<String, Object>... contexts) {
        return null;
    }

}
