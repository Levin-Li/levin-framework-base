package com.levin.oak.base.services.apperrorlog.req;

// import static com.levin.oak.base.ModuleOption.*;
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
import static com.levin.oak.base.entities.E_AppErrorLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;

////////////////////////////////////

/**
 * 新增应用错误日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:43, 代码生成哈希校验码：[95bb6543488a81667287a28da6b396d9]，请不要修改和删除此行内容。
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
// @EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)
public class CreateAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;

    @Schema(title = L_moduleId)
    @Size(max = 64)
    String moduleId;

    @Schema(title = L_occurTime)
    @NotNull
    Date occurTime;

    @Schema(title = L_title)
    @NotBlank
    @Size(max = 768)
    String title;

    @Schema(title = L_errorLevel)
    String errorLevel;

    @Schema(title = L_rootExceptionType)
    String rootExceptionType;

    @Schema(title = L_exceptionFullInfo)
    String exceptionFullInfo;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等

        if (getOccurTime() == null) {
            setOccurTime(new Date());
        }
    }
}
