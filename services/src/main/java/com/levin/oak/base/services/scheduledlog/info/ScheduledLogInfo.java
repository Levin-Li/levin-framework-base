package com.levin.oak.base.services.scheduledlog.info;

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
import static com.levin.oak.base.entities.E_ScheduledLog.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 * 调度日志
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 上午11:30:55
 * 代码生成哈希校验码：[30b244891c1bc9c2886586f5b45e11f6]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class ScheduledLogInfo implements Serializable {

    private static final long serialVersionUID = 1319130901L;


    @NotNull
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;


    @Size(max = 64)
    @Schema(title = L_tenantId )
    String tenantId;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_orgId , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String orgId;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_taskId , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String taskId;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date createTime;


    @Size(max = 128)
    @Schema(title = L_invokeCycle )
    String invokeCycle;


    @Schema(title = L_isError )
    Boolean isError;


    @Schema(title = L_invokeResult )
    String invokeResult;


}
