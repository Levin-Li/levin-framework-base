package com.levin.oak.base.services.simplepage.info;


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
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
* 简单页面
* @Author Auto gen by simple-dao-codegen 2022-5-23 10:30:00
*/
@Schema(description ="简单页面")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class SimplePageInfo implements Serializable {

    private static final long serialVersionUID = 1598619295L;


    @NotNull
    @Schema(description = "id", required = true)
    String id;


    @NotBlank
    @Size(max = 64)
    @Schema(description = "类型", required = true)
    String type;


    @NotBlank
    @Size(max = 64)
    @Schema(description = "分类名称", required = true)
    String category;


    @NotBlank
    @Size(max = 64)
    @Schema(description = "分组名称", required = true)
    String groupName;


    @NotBlank
    @Schema(description = "访问路径", required = true)
    String path;

    @Size(max = 1800)
    @Schema(description = "需要的授权，权限或角色，json数组")
    String requireAuthorizations;

    @Schema(description = "内容")
    String content;


    @Size(max = 128)
    @Schema(description = "机构ID")
    String orgId;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "租户ID")
    String tenantId;


    @Size(max = 128)
    @Schema(description = "系统子域")
    String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称", required = true)
    String name;


    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    String pinyinName;


    @InjectVar()
    @Size(max = 128)
    @Schema(description = "创建者")
    String creator;


    @NotNull
    @Schema(description = "创建时间", required = true)
    Date createTime;


    @Schema(description = "更新时间")
    Date lastUpdateTime;


    @Schema(description = "排序代码")
    Integer orderCode;

    @NotNull
    @Schema(description = "是否允许", required = true)
    Boolean enable;


    @NotNull
    @Schema(description = "是否可编辑", required = true)
    Boolean editable;


    @Size(max = 512)
    @Schema(description = "备注")
    String remark;

}
