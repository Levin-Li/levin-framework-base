package com.levin.oak.base.services.area.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.oak.base.entities.Area;
import com.levin.oak.base.entities.E_Area;
import com.levin.oak.base.services.area.info.AreaInfo;
import com.levin.oak.base.services.commons.req.BaseReq;
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
 * 区域 主键通用请求
 * //Auto gen by simple-dao-codegen 2022-3-25 17:01:36
 */

@Schema(description = "区域 主键通用请求")
@Data

@AllArgsConstructor

@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS, resultClass = AreaInfo.class)
public class AreaIdReq extends BaseReq {

    private static final long serialVersionUID = -445860277L;


    @Schema(description = "编码", required = true)
    @Eq(require = true)
    @NotNull
    protected String code;


    @PostConstruct
    public void preQuery() {
        //@todo ID 查询之前初始化数据
    }
}
