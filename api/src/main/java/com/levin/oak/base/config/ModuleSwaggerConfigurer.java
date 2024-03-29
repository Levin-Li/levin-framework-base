package com.levin.oak.base.config;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.oak.base.*;

import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//Swagger3

@Slf4j
@Configuration(PLUGIN_PREFIX + "ModuleSwaggerConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleSwaggerConfigurer", matchIfMissing = true)
@ConditionalOnClass({GroupedOpenApi.class,})
public class ModuleSwaggerConfigurer
        implements
        WebMvcConfigurer {

    /**
     * tokenName Authorization
     */
    @Value("${sa-token.token-name:}")
    private String tokenName = "Authorization";

    /**
     * swagger 枚举值和描述之间的分隔符
     */
    @Value("${swagger.enumDelimiter:}")
    private String enumDelimiter;

//    @Autowired
//    FrameworkProperties frameworkProperties;

    @Autowired
    Environment environment;

    private static final String GROUP_NAME = ModuleOption.NAME + "-" + ModuleOption.ID;

    @PostConstruct
    void init() {

        if (!StringUtils.hasText(enumDelimiter)) {
            enumDelimiter = "--";
        }

        log.info("init...");
    }

    @Bean(PLUGIN_PREFIX + "GroupedOpenApi")
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group(ID)
                .displayName(GROUP_NAME)
                .packagesToScan(PACKAGE_NAME)
                .addOpenApiCustomiser(openApi ->
                        openApi.setInfo(new Info()
                                .summary(GROUP_NAME)
                                .title(NAME)
                                .version(VERSION)
                                .description(DESC)
                        ))
                .addOperationCustomizer((operation, handlerMethod) -> operation)
                .build();
    }

}
