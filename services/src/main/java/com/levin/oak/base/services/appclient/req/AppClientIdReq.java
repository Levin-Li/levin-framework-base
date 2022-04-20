package com.levin.oak.base.services.appclient.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.AppClient;
import com.levin.oak.base.entities.E_AppClient;
import com.levin.oak.base.services.appclient.info.AppClientInfo;
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
 * 应用接入 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-4-3 0:55:04
 */

@Schema(description = "应用接入 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AppClient.class, alias = E_AppClient.ALIAS, resultClass = AppClientInfo.class)
public class AppClientIdReq extends MultiTenantReq {

    private static final long serialVersionUID = -115048882L;


    @Schema(description = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
