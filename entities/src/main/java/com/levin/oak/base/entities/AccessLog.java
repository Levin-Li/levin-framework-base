package com.levin.oak.base.entities;


import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.service.domain.Identifiable;
import com.levin.commons.service.domain.InjectVar;
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

@Entity(name = EntityConst.PREFIX + "AccessLog")

@Table(indexes = {
        @Index(columnList = E_AccessLog.tenantId),
        @Index(columnList = E_AccessLog.createTime),
        @Index(columnList = E_AccessLog.visitor),
        @Index(columnList = E_AccessLog.requestUri),
        @Index(columnList = E_AccessLog.title),
        @Index(columnList = E_AccessLog.bizType),
})
@Schema(description = "访问日志")
public class AccessLog
        implements
        MultiTenantObject  {

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "租户ID")

    protected String tenantId;

    @Schema(description = "请求的域名")

    @Column(length = 64)
    protected String domain;

    @Schema(description = "访问者")
    @Column(length = 64)
    protected String visitor;

    @Schema(description = "创建时间")
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createTime;

    @Schema(description = "标题")
    @Column(nullable = false)
    protected String title;

    @Schema(description = "日志类型")
    @Column(length = 64)
    protected String logType;

    ////////////////////////////////////

    @Schema(description = "差异修改数据")
    protected String diffModifyData;

    @Schema(description = "业务主键")
    @Contains
    protected String bizKey;

    @Schema(description = "业务类型")
    @Contains
    protected String bizType;

    ////////////////////////////////////

    @Schema(description = "请求URI")
    @Contains
    protected String requestUri;

    @Schema(description = "请求方法")
    @Column(length = 32)
    protected String requestMethod;

    @Schema(description = "请求参数")
    @Column(length = 768)
    protected String requestParams;

    @Lob
    @Schema(description = "响应数据")
    protected String responseData;

    @Schema(description = "操作IP地址")
    @Column(length = 64)
    @Contains
    protected String remoteAddr;

    @Schema(description = "服务器地址")
    @Column(length = 64)
    protected String serverAddr;

    @Schema(description = "是否有异常")
    protected Boolean isException;

    @Lob
    @Schema(description = "异常信息")
    protected String exceptionInfo;

    @Schema(description = "用户代理")
    @Column(length = 512)
    protected String userAgent;

    @Schema(description = "设备名称/操作系统")
    @Column(length = 128)
    protected String deviceName;

    @Schema(description = "浏览器名称")
    @Column(length = 64)
    protected String browserName;

    @Schema(description = "执行时间(ms)")
    protected Long executeTime;

}
