package com.levin.oak.base.services.menures.req;

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

import com.levin.oak.base.entities.MenuRes;
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
 *  更新菜单
 *  Auto gen by simple-dao-codegen 2021-10-28 16:17:42
 */
@Schema(description = "更新菜单")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)
//默认更新注解
@Update
public class UpdateMenuResReq implements ServiceReq {

    private static final long serialVersionUID = -887712701L;

    @Schema(description = "id")
    @NotNull
    @Eq(require = true)
    private Long id;

//    @Schema(description = "租户ID")
//    private Long tenantId;

    @Schema(description = "子域")
    private String domain;

    @Size(max = 1800)
    @Schema(description = "需要的授权")
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

    @Size(max = 1800)
    @Schema(description = "参数")
    private String params;

    @Schema(description = "操作列表")
    private String operationList;

    @Schema(description = "父ID")
    private Long parentId;

    @Size(max = 1800)
    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;

    @Size(max = 512)
    @Schema(description = "名称")
    private String name;

    @Size(max = 512)
    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    @Size(max = 1800)
    @Schema(description = "备注")
    private String remark;


    public UpdateMenuResReq(Long id) {
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