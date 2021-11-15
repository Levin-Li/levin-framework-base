package com.levin.oak.base.services.scheduledlog.req;

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
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////


/**
 *  新增调度日志
 *  //Auto gen by simple-dao-codegen 2021-11-13 23:58:00
 */
@Schema(description = "新增调度日志")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
public class CreateScheduledLogReq implements ServiceReq {

    private static final long serialVersionUID = 1319130901L;



    @Schema(description = "租户ID" )
    @InjectVar
    private String tenantId;


    @Schema(description = "归属组织" , required = true)
    @NotNull
    @InjectVar
    private String orgId;


    @Schema(description = "任务ID" , required = true)
    @NotNull
    private String taskId;


    @Schema(description = "创建时间" , required = true)
    @NotNull
    private Date createTime;


    @Schema(description = "执行周期" )
    private String invokeCycle;


    @Schema(description = "是否错误" )
    private Boolean isError;


    @Schema(description = "执行结果" )
    private String invokeResult;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}