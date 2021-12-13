package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.Identifiable;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.service.domain.InjectVar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Entity(name = EntityConst.PREFIX + "ScheduledLog")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "调度日志")

@Table(indexes = {
        @Index(columnList = E_ScheduledLog.tenantId),
        @Index(columnList = E_ScheduledLog.orgId),
        @Index(columnList = E_ScheduledLog.taskId),
        @Index(columnList = E_ScheduledLog.invokeCycle),
})
public class ScheduledLog
        implements MultiTenantObject, Identifiable, OrganizedObject {

    @Id
    @GeneratedValue
    Long id;

    @Schema(description = "租户ID")
    @InjectVar
    String tenantId;

    @Schema(description = "归属组织", required = true)
    @InjectVar
    @Column(nullable = false)
    String orgId;

    @Schema(description = "任务ID", required = true)
    @Column(nullable = false)
    String taskId;

    @Schema(description = "创建时间")
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createTime;

    @Schema(description = "执行周期")
    String invokeCycle;

    @Schema(description = "是否错误")
    Boolean isError;

    @Schema(description = "执行结果")
    @Lob
    String invokeResult;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }

}
