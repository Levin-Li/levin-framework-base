package com.levin.oak.base.services.scheduledlog.req;

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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
    import java.util.Date;
////////////////////////////////////

/**
 *  删除调度日志
 *  //Auto gen by simple-dao-codegen 2022-1-5 15:46:43
 */
@Schema(description = "删除调度日志")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
public class DeleteScheduledLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1319130901L;

    @OR
    @Schema(description = "id" , hidden = true)
    private Long id;

    @END
    @Schema(description = "id集合")
    @In(E_ScheduledLog.id)
    @Validator(expr = "id != null || ( idList != null &&  idList.length > 0)" , promptInfo = "删除调度日志必须指定ID")
    private Long[] idList;


    public DeleteScheduledLogReq(Long id) {
        this.id = id;
    }

    public DeleteScheduledLogReq(Long... idList) {
        this.idList = idList;
    }

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
