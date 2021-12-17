package com.levin.oak.base.services.menures.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.commons.service.domain.*;
import com.levin.commons.dao.support.*;

import javax.validation.constraints.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.services.menures.info.*;
import com.levin.oak.base.entities.MenuRes;

import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
    import com.levin.commons.service.domain.InjectVar;
    import com.levin.commons.rbac.MenuItem.*;
    import com.levin.oak.base.entities.MenuRes;
    import com.levin.oak.base.services.menures.info.*;
    import java.util.Set;
    import java.util.Date;
////////////////////////////////////

/**
 *  查询菜单
 *  @Author Auto gen by simple-dao-codegen 2021-12-17 11:53:24
 */
@Schema(description = "查询菜单")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS
, resultClass = MenuResInfo.class)
public class QueryMenuResReq extends MultiTenantReq{

    private static final long serialVersionUID = -887712701L;


    @Schema(description = "id")
    private Long id;


    @Schema(description = "租户ID")
    private Long tenantId;


    @Schema(description = "子域")
    private String domain;


    @Schema(description = "需要的授权，权限或角色用逗号隔开")
    private String requireAuthorizations;


    @Schema(description = "无权限时是否展示")
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
    private String params;


    @Schema(description = "父ID")
    private Long parentId;


    @Schema(description = "是否加载父对象")
    @Fetch(attrs = E_MenuRes.parent, condition = "#_val == true")
    private Boolean loadParent;


    @Schema(description = "是否加载子节点")
    @Fetch(attrs = E_MenuRes.children, condition = "#_val == true")
    private Boolean loadChildren;


    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    @Schema(description = "名称")
    private String name;


    @Schema(description = "创建者")
    private String creator;


    @Schema(description = "大于等于创建时间")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间")
    @Lte
    private Date lteCreateTime;



    @Schema(description = "大于等于更新时间")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间")
    @Lte
    private Date lteLastUpdateTime;



    @Schema(description = "排序代码")
    private Integer orderCode;


    @Schema(description = "是否允许")
    private Boolean enable;


    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Schema(description = "备注")
    private String remark;


    public QueryMenuResReq(Long id) {
        this.id = id;
    }
}
