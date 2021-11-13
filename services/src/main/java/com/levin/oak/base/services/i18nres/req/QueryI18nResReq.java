package com.levin.oak.base.services.i18nres.req;

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

import com.levin.oak.base.services.i18nres.info.*;
import com.levin.oak.base.entities.I18nRes;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询国际化资源
 *  @Author Auto gen by simple-dao-codegen 2021-11-13 23:58:00
 */
@Schema(description = "查询国际化资源")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = I18nRes.class, alias = E_I18nRes.ALIAS
, resultClass = I18nResInfo.class)
public class QueryI18nResReq implements ServiceReq  {

    private static final long serialVersionUID = -1681554652L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "分类")
    private String category;


    @Schema(description = "语言")
    private String lang;


    @Schema(description = "标签")
    private String label;


    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_I18nRes.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_I18nRes.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_I18nRes.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_I18nRes.lastUpdateTime)
    private Date maxLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryI18nResReq(Long id) {
        this.id = id;
    }
}
