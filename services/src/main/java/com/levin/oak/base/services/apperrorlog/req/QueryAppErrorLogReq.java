package com.levin.oak.base.services.apperrorlog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.E_AppErrorLog;
import com.levin.oak.base.services.apperrorlog.info.AppErrorLogInfo;
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
 * 查询应用错误日志
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-29 22:58:02
 */
@Schema(description = "查询应用错误日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS, resultClass = AppErrorLogInfo.class)
public class QueryAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;

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


    //@Size(max = 64)

    @Schema(description = "模块ID")
    private String moduleId;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于发生时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteOccurTime;

    @Schema(description = "小于等于发生时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteOccurTime;


    //@NotBlank
    //@Size(max = 1000)

    @Schema(description = "标题")
    private String title;

    @Schema(description = "模糊匹配 - 标题")
    @Contains
    private String containsTitle;


    @Schema(description = "错误级别")
    private String errorLevel;


    @Schema(description = "根异常类型")
    private String rootExceptionType;

    @Schema(description = "模糊匹配 - 根异常类型")
    @Contains
    private String containsRootExceptionType;


    @Schema(description = "完整异常堆栈")
    private String exceptionFullInfo;


    public QueryAppErrorLogReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
