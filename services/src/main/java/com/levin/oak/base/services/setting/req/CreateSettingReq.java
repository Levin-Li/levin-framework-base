package com.levin.oak.base.services.setting.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


import com.levin.oak.base.entities.*;

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.Setting.*;
    import java.util.Date;
////////////////////////////////////


/**
 *  新增系统设置
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:41
 */
@Schema(description = "新增系统设置")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Setting.class, alias = E_Setting.ALIAS)
public class CreateSettingReq implements ServiceReq {

    private static final long serialVersionUID = 147875794L;



    @Schema(description = "分类名称")
    @NotNull
    private String categoryName;


    @Schema(description = "分组名称")
    private String groupName;


    @Schema(description = "编码")
    @NotNull
    private String code;


    @Schema(description = "值类型")
    @NotNull
    private ValueType valueType;


    @Schema(description = "值")
    private String value;


    @Schema(description = "值是否可空")
    private Boolean nullable;


    @Schema(description = "输入占位提示")
    private String inputPlaceholder;


    @Schema(description = "名称")
    @NotNull
    @Size(max = 768)
    private String name;


    @Schema(description = "租户ID")
    private Long tenantId;


    @Schema(description = "创建者")
    @Size(max = 512)
    private String creator;


    @Schema(description = "创建时间")
    @NotNull
    private Date createTime;


    @Schema(description = "更新时间")
    private Date lastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    @NotNull
    private Boolean enable;


    @Schema(description = "是否可编辑")
    @NotNull
    private Boolean editable;


    @Schema(description = "备注")
    @Size(max = 1800)
    private String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
