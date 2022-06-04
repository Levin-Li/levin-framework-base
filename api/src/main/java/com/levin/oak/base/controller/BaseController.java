package com.levin.oak.base.controller;

import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.oak.base.ModuleOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.levin.oak.base.ModuleOption.ID;
import static com.levin.oak.base.entities.EntityConst.TYPE_NAME;

//Auto gen by simple-dao-codegen 2022-3-29 16:19:50


/**
 * 抽象控制器
 *
 * @author lilw
 */
@Slf4j
//默认需要权限访问
@ResAuthorize(domain = ID, type = TYPE_NAME)
@MenuResTag(domain = ID)
public abstract class BaseController {

    @Resource
    protected HttpServletRequest httpRequest;

    @Resource
    protected HttpServletResponse httpResponse;

    @Resource
    protected ApplicationContext applicationContext;

    protected Object selfProxy = null;

    public final String getModuleId() {
        return ModuleOption.ID;
    }

    protected boolean isNotEmpty(Object value) {
        return value != null
                && (!(value instanceof CharSequence) || StringUtils.hasText((CharSequence) value));
    }

    protected <T> T getSelfProxy(Class<T> type) {

        if (selfProxy == null) {
            selfProxy = applicationContext.getBean(type);
        }

        return (T) selfProxy;
    }

    /**
     * @return
     */
    protected String getContextPath() {
        return httpRequest.getServletContext().getContextPath();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"),false));
    }
}
