package com.levin.oak.base.services.rbac.info;

import com.levin.commons.rbac.IdentifiableObject;
import com.levin.commons.rbac.Res;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.LinkedList;
import java.util.List;

@Schema(description = "资源类型")
@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class ResTypeInfo  extends IdentifiableObject {

    final List<ResInfo> resList = new LinkedList<>();

}
