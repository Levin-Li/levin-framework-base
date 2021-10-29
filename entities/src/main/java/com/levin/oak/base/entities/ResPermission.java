package com.levin.oak.base.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;


/**
 * 资源许可
 */

@Data
@Accessors(chain = true)
@FieldNameConstants
public class ResPermission implements Serializable {

    @Schema(description = "资源域")
    protected String resDomain;

    @Schema(description = "资源类型")
    protected String resType;

    @Schema(description = "资源列表", title = "逗号隔开")
    protected String resList;

    @Schema(description = "资源操作列表", title = "逗号隔开")
    protected String resOperationList;


}
