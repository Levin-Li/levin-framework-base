package com.levin.oak.base.services.accesslog.req;

import io.swagger.v3.oas.annotations.media.Schema;

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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.accesslog.info.*;
import com.levin.oak.base.entities.AccessLog;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询访问日志
 *  @Author Auto gen by simple-dao-codegen 2022-1-6 10:16:39
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
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS
, resultClass = AccessLogInfo.class)
public class QueryAccessLogReq extends MultiTenantReq{

    private static final long serialVersionUID = 1030736962L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "请求的域名")
    private String domain;


    @Schema(description = "访问者")
    private String visitor;


    @Schema(description = "大于等于创建时间")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间")
    @Lte
    private Date lteCreateTime;



    @Schema(description = "标题")
    private String title;


    @Schema(description = "日志类型")
    private String logType;


    @Schema(description = "差异修改数据")
    private String diffModifyData;


    @Schema(description = "业务主键")
    private String bizKey;


    @Schema(description = "业务类型")
    private String bizType;


    @Schema(description = "请求URI")
    private String requestUri;


    @Schema(description = "请求方法")
    private String requestMethod;


    @Schema(description = "请求参数")
    private String requestParams;


    @Schema(description = "响应数据")
    private String responseData;


    @Schema(description = "操作IP地址")
    private String remoteAddr;


    @Schema(description = "服务器地址")
    private String serverAddr;


    @Schema(description = "是否有异常")
    private Boolean isException;


    @Schema(description = "异常信息")
    private String exceptionInfo;


    @Schema(description = "用户代理")
    private String userAgent;


    @Schema(description = "设备名称/操作系统")
    private String deviceName;


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
