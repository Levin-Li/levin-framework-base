package com.levin.oak.base.services.scheduledlog.req;

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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 * 删除调度日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月19日 23:49:55, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[b3bb5782a61eea1a9b0f0265d3fcfdad], 请不要修改和删除此行内容。
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
public class DeleteScheduledLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1319130901L;




    @Schema(title = L_id + "集合", required = true, requiredMode = REQUIRED)
    @In(value = E_ScheduledLog.id)
    @NotEmpty
    private String[] idList;

    public DeleteScheduledLogReq(String... idList) {
        this.idList = idList;
    }

    public DeleteScheduledLogReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
