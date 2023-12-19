package com.levin.oak.base.services.scheduledlog.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
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

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 新增调度日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年12月18日 下午3:51:26, 代码生成哈希校验码：[e81c85d365606e9037715b6ca9798f5a]，请不要修改和删除此行内容。
 *
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
public class CreateScheduledLogReq extends MultiTenantOrgReq<CreateScheduledLogReq> {

    private static final long serialVersionUID = 1319130901L;

    @Schema(title = L_taskId )
    @NotBlank
    @Size(max = 64)
    String taskId;

    @Schema(title = L_invokeCycle )
    @Size(max = 128)
    String invokeCycle;

    @Schema(title = L_invokeSnapshot , description = D_invokeSnapshot )
    String invokeSnapshot;

    @Schema(title = L_isError )
    Boolean isError;

    @Schema(title = L_invokeResult )
    String invokeResult;

    @Schema(title = L_createTime )
    @NotNull
    Date createTime;

    @Schema(title = L_optimisticLock )
    Integer optimisticLock;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等

        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
    }
}
