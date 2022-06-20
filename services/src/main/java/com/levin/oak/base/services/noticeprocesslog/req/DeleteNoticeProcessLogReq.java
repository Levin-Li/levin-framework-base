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

import javax.annotation.*;
import javax.validation.constraints.*;

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
 *  删除通知处理日志
 *  //Auto gen by simple-dao-codegen 2022-6-20 16:50:12
 */
@Schema(description = "删除通知处理日志")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = NoticeProcessLog.class, alias = E_NoticeProcessLog.ALIAS)
public class DeleteNoticeProcessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = -1991983093L;


    @Schema(description = "id集合")
    @In(value = E_NoticeProcessLog.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteNoticeProcessLogReq(String... idList) {
        this.idList = idList;
    }

    public DeleteNoticeProcessLogReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }



    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
