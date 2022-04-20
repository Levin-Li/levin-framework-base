package com.levin.oak.base.services.apperrorlog.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.AppErrorLog;
import com.levin.oak.base.entities.E_AppErrorLog;
import com.levin.oak.base.services.apperrorlog.info.AppErrorLogInfo;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 应用错误日志 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-29 22:58:02
 */

@Schema(description = "应用错误日志 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS, resultClass = AppErrorLogInfo.class)
public class AppErrorLogIdReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;


    @Schema(description = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
