package com.levin.oak.base.services.scheduledlog.info;


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
* 调度日志
* @Author Auto gen by simple-dao-codegen 2021-11-15 15:08:50
*/
@Schema(description ="调度日志")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class ScheduledLogInfo implements Serializable {

   private static final long serialVersionUID = 1319130901L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @InjectVar
   @Schema(description = "租户ID")
   private String tenantId;


   @NotNull
   @InjectVar
   @Schema(description = "归属组织")
   private String orgId;


   @NotNull
   @Schema(description = "任务ID")
   private String taskId;


   @NotNull
   @Schema(description = "创建时间")
   private Date createTime;


   @Schema(description = "执行周期")
   private String invokeCycle;


   @Schema(description = "是否错误")
   private Boolean isError;


   @Schema(description = "执行结果")
   private String invokeResult;


}
