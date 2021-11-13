package com.levin.oak.base.services.setting.req;

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

import com.levin.oak.base.services.setting.info.*;
import com.levin.oak.base.entities.Setting;

import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Setting.*;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询系统设置
 *  @Author Auto gen by simple-dao-codegen 2021-11-13 23:58:00
 */
@Schema(description = "查询系统设置")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS
, resultClass = SettingInfo.class)
public class QuerySettingReq implements ServiceReq  {

    private static final long serialVersionUID = 147875794L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "分类名称")
    private String categoryName;


    @Schema(description = "分组名称")
    private String groupName;


    @Schema(description = "编码")
    private String code;


    @Schema(description = "值类型")
    private ValueType valueType;


    @Schema(description = "值")
    private String value;


    @Schema(description = "值是否可空")
    private Boolean nullable;


    @Schema(description = "输入占位提示")
    private String inputPlaceholder;


    @Schema(description = "租户ID")
    private String tenantId;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "最小创建时间")
    @Gte(E_Setting.createTime)
    private Date minCreateTime;

    @Schema(description = "最大创建时间")
    @Lte(E_Setting.createTime)
    private Date maxCreateTime;



    @Schema(description = "最小更新时间")
    @Gte(E_Setting.lastUpdateTime)
    private Date minLastUpdateTime;

    @Schema(description = "最大更新时间")
    @Lte(E_Setting.lastUpdateTime)
    private Date maxLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QuerySettingReq(Long id) {
        this.id = id;
    }
}
