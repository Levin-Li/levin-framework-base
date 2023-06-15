package com.levin.oak.base.services.i18nres.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.E_I18nRes;
import com.levin.oak.base.entities.I18nRes;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.i18nres.info.I18nResInfo;
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
 * 国际化资源 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */

@Schema(title = "国际化资源 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS, resultClass = I18nResInfo.class)
public class I18nResIdReq extends MultiTenantReq {

    private static final long serialVersionUID = -1681554652L;


    @Schema(title = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
