package com.levin.oak.base.services.scheduledtask.req;

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

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.ScheduledTask;
import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import java.util.Date;
////////////////////////////////////

/**
 *  删除调度任务
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
@Schema(description = "删除调度任务")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS)
public class DeleteScheduledTaskReq implements ServiceReq {

    private static final long serialVersionUID = -2056389676L;

    @Schema(description = "id")
    private Long id;

    @Schema(description = "id集合")
    @In(E_ScheduledTask.id)
    @Validator(expr = "id != null || ( ids != null &&  ids.length > 0)" , promptInfo = "删除调度任务必须指定ID")
    private Long[] ids;


    public DeleteScheduledTaskReq(Long id) {
        this.id = id;
    }

    public DeleteScheduledTaskReq(Long... ids) {
        this.ids = ids;
    }
}