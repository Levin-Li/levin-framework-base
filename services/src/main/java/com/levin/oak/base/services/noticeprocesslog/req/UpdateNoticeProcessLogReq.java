package com.levin.oak.base.services.noticeprocesslog.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

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

import com.levin.oak.base.entities.NoticeProcessLog;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////


/**
 *  更新通知处理日志
 *  Auto gen by simple-dao-codegen 2022-6-20 16:50:12
 */
@Schema(description = "更新通知处理日志")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = NoticeProcessLog.class, alias = E_NoticeProcessLog.ALIAS)
//默认更新注解
@Update
public class UpdateNoticeProcessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = -1991983093L;

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    String id;



    @NotBlank
    @InjectVar()
    @Size(max = 128)
    @Schema(description = "用户ID")
    String ownerId;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "消息ID")
    String noticeId;

    @Size(max = 128)
    @Schema(description = "处理状态")
    String status;

    @Size(max = 512)
    @Schema(description = "备注")
    String remark;


    public UpdateNoticeProcessLogReq(String id) {
        this.id = id;
    }

    public UpdateNoticeProcessLogReq setIdOnNotBlank(String id){
        if(isNotBlank(id)){
        this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

}
