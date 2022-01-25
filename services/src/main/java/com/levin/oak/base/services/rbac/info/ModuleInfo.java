package com.levin.oak.base.services.rbac.info;


import com.levin.commons.rbac.DefaultSimpleIdentifiableObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.LinkedList;
import java.util.List;

@Schema(description = "模块信息")
@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class ModuleInfo extends DefaultSimpleIdentifiableObject {

    final List<ResTypeInfo> typeList = new LinkedList<>();

}
