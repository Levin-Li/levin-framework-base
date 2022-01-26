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

import com.levin.oak.base.entities.SimpleForm;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.Date;
////////////////////////////////////

/**
 *  删除简单表单
 *  //Auto gen by simple-dao-codegen 2022-1-26 17:07:15
 */
@Schema(description = "删除简单表单")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleForm.class, alias = E_SimpleForm.ALIAS)
public class DeleteSimpleFormReq extends MultiTenantReq {

    private static final long serialVersionUID = 1598335188L;


    @Schema(description = "id集合")
    @In(value = E_SimpleForm.id, require = true)
    @NotEmpty
    private Long[] idList;

    public DeleteSimpleFormReq(Long... idList) {
        this.idList = idList;
    }

    public DeleteSimpleFormReq setIdList(Long... idList) {
        this.idList = idList;
        return this;
    }


    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
