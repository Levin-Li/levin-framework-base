package com.levin.oak.base.services.apperrorlog.req;

import io.swagger.v3.oas.annotations.media.Schema;
import com.levin.commons.dao.annotation.Ignore;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.apperrorlog.info.*;
import com.levin.oak.base.entities.AppErrorLog;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import java.util.Date;
////////////////////////////////////

/**
 *  查询应用错误日志
 *  @Author Auto gen by simple-dao-codegen 2022-3-29 22:58:02
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
public class QueryAppErrorLogReq extends MultiTenantReq{

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
