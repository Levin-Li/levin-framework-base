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
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;

import java.util.Date;
////////////////////////////////////

/**
 * 简单表单
 *
 * @Author Auto gen by simple-dao-codegen 2022-5-23 10:30:01
 */
@Schema(title = "简单表单")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class SimpleFormInfo implements Serializable {

    private static final long serialVersionUID = 1598335188L;


    @Schema(title = "提交地址")
    String commitApi;


    @NotNull
    @Schema(title = "id", required = true)
    String id;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "类型", required = true)
    String type;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "分类名称", required = true)
    String category;


    @NotBlank
    @Size(max = 64)
    @Schema(title = "分组名称", required = true)
    String groupName;


    @NotBlank
    @Schema(title = "访问路径", required = true)
    String path;


    @Schema(title = "内容")
    String content;


    @Size(max = 128)
    @Schema(title = "机构ID")
    String orgId;


    @InjectVar()
    @Size(max = 128)
    @Schema(title = "租户ID")
    String tenantId;


    @Size(max = 128)
    @Schema(title = "系统子域")
    String domain;


    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称", required = true)
    String name;


    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    String pinyinName;


    @InjectVar()
    @Size(max = 128)
    @Schema(title = "创建者")
    String creator;


    @NotNull
    @Schema(title = "创建时间", required = true)
    Date createTime;


    @Schema(title = "更新时间")
    Date lastUpdateTime;


    @Schema(title = "排序代码")
    Integer orderCode;


    @NotNull
    @Schema(title = "是否允许", required = true)
    Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑", required = true)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    String remark;


}
