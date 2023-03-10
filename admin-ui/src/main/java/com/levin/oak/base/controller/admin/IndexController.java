package com.levin.oak.base.controller.admin;

import com.levin.commons.dao.support.PagingData;
import com.levin.commons.rbac.MenuResTag;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.BizTenantService;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.biz.rbac.RbacService;
import com.levin.oak.base.controller.BaseController;
import com.levin.oak.base.entities.EntityConst;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.services.role.RoleService;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.services.setting.req.CreateSettingReq;
import com.levin.oak.base.services.setting.req.QuerySettingReq;
import com.levin.oak.base.services.tenant.info.TenantInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
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

@Tag(name = "Admin管理视图", description = "Admin管理视图")
@Controller(PLUGIN_PREFIX + "IndexController")
@ConditionalOnClass(FreeMarkerAutoConfiguration.class)
@ConditionalOnProperty(value = PLUGIN_PREFIX + "IndexController", matchIfMissing = true)
@RequestMapping("${" + PLUGIN_PREFIX + "framework.admin-path" + ":" + ADMIN_UI_PATH + "}")
@Slf4j
@Valid
@MenuResTag(false)
@ResAuthorize(ignored = true)
public class IndexController extends BaseController {

    @Autowired
    HttpServletRequest httpRequest;

    @Autowired
    HttpServletResponse httpResponse;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    RoleService roleService;

    @Autowired
    RbacService rbacService;

    @Autowired
    AuthService authService;

    @Autowired
    BizTenantService bizTenantService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    SettingService settingService;

    @PostConstruct
    public void init() {
        log.info("默认管理后台UI启用，访问路径:" + nullSafe(frameworkProperties.getAdminPath(), ADMIN_UI_PATH));
    }


    /**
     * 首页
     * <p>
     * admin 页面入口
     * <p>
     * <p>
     * 同一个入口支持以下
     * <p>
     * 1、支持重新登录，通过参数处理
     * <p>
     * 2、未登录时选择登录模板
     * <p>
     * 3、登录成功后选择amis模板
     * <p>
     * 4、支持重系统配置中获取 amis 主题样式
     *
     * @return ApiResp
     */
    @SneakyThrows
    @GetMapping({""})
    @Operation(summary = "首页", description = "首页")
    public String index(Model modelMap) throws IOException {

        //验证码相关
        modelMap.addAttribute(FrameworkProperties.Fields.enableSmsVerificationCode, frameworkProperties.isEnableSmsVerificationCode());
        modelMap.addAttribute(FrameworkProperties.Fields.verificationCodeLen, frameworkProperties.getVerificationCodeLen());
        modelMap.addAttribute(FrameworkProperties.Fields.verificationCodeDurationOfMinutes, frameworkProperties.getVerificationCodeDurationOfMinutes());

        //重新登录
        if ("relogin".equalsIgnoreCase(httpRequest.getParameter("action"))) {

            if (authService.isLogin()) {
                authService.logout();
            }

            //request.getRequestURL():http://localhost:8080/bzbs/system/login.jsp
//        request.getRequestURI():/bzbs/system/login.jsp
//        request.getContextPath():/bzbs
//        request.getServletPath():/system/login.jsp

            return "redirect:" + httpRequest.getRequestURI();
        }

        String basePath = getContextPath() + API_PATH;

        final String adminBasePath = (getContextPath() + "/" + frameworkProperties.getAdminPath()).replace("//", "/");

        modelMap.addAttribute("appPath", adminBasePath);
        modelMap.addAttribute("appName", frameworkProperties.getSysName());

        TenantInfo tenantInfo = bizTenantService.getCurrentTenant();

        if (tenantInfo != null) {
            modelMap.addAttribute("tenant", tenantInfo);
            modelMap.addAttribute("appName", nullSafe(tenantInfo.getSysName(), frameworkProperties.getSysName()));
            modelMap.addAttribute("appLogo", nullSafe(tenantInfo.getSysLogo(), tenantInfo.getLogo()));
        } else {

        }

        final String tenantId = tenantInfo != null ? tenantInfo.getId() : null;

        final String cssCode = ADMIN_UI_PATH + "Amis.RootCss";

        PagingData<SettingInfo> pagingData = settingService.query(new QuerySettingReq()
                .setCode(cssCode)
                .setContainsPublicData(true)
                .setTenantId(tenantId), null);

        if (!pagingData.isEmpty()) {

            StringBuilder css = new StringBuilder();

            pagingData.getItems().forEach(settingInfo -> {
                if (StringUtils.hasText(settingInfo.getValueContent())) {
                    css.append(settingInfo.getValueContent())
                            .append('\n');
                }
            });

            modelMap.addAttribute("amisRootCss", css.toString());

        } else {

            //创建默认配置
            settingService.create(new CreateSettingReq()
                    .setCategoryName("系统界面")
                    .setGroupName("基础样式")
                    .setCode(cssCode)
                    .setInputPlaceholder("amisRootCss")
                    .setName("amisRootCss")
                    .setValueType(Setting.ValueType.Css)
                    .setNullable(true)
                    .setValueContent("/*amis root css*/\n" +
                            "--Layout-aside-width: 15rem;\n")
                    .setTenantId(tenantId)
            );

            modelMap.addAttribute("amisRootCss", "");
        }

        if (authService.isLogin()) {
            modelMap.addAttribute("user", authService.getUserInfo());
        }

        //设置默认图标
        if (!StringUtils.hasText((CharSequence) modelMap.getAttribute("appLogo"))) {
            modelMap.addAttribute("appLogo", (adminBasePath + "/img/default_logo.png").replace("//", "/"));
        }

        if (authService.isLogin()) {
            modelMap.addAttribute("appMenuApi", basePath + "amis/appMenuList");
        } else {
            modelMap.addAttribute("loginApi", basePath + "rbac/login");
            modelMap.addAttribute("captchaApi", basePath + "rbac/captcha");
            modelMap.addAttribute("sendSmsCodeApi", basePath + "rbac/sendSmsCode");
        }

        modelMap.addAttribute("SA_ACCOUNT", AuthService.SA_ACCOUNT);

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
            Thread.sleep(50);

            //必须要使用前缀再重定向
            return "redirect:" + redirectUrl;
        }


        return ADMIN_UI_PATH + (authService.isLogin() ?
                nullSafe(frameworkProperties.getAdminIndexTemplate(), "amis_index")
                : nullSafe(frameworkProperties.getAdminLoginTemplate(), "login_index")
        );
    }

    /**
     * 页面编辑器入口
     *
     * @return ApiResp
     */
    @SneakyThrows
    @GetMapping("editor")
    @Operation(summary = "页面编辑", description = "页面编辑")
    @ResAuthorize(domain = ID, type = EntityConst.SYS_TYPE_NAME)
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
