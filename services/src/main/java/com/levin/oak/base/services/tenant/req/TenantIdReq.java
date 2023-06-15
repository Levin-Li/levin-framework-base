package com.levin.oak.base.services.tenant.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.services.commons.req.BaseReq;
import com.levin.oak.base.services.tenant.info.TenantInfo;
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
 * 租户 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 18:38:00
 */

@Schema(title = "租户 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS, resultClass = TenantInfo.class)
public class TenantIdReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;


    @Schema(title = "ID", required = true)
    @Eq(require = true)
    @NotNull
    protected String id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
