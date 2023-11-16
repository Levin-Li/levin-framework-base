package com.levin.oak.base.services.noticeprocesslog.req;

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
import static com.levin.oak.base.entities.E_NoticeProcessLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;

////////////////////////////////////

/**
 * 新增通知处理日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月16日 下午11:01:08, 代码生成哈希校验码：[19ba091386f7ec026690cc41fd3786cc]，请不要修改和删除此行内容。
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
@TargetOption(entityClass = NoticeProcessLog.class, alias = E_NoticeProcessLog.ALIAS)
public class SimpleCreateNoticeProcessLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = -1991983093L;

    @Schema(title = L_creator)
    @Size(max = 128)
    @InjectVar(value = InjectConsts.USER_ID, isRequired = "false")
    String creator;

    @Schema(title = L_noticeId)
    @NotBlank
    @Size(max = 128)
    String noticeId;

    @Schema(title = L_status)
    @Size(max = 128)
    String status;

    @Schema(title = L_remark)
    @Size(max = 512)
    String remark;

    @Schema(title = L_createTime)
    @NotNull
    Date createTime;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等
    }
}
