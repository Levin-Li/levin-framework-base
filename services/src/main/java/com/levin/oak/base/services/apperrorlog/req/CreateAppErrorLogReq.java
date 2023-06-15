package com.levin.oak.base.services.apperrorlog.req;

import com.levin.commons.dao.TargetOption;
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

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增应用错误日志
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增应用错误日志")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)
public class CreateAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;


    @Schema(title = "模块ID")
    @Size(max = 64)
    private String moduleId;

    @Schema(title = "发生时间", required = true)
    @NotNull
    private Date occurTime;

    @Schema(title = "标题", required = true)
    @NotBlank
    @Size(max = 768)
    private String title;

    @Schema(title = "错误级别")
    private String errorLevel;

    @Schema(title = "根异常类型")
    private String rootExceptionType;

    @Schema(title = "完整异常堆栈")
    private String exceptionFullInfo;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getOccurTime() == null) {
            setOccurTime(new Date());
        }

    }

}
