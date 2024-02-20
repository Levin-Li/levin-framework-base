package com.levin.oak.base.codegen.model;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.plugin.PluginManager;
import com.levin.commons.rbac.RbacUtils;
import com.levin.commons.rbac.ResAuthorize;
import com.levin.commons.rbac.ResPermission;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.support.SpringContextHolder;
import com.levin.commons.ui.annotation.CRUD;
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
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.beans.PropertyDescriptor;
import java.io.Externalizable;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ModelBuildUtils {


    public static Map<Plugin, Module> buildInfo(ApplicationContext context) {

        LinkedHashMap<Plugin, Module> map = new LinkedHashMap<>();

        PluginManager pluginManager = context.getBean(PluginManager.class);

        for (Plugin plugin : pluginManager.getInstalledPlugins()) {
            map.put(plugin, buildInfo(context, plugin));
        }

        return map;
    }

    public static Module buildInfo(ApplicationContext context, Plugin plugin) {

        Module module = new Module();

        module.setId(plugin.getId())
                .setName(plugin.getName())
                .setLabel(plugin.getName())
                .setDesc(plugin.getRemark())
                .setType(plugin.getType());

        //获取请求映射
        //Environment environment = context.getBean(Environment.class);

        DefaultParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

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

            String entityName = beanType.getSimpleName();

            if (entityName.endsWith("Controller")) {
                entityName = entityName.substring(0, entityName.length() - 10);
            }

            if (entityName.endsWith("Biz")) {
                entityName = entityName.substring(3);
            }

            if (!StringUtils.trimAllWhitespace(basePath).replace("//", "/").equals("/")) {
                //entityName = basePath.contains("/") ? basePath.substring(basePath.lastIndexOf('/') + 1) : basePath;
            }

            entityName = entityName.replace('-', '_').replace(' ', '_');

            //首字母大写
            entityName = entityName.substring(0, 1).toUpperCase() + entityName.substring(1);

            service.setId(beanType.getName())
                    .setName(entityName)
                    .setLabel(tag == null ? "" : tag.name())
                    .setDesc(tag == null ? "" : tag.description())
                    .setType(serviceResAuthorize == null ? "" : serviceResAuthorize.type())
                    .setPathPrefix(basePath);

            Map<Method, ResAuthorize> methodResAuthorizeMap = RbacUtils.loadClassResAuthorize(controller, true);

            final boolean isViewType = AnnotatedElementUtils.findMergedAnnotation(beanType, ResponseBody.class) == null;


            //
            methodResAuthorizeMap.forEach((method, resAuthorize) -> {

                Operation operation = AnnotatedElementUtils.findMergedAnnotation(method, Operation.class);

                ResolvableType forMethodReturnType = ResolvableType.forMethodReturnType(method, beanType);

                //Spring MVC视图 View 接口  ModelAndView
                boolean isView = isViewType && AnnotatedElementUtils.findMergedAnnotation(method, ResponseBody.class) == null;

                Stack<Class<?>> classStack = new Stack<>();

                Module.Api api = new Module.Api()
                        .setId(method.toGenericString())
                        .setView(isView)
                        .setReturnTDefinePrefix(getTypeParameters(classStack, method.getTypeParameters()))
                        .setReturnTypeDefine((method.getGenericReturnType().getTypeName()).trim())
                        .setName(method.getName())
                        .setLabel(operation == null ? "" : operation.summary())
                        .setDesc(operation == null ? "" : operation.description());

                //加入UI注解
                Stream.of(method.getAnnotations()).filter(an -> an.annotationType().getPackage().getName().startsWith(CRUD.class.getPackage().getName())).forEachOrdered(api.crudAnnotations::add);

                classStack.forEach(service::addImport);
                classStack.clear();

                String typeDefine = getTypeDefine(forMethodReturnType.getType(), classStack);
                classStack.forEach(service::addImport);
                classStack.clear();

                RequestMapping methodMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);

                //简化方法ID
                if (methodMapping != null) {
                    api.setMethod(Stream.of(methodMapping.method()).map(Enum::name).collect(Collectors.toList()));
                    if (api.getMethod().isEmpty()) {
                        api.setMethod(Arrays.asList(RequestMethod.POST.name()));
                    }
                    api.setPath(Arrays.asList(methodMapping.path()));
                }

                if (resAuthorize != null && !resAuthorize.ignored()) {
                    api.setRequireAuth(true);
                    if (!resAuthorize.onlyRequireAuthenticated()) {
                        api.setRequireAuthorizations(
                                new ResPermission()
                                        .setDomain(resAuthorize.domain())
                                        .setType(resAuthorize.type())
                                        //.setRes(tagName)
                                        //不标识具体的资源
                                        .setRes(StringUtils.hasText(resAuthorize.res()) ? resAuthorize.res() : "")
                                        .setAction(resAuthorize.action()).toString()
                        );
                    }
                }

                if (!isView) {
                    //处理返回值
                    buildDtoInfo(forMethodReturnType, module.getSchemas());
                }

                String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);

                //处理参
                int paramIndex = 0;
                for (Parameter parameter : method.getParameters()) {

                    String parameterName = parameterNames != null && parameterNames.length >= paramIndex ? parameterNames[paramIndex] : parameter.getName();

                    ResolvableType paramResolvableType = ResolvableType.forMethodParameter(method, paramIndex++, beanType);

                    Class<?> resolve = paramResolvableType.resolve();
                    if (Stream.of(HttpServletRequest.class, HttpServletResponse.class, HttpSession.class, MultipartFile.class, MultipartRequest.class)
                            .anyMatch(r -> r.isAssignableFrom(resolve))) {
                        log.debug("跳过参数 {} {}", resolve, parameterName);
                        continue;
                    }

                    //加入导入
                    classStack.clear();
                    getTypeDefine(paramResolvableType.getType(), classStack);
                    classStack.forEach(service::addImport);

                    Module.Column param = new Module.Column()
                            .setId("" + paramIndex)
                            .setRequestBody(parameter.isAnnotationPresent(RequestBody.class))
                            .setRequiredMode(Stream.of(parameter.getAnnotations()).filter(an -> an instanceof NotEmpty || an instanceof NotBlank || an instanceof NotNull).map(an -> an.annotationType().getSimpleName()).findFirst().orElse(null))
                            .setName(parameterName);

                    Schema columnSchema = parameter.getAnnotation(Schema.class);

                    RequestParam requestParam = parameter.getAnnotation(RequestParam.class);

                    if (requestParam != null) {

                        if (StringUtils.hasText(requestParam.name())) {
                            param.setName(requestParam.name());
                        }

                        if (requestParam.required()) {
                            param.setRequiredMode(NotNull.class.getSimpleName());
                        }

                    }

                    PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);

                    if (pathVariable != null) {

                        param.setPathVariable(true);

                        if (StringUtils.hasText(pathVariable.name())) {
                            param.setName(pathVariable.name());
                        }
                        if (pathVariable.required()) {
                            param.setRequiredMode(NotNull.class.getSimpleName());
                        }
                    }

                    if (isSimpleType(resolve)) {
                        param.setSimpleType(true)
                                .setTypeDefine(paramResolvableType.getType().getTypeName());
                    } else {
                        param.setSimpleType(resolve.isEnum())
                                .setTypeDefinePrefix(getTypeParameters(null, method.getTypeParameters()))
                                .setTypeDefine(paramResolvableType.getType().getTypeName());

                        if (columnSchema == null) {
                            columnSchema = resolve.getAnnotation(Schema.class);
                        }
                    }

                    if (columnSchema != null) {
                        param.setLabel(columnSchema.title())
                                .setDesc(columnSchema.description());
                    } else {
                        log.warn("方法{} 参数：{} {} 没有 Schema注解", method, parameterName, paramIndex);
                    }

                    //如果label为空，从desc中获取
                    if (StrUtil.isBlank(param.label)) {
                        String[] desc = LangUtils.splitDesc(param.desc);
                        if (desc.length > 1) {
                            param.setLabel(desc[0]).setDesc(desc[1]);
                        }
                    }

                    //递归解析字段类型，去除数组
                    while (paramResolvableType.isArray()) {
                        paramResolvableType = paramResolvableType.getComponentType();
                    }

                    //如果是枚举
                    if (!isSimpleType(resolve)) {
                        //递归解析字段类型
                        // log.info("开始解析字段：{} 属性：{} 类型：{} - {}", resolve.getName(), columnName, forColumnType.getType().getTypeName(), readMethod.getGenericReturnType());
                        buildDtoInfo(paramResolvableType, module.getSchemas());
                    }

                    api.paramList.add(param);

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

    private static boolean isSimpleType(Class<?> resolve) {
        return resolve == null
                //不包含枚举
                || (BeanUtils.isSimpleProperty(resolve) && !resolve.isEnum())
                || resolve == Object.class
                || resolve == Serializable.class
                || resolve == Externalizable.class
                || resolve == Void.class
                || resolve == void.class;
    }

    private static String findConstraints(AccessibleObject... elements) {
        return Stream.of(elements).filter(Objects::nonNull)
                .flatMap(ele -> Stream.of(ele.getAnnotations()))
                .filter(an -> an.annotationType().getPackage().getName().startsWith(NotNull.class.getPackage().getName()))
                .filter(an -> an instanceof NotEmpty || an instanceof NotBlank || an instanceof NotNull)
                .map(an -> an.annotationType().getSimpleName())
                .findFirst().orElse(null);
    }


    private static <T extends Annotation> T findAnnotation(Class<T> annotationClass, AccessibleObject... elements) {
        return Stream.of(elements).filter(Objects::nonNull).map(ele -> ele.getAnnotation(annotationClass)).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public static String getRootTypeDefine(Type type) {
        return getTypeDefine(type, null);
    }

    public static String getTypeDefine(Type type, Stack<Class<?>> stack) {

        if (stack == null) {
            stack = new Stack<>();
        }

        int arrayDepth = 0;

        while (type instanceof GenericArrayType) {
            arrayDepth++;
            type = ((GenericArrayType) type).getGenericComponentType();
        }

        StringBuilder typDefineBuilder = new StringBuilder();

        //枚举，特殊处理
        if (type instanceof Class) {

            Class<?> clazzType = (Class<?>) type;

            typDefineBuilder.append(clazzType.getName());

            //防止递归
            if (!stack.contains(clazzType)
                    && clazzType != Object.class
                    && clazzType != Enum.class) {

                stack.push(clazzType);

                typDefineBuilder.append(getTypeParameters(stack, clazzType.getTypeParameters()));
            }

        } else if (type instanceof TypeVariable) {

            TypeVariable<?> typeVariable = (TypeVariable<?>) type;

            typDefineBuilder.append(typeVariable.getName());

            if (typeVariable.getBounds() != null
                    && typeVariable.getBounds().length > 0
                    && typeVariable.getBounds()[0] instanceof Class) {
                Class<?> clazzType = (Class<?>) typeVariable.getBounds()[0];
                if (!stack.contains(clazzType) && clazzType != Object.class && clazzType != Enum.class) {
                    stack.push(clazzType);
                }
            }

            typDefineBuilder.append(getBounds(stack, true, typeVariable.getBounds()));

        } else if (type instanceof ParameterizedType) {

            ParameterizedType pType = (ParameterizedType) type;

            Type rawType = pType.getRawType();

            typDefineBuilder.append(rawType.getTypeName());

            if (rawType instanceof Class) {
                Class<?> clazzType = (Class<?>) rawType;
                if (!stack.contains(clazzType) && clazzType != Object.class && clazzType != Enum.class) {
                    stack.push(clazzType);
                }
            } else {
                log.warn("ParameterizedType getRawType 未知类型:{}", rawType);
            }

            typDefineBuilder.append(getTypeParameters(stack, pType.getActualTypeArguments()));

        } else if (type instanceof WildcardType) {

            WildcardType wildcardType = (WildcardType) type;

            // 获取泛型表达式上界（对应extends后面的类型）
//            Type[] getUpperBounds();
            // 获取泛型表达式下界（对应super后面的类型）
//            Type[] getLowerBounds();

            typDefineBuilder.append("?")
                    .append(getBounds(stack, true, wildcardType.getUpperBounds()))
                    .append(getBounds(stack, false, wildcardType.getLowerBounds()));

        } else {
            log.warn("未知类型:{}", type);
            typDefineBuilder.append(type.getTypeName());
        }

        for (int i = 0; i < arrayDepth; i++)
            typDefineBuilder.append("[]");

        return typDefineBuilder.toString();
    }


    private static String getBounds(Stack<Class<?>> stack, boolean isUpper, Type... types) {

        if (types == null
                || types.length == 0
                || types[0] == Object.class) {
            return "";
        }

        return (isUpper ? " extends " : " super ") + getTypeDefine(types[0], stack);

    }

    /**
     * 获取泛型参数定义
     * <p>
     * 主要是类和方法上的定义
     *
     * @param types
     * @return
     */
    public static String getTypeParameters(Stack<Class<?>> stack, Type... types) {

        if (types == null || types.length == 0) {
            return "";
        }

        StringJoiner joiner = new StringJoiner(", ", "<", ">");

        for (Type type : types) {
            joiner.add(getTypeDefine(type, stack));
        }

        return joiner.toString();
    }

    /**
     * 要防止字段递归
     *
     * @param resolvableType
     * @param outResultMap
     */
    public static void buildDtoInfo(ResolvableType resolvableType, Map<String, Module.DtoSchema> outResultMap) {

        if (resolvableType == null) {
            return;
        }

        if (resolvableType.isArray()) {
            resolvableType = resolvableType.getComponentType();
        }

        //如果是简单类型，直接返回
        Class<?> resolve = resolvableType.resolve();

        //如果是简单类型，直接返回
        if (isSimpleType(resolve)) {
            return;
        }

        Module.DtoSchema dtoSchema = outResultMap.computeIfAbsent(resolve.getName(),
                k -> new Module.DtoSchema()
                        .setId(resolve.getName())
                        .setClassType(resolve)
                        .setInterface(resolve.isInterface())
                        .setEnum(resolve.isEnum())
                        .setName(resolve.getSimpleName())
                        .setTypeDefine(getRootTypeDefine(resolve))
        );


        if (dtoSchema.isEnum()) {

            //如果已经存在，并且字段已经解析了，直接返回
            if (dtoSchema.getColumnList() != null) {
                return;
            }

            dtoSchema.setColumnList(new ArrayList<>());

            for (Object _enumConstant : dtoSchema.classType.getEnumConstants()) {
                Enum<?> enumConstant = (Enum<?>) _enumConstant;

                Module.Column column = new Module.Column()
                        .setId(enumConstant.name())
                        .setName(enumConstant.name())
                        .setLabel(EnumDesc.getDesc(enumConstant));

                dtoSchema.getColumnList().add(column);
            }

            return;
        }

        //放入
        addImports(resolve, dtoSchema);

        //处理泛型
        for (ResolvableType generic : resolvableType.getGenerics()) {

            if (generic.resolve() != resolve //防止递归
                    && !isSimpleType(generic.resolve())
                    && (!outResultMap.containsKey(generic.resolve().getName()) || generic.hasGenerics())) {
                buildDtoInfo(generic, outResultMap);
            }
        }

        //如果已经存在，并且字段已经解析了，直接返回
        if (dtoSchema.getColumnList() != null) {
            return;
        }

        dtoSchema.setColumnList(new ArrayList<>());

        //如果是容器直接返回，忽略字段
        if (Iterable.class.isAssignableFrom(resolve)
                || Map.class.isAssignableFrom(resolve)) {
            return;
        }

        Schema schema = AnnotatedElementUtils.findMergedAnnotation(resolve, Schema.class);

        if (schema != null) {
            dtoSchema.setLabel(schema.title())
                    .setDesc(schema.description());
        }

        //
        dtoSchema.setColumnList(parseColumns(resolvableType, dtoSchema, outResultMap));

        if (resolve.isInterface()) {

            for (ResolvableType resolvableTypeInterface : resolvableType.getInterfaces()) {
                if (canIgnore(resolvableTypeInterface)) {
                    continue;
                }

                addImports(resolvableTypeInterface.resolve(), dtoSchema);
                addImports(resolvableTypeInterface.getType(), dtoSchema);

                if (!isSimpleType(resolvableTypeInterface.resolve())) {
                    buildDtoInfo(resolvableTypeInterface, outResultMap);
                }
                //父类型描述，包含泛型
                dtoSchema.getInterfaceList().add(resolvableTypeInterface.getType().getTypeName());
            }

        } else {

            //处理父类型
            ResolvableType superResolvableType = resolvableType.getSuperType();

            if (!canIgnore(superResolvableType)) {

                addImports(superResolvableType.resolve(), dtoSchema);
                addImports(superResolvableType.getType(), dtoSchema);

                //递归
                //递归构建父类型
                buildDtoInfo(superResolvableType, outResultMap);

                //父类型描述，包含泛型
                dtoSchema.setSuperType(superResolvableType.getType().getTypeName());

            }

        }

        log.info("解析类型 ： {} ", dtoSchema.getFullTypeDefine());

    }


    private static boolean canIgnore(ResolvableType resolvableType) {

        if (resolvableType == null || resolvableType.resolve() == null) {
            return true;
        }

        return Stream.of("java.lang.", "java.util.", "java.io.").anyMatch(it -> resolvableType.resolve().getName().startsWith(it));
    }

    private static void addImports(Type resolvableTypeInterface, Module.DtoSchema dtoSchema) {
        Stack<Class<?>> classStack = new Stack<>();
        getTypeDefine(resolvableTypeInterface, classStack);
        classStack.forEach(dtoSchema::addImport);
    }

    private static List<Module.Column> parseColumns(ResolvableType resolvableType, Module.DtoSchema dtoSchema, Map<String, Module.DtoSchema> outResultMap) {

        Class<?> resolve = resolvableType.resolve();

        List<Module.Column> columnList = new ArrayList<>();

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

                //请求对象
                if (resolve.getName().endsWith("Req")) {
                    continue;
                }
            }

            Field field = ReflectionUtils.findField(resolve, columnName);

            //如果字段忽略
            if (findAnnotation(JsonIgnore.class, readMethod, field) != null) {
                continue;
            }


            Schema columnSchema = findAnnotation(Schema.class, readMethod, field);

            //字段类型
            ResolvableType forColumnType = ResolvableType.forType(readMethod.getGenericReturnType(), resolvableType);

            if (readMethod.getDeclaringClass().getSimpleName().equals("Plugin")) {

                TypeVariable<Method>[] typeParameters = readMethod.getTypeParameters();
                log.debug("{}", readMethod.getGenericReturnType().getTypeName());
            }

            Module.Column column = new Module.Column()
                    .setId(trimId.apply(readMethod.toGenericString()))
                    .setName(columnName)
                    .setReadOnly(!hasSetter)
                    .setRequiredMode(findConstraints(readMethod, field))
                    .setTypeDefinePrefix(getTypeParameters(null, readMethod.getTypeParameters()))
                    .setTypeDefine(readMethod.getGenericReturnType().getTypeName());

            Stream.of(readMethod.getAnnotations()).filter(an -> an.annotationType().getPackage().getName().startsWith(CRUD.class.getPackage().getName())).forEachOrdered(column.crudAnnotations::add);

            if (field != null) {
                Stream.of(field.getDeclaredAnnotations()).filter(an -> an.annotationType().getPackage().getName().startsWith(CRUD.class.getPackage().getName())).forEachOrdered(column.crudAnnotations::add);
            }

            addImports(readMethod.getGenericReturnType(), dtoSchema);


            if (columnSchema != null) {
                column.setLabel(columnSchema.title())
                        .setDesc(columnSchema.description());
            } else {
                log.warn("解析字段：{} 属性：{} 没有 Schema注解", resolve.getName(), columnName);
            }

            log.info("解析字段：{} {}", column.getTypeDefine(), columnName);

            //如果label为空，从desc中获取
            if (StrUtil.isBlank(column.label)) {
                String[] desc = LangUtils.splitDesc(column.desc);
                if (desc.length > 1) {
                    column.setLabel(desc[0]).setDesc(desc[1]);
                }
            }

            //递归解析字段类型，去除数组
            while (forColumnType.isArray()) {
                forColumnType = forColumnType.getComponentType();
            }

            Class<?> columnResolve = forColumnType.resolve();

            //如果是枚举
            if (!isSimpleType(columnResolve)) {
                column.setSimpleType(columnResolve.isEnum());
                //递归解析字段类型
                // log.info("开始解析字段：{} 属性：{} 类型：{} - {}", resolve.getName(), columnName, forColumnType.getType().getTypeName(), readMethod.getGenericReturnType());
                buildDtoInfo(forColumnType, outResultMap);
            } else {
                column.setSimpleType(true);
            }

            //加入字段
            columnList.add(column);
        }

        return columnList;
    }


}
