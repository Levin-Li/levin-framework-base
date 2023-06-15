package com.levin.oak.base.biz.rbac.info;

import com.levin.commons.rbac.DefaultSimpleIdentifiableObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Schema(title = "资源类型")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")

@Accessors(chain = true)
@FieldNameConstants
public class ActionInfo
        extends DefaultSimpleIdentifiableObject {

    @Schema(title = "完整表达式")
    String permissionExpr;

    @Override
    public String toString() {
        return permissionExpr;
    }
}
