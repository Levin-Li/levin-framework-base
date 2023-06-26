package com.levin.oak.base.services.apperrorlog.req;

import static com.levin.oak.base.entities.EntityConst.*;

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

import javax.annotation.*;
import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AppErrorLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  删除应用错误日志
 *  //Auto gen by simple-dao-codegen 2023年6月26日 下午6:06:02
 * 代码生成哈希校验码：[d6a6e50befe1ba666d083d3aef62b63e]
 */
@Schema(title = DELETE_ACTION + BIZ_NAME)
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


    @Schema(title = L_id + "集合", required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @In(value = E_AppErrorLog.id)
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
