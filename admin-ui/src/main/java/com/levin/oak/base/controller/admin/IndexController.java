package com.levin.oak.base.controller.admin;

import com.levin.commons.rbac.MenuResTag;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;

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

@Tag(name = "授权管理", description = "Admin管理视图")
@Controller(PLUGIN_PREFIX + "IndexController")
@ConditionalOnClass(FreeMarkerAutoConfiguration.class)
@ConditionalOnProperty(value = PLUGIN_PREFIX + "IndexController", matchIfMissing = true)
@RequestMapping("${" + PLUGIN_PREFIX + "framework.adminPath" + ":" + ADMIN_UI_PATH + "}")
@Slf4j
@Valid
@MenuResTag(false)
public class IndexController {

    @Resource
    HttpServletRequest httpRequest;

    @Resource
    HttpServletResponse httpResponse;

    @Resource
    ApplicationContext applicationContext;

    @Resource
    RoleService roleService;

    @Resource
    RbacService rbacService;

    @Resource
    AuthService authService;

    @Resource
    BizTenantService bizTenantService;

    @Resource
    ResourceLoader resourceLoader;

    @Resource
    ServerProperties serverProperties;

    @Resource
    FrameworkProperties frameworkProperties;

    /**
     * 首页
     *
     * @return ApiResp
     */
    @SneakyThrows
    @RequestMapping
    public String index(Model modelMap) throws IOException {

        String basePath = serverProperties.getServlet().getContextPath() + API_PATH;

        final String adminBasePath = (serverProperties.getServlet().getContextPath() + "/" + frameworkProperties.getAdminPath()).replace("//", "/");

        modelMap.addAttribute("appPath", adminBasePath);
        modelMap.addAttribute("appName", frameworkProperties.getSysName());

        TenantInfo tenantInfo = bizTenantService.getCurrentTenant();

        if (tenantInfo != null) {
            modelMap.addAttribute("tenant", tenantInfo);
            modelMap.addAttribute("appName", nullSafe(tenantInfo.getSysName(), frameworkProperties.getSysName()));
            modelMap.addAttribute("appLogo", nullSafe(tenantInfo.getSysLogo(), tenantInfo.getLogo()));
        } else {

        }

        //设置默认图标
        if (!StringUtils.hasText((CharSequence) modelMap.getAttribute("appLogo"))) {
            modelMap.addAttribute("appLogo", adminBasePath + "/img/default_logo.png");
        }

        if (authService.isLogin()) {
            modelMap.addAttribute("appMenuApi", basePath + "amis/appMenuList");
        } else {
            modelMap.addAttribute("loginApi", basePath + "rbac/login");
            modelMap.addAttribute("captchaApi", basePath + "rbac/captcha");
        }

        String redirectUrl = httpRequest.getParameter("r");

        //登录后重定向地址
        if (authService.isLogin()
                && StringUtils.hasText(redirectUrl)) {

            if (redirectUrl.startsWith("http://")
                    || redirectUrl.startsWith("https://")) {
                //如果是完整路径
            } else {

            }

            //防止死循环时，大量占用CPU
            Thread.sleep(100);

            //必须要使用前缀再重定向
            return "redirect:" + redirectUrl;
        }

        return ADMIN_UI_PATH + (authService.isLogin() ?
                nullSafe(frameworkProperties.getAdminIndexTemplate(), "amis_index")
                : nullSafe(frameworkProperties.getAdminLoginTemplate(), "login_index")
        );
    }

    /**
     * 首页
     *
     * @return ApiResp
     */
    @SneakyThrows
    @RequestMapping("editor")
//    @Operation(tags = {"UI编辑"}, summary = "页面编辑")
//    @ResAuthorize(domain = ID, type = EntityConst.SYS_TYPE_NAME, onlyRequireAuthenticated = true)
    public String editor(Model modelMap) {

        //        request.getRequestURL():http://localhost:8080/bzbs/system/login.jsp
//        request.getRequestURI():/bzbs/system/login.jsp
//        request.getContextPath():/bzbs
//        request.getServletPath():/system/login.jsp

        String path = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        path = path.substring(path.indexOf(contextPath) + contextPath.length(), path.indexOf("/editor"));

        final String basePath = path;

        if (!authService.isLogin()) {

            //重定向到登录页面
            path += "/?r=" + URLEncoder.encode(httpRequest.getRequestURL().toString(), "utf-8");

            log.debug("sendRedirect:" + contextPath + "/" + path);

            //防止死循环时，大量占用CPU
            Thread.sleep(100);

            //必须要使用前缀再重定向
            return "redirect:" + path;

        } else if (false) {
            //
            return ADMIN_UI_PATH + "/editor/index";
        } else {
            //必须要使用前缀再重定向
            path = "redirect:" + path + "/editor/index.html";

            if (StringUtils.hasText(httpRequest.getQueryString())) {
                path += "?" + URLEncoder.encode(httpRequest.getQueryString(), "utf-8");
            }

            log.debug("sendRedirect:" + path);

            return path;
        }
    }

    String nullSafe(String v, String defaultV) {
        return StringUtils.hasText(v) ? v : defaultV;
    }
}