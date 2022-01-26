package com.levin.oak.base.services.simpleform.req;

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
import com.levin.oak.base.services.simpleform.info.*;
import com.levin.oak.base.entities.SimpleForm;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.Date;
////////////////////////////////////

/**
*  简单表单 主键通用请求
*  //Auto gen by simple-dao-codegen 2022-1-26 17:07:15
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


    @Schema(description = "id" , required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;
    

    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
