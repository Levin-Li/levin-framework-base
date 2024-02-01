package com.levin.oak.base.codegen.model;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.service.support.SpringContextHolder;
import com.levin.commons.utils.LangUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
public class ModelUtils {

    public static Module buildInfo(ApplicationContext context, Plugin plugin) {

        Module module = new Module();

        module.setId(plugin.getId())
                .setName(plugin.getName())
                .setLabel(plugin.getName())
                .setDesc(plugin.getRemark())
                .setType(plugin.getType());


        //获取请求映射
        //Environment environment = context.getBean(Environment.class);

        //查找模块的所有控制器
        List<Object> controllers = SpringContextHolder.findBeanByPkgName(context, Controller.class, plugin.getPackageName());

        final Function<String, String> evalFun = getPathEvalFun(context);

        controllers.forEach(controller -> {

            Module.Service service = new Module.Service();
            module.getServiceList().add(service);

            final Class<?> beanType = AopProxyUtils.ultimateTargetClass(controller);

            RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(beanType, RequestMapping.class);
            Tag tag = AnnotatedElementUtils.findMergedAnnotation(beanType, Tag.class);
            ResAuthorize serviceResAuthorize = AnnotatedElementUtils.findMergedAnnotation(beanType, ResAuthorize.class);

            //只取一个地址
            String basePath = requestMapping == null ? "" : requestMapping.value()[0];

            //处理路径变量
            basePath = evalFun.apply(basePath);

            String entityName = beanType.getSimpleName().replace("Controller", "");

            if (!StringUtils.trimAllWhitespace(basePath).replace("//", "/").equals("/")) {
                entityName = basePath.contains("/") ? basePath.substring(basePath.lastIndexOf('/') + 1) : basePath;
            }

            service.setId(beanType.getName())
                    .setName(entityName)
                    .setLabel(tag == null ? "" : tag.name())
                    .setDesc(tag == null ? "" : tag.description())
                    .setType(serviceResAuthorize == null ? "" : serviceResAuthorize.type())
                    .setPathPrefix(basePath);

            Map<Method, ResAuthorize> methodResAuthorizeMap = RbacUtils.loadClassResAuthorize(controller, true);

            final boolean isViewType = AnnotatedElementUtils.findMergedAnnotation(beanType, ResponseBody.class) == null;


            methodResAuthorizeMap.forEach((method, resAuthorize) -> {

                Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);

                ResolvableType forMethodReturnType = ResolvableType.forMethodReturnType(method, beanType);

                //Spring MVC视图 View 接口  ModelAndView
                boolean isView = isViewType && AnnotatedElementUtils.findMergedAnnotation(method, ResponseBody.class) == null;

                Module.Api api = new Module.Api()
                        .setId(method.toGenericString())
                        .setView(isView)
                        .setReturnType(forMethodReturnType.getType().getTypeName())
                        .setName(method.getName())
                        .setLabel(operation == null ? "" : operation.summary())
                        .setDesc(operation == null ? "" : operation.description());

                //简化方法ID
                api.setId(api.id.substring(api.id.indexOf(api.returnType) + api.returnType.length() + 1));

                if (!isView) {
                    //处理返回值
                    buildDtoInfo(forMethodReturnType
                            , cls -> !module.getSchemas().containsKey(cls.getName())
                            , dto -> module.getSchemas().put(dto.getId(), dto)
                    );
                }

                service.getApiList().add(api);

            });

        });

        return module;
    }

    private static Function<String, String> getPathEvalFun(ApplicationContext context) {

        Environment environment = context.getBean(Environment.class);

        StandardBeanExpressionResolver expressionResolver = new StandardBeanExpressionResolver();

        BeanExpressionContext beanExpressionContext = new BeanExpressionContext((ConfigurableBeanFactory) context.getAutowireCapableBeanFactory(), null);

        return (String path) -> {

            while (path.contains("${")) {
                path = environment.resolvePlaceholders(path);
            }

            while (path.contains("#{")) {
                path = expressionResolver.evaluate(path, beanExpressionContext).toString();
            }

            return path;
        };
    }

    private static boolean isIgnore(Class<?> resolve, Predicate<Class<?>> isParse) {
        return BeanUtils.isSimpleProperty(resolve)
                || resolve == Object.class
                || resolve == Void.class
                || resolve == void.class
                //忽略集合类型
                || (isParse != null && !isParse.test(resolve));
    }

//    private List<Annotation> findRequiredMode(AccessibleObject... elements) {
//        return Stream.of(elements).filter(Objects::nonNull).map(ele -> ele.getAnnotation(NotEmpty.class))
//    }

    /**
     * 要防止字段递归
     *
     * @param resolvableType
     * @param isParse
     * @param consumer
     */
    public static void buildDtoInfo(ResolvableType resolvableType, Predicate<Class<?>> isParse, Consumer<Module.DtoSchema> consumer) {

        if (resolvableType.isArray()) {
            resolvableType = resolvableType.getComponentType();
        }
        //如果是简单类型，直接返回
        Class<?> resolve = resolvableType.resolve();

        //如果是简单类型，直接返回
        if (isIgnore(resolve, null)) {
            return;
        }

        Assert.isTrue(!resolvableType.hasUnresolvableGenerics(), "发型未能解析的泛型-" + resolvableType.getType().getTypeName());

        for (ResolvableType generic : resolvableType.getGenerics()) {
            if (!isIgnore(generic.resolve(), isParse)) {
                buildDtoInfo(generic, isParse, consumer);
            }
        }

        //如果是集合直接返回
        if (!isParse.test(resolve)
                || Iterable.class.isAssignableFrom(resolve)
                || Map.class.isAssignableFrom(resolve)) {
            return;
        }

        log.info("解析类型：{}", resolve.getName());

        Schema schema = AnnotatedElementUtils.findMergedAnnotation(resolve, Schema.class);

        Module.DtoSchema dto = new Module.DtoSchema()
                .setId(resolve.getName())
                .setName(resolve.getSimpleName())
                .setLabel(schema == null ? "" : schema.title())
                .setDesc(schema == null ? "" : schema.description());

        //生成
        consumer.accept(dto);

        final Function<String, String> trimId = txt -> txt.substring(txt.lastIndexOf(' ') + 1);


        JsonIgnoreProperties jsonIgnoreProperties = AnnotatedElementUtils.findMergedAnnotation(resolve, JsonIgnoreProperties.class);

        List<String> ignoreProperties = jsonIgnoreProperties == null || jsonIgnoreProperties.allowGetters() ? Collections.emptyList() : Arrays.asList(jsonIgnoreProperties.value());

        for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(resolve)) {

            //忽略的属性
            String columnName = pd.getName();

            if (ignoreProperties.contains(columnName)) {
                continue;
            }

            Method readMethod = pd.getReadMethod();

            if (readMethod == null
                    || Modifier.isStatic(readMethod.getModifiers())
                    || !Modifier.isPublic(readMethod.getModifiers())
                    //不是当前类的字段
                    || readMethod.getDeclaringClass() != resolve
                    || AnnotatedElementUtils.findMergedAnnotation(readMethod, JsonIgnore.class) != null
            ) {
                continue;
            }

            boolean hasSetter = pd.getWriteMethod() != null;

            if (!hasSetter && AnnotatedElementUtils.findMergedAnnotation(readMethod, JsonProperty.class) == null) {
                //如果只有get方法，并且没有注明JsonProperty，则忽略
                //continue;
            }

            Schema columnSchema = AnnotatedElementUtils.findMergedAnnotation(readMethod, Schema.class);

            Field field = ReflectionUtils.findField(resolve, columnName);

            //如果字段忽略
            if (field != null && field.isAnnotationPresent(JsonIgnore.class)) {
                continue;
            }

            if (columnSchema == null && field != null) {
                columnSchema = AnnotatedElementUtils.findMergedAnnotation(field, Schema.class);
            }

            //字段类型
            ResolvableType forColumnType = ResolvableType.forType(readMethod.getGenericReturnType(), resolvableType);

            Module.Column column = new Module.Column()
                    .setId(trimId.apply(readMethod.toGenericString()))
                    .setName(columnName)

                    //是否必须
                    //.setRequiredMode()

                    //字段类型描述，包含泛型信息
                    .setType(forColumnType.getType().getTypeName());

            if (columnSchema != null) {
                column.setLabel(columnSchema.title())
                        .setDesc(columnSchema.description());
            } else {
                log.warn("解析字段：{} 属性：{} 没有 Schema注解", resolve.getName(), columnName);
            }

            //如果label为空，从desc中获取
            if (StrUtil.isBlank(column.label)) {
                String[] desc = LangUtils.splitDesc(column.desc);
                if (desc.length > 1) {
                    column.setLabel(desc[0]).setDesc(desc[1]);
                }
            }

            //递归解析字段类型，去除数组
            forColumnType = forColumnType.isArray() ? forColumnType.getComponentType() : forColumnType;

            //如果是枚举
            if (forColumnType.resolve().isEnum()) {
                for (Object enumConstant : forColumnType.resolve().getEnumConstants()) {
                    column.getOptions().add(enumConstant.toString());
                }
            } else {
                //递归解析字段类型
                buildDtoInfo(forColumnType, isParse, consumer);
            }

            //加入字段
            dto.getColumnList().add(column);
        }

        //处理父类型
        Type superclass = resolve.getGenericSuperclass();

        if (superclass != null
                && superclass != Object.class) {

            //递归
            ResolvableType superResolvableType = ResolvableType.forType(superclass, resolvableType);

            //递归构建父类型
            buildDtoInfo(superResolvableType, isParse, consumer);

            //父类型描述，包含泛型
            dto.setSuperType(superResolvableType.getType().getTypeName());
        }

    }


}
