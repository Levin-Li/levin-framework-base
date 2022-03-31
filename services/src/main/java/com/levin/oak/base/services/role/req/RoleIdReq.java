package com.levin.oak.base.services.role.req;

import com.levin.commons.dao.annotation.Ignore;
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
import com.levin.oak.base.services.role.info.*;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.oak.base.entities.Role.*;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
*  角色 主键通用请求
*  //Auto gen by simple-dao-codegen 2022-3-25 17:01:35
*/

@Schema(description = "角色 主键通用请求")
@Data

    @AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS, resultClass = RoleInfo.class)
public class RoleIdReq extends MultiTenantReq {

private static final long serialVersionUID = -445356492L;

    @Schema(description = "id" , required = true)
    @Eq(require = true)
    @NotNull
    protected Long id;

    @Schema(description = "是否包含公共数据", hidden = true)
    @Ignore
    private boolean isContainsPublicData = false;

    /**
     * 是否为公共数据
     *
     * @return
     */
    @Override
    public boolean isContainsPublicData() {
        //允许查询公共的角色
        return isContainsPublicData;
    }

    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
