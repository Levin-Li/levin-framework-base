package com.levin.oak.base.services.apperrorlog.info;


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
* 应用错误日志
* @Author Auto gen by simple-dao-codegen 2022-3-29 22:58:02
*/
@Schema(description ="应用错误日志")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class AppErrorLogInfo implements Serializable {

   private static final long serialVersionUID = 1594864095L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @Schema(description = "租户ID")
   private String tenantId;


   @Size(max = 64)
   @Schema(description = "模块ID")
   private String moduleId;


   @NotNull
   @Schema(description = "发生时间")
   private Date occurTime;


   @NotBlank
   @Size(max = 1000)
   @Schema(description = "标题")
   private String title;


   @Schema(description = "错误级别")
   private String errorLevel;


   @Schema(description = "根异常类型")
   private String rootExceptionType;


   @Schema(description = "完整异常堆栈")
   private String exceptionFullInfo;


}
