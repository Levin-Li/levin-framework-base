package com.levin.oak.base.services.scheduledlog.info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 调度日志
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:26
 */
@Schema(title = "调度日志")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class ScheduledLogInfo implements Serializable {

    private static final long serialVersionUID = 1319130901L;


    @NotNull
    @Schema(title = "id", required = true)
    private Long id;


    @Size(max = 64)
    @Schema(title = "租户ID")
    private String tenantId;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "归属组织", required = true)
    private String orgId;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "任务ID", required = true)
    private String taskId;


    @NotNull
    @Schema(title = "创建时间", required = true)
    private Date createTime;


    @Size(max = 256)
    @Schema(title = "执行周期")
    private String invokeCycle;


    @Schema(title = "是否错误")
    private Boolean isError;


    @Schema(title = "执行结果")
    private String invokeResult;


}
