package com.levin.oak.base.services.scheduledtask.info;


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
import java.util.Date;
////////////////////////////////////

/**
* 调度任务
* @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:26
*/
@Schema(description ="调度任务")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class ScheduledTaskInfo implements Serializable {

   private static final long serialVersionUID = -2056389676L;


   @NotNull
   @Schema(description = "id" , required = true)
   private Long id;


   @NotBlank
   @Size(max = 64)
   @Schema(description = "任务分类" , required = true)
   private String category;


   @NotBlank
   @Size(max = 64)
   @Schema(description = "任务组" , required = true)
   private String groupName;


   @NotBlank
   @Schema(description = "调度表达式" , required = true)
   private String cron;


   @Schema(description = "执行表达式" )
   private String invokeExpr;


   @Schema(description = "允许并发执行" )
   private Boolean parallelInvoke;


   @Schema(description = "最后一次时间" )
   private Date lastInvokedTime;


   @Schema(description = "下一次时间" )
   private Date nextInvokeTime;


   @Size(max = 64)
   @Schema(description = "机构ID" )
   private String orgId;


   @Size(max = 64)
   @Schema(description = "租户ID" )
   private String tenantId;


   @Size(max = 64)
   @Schema(description = "系统子域" )
   private String domain;


   @NotBlank
   @Size(max = 128)
   @Schema(description = "名称" , required = true)
   private String name;


   @Size(max = 128)
   @Schema(description = "拼音，格式：全拼(简拼)" )
   private String pinyinName;


   @Size(max = 128)
   @Schema(description = "创建者" )
   private String creator;


   @NotNull
   @Schema(description = "创建时间" , required = true)
   private Date createTime;


   @Schema(description = "更新时间" )
   private Date lastUpdateTime;


   @Schema(description = "排序代码" )
   private Integer orderCode;


   @NotNull
   @Schema(description = "是否允许" , required = true)
   private Boolean enable;


   @NotNull
   @Schema(description = "是否可编辑" , required = true)
   private Boolean editable;


   @Size(max = 512)
   @Schema(description = "备注" )
   private String remark;


}
