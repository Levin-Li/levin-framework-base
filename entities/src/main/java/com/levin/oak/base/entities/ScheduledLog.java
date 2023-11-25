package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.domain.support.SimpleTenantOrgObject;
import com.levin.commons.service.domain.Identifiable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "ScheduledLog")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "调度日志")

@Table(
        indexes = {
                @Index(columnList = E_ScheduledLog.tenantId),
                @Index(columnList = E_ScheduledLog.orgId),
                @Index(columnList = E_ScheduledLog.taskId),
                @Index(columnList = E_ScheduledLog.invokeCycle),
        }
)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class ScheduledLog
        extends SimpleTenantOrgObject implements Identifiable {

    @Id
    @GeneratedValue(generator = "default_id")
    String id;

    @Schema(title = "任务ID")
    @Column(nullable = false, length = 64)
    String taskId;

    @Schema(title = "执行周期")
    @Column(length = 128)
    String invokeCycle;

    @Schema(title = "指向内容快照", description = "包括调度表达式，执行脚本，执行参数等")
    @Lob
    String invokeSnapshot;

    @Schema(title = "是否错误")
    Boolean isError;

    @Schema(title = "执行结果")
    @Lob
    String invokeResult;

}
