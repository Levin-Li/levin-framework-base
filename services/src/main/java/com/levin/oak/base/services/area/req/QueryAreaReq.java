package com.levin.oak.base.services.area.req;

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

import com.levin.oak.base.services.area.info.*;
import com.levin.oak.base.entities.Area;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import java.util.Set;
    import com.levin.oak.base.entities.Area.*;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询区域
 *  @Author Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "查询区域")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS
, resultClass = AreaInfo.class)
public class QueryAreaReq implements ServiceReq  {

    private static final long serialVersionUID = -445860277L;


    @Schema(description = "编码")
    private String code;


    @Schema(description = "图标")
    private String icon;


    @Schema(description = "父区域ID")
    private String parentCode;


    @Schema(description = "加载父区域")
    @Fetch(attrs = E_Area.parent, condition = "#_val == true")
    private Boolean loadParent;


    @Schema(description = "加载子区域")
    @Fetch(attrs = E_Area.children, condition = "#_val == true")
    private Boolean loadChildren;


    @Schema(description = "类型")
    private Type type;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_Area.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_Area.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_Area.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_Area.lastUpdateTime)
    private Date maxLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryAreaReq(String code) {
        this.code = code;
    }
}