package com.levin.oak.base.controller.commons.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Schema(description = "枚举信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class EnumInfo
        implements Serializable {

    @Schema(description = "枚举项")
    @Data
    @Accessors(chain = true)
    public static class Item {

        @Schema(description = "枚举项编号")
        int ordinal;

        @Schema(description = "值")
        String value;

        @Schema(description = "标签")
        String label;

        @Schema(description = "完整描述")
        Enum detail;

    }

    @Schema(description = "完整名称")
    String fullName;

    @Schema(description = "名称")
    String name;

    @Schema(description = "标签")
    String label;

    @Schema(description = "有序值列表，key名称")
    final List<Item> values = new ArrayList<>(6);

}
