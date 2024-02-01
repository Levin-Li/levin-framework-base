package com.levin.oak.base.codegen.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Module implements Serializable {

    @Data
    @Accessors(chain = true)
    @ToString
    public static class Service implements Serializable {

        @Schema(title = "")
        String id;

        String name;

        String label;

        String desc;

        String type;

        String pathPrefix;

        final List<Api> apiList = new ArrayList<>();

    }


    @Data
    @Accessors(chain = true)
    @ToString
    public static class Api implements Serializable {

        @Schema(title = "")
        String id;

        String name;

        String method;

        String path;

        String label;

        String desc;

        @Schema(title = "是否是视图")
        boolean isView;

        @Schema(title = "是否需要认证")
        boolean requireAuth;

        @Schema(title = "需要的权限列表")
        String requireAuthorizations;

        @Schema(title = "接口返回值")
        String returnType;

        @Schema(title = "接口参数列表")
        final List<Column> paramList = new ArrayList<>();

    }


    @Data
    @Accessors(chain = true)
    @ToString
    public static class DtoSchema implements Serializable {

        @Schema(title = "")
        String id;

        String name;

        String label;

        String desc;

        @Schema(title = "父类")
        String superType;

        final List<Column> columnList = new ArrayList<>();

    }

    @Data
    @Accessors(chain = true)
    @ToString
    public static class Column implements Serializable {

        @Schema(title = "")
        String id;

        String name;

        String label;

        String desc;

        //
        String requiredMode;

        boolean isReadOnly;

        String type;

        String dictCode;

        final List<String> options = new ArrayList<>();

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
