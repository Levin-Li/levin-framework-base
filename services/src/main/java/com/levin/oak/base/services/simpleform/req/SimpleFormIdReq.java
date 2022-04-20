package com.levin.oak.base.services.simpleform.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.E_SimpleForm;
import com.levin.oak.base.entities.SimpleForm;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.simpleform.info.SimpleFormInfo;
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
 * 简单表单 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:37
 */

@Schema(description = "简单表单 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleForm.class, alias = E_SimpleForm.ALIAS, resultClass = SimpleFormInfo.class)
public class SimpleFormIdReq extends MultiTenantReq {

    private static final long serialVersionUID = 1598335188L;


    @Schema(description = "id", required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
