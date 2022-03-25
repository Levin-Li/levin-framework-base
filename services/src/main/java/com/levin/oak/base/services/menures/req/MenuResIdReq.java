package com.levin.oak.base.services.menures.req;

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
import com.levin.oak.base.services.menures.info.*;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.rbac.MenuItem.*;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.services.menures.info.*;
import java.util.Set;
import java.util.Date;
////////////////////////////////////

/**
*  菜单 主键通用请求
*  //Auto gen by simple-dao-codegen 2022-3-25 13:28:15
*/

@Schema(description = "菜单 主键通用请求")
@Data

    @AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS, resultClass = MenuResInfo.class)
public class MenuResIdReq extends MultiTenantReq {

private static final long serialVersionUID = -887712701L;


    @Schema(description = "id" , required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;
    

    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
