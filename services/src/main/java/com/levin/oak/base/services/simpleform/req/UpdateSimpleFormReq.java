package com.levin.oak.base.services.simpleform.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.SimpleForm;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////


/**
 *  更新简单表单
 *  Auto gen by simple-dao-codegen 2022-5-23 10:30:01
 */
@Schema(description = "更新简单表单")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = SimpleForm.class, alias = E_SimpleForm.ALIAS)
//默认更新注解
@Update
public class UpdateSimpleFormReq extends MultiTenantReq {

    private static final long serialVersionUID = 1598335188L;

    @Schema(description = "id" , required = true)
    @NotNull
    @Eq(require = true)
    String id;

    @Schema(description = "可编辑条件" , hidden = true)
    @Eq(condition ="!#user.isSuperAdmin()")
    final boolean eqEditable = true;


    @Schema(description = "提交地址")
    String commitApi;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "类型")
    String type;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "分类名称")
    String category;

    @NotBlank
    @Size(max = 64)
    @Schema(description = "分组名称")
    String groupName;

    @NotBlank
    @Schema(description = "访问路径")
    String path;

    @Schema(description = "内容")
    String content;

    @Size(max = 128)
    @Schema(description = "系统子域")
    String domain;

    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    String name;

    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    String pinyinName;

    @Schema(description = "更新时间")
    Date lastUpdateTime;

    @Schema(description = "排序代码")
    Integer orderCode;

    @Schema(description = "是否允许")
    Boolean enable;

    @Schema(description = "是否可编辑")
    Boolean editable;

    @Size(max = 512)
    @Schema(description = "备注")
    String remark;


    public UpdateSimpleFormReq(String id) {
        this.id = id;
    }
    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}
