package com.levin.oak.base.entities;

import com.levin.commons.plugin.ResInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;


/**
 * 资源操作
 */

@Data
@Accessors(chain = true)
@FieldNameConstants
public class ResOperation implements ResInfo.Operation {

    @Schema(description = "ID")
    protected String id;

    @Schema(description = "名称")
    protected String name;

    @Schema(description = "授权")
    protected String requireAuthorizations;

    @Schema(description = "是否启用")
    protected boolean enable = true;

    @Schema(description = "排序码")
    protected Integer orderCode;

    @Schema(description = "备注")
    protected String remark;

}
