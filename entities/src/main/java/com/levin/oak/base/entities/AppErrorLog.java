package com.levin.oak.base.entities;


import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants

@Entity(name = EntityConst.PREFIX + "AppErrorLog")

@Table(indexes = {
        @Index(columnList = E_AppErrorLog.tenantId),
        @Index(columnList = E_AppErrorLog.moduleId),
        @Index(columnList = E_AppErrorLog.occurTime),
        @Index(columnList = E_AppErrorLog.title),
        @Index(columnList = E_AppErrorLog.errorLevel),
        @Index(columnList = E_AppErrorLog.rootExceptionType),
})
@Schema(description = "应用错误日志")
public class AppErrorLog implements MultiTenantObject {

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "租户ID")
    protected String tenantId;

    @Schema(description = "模块ID")
    @Column(length = 64)
    protected String moduleId;

    @Schema(description = "发生时间")
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date occurTime;

    @Schema(description = "标题")
    @Column(nullable = false, length = 1000)
    @Contains
    protected String title;

    @Schema(description = "错误级别")
    protected String errorLevel;

    @Schema(description = "根异常类型")
    @Contains
    protected String rootExceptionType;

    @Lob
    @Schema(description = "完整异常堆栈")
    protected String exceptionFullInfo;

    @PrePersist
    public void prePersist() {
        if (occurTime == null) {
            occurTime = new Date();
        }
    }

}
