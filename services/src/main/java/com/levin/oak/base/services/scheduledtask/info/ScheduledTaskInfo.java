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
* @Author Auto gen by simple-dao-codegen 2021-10-28 16:17:41
*/
@Schema(description ="调度任务")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"" + E_ScheduledTask.id})
@ToString(exclude = {})
@FieldNameConstants
public class ScheduledTaskInfo implements Serializable {

   private static final long serialVersionUID = -2056389676L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @NotNull
   @Schema(description = "任务组")
   private String group;


   @NotNull
   @Schema(description = "调度表达式")
   private String cron;


   @NotNull
   @Schema(description = "执行表达式")
   private String invokeExpr;


   @Schema(description = "允许并发执行")
   private Boolean parallelInvoke;


   @Schema(description = "最后一次时间")
   private Date lastInvokedTime;


   @Schema(description = "执行结果")
   private String invokeResult;


   @Schema(description = "下一次时间")
   private Date nextInvokeTime;


   @NotNull
   @Size(max = 768)
   @Schema(description = "名称")
   private String name;


   @Schema(description = "租户ID")
   private Long tenantId;


   @Size(max = 512)
   @Schema(description = "创建者")
   private String creator;


   @NotNull
   @Schema(description = "创建时间")
   private Date createTime;


   @Schema(description = "更新时间")
   private Date lastUpdateTime;


   @Schema(description = "排序代码")
   private Integer orderCode;


   @NotNull
   @Schema(description = "是否允许")
   private Boolean enable;


   @NotNull
   @Schema(description = "是否可编辑")
   private Boolean editable;


   @Size(max = 1800)
   @Schema(description = "备注")
   private String remark;


}