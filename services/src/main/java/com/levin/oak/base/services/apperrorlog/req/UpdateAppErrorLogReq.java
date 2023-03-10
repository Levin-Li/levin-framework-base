package com.levin.oak.base.services.apperrorlog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.E_AppErrorLog;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新应用错误日志
 * Auto gen by simple-dao-codegen 2022-3-29 22:58:02
 */
@Schema(description = "更新应用错误日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)
//默认更新注解
@Update
public class UpdateAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;

    @Schema(description = "id", required = true)
    @NotNull
    @Eq(require = true)
    private Long id;

    @Size(max = 64)
    @Schema(description = "模块ID")
    private String moduleId;

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


    public UpdateAppErrorLogReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

}
