package com.levin.oak.base.services.accesslog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.E_AccessLog;
import com.levin.oak.base.services.accesslog.info.AccessLogInfo;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 查询访问日志
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-30 8:44:20
 */
@Schema(description = "查询访问日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS, resultClass = AccessLogInfo.class)
public class QueryAccessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1030736962L;

    @Ignore
    @Schema(description = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull
    @Schema(description = "id")
    private Long id;

    @Schema(description = "请求的域名")
    private String domain;

    @Schema(description = "访问者")
    private String visitor;

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;


    @Schema(description = "标题")
    private String title;

    @Schema(description = "模糊匹配 - 标题")
    @Contains
    private String containsTitle;

    //@Size(max = 64)

    @Schema(description = "日志类型")
    private String logType;


    @Schema(description = "差异修改数据")
    private String diffModifyData;


    @Schema(description = "业务主键")
    private String bizKey;

    @Schema(description = "模糊匹配 - 业务主键")
    @Contains
    private String containsBizKey;


    @Schema(description = "业务类型")
    private String bizType;

    @Schema(description = "模糊匹配 - 业务类型")
    @Contains
    private String containsBizType;


    @Schema(description = "请求URI")
    private String requestUri;

    @Schema(description = "模糊匹配 - 请求URI")
    @Contains
    private String containsRequestUri;


    //@Size(max = 32)

    @Schema(description = "请求方法")
    private String requestMethod;


    @Schema(description = "请求参数")
    private String requestParams;


    @Schema(description = "头部信息")
    private String headInfo;


    @Schema(description = "响应数据")
    private String responseData;


    //@Size(max = 128)

    @Schema(description = "操作IP地址")
    private String remoteAddr;

    @Schema(description = "模糊匹配 - 操作IP地址")
    @Contains
    private String containsRemoteAddr;


    //@Size(max = 64)

    @Schema(description = "服务器地址")
    private String serverAddr;


    @Schema(description = "是否有异常")
    private Boolean isException;


    @Schema(description = "异常信息")
    private String exceptionInfo;


    //@Size(max = 768)

    @Schema(description = "用户代理")
    private String userAgent;


    //@Size(max = 128)

    @Schema(description = "设备名称/操作系统")
    private String deviceName;


    //@Size(max = 64)

    @Schema(description = "浏览器名称")
    private String browserName;


    @Schema(description = "执行时间(ms)")
    private Long executeTime;


    public QueryAccessLogReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
