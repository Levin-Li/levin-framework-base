package com.levin.oak.base.services.apperrorlog.info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 应用错误日志
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-29 22:58:02
 */
@Schema(title = "应用错误日志")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class AppErrorLogInfo implements Serializable {

    private static final long serialVersionUID = 1594864095L;


    @NotNull
    @Schema(title = "id")
    private Long id;


    @Schema(title = "租户ID")
    private String tenantId;


    @Size(max = 64)
    @Schema(title = "模块ID")
    private String moduleId;


    @NotNull
    @Schema(title = "发生时间")
    private Date occurTime;


    @NotBlank
    @Size(max = 1000)
    @Schema(title = "标题")
    private String title;


    @Schema(title = "错误级别")
    private String errorLevel;


    @Schema(title = "根异常类型")
    private String rootExceptionType;


    @Schema(title = "完整异常堆栈")
    private String exceptionFullInfo;


}
