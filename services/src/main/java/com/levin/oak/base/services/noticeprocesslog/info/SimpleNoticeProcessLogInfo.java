package com.levin.oak.base.services.noticeprocesslog.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
/////////////////////////////////////////////////////
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
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 通知处理日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:43, 代码生成哈希校验码：[4825b0b8e9d377caab5f8776ad27b068]，请不要修改和删除此行内容。
 *
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
@Select
public class SimpleNoticeProcessLogInfo implements Serializable {

    private static final long serialVersionUID = -1991983093L;

    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id)
    String id;

    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @NotBlank
    @Size(max = 128)
    @Schema(title = L_noticeId)
    String noticeId;

    @Size(max = 128)
    @Schema(title = L_status)
    String status;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    @Size(max = 128)
    @Schema(title = L_tenantId)
    String tenantId;

    @Size(max = 128)
    @Schema(title = L_orgId)
    String orgId;

    @NotNull
    @Schema(title = L_createTime)
    Date createTime;


}
