package com.levin.oak.base.entities;


import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.E_SimpleTenantOrgObject;
import com.levin.commons.dao.domain.support.SimpleTenantOrgObject;
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

@Table(
        indexes = {
                @Index(columnList = E_SimpleTenantOrgObject.tenantId),
                @Index(columnList = E_SimpleTenantOrgObject.orgId),
                @Index(columnList = E_AccessLog.createTime),
                @Index(columnList = E_AccessLog.visitor),
                @Index(columnList = E_AccessLog.requestUri),
                @Index(columnList = E_AccessLog.title),
                @Index(columnList = E_AccessLog.bizType),
        }
)
@Schema(title = "访问日志")
public class AccessLog extends SimpleTenantOrgObject {

    @Id
    @GeneratedValue
//    @GeneratedValue(generator = "default_id")
    protected Long id;

    @Schema(title = "系统域")
    @Contains
    protected String domain;

    @Schema(title = "访问者")
    @Column(length = 64)
    protected String visitor;

    @Schema(title = "标题")
    @Column(nullable = false)
    @Contains
    protected String title;

    @Schema(title = "日志类型")
    @Column(length = 64)
    protected String logType;

    ////////////////////////////////////

    @Schema(title = "差异修改数据")
    protected String diffModifyData;

    @Schema(title = "业务主键")
    @Contains
    protected String bizKey;

    @Schema(title = "业务类型")
    @Contains
    protected String bizType;

    ////////////////////////////////////

    @Schema(title = "请求URI")
    @Contains
    protected String requestUri;

    @Schema(title = "请求方法")
    @Column(length = 32)
    protected String requestMethod;

    @Schema(title = "请求参数")
    @Lob
    protected String requestParams;

    @Lob
    @Schema(title = "头部信息")
    protected String headInfo;

    @Lob
    @Schema(title = "响应数据")
    protected String responseData;

    @Schema(title = "操作IP地址")
    @Column(length = 128)
    @Contains
    protected String remoteAddr;

    @Schema(title = "服务器地址")
    @Column(length = 64)
    protected String serverAddr;

    @Schema(title = "是否有异常")
    protected Boolean isException;

    @Lob
    @Schema(title = "异常信息")
    protected String exceptionInfo;

    @Schema(title = "用户代理")
    @Column(length = 768)
    protected String userAgent;

    @Schema(title = "设备名称/操作系统")
    @Column(length = 128)
    protected String deviceName;

    @Schema(title = "浏览器名称")
    @Column(length = 64)
    protected String browserName;

    @Schema(title = "执行时间(ms)")
    protected Long executeTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}
