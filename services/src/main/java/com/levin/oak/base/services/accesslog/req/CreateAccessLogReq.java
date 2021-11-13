package com.levin.oak.base.services.accesslog.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////


/**
 *  新增访问日志
 *  //Auto gen by simple-dao-codegen 2021-11-13 23:58:00
 */
@Schema(description = "新增访问日志")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
public class CreateAccessLogReq implements ServiceReq {

    private static final long serialVersionUID = 1030736962L;



    @Schema(description = "租户ID" )
    @InjectVar
    private Long tenantId;


    @Schema(description = "请求的域名" )
    @InjectVar
    private String domain;


    @Schema(description = "访问者" )
    private String visitor;


    @Schema(description = "创建时间" , required = true)
    @NotNull
    private Date createTime;


    @Schema(description = "标题" , required = true)
    @NotNull
    private String title;


    @Schema(description = "日志类型" )
    private String logType;


    @Schema(description = "差异修改数据" )
    private String diffModifyData;


    @Schema(description = "业务主键" )
    private String bizKey;


    @Schema(description = "业务类型" )
    private String bizType;


    @Schema(description = "请求URI" )
    private String requestUri;


    @Schema(description = "请求方法" )
    private String requestMethod;


    @Schema(description = "请求参数" )
    private String requestParams;


    @Schema(description = "响应数据" )
    private String responseData;


    @Schema(description = "操作IP地址" )
    private String remoteAddr;


    @Schema(description = "服务器地址" )
    private String serverAddr;


    @Schema(description = "是否有异常" )
    private String isException;


    @Schema(description = "异常信息" )
    private String exceptionInfo;


    @Schema(description = "用户代理" )
    private String userAgent;


    @Schema(description = "设备名称/操作系统" )
    private String deviceName;


    @Schema(description = "浏览器名称" )
    private String browserName;


    @Schema(description = "执行时间(ms)" )
    private Long executeTime;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
