package com.levin.oak.base.services.apperrorlog.req;

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

import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.Date;
////////////////////////////////////

/**
 *  删除应用错误日志
 *  //Auto gen by simple-dao-codegen 2022-3-29 22:58:02
 */
@Schema(description = "删除应用错误日志")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)
public class DeleteAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;


    @Schema(description = "id集合")
    @In(value = E_AppErrorLog.id, require = true)
    @NotEmpty
    private Long[] idList;

    public DeleteAppErrorLogReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteAppErrorLogReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
