package com.levin.oak.base.services.scheduledlog.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.scheduledlog.info.*;
import com.levin.oak.base.entities.ScheduledLog;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询调度日志
 *  @Author Auto gen by simple-dao-codegen 2021-11-15 15:01:48
 */
@Schema(description = "查询调度日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS
, resultClass = ScheduledLogInfo.class)
public class QueryScheduledLogReq implements ServiceReq  {

    private static final long serialVersionUID = 1319130901L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "归属组织")
    private String orgId;


    @Schema(description = "任务ID")
    private String taskId;


    @Schema(description = "最小创建时间")
    @Gte(E_ScheduledLog.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_ScheduledLog.createTime)
    private Date maxCreateTime;



    @Schema(description = "执行周期")
    private String invokeCycle;


    @Schema(description = "是否错误")
    private Boolean isError;


    @Schema(description = "执行结果")
    private String invokeResult;


    public QueryScheduledLogReq(Long id) {
        this.id = id;
    }
}
