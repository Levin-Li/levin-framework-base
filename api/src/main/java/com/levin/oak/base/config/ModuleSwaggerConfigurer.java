package com.levin.oak.base.config;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.SignatureReq;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
import static org.springframework.beans.factory.support.AbstractBeanDefinition.DEPENDENCY_CHECK_OBJECTS;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Swagger3
@EnableOpenApi

//@EnableSwagger2

@Slf4j
@Configuration(PLUGIN_PREFIX + "ModuleSwaggerConfigurer")
//@Component(PLUGIN_PREFIX + "ModuleSwaggerConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleSwaggerConfigurer", matchIfMissing = true)
//@AutoConfigureAfter({Plugin.class, PluginManager.class})
@ConditionalOnClass({Docket.class})
public class ModuleSwaggerConfigurer
        implements
        ModelPropertyBuilderPlugin,
        WebMvcConfigurer,
        ApplicationContextAware,
//        BeanFactoryPostProcessor,
        ResourceLoaderAware,
        BeanClassLoaderAware,
        EnvironmentAware,
//        BeanDefinitionRegistryPostProcessor,
        ApplicationListener<ContextRefreshedEvent> {

    public static final String[] SWAGGER_UI_MAPPING_PATHS = {};

    /**
     * tokenName Authorization
     */
    @Value("${sa-token.token-name:}")
    private String tokenName = "Authorization";

    /**
     * 是否开启swagger
     */
    @Value("${swagger.enabled:true}")
    private boolean enabled;

    /**
     * swagger 枚举值和描述之间的分隔符
     */
    @Value("${swagger.enumDelimiter:}")
    private String enumDelimiter;

    @Resource
    FrameworkProperties frameworkProperties;

    @Resource
    PluginManager pluginManager;

    ApplicationContext context;

    ResourceLoader resourceLoader;

    ClassLoader beanClassLoader;

    @Resource
    Environment environment;

    private static final String GROUP_NAME = ModuleOption.NAME + "-" + ModuleOption.ID;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        log.debug("onApplicationEvent ContextRefreshedEvent..." + event.getApplicationContext());

        if (event.getApplicationContext() instanceof GenericApplicationContext) {

            if (context == null) {
                context = (GenericApplicationContext) event.getApplicationContext();
            }
        }
    }

    @PostConstruct
    void init() {

        log.info(" ModuleSwaggerConfigurer init...");

        if (!StringUtils.hasText(enumDelimiter)) {
            enumDelimiter = "--";
        }

    }

    //    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        for (String beanName : registry.getBeanDefinitionNames()) {

            BeanDefinition bd = registry.getBeanDefinition(beanName);

            if (bd == null || !StringUtils.hasText(bd.getBeanClassName())) {
                continue;
            }

            String beanClassName = bd.getBeanClassName();

            Class beanClass = null;

            try {
                beanClass = this.resourceLoader.getClassLoader().loadClass(beanClassName);
            } catch (ClassNotFoundException e) {
                continue;
            }

            if (!Plugin.class.isAssignableFrom(beanClass)) {
                continue;
            }
//
            final Supplier<Docket> docketSupplier = () -> newPluginDocket(context.getBean(beanName, Plugin.class));

            RootBeanDefinition beanDefinition = (RootBeanDefinition) BeanDefinitionBuilder.rootBeanDefinition(DocketFactoryBean.class)
                    .addDependsOn(beanName)
                    .setDependencyCheck(DEPENDENCY_CHECK_OBJECTS)
                    .addPropertyValue("supplier", docketSupplier)
                    .setLazyInit(false)
                    .getBeanDefinition();

            beanDefinition.setTargetType(Docket.class);

            registry.registerBeanDefinition(beanName.concat("Docket"), beanDefinition);
        }

//
//        for (String beanName : registry.getBeanDefinitionNames()) {
//            if (beanName.startsWith("springfox.")) {
//                registry.getBeanDefinition(beanName).setLazyInit(true);
//            }
//        }

    }

    //    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        if (context == null && beanFactory instanceof ApplicationContext) {
            context = (ApplicationContext) beanFactory;
        }

        //beanFactory.getBeanDefinition("").setLazyInit(true);

        // 可以修改应用程序上下文的内部bean工厂。所有的bean定义都将被加载，但是还没有bean被实例化,允许重写或添加属性。

//        beanFactory.getBeanProvider(Plugin.class).orderedStream().forEach(plugin -> {
//            log.info("Register Plugin {}-{} Swagger Docket Bean...", plugin.getId(), plugin.getName());
//        });

    }


    @Data
    public static class DocketFactoryBean
            implements FactoryBean<Docket>, BeanFactoryAware {

        Supplier<Docket> supplier;

        BeanFactory beanFactory;

        @Override
        public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
            this.beanFactory = beanFactory;
        }

        @Override
        public boolean isSingleton() {
            return false;
        }

        @Override
        public Docket getObject() throws Exception {
            return supplier.get();
        }

        @Override
        public Class<?> getObjectType() {
            return Docket.class;
        }
    }

    /**
     * @param plugin
     * @return
     */
    private Docket newPluginDocket(Plugin plugin) {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo(plugin))
                .enable(enabled)
                .groupName(plugin.getName() + "-" + plugin.getId())
                .select()
                //apis： 添加swagger接口提取范围
                .apis(RequestHandlerSelectors.basePackage(plugin.getPackageName()))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(path -> path.startsWith(API_PATH))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    /**
     * api 信息
     *
     * @return
     */
    private static ApiInfo apiInfo(Plugin plugin) {

        String name = plugin.getName() + "-" + plugin.getId();

        return new ApiInfoBuilder()
                .title("插件[" + name + "]接口文档")
                .description("插件[" + name + "]接口文档")
                .contact(new Contact("Levin", "https://github.com/Levin-Li/simple-dao", "99668980@qq.com"))
                .version(plugin.getVersion())
                .build();
    }


    @Bean(PLUGIN_PREFIX + "Docket")
    //默认激活的 profile
//    @Profile({"local", "dev", "test"})
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(enabled)
                .groupName(GROUP_NAME)
                .select()
                //apis： 添加swagger接口提取范围
                .apis(RequestHandlerSelectors.basePackage(ModuleOption.PACKAGE_NAME))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(path -> path.startsWith(API_PATH))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
    }

    /**
     * api 信息
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("插件[" + GROUP_NAME + "]接口文档")
                .description("插件[" + GROUP_NAME + "]接口文档")
                .contact(new Contact("Levin", "https://github.com/Levin-Li/simple-dao", "99668980@qq.com"))
                .version(ModuleOption.VERSION)
                .build();
    }

    /**
     * 生成全局通用参数
     *
     * @return
     */
    private List<RequestParameter> getGlobalRequestParameters() {

        List<RequestParameter> parameters = new ArrayList<>();

        if (StringUtils.hasText(tokenName)) {
            parameters.add(newParameter(tokenName, "鉴权token，从登录接口获取",
                    Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> env.contains("prod")), null));
        }

        if (frameworkProperties == null) {
            frameworkProperties = context.getBean(FrameworkProperties.class);
        }

        if (frameworkProperties.getSign().isEnable()) {
            parameters.add(newParameter(SignatureReq.Fields.appId, "应用ID"));
//        parameters.add(newParameter(SignatureReq.Fields.appSecret, "应用密钥"));
            parameters.add(newParameter(SignatureReq.Fields.channelCode, "渠道编码"));
//        parameters.add(newParameter(SignatureReq.Fields.nonceStr, "随机字符串"));

//        parameters.add(newParameter(SignatureReq.Fields.timeStamp, "整数时间戳（秒）", true, ScalarType.LONG));

            parameters.add(newParameter(SignatureReq.Fields.sign, "签名，验签规则:md5(Utf8(应用ID +  渠道编码 + 应用密钥 + 当前时间毫秒数/(45 * 1000) ))"));
        }

        return parameters;
    }

    private RequestParameter newParameter(String name, String description) {
        return newParameter(name, description, true, null);
    }

    /**
     * 新参数
     *
     * @param name
     * @param description
     * @param required
     * @return
     */
    private RequestParameter newParameter(String name, String description, boolean required, ScalarType scalarType) {
        return new RequestParameterBuilder()
                .name(name)
                .description(description)
                .required(required)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(scalarType == null ? ScalarType.STRING : scalarType)))
                .build();
    }


    @Override
    public void apply(ModelPropertyContext context) {

        Optional<BeanPropertyDefinition> definition = context.getBeanPropertyDefinition();

        if (!definition.isPresent()) {
            return;
        }

        BeanPropertyDefinition pd = definition.get();
        //

        final Class<?> enumType = pd.getRawPrimaryType();

        final PropertySpecificationBuilder specificationBuilder = context.getSpecificationBuilder();

        //过滤得到目标类型
        if (enumType.isEnum()
                && EnumDesc.class.isAssignableFrom(enumType)
            //  && enumType.getName().startsWith(PACKAGE_NAME)
        ) {
            Enumerated enumerated = pd.hasField() ? pd.getField().getAnnotation(Enumerated.class)
                    : (pd.hasGetter() ? pd.getGetter().getAnnotation(Enumerated.class) : pd.getSetter().getAnnotation(Enumerated.class));

            boolean isIndex = false;//enumerated == null || EnumType.ORDINAL.ordinal() == enumerated.value().ordinal();

            final List<String> displayValues = Arrays.stream((Enum[]) enumType.getEnumConstants())
                    .map(e -> (isIndex ? e.ordinal() : e.name()) + enumDelimiter + ((EnumDesc) e).getDesc() + "")
                    .collect(Collectors.toList());

            final AllowableListValues allowableListValues = new AllowableListValues(displayValues, enumType.getTypeName());

            specificationBuilder.enumerationFacet(builder -> {
                builder.allowedValues(allowableListValues);
            });
        }

        /////////////////////////////////////////////////////////////////////

        Annotation[] annotations = {};

        if (pd.hasField()) {
            annotations = pd.getField().getAnnotated().getAnnotations();
        } else if (pd.hasGetter()) {
            annotations = pd.getGetter().getAnnotated().getDeclaredAnnotations();
        } else {
            return;
        }

        Optional<Annotation> optionalAnnotation = Arrays.stream(annotations).filter(annotation -> annotation instanceof Schema).findFirst();

        if (optionalAnnotation.isPresent()) {
            Schema schema = (Schema) optionalAnnotation.get();
            if (!schema.title().trim().equals(schema.description().trim())) {
                //加强描述
                final String desc = (StringUtils.hasText(schema.title()) ? schema.title() + ", " : "") + schema.description();
                specificationBuilder.description(desc);
            }
        }

    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }


    /**
     * 解决 swagger3   swagger-ui.html 404无法访问的问题
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Stream.of(SWAGGER_UI_MAPPING_PATHS)
                .filter(p -> !registry.hasMappingForPattern(p))
                .forEachOrdered(pathPattern ->
                        registry.addResourceHandler(pathPattern)
                                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                );
    }

}