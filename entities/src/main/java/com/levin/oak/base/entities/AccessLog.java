package com.levin.oak.base.entities;


import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.E_SimpleTenantOrgObject;
import com.levin.commons.dao.domain.support.SimpleTenantOrgObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
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
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class AccessLog extends SimpleTenantOrgObject {

    @Id
    @GeneratedValue
//    @GeneratedValue(generator = "default_id")
    protected Long id;

    @Schema(title = "系统域", description = "归属的子系统")
    @Contains
    protected String domain;

    @Schema(title = "访问者")
    @Column(length = 64)
    @InjectVar(value = InjectConst.USER_NAME, isRequired = "false")
    @Contains
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
    @Column(length = 768)
    protected String requestUri;

    @Schema(title = "请求方法")
    @Column(length = 32)
    protected String requestMethod;

    @Lob
    @Schema(title = "头部信息")
    protected String headInfo;

    @Schema(title = "请求参数")
    @Lob
    protected String requestParams;

    @Schema(title = "请求体")
    @Lob
    protected String requestBody;

    @Schema(title = "响应体")
    @Lob
    protected String responseBody;

    @Schema(title = "操作IP地址")
    @Column(length = 128)
    @Contains
    protected String remoteAddr;

    @Schema(title = "访问地区")
    @Column(length = 256)
    @Contains
    protected String accessRegion;

    @Schema(title = "服务器地址")
    @Column(length = 64)
    protected String serverAddr;

    @Schema(title = "是否有异常")
    protected Boolean isException;

    @Lob
    @Schema(title = "异常信息")
    protected String exceptionInfo;

    @Schema(title = "用户代理")
    @Column(length = 1800)
    @Contains
    protected String userAgent;

    @Schema(title = "设备名称/操作系统")
    @Column(length = 128)
    protected String deviceName;

    @Schema(title = "浏览器名称")
    @Column(length = 128)
    protected String browserName;

    @Schema(title = "执行耗时(ms)")
    protected Long executeTime;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}
