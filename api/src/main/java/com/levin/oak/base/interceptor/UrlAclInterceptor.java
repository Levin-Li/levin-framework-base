package com.levin.oak.base.interceptor;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson2.JSONObject;
import com.levin.commons.utils.IPAddrUtils;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import com.levin.oak.base.biz.rbac.AuthService;
import com.levin.oak.base.entities.Setting;
import com.levin.oak.base.entities.vo.UrlAcl;
import com.levin.oak.base.services.setting.SettingService;
import com.levin.oak.base.services.setting.info.SettingInfo;
import com.levin.oak.base.services.setting.req.CreateSettingReq;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 白名单拦截器
 */
@Data
@Slf4j
@Accessors(chain = true)
public class UrlAclInterceptor
        implements HandlerInterceptor {

    @Autowired
    FrameworkProperties frameworkProperties;

    @Autowired
    ServerProperties serverProperties;

    @Autowired
    AuthService authService;

    @Autowired
    SettingService settingService;

    @PostConstruct
    public void init() {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //如果使用过滤器模式，则直接返回
        return frameworkProperties.getWhitelist().isUseWebFilter() || canPass(request, response, handler);
    }

    public boolean canPass(HttpServletRequest request, HttpServletResponse response, Object handler) {

        FrameworkProperties.WhitelistCfg whitelistCfg = frameworkProperties.getWhitelist();

        if (!whitelistCfg.isEnable()
                //如果不是HandlerMethod，直接放行
                || (whitelistCfg.isOnlyControllerMethod() && handler != null && !(handler instanceof HandlerMethod))) {
            return true;
        }

        String path = getRequestPath(request);// request.getRequestURI();

        if (!StringUtils.hasText(path)
                || !whitelistCfg.isPathMatched(path)) {
            return true;
        }

        //访问域名
        String domain = request.getServerName();

        //用于在过滤器调用时，兼容问题
        if (RequestContextHolder.getRequestAttributes() == null) {
            LocaleContextHolder.setLocale(request.getLocale());
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        }

        //要满足默认的安全测路，也要满足租户自定义的安全测路
        return
                //平台全局，尝试自动创建
                canPass(request, getAndAutoCreate("平台", "@*", null))

                        //租户全局   ，尝试自动创建
                        && canPass(request, authService.isLogin() ? getAndAutoCreate("系统", "@" + authService.getUserInfo().getTenantId() + "@*", authService.getUserInfo().getTenantId()) : null)

                        //域名+租户全局   ，尝试自动创建
                        && canPass(request, authService.isLogin() ? getAndAutoCreate("域名-" + domain, "@" + authService.getUserInfo().getTenantId() + "@" + domain + "@*", authService.getUserInfo().getTenantId()) : null)

                ;

    }


    private UrlAcl getAndAutoCreate(String title, String id, String tenantId) {

        id = UrlAcl.class.getName() + id;

        SettingInfo info = settingService.findById(id);

        if (info == null) {

            settingService.create(
                    new CreateSettingReq()
                            .setId(id)
                            //默认不启用
                            .setEnable(false)
                            .setEditable(true)
                            .setName("URL访问控制-" + title)
                            .setGroupName("URL访问控制")
                            .setCategoryName("系统安全")
                            .setRemark("系统自动生成的配置")
                            .setCode(UrlAcl.class.getName())
                            //默认不启用
                            //.setValueContent(JSONObject.toJSONString(new UrlAccessControl().setTitle(title).setEnable(false).setUrlPathExcludeList("*"), JSONWriter.Feature.WriteNullStringAsEmpty))
                            .setEditor("form://" + UrlAcl.class.getName())
                            .setValueType(Setting.ValueType.Json)
                            .setTenantId(tenantId)
            );

            return null;
        }

        if (!Boolean.TRUE.equals(info.getEnable())
                || !StringUtils.hasText(info.getValueContent())) {
            return null;
        }

        return JSONObject.parseObject(info.getValueContent(), UrlAcl.class);
    }

    private boolean canPass(HttpServletRequest request, UrlAcl acl) {

        if (acl == null || !Boolean.TRUE.equals(acl.getEnable())) {
            return true;
        }

        String urlPath = getRequestPath(request);

        //先检查URL是否匹配，如果不匹配则直接返回通过
        if (anyMatch(false, () -> urlPath, acl.getUrlPathExcludeList())

                //检查URL是否匹配，如果不匹配则直接返回通过
                || !anyMatch(false, () -> urlPath, acl.getUrlPathList())) {
            return true;
        }

        final String clientIp = IPAddrUtils.try2GetUserRealIPAddr(request);

        //如果IP排除的，直接通过
        if (anyMatch(false, () -> clientIp, acl.getIpExcludeList())) {
            //如果是排除的IP，则直接通过
            return true;
        }

        boolean passed = anyMatch(() -> clientIp, acl.getIpList());

        if (!passed) {
            log.warn("非法的IP({})访问：{}，允许的IP列表：{}", clientIp, request.getRequestURL().toString(), acl.getIpList());
        }

        Assert.isTrue(passed, "不支持的地址请求");

        passed = anyMatch(() -> request.getServerName(), acl.getDomainList());

        if (!passed) {
            log.warn("非法的域名({})访问：{}，允许的域名列表：{}", request.getServerName(), request.getRequestURL().toString(), acl.getDomainList());
        }

        Assert.isTrue(passed, "不支持的域名请求");

        Assert.isTrue(anyMatch(request::getMethod, acl.getMethodList()), "不支持的请求方法" + request.getMethod());

        UserAgent userAgent = UserAgentUtil.parse(request.getHeader("user-agent"));

        Assert.isTrue(anyMatch(() -> userAgent.getBrowser() != null ? userAgent.getBrowser().getName() : null, acl.getBrowserList()), "不支持的浏览器");

        Assert.isTrue(anyMatch(() -> userAgent.getOs() != null ? userAgent.getOs().getName() : null, acl.getOsList()), "不支持的系统类型");

        Assert.isTrue(anyMatch(() -> userAgent.getEngine() != null ? userAgent.getEngine().getName() : null, acl.getBrowserTypeList()), "不支持的浏览器类型");

        //地区匹配  ("中国|0|", "")
        Assert.isTrue(anyMatch(() -> IPAddrUtils.searchIpRegion(clientIp), acl.getRegionList()), "不支持的地区");

        return true;
    }

    public static List<String> parseItem(String rules) {

        if (!StringUtils.hasText(rules)) {
            return Collections.emptyList();
        }

        return Stream.of(rules.split("[,，\\t\\s\\n\\r|]")).filter(StringUtils::hasText).collect(Collectors.toList());
    }

    public static boolean anyMatch(Supplier<String> valueSupplier, String rules) {
        return anyMatch(true, () -> valueSupplier.get(), rules);
    }

    public static boolean anyMatch(boolean emptyValue, Supplier<String> valueSupplier, String rules) {

        List<String> items = parseItem(rules);

        if (items.isEmpty()) {
            return emptyValue;
        }

        String value = valueSupplier.get();

        //如果有规则但却没有值，则直接返回false
        if (!StringUtils.hasText(value)) {
            return false;
        }

        return items.stream().anyMatch(item -> PatternMatchUtils.simpleMatch(item, value));
    }


    public static boolean noneMatch(boolean emptyValue, Supplier<String> valueSupplier, String rules) {

        List<String> items = parseItem(rules);

        if (items.isEmpty()) {
            return emptyValue;
        }

        String value = valueSupplier.get();

        //如果有规则但却没有值，则直接返回false
        if (!StringUtils.hasText(value)) {
            return false;
        }

        return items.stream().noneMatch(item -> PatternMatchUtils.simpleMatch(item, value));
    }


    public static String getRequestPath(HttpServletRequest request) {

        String contextPath = StringUtils.trimWhitespace(request.getContextPath());

        if (StringUtils.hasText(contextPath)) {
            contextPath = contextPath.replace("//", "/");
            while (contextPath.trim().endsWith("/")) {
                contextPath = contextPath.trim();
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
        }

        String path = request.getRequestURI().replace("//", "/");

        //去除应用路径
        if (StringUtils.hasText(contextPath)
                && path.startsWith(contextPath)) {
            path = path.substring(contextPath.length());
        }

        return path;
    }


}
