package com.levin.oak.base.codegen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Accessors(chain = true)
public class Module implements Serializable {

    @Data
    @Accessors(chain = true)
    @ToString
    public static class Service implements Serializable {

        String id;

        String name;

        String label;

        String desc;

        String type;

        String pathPrefix;

        final List<Api> apiList = new ArrayList<>();

        final Set<Class<?>> importList = new LinkedHashSet<>();

        public Service addImport(Class<?> type) {

            if (type != null
                    && (type.isEnum() || !BeanUtils.isSimpleProperty(type))
                    && Stream.of("java.lang.", "java.util.", "java.io.").noneMatch(it -> type.getName().startsWith(it))
            ) {
                importList.add(type);
            }

            return this;
        }
    }


    @Data
    @Accessors(chain = true)
    @ToString
    public static class Api implements Serializable {

        String id;

        String name;

        List<String> method;

        List<String> path;

        String label;

        String desc;

        @Schema(title = "是否是视图")
        boolean isView;

        @Schema(title = "是否需要认证")
        boolean requireAuth;

        @Schema(title = "需要的权限列表")
        String requireAuthorizations;

        String returnTDefinePrefix;

        @Schema(title = "接口返回值")
        String returnTypeDefine;

        @Schema(title = "接口参数列表")
        final List<Column> paramList = new ArrayList<>();

        @Schema(title = "CURD注解列表")
        final Set<Annotation> crudAnnotations = new LinkedHashSet<>();

    }


    @Data
    @Accessors(chain = true)
    @ToString
    public static class DtoSchema implements Serializable {

        String id;

        String name;

        String label;

        String desc;

        String typeDefine;

        @Schema(title = "父类")
        String superType;

        boolean isInterface;

        boolean isEnum;

        List<String> interfaceList = new ArrayList<>();

        List<Column> columnList = null;

        @JsonIgnore
        Class<?> classType;

        final Set<Class<?>> importList = new LinkedHashSet<>();

        public DtoSchema addImport(Class<?> type) {

            if (type != null
                    && type != classType
                    && (type.isEnum() || !BeanUtils.isSimpleProperty(type))
                    && Stream.of("java.lang.", "java.util.", "java.io.").noneMatch(it -> type.getName().startsWith(it))
            ) {
                importList.add(type);
            }

            return this;
        }

        public String getFullTypeDefine() {

            StringBuilder builder = new StringBuilder();

            builder.append(typeDefine);

            String interfaces = interfaceList.stream().collect(Collectors.joining(", "));

            if (isInterface) {
                builder.append(" extends " + interfaces);
            } else {
                if (StringUtils.hasText(superType)) {
                    builder.append(" extends " + superType);
                }

                if (!interfaceList.isEmpty()) {
                    builder.append(" implements ").append(interfaces);
                }
            }

            return builder.toString();
        }
    }

    @Data
    @Accessors(chain = true)
    @ToString
    public static class Column implements Serializable {

        String id;

        String name;

        String label;

        String desc;

        String requiredMode;

        boolean isReadOnly;

        boolean isPathVariable;

        boolean isRequestBody;

        boolean isSimpleType;

        String typeDefinePrefix;

        String typeDefine;

        String dictCode;

        final List<String> options = new ArrayList<>();

        @Schema(title = "CURD注解列表")
        final Set<Annotation> crudAnnotations = new LinkedHashSet<>();
    }

    String id;

    String name;

    String label;

    String desc;

    String type;

    String pathPrefix;

    final List<Service> serviceList = new ArrayList<>();

    final Map<String, DtoSchema> schemas = new java.util.LinkedHashMap<>();

}
