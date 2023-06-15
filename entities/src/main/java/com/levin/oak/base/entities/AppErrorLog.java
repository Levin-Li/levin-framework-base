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
@Schema(title = "应用错误日志")
@Deprecated //一般不建议使用
public class AppErrorLog implements MultiTenantObject {

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(title = "租户ID")
    protected String tenantId;

    @Schema(title = "模块ID")
    @Column(length = 64)
    protected String moduleId;

    @Schema(title = "发生时间")
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date occurTime;

    @Schema(title = "标题")
    @Column(nullable = false, length = 768)
    @Contains
    protected String title;

    @Schema(title = "错误级别")
    protected String errorLevel;

    @Schema(title = "根异常类型")
    @Contains
    protected String rootExceptionType;

    @Lob
    @Schema(title = "完整异常堆栈")
    protected String exceptionFullInfo;

    @PrePersist
    public void prePersist() {
        if (occurTime == null) {
            occurTime = new Date();
        }
    }

}
