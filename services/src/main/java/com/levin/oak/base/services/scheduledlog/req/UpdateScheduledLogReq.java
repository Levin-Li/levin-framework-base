package com.levin.oak.base.services.scheduledlog.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.entities.*;


////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////


/**
 *  更新调度日志
 *  Auto gen by simple-dao-codegen 2021-11-23 16:11:31
 */
@Schema(description = "更新调度日志")
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
public class UpdateScheduledLogReq implements ServiceReq {

    private static final long serialVersionUID = 1319130901L;

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    //@InjectVar
    @Schema(description = "租户ID")
    private String tenantId;

    //@InjectVar
    @Schema(description = "归属组织")
    private String orgId;

    @Schema(description = "任务ID")
    private String taskId;

    @Schema(description = "执行周期")
    private String invokeCycle;

    @Schema(description = "是否错误")
    private Boolean isError;

    @Schema(description = "执行结果")
    private String invokeResult;


    public UpdateScheduledLogReq(Long id) {
        this.id = id;
    }
    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

}
