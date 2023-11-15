package com.levin.oak.base.config;

import com.levin.oak.base.autoconfigure.FrameworkProperties;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.annotation.PostConstruct;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static com.levin.oak.base.ModuleOption.PLUGIN_PREFIX;

/**
 * 验证配置
 */
@Slf4j
@Configuration(PLUGIN_PREFIX + "ValidationConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "ValidationConfigurer", matchIfMissing = true)
@ConditionalOnClass({Validator.class,})
@ConditionalOnMissingBean({Validator.class,})
public class ValidationConfigurer {

    @Autowired
    FrameworkProperties frameworkProperties;

    @PostConstruct
    public void init() {
        log.info("*** 验证器自定义配置，允许验证器快速失败，可以通过配置[{}={}]关闭该配置组件。", PLUGIN_PREFIX + "ValidationConfigurer", "false");
    }

    @Bean
    public Validator validator() {

        ValidatorFactory validatorFactory =

                Validation.byProvider(HibernateValidator.class)

                        .configure()
                        //failFast：只要出现校验失败的情况，就立即结束校验，不再进行后续的校验。
                        .failFast(true)

                        .buildValidatorFactory();

        return validatorFactory.getValidator();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {

        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();

        methodValidationPostProcessor.setValidator(validator());

        return methodValidationPostProcessor;
    }

}
