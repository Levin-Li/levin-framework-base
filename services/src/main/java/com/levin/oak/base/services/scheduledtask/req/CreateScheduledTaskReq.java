package com.levin.oak.base.services.scheduledtask.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import java.util.Date;
    import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////


/**
 *  新增调度任务
 *  //Auto gen by simple-dao-codegen 2021-11-13 23:58:00
 */
@Schema(description = "新增调度任务")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS)
public class CreateScheduledTaskReq implements ServiceReq {

    private static final long serialVersionUID = -2056389676L;



    @Schema(description = "任务分类" , required = true)
    @NotNull
    private String category;


    @Schema(description = "任务组" , required = true)
    @NotNull
    private String groupName;


    @Schema(description = "调度表达式" , required = true)
    @NotNull
    private String cron;


    @Schema(description = "执行表达式" )
    private String invokeExpr;


    @Schema(description = "允许并发执行" )
    private Boolean parallelInvoke;


    @Schema(description = "最后一次时间" )
    private Date lastInvokedTime;


    @Schema(description = "下一次时间" )
    private Date nextInvokeTime;


    @Schema(description = "机构ID" )
    @InjectVar
    private String orgId;


    @Schema(description = "租户ID" )
    @InjectVar
    private String tenantId;


    @Schema(description = "名称" , required = true)
    @NotNull
    @Size(max = 512)
    private String name;


    @Schema(description = "创建者" )
    @Size(max = 512)
    private String creator;


    @Schema(description = "创建时间" , required = true)
    @NotNull
    private Date createTime;


    @Schema(description = "更新时间" )
    private Date lastUpdateTime;


    @Schema(description = "排序代码" )
    private Integer orderCode;


    @Schema(description = "是否允许" , required = true)
    @NotNull
    private Boolean enable;


    @Schema(description = "是否可编辑" , required = true)
    @NotNull
    private Boolean editable;


    @Schema(description = "备注" )
    @Size(max = 1800)
    private String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
