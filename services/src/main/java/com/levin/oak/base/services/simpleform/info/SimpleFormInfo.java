package com.levin.oak.base.services.simpleform.info;


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
* 动态API
* @Author Auto gen by simple-dao-codegen 2021-11-15 15:08:51
*/
@Schema(description ="动态API")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class SimpleFormInfo implements Serializable {

   private static final long serialVersionUID = 1598335188L;


   @NotNull
   @Schema(description = "id")
   private Long id;


   @NotNull
   @Schema(description = "分类名称")
   private String category;


   @NotNull
   @Schema(description = "分组名称")
   private String groupName;


   @NotNull
   @Schema(description = "路径")
   private String path;


   @Schema(description = "表单文本")
   private String formText;


   @InjectVar
   @Schema(description = "机构ID")
   private String orgId;


   @InjectVar
   @Schema(description = "租户ID")
   private String tenantId;


   @Schema(description = "系统子域")
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