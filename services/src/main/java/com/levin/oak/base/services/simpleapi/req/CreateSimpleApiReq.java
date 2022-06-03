package com.levin.oak.base.services.simpleapi.req;

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
import com.levin.oak.base.entities.SimpleApi.*;
import java.util.Date;
////////////////////////////////////


/**
 *  新增简单动态接口
 *  //Auto gen by simple-dao-codegen 2022-5-23 10:30:00
 */
@Schema(description = "新增简单动态接口")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS)
public class CreateSimpleApiReq extends MultiTenantReq {

    private static final long serialVersionUID = 1021385738L;

    @Schema(title = "逗号隔开", description = "http方法"  )
    @Size(max = 16)
    String methods;

    @Schema(description = "脚本语言"  , required = true)
    @NotNull
    Language language;


    @Schema(description = "类型"  , required = true)
    @NotBlank
    @Size(max = 64)
    String type;

    @Schema(description = "分类名称"  , required = true)
    @NotBlank
    @Size(max = 64)
    String category;

    @Schema(description = "分组名称"  , required = true)
    @NotBlank
    @Size(max = 64)
    String groupName;

    @Schema(description = "访问路径"  , required = true)
    @NotBlank
    String path;

    @Size(max = 1800)
    @Schema(description = "需要的授权，权限或角色，json数组")
     String requireAuthorizations;

    @Schema(description = "内容"  )
    String content;

    @Schema(description = "系统子域"  )
    @Size(max = 128)
    String domain;

    @Schema(description = "名称"  , required = true)
    @NotBlank
    @Size(max = 128)
    String name;

    @Schema(description = "拼音，格式：全拼(简拼)"  )
    @Size(max = 128)
    String pinyinName;

    @Schema(description = "创建者" , hidden = true )
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    String creator;

    @Schema(description = "创建时间" , hidden = true )
    //@NotNull
    Date createTime;

    @Schema(description = "更新时间" , hidden = true )
    Date lastUpdateTime;

    @Schema(description = "排序代码" , hidden = true )
    Integer orderCode;

    @Schema(description = "是否允许" , hidden = true )
    //@NotNull
    Boolean enable;

    @Schema(description = "是否可编辑" , hidden = true )
    //@NotNull
    Boolean editable;

    @Schema(description = "备注" , hidden = true )
    //@Size(max = 512)
    String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
