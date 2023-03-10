package com.levin.oak.base.services.noticeprocesslog.info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

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

////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;

import java.util.Date;
////////////////////////////////////

/**
 * 通知处理日志
 *
 * @Author Auto gen by simple-dao-codegen 2022-6-20 16:50:12
 */
@Schema(description = "通知处理日志")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class NoticeProcessLogInfo implements Serializable {

    private static final long serialVersionUID = -1991983093L;


    @NotBlank
    @Size(max = 64)
    @Schema(description = "id", required = true)
    String id;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "租户ID")
    String tenantId;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "部门ID")
    String orgId;


    @NotBlank
    @InjectVar()
    @Size(max = 128)
    @Schema(description = "用户ID", required = true)
    String ownerId;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "消息ID", required = true)
    String noticeId;


    @Size(max = 128)
    @Schema(description = "处理状态")
    String status;


    @NotNull
    @Schema(description = "处理时间", required = true)
    Date createTime;


    @Size(max = 512)
    @Schema(description = "备注")
    String remark;


}
