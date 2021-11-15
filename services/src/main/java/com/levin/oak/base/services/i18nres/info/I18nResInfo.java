package com.levin.oak.base.services.i18nres.info;


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
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
* 国际化资源
* @Author Auto gen by simple-dao-codegen 2021-11-15 15:01:48
*/
@Schema(description ="国际化资源")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class I18nResInfo implements Serializable {

   private static final long serialVersionUID = -1681554652L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @NotNull
   @Schema(description = "分类")
   private String category;


   @NotNull
   @Size(max = 32)
   @Schema(description = "语言")
   private String lang;


   @NotNull
   @Size(max = 768)
   @Schema(description = "标签")
   private String label;


   @InjectVar
   @Schema(description = "租户ID")
   private String tenantId;


   @Schema(description = "子域，模块或是自系统")
   private String domain;


   @NotNull
   @Size(max = 512)
   @Schema(description = "名称")
   private String name;


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
