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
import java.util.Date;
////////////////////////////////////


/**
 *  新增简单页面
 *  //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(description = "新增简单页面")
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


    @Schema(description = "分类名称"  , required = true)
    @NotBlank
    @Size(max = 64)
    private String category;

    @Schema(description = "分组名称"  , required = true)
    @NotBlank
    @Size(max = 64)
    private String groupName;

    @Schema(description = "访问路径"  , required = true)
    @NotBlank
    private String path;

    @Schema(description = "内容"  )
    private String content;

    @Schema(description = "系统子域"  )
    @Size(max = 64)
    private String domain;

    @Schema(description = "名称"  , required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(description = "拼音，格式：全拼(简拼)"  )
    @Size(max = 128)
    private String pinyinName;

    @Schema(description = "创建者" , hidden = true )
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(description = "创建时间" , hidden = true )
    //@NotNull
    private Date createTime;

    @Schema(description = "更新时间" , hidden = true )
    private Date lastUpdateTime;

    @Schema(description = "排序代码" , hidden = true )
    private Integer orderCode;

    @Schema(description = "是否允许" , hidden = true )
    //@NotNull
    private Boolean enable;

    @Schema(description = "是否可编辑" , hidden = true )
    //@NotNull
    private Boolean editable;

    @Schema(description = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
