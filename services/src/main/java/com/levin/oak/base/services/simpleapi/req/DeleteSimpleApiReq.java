package com.levin.oak.base.services.simpleapi.req;

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

import com.levin.oak.base.entities.SimpleApi;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.oak.base.entities.SimpleApi.*;

import java.util.Date;
////////////////////////////////////

/**
 * 删除简单动态接口
 * //Auto gen by simple-dao-codegen 2022-5-23 10:30:00
 */
@Schema(title = "删除简单动态接口")
@Data

//@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS)
public class DeleteSimpleApiReq extends MultiTenantReq {

    private static final long serialVersionUID = 1021385738L;


    @Schema(title = "id集合")
    @In(value = E_SimpleApi.id, require = true)
    @NotEmpty
    private String[] idList;

    public DeleteSimpleApiReq(String... idList) {
        this.idList = idList;
    }

    public DeleteSimpleApiReq setIdList(String... idList) {
        this.idList = idList;
        return this;
    }


    @Schema(title = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @PostConstruct
    public void preDelete() {
        //@todo 删除之前初始化数据
    }

}
