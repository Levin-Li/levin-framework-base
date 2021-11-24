package com.levin.oak.base.controller.rbac.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Schema(description = "Amis 响应")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class AmisResp implements Serializable {


    int status;

    String msg;

    final Map<String, Object> data = new LinkedHashMap<>();

}
