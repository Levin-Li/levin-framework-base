package com.levin.oak.base.services.dict.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.dict.info.*;
import com.levin.oak.base.entities.Dict;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Dict.*;
    import java.util.List;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询字典
 *  @Author Auto gen by simple-dao-codegen 2021-11-15 15:01:48
 */
@Schema(description = "查询字典")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Dict.class, alias = E_Dict.ALIAS
, resultClass = DictInfo.class)
public class QueryDictReq implements ServiceReq  {

    private static final long serialVersionUID = -445779596L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "类型")
    private Type type;


    @Schema(description = "编码")
    private String code;


    @Schema(description = "编码项")
    private String items;




    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "子域，模块或是自系统")
    private String domain;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_Dict.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_Dict.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_Dict.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_Dict.lastUpdateTime)
    private Date maxLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryDictReq(Long id) {
        this.id = id;
    }
}
