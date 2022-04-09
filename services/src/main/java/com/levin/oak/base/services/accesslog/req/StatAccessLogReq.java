package com.levin.oak.base.services.accesslog.req;

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
import java.io.Serializable;

import com.levin.oak.base.services.accesslog.info.*;
import com.levin.oak.base.entities.AccessLog;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.support.InjectConsts;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  统计访问日志
 *  @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(description = "统计访问日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS,
     //连接统计
    //joinOptions = { @JoinOption(entityClass = XXX.class,alias = E_XXX.ALIAS,joinColumn = E_XXX.joinColumn)},
    resultClass = StatAccessLogReq.Result.class
)
public class StatAccessLogReq extends MultiTenantReq{

    private static final long serialVersionUID = 1030736962L;


    //@NotNull

    @Schema(description = "id")
     Long id;


    @Schema(description = "请求的域名")
     String domain;
    @Schema(description = "模糊匹配 - 请求的域名")
    @Contains
     String containsDomain;

    //@Size(max = 64)

    @Schema(description = "访问者")
     String visitor;

    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
     Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
     Date lteCreateTime;


    //@NotBlank

    @Schema(description = "标题")
     String title;
    @Schema(description = "模糊匹配 - 标题")
    @Contains
     String containsTitle;

    //@Size(max = 64)

    @Schema(description = "日志类型")
     String logType;


    @Schema(description = "差异修改数据")
     String diffModifyData;


    @Schema(description = "业务主键")
     String bizKey;
    @Schema(description = "模糊匹配 - 业务主键")
    @Contains
     String containsBizKey;


    @Schema(description = "业务类型")
     String bizType;
    @Schema(description = "模糊匹配 - 业务类型")
    @Contains
     String containsBizType;


    @Schema(description = "请求URI")
     String requestUri;
    @Schema(description = "模糊匹配 - 请求URI")
    @Contains
     String containsRequestUri;

    //@Size(max = 32)

    @Schema(description = "请求方法")
     String requestMethod;


    @Schema(description = "请求参数")
     String requestParams;


    @Schema(description = "头部信息")
     String headInfo;


    @Schema(description = "响应数据")
     String responseData;

    //@Size(max = 128)

    @Schema(description = "操作IP地址")
     String remoteAddr;
    @Schema(description = "模糊匹配 - 操作IP地址")
    @Contains
     String containsRemoteAddr;

    //@Size(max = 64)

    @Schema(description = "服务器地址")
     String serverAddr;


    @Schema(description = "是否有异常")
     Boolean isException;


    @Schema(description = "异常信息")
     String exceptionInfo;

    //@Size(max = 768)

    @Schema(description = "用户代理")
     String userAgent;

    //@Size(max = 128)

    @Schema(description = "设备名称/操作系统")
     String deviceName;

    //@Size(max = 64)

    @Schema(description = "浏览器名称")
     String browserName;


    @Schema(description = "执行时间(ms)")
     Long executeTime;

    public StatAccessLogReq(Long id) {
        this.id = id;
    }

    //
    //@Schema(description = "是否按状态分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore
    //private boolean isGroupByStatus;

    //@Schema(description = "是否按日期分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore //
    //private boolean isGroupByDate;


    @PostConstruct
    public void preStat() {
    //@todo 统计之前初始化数据
    }

    @Schema(description = "访问日志统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(description = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(description = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_AccessLog.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(description = "记录数")
        @Count
        Integer cnt;

        //@Schema(description = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_AccessLog.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(description = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
