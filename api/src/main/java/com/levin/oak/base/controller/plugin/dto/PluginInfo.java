package com.levin.oak.base.controller.plugin.dto;

import com.levin.commons.rbac.MenuItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Schema(description = "插件信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class PluginInfo implements Serializable {

    @Schema(description = "插件ID")
    @NotNull
    String id;

    @Schema(description = "名称")
    @NotNull
    String name;//

    @Schema(description = "名称空间")
    @NotNull
    String packageName;//

    @Schema(description = "插件版本")
    @NotNull
    String version;

    @Schema(description = "插件描述")
    Integer orderCode;

    @Schema(description = "插件描述")
    String remark;

    @Schema(description = "插件作者")
    Map<String, String> author;

    @Schema(description = "插件类型")
    String type;

    @Schema(description = "插件图标")
    String icon;

    @Schema(description = "插件菜单")
    List<MenuItem> menuList;

}
