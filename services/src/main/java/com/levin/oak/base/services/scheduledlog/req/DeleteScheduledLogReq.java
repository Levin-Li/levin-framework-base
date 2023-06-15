package com.levin.oak.base.services.scheduledlog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.In;
import com.levin.oak.base.entities.E_ScheduledLog;
import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 删除调度日志
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */
@Schema(title = "删除调度日志")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
public class DeleteScheduledLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1319130901L;


    @Schema(title = "id集合")
    @In(value = E_ScheduledLog.id, require = true)
    @NotEmpty
    private Long[] idList;

    public DeleteScheduledLogReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteScheduledLogReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
