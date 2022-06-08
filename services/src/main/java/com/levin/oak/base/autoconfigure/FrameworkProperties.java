package com.levin.oak.base.autoconfigure;

import com.levin.commons.service.support.MatchConfig;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

@Data
@Accessors(chain = true)
@FieldNameConstants
@ConfigurationProperties(prefix = PLUGIN_PREFIX + "framework")
public class FrameworkProperties implements Serializable {

    /**
     * 是否允许图片验证码
     */
    private boolean enableCaptcha = false;

    /**
     *
     */
    private int captchaCodeLen = 4;

    /**
     * admin 路径
     */
    private String adminPath = null;

    /**
     * admin 首页 amis 模板，不需要包含模板根路径
     */
    private String adminIndexTemplate = null;

    /**
     * admin 登录页面模板，不需要包含模板根路径
     */
    private String adminLoginTemplate = null;

    /**
     * 是否为菜单增加一个根节点，显示更好看
     */
    private boolean autoAddAmisMenuRootNode = true;

    /**
     * 系统名称
     */
    private String sysName = "";

    /**
     * 请求变量注入
     */
    @NestedConfigurationProperty
    private final Cfg inject = new Cfg("inject", "InjectVar注解变量注入", "Http请求对象[InjectVar]注解变量注入");

    /**
     * 签名配置
     */
    @NestedConfigurationProperty
    private final Cfg sign = new Cfg("sign", "接口签名验证", "接口签名验证");

    /**
     * 控制器访问控制
     */
    @NestedConfigurationProperty
    private final Cfg controllerAcl = new Cfg("controller", "控制器访问鉴权控制", "控制器访问鉴权控制");

    /**
     * 资源访问控制
     */
    @NestedConfigurationProperty
    private List<ResCfg> resourcesAcl = Collections.emptyList();

    /**
     * 租户绑定域名
     */
    @NestedConfigurationProperty
    private final Cfg tenantBindDomain = new Cfg("tenantBindDomain", "租户域名鉴权控制", "租户域名鉴权控制");

    /**
     * 访问日志记录
     */
    @NestedConfigurationProperty
    private final Cfg log = new Cfg("log", "访问日志记录", "记录访问日志到数据库中");

    @Getter(AccessLevel.PRIVATE)
    private ServerProperties serverProperties;

    @Autowired
    void setServerProperties(ObjectProvider<ServerProperties> serverProperties) {
        this.serverProperties = serverProperties.getIfUnique();
    }

    /**
     * 资源配置
     */
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class ResCfg {

        protected final AntPathMatcher antPathMatcher = new AntPathMatcher();

        protected boolean enable = true;

        protected boolean onlyRequireAuthenticated = false;

        protected boolean andMode = true;

        /**
         * 需要的权限列表，包括角色
         */
        protected List<String> requiredPermissions = Collections.emptyList();

        protected List<String> excludePathPatterns = Collections.emptyList();

        protected List<String> includePathPatterns = Collections.emptyList();

        public ResCfg() {
        }

        public boolean isPathMatched(String path) {
            return this.enable && this.excludePathPatterns.stream().filter(StringUtils::hasText).noneMatch((p) -> {
                return this.antPathMatcher.match(p, path);
            }) && (this.includePathPatterns.stream().noneMatch(StringUtils::hasText) || this.includePathPatterns.stream().filter(StringUtils::hasText).anyMatch((p) -> {
                return this.antPathMatcher.match(p, path);
            }));
        }

    }

    /**
     * 匹配控制
     */
//    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Cfg extends MatchConfig {

        /**
         * 所有的配置
         */
        private static final List<Cfg> allCfg = new ArrayList<>(10);

        public static void fireAllFriendlyTip(boolean tip, Consumer<String> consumer) {
            allCfg.forEach(cfg -> cfg.friendlyTip(tip, consumer));
        }

        protected Cfg(String key, String name, String description) {
            super(key, name, description);
            allCfg.add(this);
        }

        /**
         * 返回友好提示信息
         *
         * @param tip
         * @param consumer
         * @return
         */
        public boolean friendlyTip(boolean tip, Consumer<String> consumer) {
            if (tip && consumer != null) {
                consumer.accept(String.format("*** 友情提示 *** %s 已经 %s ，可以配置[ %s.%s.enable = %s] %s，配置描述：%s"
                        , name, (enable ? "启用" : "禁用"), PLUGIN_PREFIX + "framework", key, !enable, (enable ? "禁用" : "启用"), description));
            }
            return tip;
        }

    }

}
