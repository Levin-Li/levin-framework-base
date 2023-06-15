package com.levin.oak.base.services.noticeprocesslog.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;

import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;

import java.util.Date;
////////////////////////////////////


/**
 * 新增通知处理日志
 * //Auto gen by simple-dao-codegen 2022-6-20 16:50:12
 */
@Schema(title = "新增通知处理日志")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = NoticeProcessLog.class, alias = E_NoticeProcessLog.ALIAS)
public class CreateNoticeProcessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = -1991983093L;


    @Schema(title = "用户ID", required = true)
    @NotBlank
    @InjectVar()
    @Size(max = 128)
    String ownerId;

    @Schema(title = "消息ID", required = true)
    @NotBlank
    @Size(max = 128)
    String noticeId;

    @Schema(title = "处理状态")
    @Size(max = 128)
    String status;

    @Schema(title = "处理时间", required = true)
    @NotNull
    Date createTime;

    @Schema(title = "备注")
    @Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
