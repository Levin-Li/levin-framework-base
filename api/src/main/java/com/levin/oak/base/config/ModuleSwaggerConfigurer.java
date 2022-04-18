package com.levin.oak.base.config;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.SignatureReq;
import com.levin.oak.base.ModuleOption;
import com.levin.oak.base.autoconfigure.FrameworkProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.levin.oak.base.ModuleOption.PACKAGE_NAME;
import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Swagger3
@EnableOpenApi

//@EnableSwagger2

@Slf4j
@Configuration(PLUGIN_PREFIX + "ModuleSwaggerConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleSwaggerConfigurer", matchIfMissing = true)

@ConditionalOnClass({Docket.class})
public class ModuleSwaggerConfigurer implements ModelPropertyBuilderPlugin, WebMvcConfigurer {

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

    @Resource
    FrameworkProperties frameworkProperties;

    @Resource
    Environment environment;


    private final WeakHashMap<String, ?> tempCache = new WeakHashMap<>();

    private static final String GROUP_NAME = ModuleOption.NAME + "-" + ModuleOption.ID;

    @PostConstruct
    void init() {
        log.info("init...");
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
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_NAME))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .paths(path -> path.startsWith(API_PATH))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters());
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


    @Override
    public void apply(ModelPropertyContext context) {

        Optional<BeanPropertyDefinition> definition = context.getBeanPropertyDefinition();

        if (!definition.isPresent()) {
            return;
        }

        BeanPropertyDefinition pd = definition.get();

        Annotation[] annotations = {};

        if (pd.hasGetter()) {
            annotations = pd.getGetter().getAnnotated().getDeclaredAnnotations();
        } else if (pd.hasField()) {
            annotations = pd.getField().getAnnotated().getAnnotations();
        } else {
            return;
        }

        //

        final Class<?> enumType = pd.getRawPrimaryType();

        final PropertySpecificationBuilder specificationBuilder = context.getSpecificationBuilder();

        //过滤得到目标类型
        if (enumType.isEnum()
                && EnumDesc.class.isAssignableFrom(enumType)
            //  && enumType.getName().startsWith(PACKAGE_NAME)
        ) {
            final List<String> displayValues = Arrays.stream((Enum[]) enumType.getEnumConstants())
                    .map(e -> e.name() + " -- " + ((EnumDesc) e).getDesc() + "")
                    .collect(Collectors.toList());

            final AllowableListValues allowableListValues = new AllowableListValues(displayValues, enumType.getTypeName());

            specificationBuilder.enumerationFacet(builder -> {
                builder.allowedValues(allowableListValues);
            });
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