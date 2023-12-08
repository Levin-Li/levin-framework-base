package com.levin.oak.base.services.scheduledtask.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;

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

import com.levin.oak.base.entities.ScheduledTask;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledTask.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新调度任务
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月8日 下午11:11:16, 代码生成哈希校验码：[5f8d3754c5e380953cdc392ca33d0d94]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledTask.class, alias = E_ScheduledTask.ALIAS)

public class UpdateScheduledTaskReq extends SimpleUpdateScheduledTaskReq {

    private static final long serialVersionUID = -2056389676L;


    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotBlank
    @Eq(require = true)
    String id;

    public UpdateScheduledTaskReq() {
    }

    public UpdateScheduledTaskReq(String id) {
        this.id = id;
    }

    public UpdateScheduledTaskReq(String id, boolean forceUpdate) {
        super(forceUpdate);
        this.id = id;
    }

    public UpdateScheduledTaskReq updateIdWhenNotBlank(String id){
        if(isNotBlank(id)){
            this.id = id;
        }
        return this;
    }

    public static UpdateScheduledTaskReq of(String id, boolean forceUpdate){
        return new UpdateScheduledTaskReq(id, forceUpdate);
    }

}
