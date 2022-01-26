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

import org.springframework.format.annotation.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.scheduledlog.info.*;
import com.levin.oak.base.entities.ScheduledLog;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import java.util.Date;
////////////////////////////////////

/**
 *  查询调度日志
 *  @Author Auto gen by simple-dao-codegen 2022-1-26 17:07:14
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
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS, resultClass = ScheduledLogInfo.class)
public class QueryScheduledLogReq extends MultiTenantReq{

    private static final long serialVersionUID = 1319130901L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "任务ID")
    private String taskId;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;



    @Schema(description = "执行周期")
    private String invokeCycle;


    @Schema(description = "是否错误")
    private Boolean isError;


    @Schema(description = "执行结果")
    private String invokeResult;


    public QueryScheduledLogReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
