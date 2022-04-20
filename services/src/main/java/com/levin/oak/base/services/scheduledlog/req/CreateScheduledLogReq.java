package com.levin.oak.base.services.scheduledlog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.oak.base.entities.E_ScheduledLog;
import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增调度日志
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(description = "新增调度日志")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
public class CreateScheduledLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1319130901L;


    @Schema(description = "任务ID", required = true)
    @NotBlank
    @Size(max = 64)
    private String taskId;

    @Schema(description = "创建时间", required = true)
    @NotNull
    private Date createTime;

    @Schema(description = "执行周期")
    @Size(max = 256)
    private String invokeCycle;

    @Schema(description = "是否错误")
    private Boolean isError;

    @Schema(description = "执行结果")
    private String invokeResult;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
