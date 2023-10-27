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
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacResService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.controller.commons.dto.EnumInfo;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.user.UserService;
import com.levin.oak.base.utils.UrlPathUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.levin.oak.base.ModuleOption.*;


@Controller(PLUGIN_PREFIX + "ApiDocController")
@ConditionalOnProperty(PLUGIN_PREFIX + "framework.api-doc-path")
@RequestMapping("${" + PLUGIN_PREFIX + "framework.api-doc-path" + ":doc}")
@Tag(name = "ApiDoc", description = "API文档")
@Slf4j
@Valid
@ResAuthorize(domain = ID, type = EntityConst.SYS_TYPE_NAME, ignored = true)
@MenuResTag(false)
public class ApiDocController extends BaseController {

    private static final String SPRING_DOC_PATH = "/v3/api-docs/";

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    ServerProperties serverProperties;

    @PostConstruct
    public void init() {
        log.info("SpringDoc UI访问路径{}，api路径 [{}{}]", frameworkProperties.getApiDocPath(), frameworkProperties.getApiDocPath(), SPRING_DOC_PATH);
    }

    @RequestMapping(SPRING_DOC_PATH + "**")
    @Operation(summary = "SpringDoc API 路径", description = "转发", hidden = true)
    public String springDocPath() {
        //转发到根路径
        //        request.getRequestURL():http://localhost:8080/bzbs/system/login.jsp
        //        request.getRequestURI():/bzbs/system/login.jsp
        //        request.getContextPath():/bzbs
        //        request.getServletPath():/system/login.jsp
        String requestURI = httpRequest.getRequestURI();
        return "forward:" + requestURI.substring(requestURI.indexOf(SPRING_DOC_PATH));
    }

    @GetMapping({"", "/"})
    @Operation(summary = "文档跟路径转发", description = "重定向", hidden = true)
    public String index0() {
        //转发到根路径
        return "redirect:" + UrlPathUtils.safeUrl("/" + frameworkProperties.getApiDocPath() + "/index.html");
    }

    @GetMapping({"index.html"})
    @Operation(summary = "文档跟路径转发", description = "转发", hidden = true)
    public String index() {
        return "forward:" + UrlPathUtils.safeUrl("/" + frameworkProperties.getApiDocPath() + "/doc.html");
    }

}
