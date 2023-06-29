package com.levin.oak.base.services.noticeprocesslog.info;

import static com.levin.oak.base.entities.EntityConst.*;

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
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 * 通知处理日志
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:12, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[a869d2929b9d519579c0f6ab69c82fcf], 请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class NoticeProcessLogInfo implements Serializable {

    private static final long serialVersionUID = -1991983093L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String id;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_orgId )
    String orgId;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_ownerId , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String ownerId;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_noticeId , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String noticeId;


    @Size(max = 128)
    @Schema(title = L_status )
    String status;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date createTime;


    @Size(max = 512)
    @Schema(title = L_remark )
    String remark;


}
