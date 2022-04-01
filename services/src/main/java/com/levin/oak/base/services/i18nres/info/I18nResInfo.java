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
import java.util.Date;
////////////////////////////////////

/**
* 国际化资源
* @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:27
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
   @Schema(description = "id" , required = true)
   private Long id;


   @NotBlank
   @Size(max = 64)
   @Schema(description = "分类" , required = true)
   private String category;


   @NotBlank
   @Size(max = 32)
   @Schema(description = "语言" , required = true)
   private String lang;


   @NotBlank
   @Size(max = 768)
   @Schema(description = "标签" , required = true)
   private String label;


   @Size(max = 64)
   @Schema(description = "租户ID" )
   private String tenantId;


   @Size(max = 64)
   @Schema(description = "系统子域" )
   private String domain;


   @NotBlank
   @Size(max = 128)
   @Schema(description = "名称" , required = true)
   private String name;


   @Size(max = 128)
   @Schema(description = "拼音，格式：全拼(简拼)" )
   private String pinyinName;


   @Size(max = 128)
   @Schema(description = "创建者" )
   private String creator;


   @NotNull
   @Schema(description = "创建时间" , required = true)
   private Date createTime;


   @Schema(description = "更新时间" )
   private Date lastUpdateTime;


   @Schema(description = "排序代码" )
   private Integer orderCode;


   @NotNull
   @Schema(description = "是否允许" , required = true)
   private Boolean enable;


   @NotNull
   @Schema(description = "是否可编辑" , required = true)
   private Boolean editable;


   @Size(max = 512)
   @Schema(description = "备注" )
   private String remark;


}
