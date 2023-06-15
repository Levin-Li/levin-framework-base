package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

/**
 * 调用任务，可以用于定期数据采集
 */

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants

@Schema(title = "调度任务")

@Entity(name = EntityConst.PREFIX + "ScheduledTask")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractNamedMultiTenantObject.Fields.tenantId),
                @Index(columnList = TenantOrganizedEntity.Fields.orgId),
                @Index(columnList = E_ScheduledTask.groupName),
        }
)
public class ScheduledTask
        extends TenantOrganizedEntity {

    @Id
//    @GeneratedValue
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "任务分类")
    @Column(nullable = false, length = 128)
    String category;

    @Schema(title = "任务组")
    @Column(nullable = false, length = 128)
    @Contains
    String groupName;

    @Schema(title = "调度表达式")
    @Column(nullable = false)
    String cron;

    @Schema(title = "执行表达式", description = "可以是 Groovy")
    @Lob
    String invokeExpr;

    @Schema(title = "允许并发执行")
    Boolean parallelInvoke;

    @Schema(title = "最后一次时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date lastInvokedTime;

    @Schema(title = "下一次时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date nextInvokeTime;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();

        if (parallelInvoke == null) {
            parallelInvoke = false;
        }
    }

}
