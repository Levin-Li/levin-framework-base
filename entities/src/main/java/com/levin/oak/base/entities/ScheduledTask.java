package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Entity(name = TableOption.PREFIX + "scheduled_task")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "调度任务")

@Table(indexes = {
        @Index(columnList = AbstractNamedEntityObject.Fields.name),
        @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
        @Index(columnList = AbstractMultiTenantObject.Fields.tenantId),
        @Index(columnList = E_ScheduledTask.group),
})
public class ScheduledTask
        extends MultiTenantNamedEntity<Long> {

    @Id
    @GeneratedValue
    Long id;

    @Schema(description = "任务组")
    @Column(nullable = false)
    String group;

    @Schema(description = "调度表达式")
    @Column(nullable = false)
    String cron;

    @Schema(description = "执行表达式")
    @Column(nullable = false)
    String invokeExpr;

    @Schema(description = "允许并发执行")
    Boolean parallelInvoke;

    @Schema(description = "最后一次时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date lastInvokedTime;

    @Schema(description = "执行结果")
    @Lob
    String invokeResult;

    @Schema(description = "下一次时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date nextInvokeTime;


    @Override
    public void prePersist() {
        super.prePersist();

        if (parallelInvoke == null) {
            parallelInvoke = false;
        }
    }

}
