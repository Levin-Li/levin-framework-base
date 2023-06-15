package com.levin.oak.base.services.menures.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.rbac.MenuItem.ActionType;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import com.levin.oak.base.entities.E_MenuRes;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增菜单
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增菜单")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS)
public class CreateMenuResReq extends MultiTenantReq {

    private static final long serialVersionUID = -887712701L;


    @Schema(title = "子系统")
    @Size(max = 128)
    private String domain;

    @Schema(title = "需要的授权，权限或角色，json数组")
    @Size(max = 1800)
    private String requireAuthorizations;

    @Schema(title = "无权限时是否展示", required = true)
    @NotNull
    private Boolean alwaysShow;

    @Schema(title = "目标")
    @Size(max = 64)
    private String target;

    @Schema(title = "打开方式")
    private ActionType actionType;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "路径/链接")
    private String path;

    @Schema(title = "参数")
    @Size(max = 1800)
    private String params;

    @Schema(title = "父ID")
    private String parentId;


    @Schema(title = "id路径， 使用|包围，如|1|3|15|")
    @Size(max = 1800)
    private String idPath;

    @Schema(title = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(title = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    private String pinyinName;

    @Schema(title = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(title = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(title = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(title = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(title = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(title = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(title = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
