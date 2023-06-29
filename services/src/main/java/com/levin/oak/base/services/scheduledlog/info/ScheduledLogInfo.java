package com.levin.oak.base.services.scheduledlog.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
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
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 下午6:00:39, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[b01d1ee935bbd9f0f8f2593dd955451a], 请不要修改和删除此行内容。
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
    @Schema(title = L_id , required = true, requiredMode = REQUIRED)
    Long id;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_taskId , required = true, requiredMode = REQUIRED)
    String taskId;


    @Size(max = 128)
    @Schema(title = L_invokeCycle )
    String invokeCycle;


    @Schema(title = L_invokeSnapshot , description = D_invokeSnapshot )
    String invokeSnapshot;


    @Schema(title = L_isError )
    Boolean isError;


    @Schema(title = L_invokeResult )
    String invokeResult;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_orgId )
    String orgId;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = REQUIRED)
    Date createTime;


}
