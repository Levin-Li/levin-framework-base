package com.levin.oak.base.services.simpleapi.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.E_SimpleApi;
import com.levin.oak.base.entities.SimpleApi;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.simpleapi.info.SimpleApiInfo;
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
 * 简单接口 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */

@Schema(description = "简单接口 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS, resultClass = SimpleApiInfo.class)
public class SimpleApiIdReq extends MultiTenantReq {

    private static final long serialVersionUID = 1021385738L;


    @Schema(description = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
