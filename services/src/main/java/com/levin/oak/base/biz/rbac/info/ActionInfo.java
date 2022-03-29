package com.levin.oak.base.biz.rbac.info;

import com.levin.commons.rbac.DefaultSimpleIdentifiableObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Schema(description = "资源类型")
@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class ActionInfo extends DefaultSimpleIdentifiableObject {

}
