package com.levin.oak.base.services.area.req;

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
    import com.levin.oak.base.entities.Area;
    import com.levin.oak.base.services.area.info.*;
    import java.util.Set;
    import com.levin.oak.base.entities.Area.*;
    import java.util.Date;
////////////////////////////////////


/**
 *  新增区域
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "新增区域")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Area.class, alias = E_Area.ALIAS)
public class CreateAreaReq implements ServiceReq {

    private static final long serialVersionUID = -445860277L;



    @Schema(description = "图标")
    private String icon;


    @Schema(description = "父区域ID")
    private String parentCode;




    @Schema(description = "类型")
    @NotNull
    private Type type;


    @Schema(description = "名称")
    @NotNull
    @Size(max = 512)
    private String name;


    @Schema(description = "创建者")
    @Size(max = 512)
    private String creator;


    @Schema(description = "创建时间")
    @NotNull
    private Date createTime;


    @Schema(description = "更新时间")
    private Date lastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    @NotNull
    private Boolean enable;


    @Schema(description = "是否可编辑")
    @NotNull
    private Boolean editable;


    @Schema(description = "备注")
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