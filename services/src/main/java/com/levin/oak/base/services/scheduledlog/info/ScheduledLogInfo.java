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
import java.util.Date;
////////////////////////////////////

/**
* 调度日志
* @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:26
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
   @Schema(description = "id" , required = true)
   private Long id;


   @Size(max = 64)
   @Schema(description = "租户ID" )
   private String tenantId;


   @NotBlank
   @Size(max = 64)
   @Schema(description = "归属组织" , required = true)
   private String orgId;


   @NotBlank
   @Size(max = 64)
   @Schema(description = "任务ID" , required = true)
   private String taskId;


   @NotNull
   @Schema(description = "创建时间" , required = true)
   private Date createTime;


   @Size(max = 256)
   @Schema(description = "执行周期" )
   private String invokeCycle;


   @Schema(description = "是否错误" )
   private Boolean isError;


   @Schema(description = "执行结果" )
   private String invokeResult;


}
