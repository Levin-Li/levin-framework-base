package com.levin.oak.base.biz.rbac.info;

import com.levin.commons.rbac.DefaultSimpleIdentifiableObject;
import com.levin.commons.rbac.Res;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.util.LinkedList;
import java.util.List;

@Schema(description = "资源信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"domain", "type", "id"})
@ToString
@Accessors(chain = true)
@FieldNameConstants
public class ResInfo extends DefaultSimpleIdentifiableObject {

    @Schema(description = "资源域")
    String domain;

    @Schema(description = "资源类型")
    String type;

//    final List<Res.Action> actionList = new LinkedList<>();
    final List<ActionInfo> actionList = new LinkedList<>();

}
