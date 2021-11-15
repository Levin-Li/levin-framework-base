package com.levin.oak.base.services.scheduledtask.req;

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

import com.levin.oak.base.services.scheduledtask.info.*;
import com.levin.oak.base.entities.ScheduledTask;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import java.util.Date;
    import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 *  查询调度任务
 *  @Author Auto gen by simple-dao-codegen 2021-11-15 15:08:50
 */
@Schema(description = "查询调度任务")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS
, resultClass = ScheduledTaskInfo.class)
public class QueryScheduledTaskReq implements ServiceReq  {

    private static final long serialVersionUID = -2056389676L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "任务分类")
    private String category;


    @Schema(description = "任务组")
    private String groupName;


    @Schema(description = "调度表达式")
    private String cron;


    @Schema(description = "执行表达式")
    private String invokeExpr;


    @Schema(description = "允许并发执行")
    private Boolean parallelInvoke;


    @Schema(description = "最小最后一次时间")
    @Gte(E_ScheduledTask.lastInvokedTime)
    private Date minLastInvokedTime;

    @Schema(description = "最大最后一次时间")
    @Lte(E_ScheduledTask.lastInvokedTime)
    private Date maxLastInvokedTime;



    @Schema(description = "最小下一次时间")
    @Gte(E_ScheduledTask.nextInvokeTime)
    private Date minNextInvokeTime;

    @Schema(description = "最大下一次时间")
    @Lte(E_ScheduledTask.nextInvokeTime)
    private Date maxNextInvokeTime;



    @Schema(description = "机构ID")
    private String orgId;


    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "系统子域")
    private String domain;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_ScheduledTask.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_ScheduledTask.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_ScheduledTask.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_ScheduledTask.lastUpdateTime)
    private Date maxLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryScheduledTaskReq(Long id) {
        this.id = id;
    }
}
