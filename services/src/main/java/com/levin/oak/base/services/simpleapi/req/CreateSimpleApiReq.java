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

////////////////////////////////////
//自动导入列表
    import com.levin.oak.base.entities.SimpleApi.*;
    import com.levin.commons.service.domain.InjectVar;
    import java.util.Date;
////////////////////////////////////


/**
 *  新增动态API
 *  //Auto gen by simple-dao-codegen 2021-11-15 15:01:48
 */
@Schema(description = "新增动态API")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = SimpleApi.class, alias = E_SimpleApi.ALIAS)
public class CreateSimpleApiReq implements ServiceReq {

    private static final long serialVersionUID = 1021385738L;



    @Schema(description = "子域" )
    private String domain;


    @Schema(description = "分类名称" , required = true)
    @NotNull
    private String category;


    @Schema(description = "分组名称" , required = true)
    @NotNull
    private String groupName;


    @Schema(description = "路径" , required = true)
    @NotNull
    private String path;


    @Schema(description = "http方法" )
    private String methods;


    @Schema(description = "脚本语言" , required = true)
    @NotNull
    private Language language;


    @Schema(description = "处理脚本" )
    private String script;


    @Schema(description = "机构ID" )
    @InjectVar
    private String orgId;


    @Schema(description = "子域" )
    private String domain;


    @Schema(description = "租户ID" )
    @InjectVar
    private String tenantId;


    @Schema(description = "子域，模块或是自系统" )
    private String domain;


    @Schema(description = "名称" , required = true)
    @NotNull
    @Size(max = 512)
    private String name;


    @Schema(description = "创建者" )
    @Size(max = 512)
    private String creator;


    @Schema(description = "创建时间" , required = true)
    @NotNull
    private Date createTime;


    @Schema(description = "更新时间" )
    private Date lastUpdateTime;


    @Schema(description = "排序代码" )
    private Integer orderCode;


    @Schema(description = "是否允许" , required = true)
    @NotNull
    private Boolean enable;


    @Schema(description = "是否可编辑" , required = true)
    @NotNull
    private Boolean editable;


    @Schema(description = "备注" )
    @Size(max = 1800)
    private String remark;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
