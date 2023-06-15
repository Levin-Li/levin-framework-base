package com.levin.oak.base.services.accesslog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.stat.Count;
import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.E_AccessLog;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 统计访问日志
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-9 16:44:59
 */
@Schema(title = "统计访问日志")
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
public class StatAccessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1030736962L;


    //@NotNull

    @Schema(title = "id")
    Long id;


    @Schema(title = "请求的域名")
    String domain;
    @Schema(title = "模糊匹配 - 请求的域名")
    @Contains
    String containsDomain;

    //@Size(max = 64)

    @Schema(title = "访问者")
    String visitor;

    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    Date lteCreateTime;


    //@NotBlank

    @Schema(title = "标题")
    String title;
    @Schema(title = "模糊匹配 - 标题")
    @Contains
    String containsTitle;

    //@Size(max = 64)

    @Schema(title = "日志类型")
    String logType;


    @Schema(title = "差异修改数据")
    String diffModifyData;


    @Schema(title = "业务主键")
    String bizKey;
    @Schema(title = "模糊匹配 - 业务主键")
    @Contains
    String containsBizKey;


    @Schema(title = "业务类型")
    String bizType;
    @Schema(title = "模糊匹配 - 业务类型")
    @Contains
    String containsBizType;


    @Schema(title = "请求URI")
    String requestUri;
    @Schema(title = "模糊匹配 - 请求URI")
    @Contains
    String containsRequestUri;

    //@Size(max = 32)

    @Schema(title = "请求方法")
    String requestMethod;


    @Schema(title = "请求参数")
    String requestParams;


    @Schema(title = "头部信息")
    String headInfo;


    @Schema(title = "响应数据")
    String responseData;

    //@Size(max = 128)

    @Schema(title = "操作IP地址")
    String remoteAddr;
    @Schema(title = "模糊匹配 - 操作IP地址")
    @Contains
    String containsRemoteAddr;

    //@Size(max = 64)

    @Schema(title = "服务器地址")
    String serverAddr;


    @Schema(title = "是否有异常")
    Boolean isException;


    @Schema(title = "异常信息")
    String exceptionInfo;

    //@Size(max = 768)

    @Schema(title = "用户代理")
    String userAgent;

    //@Size(max = 128)

    @Schema(title = "设备名称/操作系统")
    String deviceName;

    //@Size(max = 64)

    @Schema(title = "浏览器名称")
    String browserName;


    @Schema(title = "执行时间(ms)")
    Long executeTime;

    public StatAccessLogReq(Long id) {
        this.id = id;
    }

    //
    //@Schema(title = "是否按状态分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore
    //private boolean isGroupByStatus;

    //@Schema(title = "是否按日期分组统计")
    //@CtxVar //增加当前字段名称和字段值到环境变量中
    //@Ignore //
    //private boolean isGroupByDate;


    @PostConstruct
    public void preStat() {
        //@todo 统计之前初始化数据
    }

    @Schema(title = "访问日志统计结果")
    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    public static class Result
            implements Serializable {

        //@Schema(title = "状态分组统计")
        //@GroupBy(condition = "#isGroupByStatus")
        //Status status;

        //@Schema(title = "时间分组统计")
        //@GroupBy(condition = "#isGroupByDate", value = "date_format(" + E_AccessLog.createDate + ",'%Y-%m-%d')", orderBy = @OrderBy(type = OrderBy.Type.Asc))
        //String createDate;

        @Schema(title = "记录数")
        @Count
        Integer cnt;

        //@Schema(title = "分类记录数")
        //@Count(fieldCases = {@Case(column = E_AccessLog.status, whenOptions = {@Case.When(whenExpr = "OFF", thenExpr = "1")}, elseExpr = "NULL")})
        //Integer caseCnt;

        //@Schema(title = "累计" , havingOp=Op.Gt)
        //@Sum
        //Double sumGmv;

    }

}
