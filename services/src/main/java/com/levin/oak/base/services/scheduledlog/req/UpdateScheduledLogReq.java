package com.levin.oak.base.services.scheduledlog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
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

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新调度日志
 * Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "更新调度日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
//默认更新注解
@Update
public class UpdateScheduledLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1319130901L;

    @Schema(title = "id", required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    @Schema(title = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @NotBlank
    @Size(max = 64)
    @Schema(title = "任务ID")
    private String taskId;

    @Size(max = 256)
    @Schema(title = "执行周期")
    private String invokeCycle;

    @Schema(title = "是否错误")
    private Boolean isError;

    @Schema(title = "执行结果")
    private String invokeResult;


    public UpdateScheduledLogReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

}
