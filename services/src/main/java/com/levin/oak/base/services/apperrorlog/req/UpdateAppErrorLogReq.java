package com.levin.oak.base.services.apperrorlog.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
////////////////////////////////////


/**
 *  更新应用错误日志
 *  Auto gen by simple-dao-codegen 2022-3-29 22:58:02
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

    @Schema(description = "id" , required = true)
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
