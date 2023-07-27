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

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_ScheduledLog.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;

////////////////////////////////////

/**
 * 更新调度日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:43, 代码生成哈希校验码：[a91916252d78db0ab71f05648b1fbcf0]，请不要修改和删除此行内容。
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// @EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
// 默认更新注解
@Update
public class UpdateScheduledLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1319130901L;

    @Schema(title = L_id, required = true, requiredMode = REQUIRED)
    @NotNull
    @Eq(require = true)
    String id;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_taskId)
    String taskId;

    @Size(max = 128)
    @Schema(title = L_invokeCycle)
    String invokeCycle;

    @Schema(title = L_invokeSnapshot, description = D_invokeSnapshot)
    String invokeSnapshot;

    @Schema(title = L_isError)
    Boolean isError;

    @Schema(title = L_invokeResult)
    String invokeResult;

    public UpdateScheduledLogReq(String id) {
        this.id = id;
    }

    public UpdateScheduledLogReq updateIdWhenNotBlank(String id) {
        if (isNotBlank(id)) {
            this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        // @todo 更新之前初始化数据
    }
}
