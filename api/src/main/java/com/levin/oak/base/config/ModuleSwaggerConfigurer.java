package com.levin.oak.base.config;

import static com.levin.oak.base.ModuleOption.*;

import com.levin.oak.base.*;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//Swagger3

@Slf4j
@Configuration(PLUGIN_PREFIX + "ModuleSwaggerConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ModuleSwaggerConfigurer", matchIfMissing = true)
@ConditionalOnClass({GroupedOpenApi.class,})
//@Profile({"local", "dev", "test"})
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
                        //Tag排序
                        openApi.tags(sort(openApi.getTags()))
                                //设置模块信息
                                .info(new Info()
                                        .summary(GROUP_NAME)
                                        .title(NAME)
                                        .version(API_VERSION)
                                        .description(DESC)
                                )
                ).build();
    }

    /**
     * 排序
     * 目前实际测试无效果
     * 因为 tag.getExtensions() 为null
     *
     * @param tagList
     * @return
     */
    private List<Tag> sort(List<Tag> tagList) {
        return tagList.stream().sorted(Comparator.comparing(tag -> (tag.getExtensions() == null ? Collections.<String, Object>emptyMap() : tag.getExtensions())
                        .keySet().stream().filter(key -> key.startsWith("x-SORT-")).findFirst().orElse("x-SORT-10000")))
                .collect(Collectors.toList());
    }

}
