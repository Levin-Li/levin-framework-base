package com.levin.oak.base.services.accesslog.info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

/////////////////////////////////////////////////////
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
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
* 访问日志
* @Author Auto gen by simple-dao-codegen 2021-11-12 9:56:30
*/
@Schema(description ="访问日志")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class AccessLogInfo implements Serializable {

   private static final long serialVersionUID = 1030736962L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @InjectVar
   @Schema(description = "租户ID")
   private Long tenantId;


   @InjectVar
   @Schema(description = "请求的域名")
   private String domain;


   @Schema(description = "访问者")
   private String visitor;


   @NotNull
   @Schema(description = "创建时间")
   private Date createTime;


   @NotNull
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
   private String isException;


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


}
