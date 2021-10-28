package com.levin.oak.base.services.setting.info;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

/////////////////////////////////////////////////////
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
import com.levin.oak.base.entities.Setting.*;
import java.util.Date;
////////////////////////////////////

/**
* 系统设置
* @Author Auto gen by simple-dao-codegen 2021-10-28 16:17:41
*/
@Schema(description ="系统设置")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"" + E_Setting.id})
@ToString(exclude = {})
@FieldNameConstants
public class SettingInfo implements Serializable {

   private static final long serialVersionUID = 147875794L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @NotNull
   @Schema(description = "分类名称")
   private String categoryName;


   @Schema(description = "分组名称")
   private String groupName;


   @NotNull
   @Schema(description = "编码")
   private String code;


   @NotNull
   @Schema(description = "值类型")
   private ValueType valueType;


   @Schema(description = "值")
   private String value;


   @Schema(description = "值是否可空")
   private Boolean nullable;


   @Schema(description = "输入占位提示")
   private String inputPlaceholder;


   @NotNull
   @Size(max = 768)
   @Schema(description = "名称")
   private String name;


   @Schema(description = "租户ID")
   private Long tenantId;


   @Size(max = 512)
   @Schema(description = "创建者")
   private String creator;


   @NotNull
   @Schema(description = "创建时间")
   private Date createTime;


   @Schema(description = "更新时间")
   private Date lastUpdateTime;


   @Schema(description = "排序代码")
   private Integer orderCode;


   @NotNull
   @Schema(description = "是否允许")
   private Boolean enable;


   @NotNull
   @Schema(description = "是否可编辑")
   private Boolean editable;


   @Size(max = 1800)
   @Schema(description = "备注")
   private String remark;


}
