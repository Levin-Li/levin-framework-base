package com.levin.oak.base.services.simplepage.req;

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
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;

import java.util.Date;
////////////////////////////////////


/**
 * 新增简单页面
 * //Auto gen by simple-dao-codegen 2022-5-23 10:30:00
 */
@Schema(title = "新增简单页面")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = SimplePage.class, alias = E_SimplePage.ALIAS)
public class CreateSimplePageReq extends MultiTenantReq {

    private static final long serialVersionUID = 1598619295L;


    @Schema(title = "类型", required = true)
    @NotBlank
    @Size(max = 64)
    String type;

    @Schema(title = "分类名称", required = true)
    @NotBlank
    @Size(max = 64)
    String category;

    @Schema(title = "分组名称", required = true)
    @NotBlank
    @Size(max = 64)
    String groupName;

    @Schema(title = "访问路径", required = true)
    @NotBlank
    String path;

    @Size(max = 1800)
    @Schema(title = "需要的授权，权限或角色，json数组")
    String requireAuthorizations;

    @Schema(title = "内容")
    String content;

    @Schema(title = "系统子域")
    @Size(max = 128)
    String domain;

    @Schema(title = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(title = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    String pinyinName;

    @Schema(title = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    String creator;

    @Schema(title = "创建时间", hidden = true)
    //@NotNull
    Date createTime;

    @Schema(title = "更新时间", hidden = true)
    Date lastUpdateTime;

    @Schema(title = "排序代码", hidden = true)
    Integer orderCode;

    @Schema(title = "是否允许", hidden = true)
    //@NotNull
    Boolean enable;

    @Schema(title = "是否可编辑", hidden = true)
    //@NotNull
    Boolean editable;

    @Schema(title = "备注", hidden = true)
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
