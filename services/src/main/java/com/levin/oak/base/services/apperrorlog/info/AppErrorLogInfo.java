package com.levin.oak.base.services.apperrorlog.info;

import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
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
import static com.levin.oak.base.entities.E_AppErrorLog.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 * 应用错误日志
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 上午12:43:08
 * 代码生成哈希校验码：[3497653eb7f05c0c2637f58735852a3c]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class AppErrorLogInfo implements Serializable {

    private static final long serialVersionUID = 1594864095L;


    @NotNull
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;


    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 64)
    @Schema(title = L_moduleId )
    String moduleId;


    @NotNull
    @Schema(title = L_occurTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date occurTime;


    @NotBlank
    @Size(max = 768)
    @Schema(title = L_title , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String title;


    @Schema(title = L_errorLevel )
    String errorLevel;


    @Schema(title = L_rootExceptionType )
    String rootExceptionType;


    @Schema(title = L_exceptionFullInfo )
    String exceptionFullInfo;


}
