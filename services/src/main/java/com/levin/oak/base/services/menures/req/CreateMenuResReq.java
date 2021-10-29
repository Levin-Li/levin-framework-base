package com.levin.oak.base.services.menures.req;

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
    import com.levin.commons.plugin.MenuItem.*;
    import com.levin.oak.base.entities.ResOperation;
    import java.util.List;
    import com.levin.oak.base.entities.MenuRes;
    import com.levin.oak.base.services.menures.info.*;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////


/**
 *  新增菜单
 *  //Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "新增菜单")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)
public class CreateMenuResReq implements ServiceReq {

    private static final long serialVersionUID = -887712701L;



    @Schema(description = "租户ID")
    private Long tenantId;


    @Schema(description = "子域")
    private String domain;


    @Schema(description = "需要的授权")
    @Size(max = 1800)
    private String requireAuthorizations;


    @Schema(description = "无权限时是否展示")
    @NotNull
    private Boolean alwaysShow;


    @Schema(description = "目标")
    private String target;


    @Schema(description = "打开方式")
    private ActionType actionType;


    @Schema(description = "图标")
    private String icon;


    @Schema(description = "路径/链接")
    private String path;


    @Schema(description = "参数")
    @Size(max = 1800)
    private String params;


    @Schema(description = "操作列表")
    private String operationList;



    @Schema(description = "父ID")
    private Long parentId;




    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    @Size(max = 1800)
    private String idPath;


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