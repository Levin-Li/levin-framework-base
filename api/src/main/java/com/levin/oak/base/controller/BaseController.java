package com.levin.oak.base.controller;

import com.levin.commons.dao.*;
import com.levin.commons.rbac.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.util.*;
import javax.validation.*;
import java.util.*;
import javax.annotation.*;

import javax.servlet.http.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;
import javax.validation.constraints.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.*;

import com.levin.oak.base.*;

import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

//Auto gen by simple-dao-codegen 2023年6月26日 下午6:06:01


/**
 * 抽象控制器
 *
 * @author lilw
 * Auto gen by simple-dao-codegen 2023年6月26日 下午6:06:01
 * 代码生成哈希校验码：[e687f51c51b246b75cafdae273387791]
 */
@Slf4j
//默认需要权限访问
@ResAuthorize(domain = ID, type = TYPE_NAME)
@MenuResTag(domain = ID)
public abstract class BaseController {

    @Autowired
    protected HttpServletRequest httpRequest;

    @Autowired
    protected HttpServletResponse httpResponse;

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    protected SimpleDao simpleDao;

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
    public void initBinder(WebDataBinder binder){
       // binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"),false));
    }

    /**
     * 检查结果
     * @param n
     * @param failAction
     * @return
     */
    protected int checkResult(int n, String failAction) {
        Assert.isTrue(n > 0, failAction);
        return n;
    }

    /**
     * 检查结果
     * @param ok
     * @param failAction
     * @return
     */
    protected boolean checkResult(boolean ok, String failAction) {
        Assert.isTrue(ok, failAction);
        return ok;
    }
}
