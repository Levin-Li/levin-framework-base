package com.levin.oak.base.services.scheduledlog.req;

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

import com.levin.oak.base.entities.ScheduledLog;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
    import java.util.Date;
////////////////////////////////////

/**
*  ID 查询调度日志
*  //Auto gen by simple-dao-codegen 2022-1-11 16:42:29
*/
@Schema(description = "ID 查询调度日志")
@Data

    @AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = ScheduledLog.class, alias = E_ScheduledLog.ALIAS)
public class QueryScheduledLogByIdReq extends MultiTenantReq {

private static final long serialVersionUID = 1319130901L;


    @Schema(description = "id" , required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;

    //public QueryScheduledLogByIdReq(Long id) {
    //    this.id = id;
    //}

    /**
     * 获取缓存ID
     */
    @Schema(description = "缓存ID-内部使用" , hidden = true)
    public final String getCacheId() {

        String cid = id.toString() + ""  + tenantId.toString();

        return cid.trim().length() > 0 ? cid : null;

    }


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
