package com.levin.oak.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.levin.commons.dao.domain.support.TestEntity;
import com.levin.commons.service.domain.ApiResp;
import com.levin.commons.service.domain.BaseResp;
import com.levin.commons.service.domain.EnumDesc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;

import static com.levin.oak.base.ModuleOption.*;

@Slf4j
@Configuration(PLUGIN_PREFIX + "AppWebMvcConfigurer")
@ConditionalOnProperty(prefix = PLUGIN_PREFIX, name = "AppWebMvcConfigurer", matchIfMissing = true)
public class AppWebMvcConfigurer implements WebMvcConfigurer {

//    @Autowired
//    RbacService rbacService;
//
//    @Autowired
//    AuthService authService;
//
//    @Autowired
//    BizTenantService bizTenantService;

    @PostConstruct
    void init() {
        log.info("init...");
    }
//
//    static class String2EnumCF implements ConverterFactory<String, Enum> {
//        @Override
//        public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
//            return name -> (T) EnumDesc.parse(targetType, name);
//        }
//    }
//
//    static class Number2EnumCF implements ConverterFactory<Number, Enum> {
//        @Override
//        public <T extends Enum> Converter<Number, T> getConverter(Class<T> targetType) {
//            return code -> (T) EnumDesc.parse(targetType, code.intValue());
//        }
//    }
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//
//        //移除默认的转换器
//        registry.removeConvertible(String.class, Enum.class);
//        registry.removeConvertible(int.class, Enum.class);
//        registry.removeConvertible(Integer.class, Enum.class);
//        registry.removeConvertible(Number.class, Enum.class);
//
//        registry.addConverterFactory(new String2EnumCF());
//        registry.addConverterFactory(new Number2EnumCF());
//
//    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper o = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();

                SimpleModule module = new SimpleModule().addSerializer(TestEntity.class, new JsonSerializer<TestEntity>() {
                    @Override
                    public void serialize(TestEntity testEntity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

                        jsonGenerator.writeStartObject();

                        jsonGenerator.writeBooleanField("test", true);

                        //定制输出
                        jsonGenerator.writeEndObject();

                    }
                });

                // o.registerModule(module);
            }
        }

    }

}
