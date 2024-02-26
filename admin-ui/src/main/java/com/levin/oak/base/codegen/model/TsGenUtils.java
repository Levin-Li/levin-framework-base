package com.levin.oak.base.codegen.model;

import cn.hutool.core.lang.Assert;
import com.google.gson.Gson;
import com.levin.commons.plugin.Plugin;
import com.levin.commons.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.io.Externalizable;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Stream;

import static com.levin.oak.base.codegen.model.ModelBuildUtils.getTypeDefine;


@Slf4j
public class TsGenUtils {

    public static void genTsClient(ApplicationContext context, File baseDir) {

        Map<Plugin, Module> moduleMap = ModelBuildUtils.buildInfo(context);

        final Map<String, String> baseTypeMap = Collections.unmodifiableMap(getTsTypeMap());


        moduleMap.forEach((plugin, module) -> {

            Map<String, String> tsTypeMap = new LinkedHashMap<>();

            //类型替换
            module.getSchemas().forEach((name, schema) -> schema.setName(getTypeName(tsTypeMap, schema.classType)));

            //放入基本类型
            tsTypeMap.putAll(baseTypeMap);

            Map<String, Object> moduleParams = MapUtils
                    .putFirst("plugin", plugin)
                    .put("moduleId", module.id.replace(".", "_"))
                    .put("module", module)
                    .build();

            module.getSchemas().forEach((name, schema) -> {

                if (Map.class.isAssignableFrom(schema.classType)
                        || Iterable.class.isAssignableFrom(schema.classType)
                        || Model.class.isAssignableFrom(schema.classType)
                        || Comparable.class == schema.classType
                ) {
                    return;
                }

                schema.setSuperType(replaceType(schema.superType, tsTypeMap))
                        .setTypeDefine(replaceType(schema.typeDefine, tsTypeMap));

                List<String> interfaceList = new ArrayList<>();

                schema.interfaceList.forEach(it -> {
                    interfaceList.add(replaceType(it, tsTypeMap));
                });

                schema.interfaceList = interfaceList;

                schema.columnList.forEach(column -> {
                    column.setTypeDefine(replaceType(column.typeDefine, tsTypeMap))
                            .setTypeDefinePrefix(replaceType(column.typeDefinePrefix, tsTypeMap));
                });

                Set<String> importList = new LinkedHashSet<>();

                schema.importList.forEach(imp -> {
                    String newName = replaceType(imp.getName(), tsTypeMap);
                    if (!isBaseTsType(newName)) {
                        importList.add(newName);
                    }
                });

                //内部类处理
                Map<String, Object> params = MapUtils
                        .put(moduleParams)
                        .put("schema", schema)
                        .put("schemaName", schema.name)
                        .put("schemaImportList", importList)
                        .build();

                TemplateUtils.genFileByTemplate(baseDir, null, "client/${moduleId}/service/dto/${schemaName}.ts", params);

            });

            module.serviceList.forEach(service -> {

                service.apiList.forEach(api -> {

                    api.setReturnTypeDefine(replaceType(api.returnTypeDefine, tsTypeMap))
                            .setReturnTDefinePrefix(replaceType(api.returnTDefinePrefix, tsTypeMap))
                    //
                    //   .setReturnTDefinePrefix("<T extends any>")
                    ;

                    api.paramList.forEach(param -> {
                        param.setTypeDefine(replaceType(param.typeDefine, tsTypeMap))
                                .setTypeDefinePrefix(replaceType(param.typeDefinePrefix, tsTypeMap));
                    });

                });

                Set<String> importList = new LinkedHashSet<>();

                service.importList.forEach(imp -> {
                    String newName = replaceType(imp.getName(), tsTypeMap);
                    if (!isBaseTsType(newName)) {
                        importList.add(newName);
                    }
                });


                Map<String, Object> params = MapUtils
                        .put(moduleParams)
                        .put("serviceName", service.name + "Service")
                        .put("service", service)
                        .put("serviceImportList", importList)
                        .build();

                TemplateUtils.genFileByTemplate(baseDir, null, "client/${moduleId}/service/${serviceName}.ts", params);

            });

            TemplateUtils.genFileByTemplate(baseDir, null, "client/${moduleId}/service/index.ts", moduleParams);

        });

    }


    public static boolean isBaseTsType(String type) {
        return Stream.of("string", "number", "boolean", "Date", "Set", "Map", "Array", "any").anyMatch(it -> it.equals(type));
    }

    private static String replaceType(String type, Map<String, String> typeMap) {

        if (!StringUtils.hasText(type)) {
            return type;
        }

        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            type = type.replace(entry.getKey(), entry.getValue());
        }

        return type;
    }

    private static Map<String, String> getTsTypeMap() {

        String number = "number";

        String string = "string";

        return MapUtils

                .put(Number.class.getName(), number)
                .put(BigDecimal.class.getName(), number)
                .put(BigInteger.class.getName(), number)

                .put(byte.class.getName(), number)
                .put("byte", number)
                .put(Byte.class.getName(), number)

                .put(short.class.getName(), number)
                .put("short", number)
                .put(Short.class.getName(), number)

                .put(int.class.getName(), number)
                .put("int", number)
                .put(Integer.class.getName(), number)

                .put(long.class.getName(), number)
                .put("long", number)
                .put(Long.class.getName(), number)

                .put(float.class.getName(), number)
                .put("float", number)
                .put(Float.class.getName(), number)

                .put(double.class.getName(), number)
                .put("double", number)
                .put(Double.class.getName(), number)


                .put(Boolean.class.getName(), "boolean")
                .put(boolean.class.getName(), "boolean")

                .put(CharSequence.class.getName(), string)
                .put(String.class.getName(), string)
                .put(Character.class.getName(), string)
                .put(char.class.getName(), string)

                //序列化成字符串
                .put(Date.class.getName(), string)

                .put(Object.class.getName(), "any")
                .put(Serializable.class.getName(), "any")
                .put(Externalizable.class.getName(), "any")

                .put(Comparable.class.getName(), "any")
                .put("any<?>", "any")

                .build();
    }

    private static String getName(Class<?> type) {
        return type.getName().substring(type.getPackage().getName().length() + 1);
    }


    private static String getTypeName(Map<String, String> tsTypeMap, Class<?> type) {

        String shortName = getName(type);

        if (Map.class.isAssignableFrom(type) || org.springframework.ui.Model.class.isAssignableFrom(type)) {
            shortName = "Map";
        } else if (Set.class.isAssignableFrom(type)) {
            shortName = "Set";
        } else if (Iterable.class.isAssignableFrom(type) || type.isArray()) {
            shortName = "Array";
        } else if (tsTypeMap.containsValue(shortName)) {
            shortName = shortName + Math.abs(type.getName().hashCode());
            Assert.isTrue(!tsTypeMap.containsValue(shortName), "类型名重复:" + type.getName() + "  --> " + shortName);
        }

        //映射的类型
        tsTypeMap.put(type.getName(), shortName);

        return shortName;
    }

}
