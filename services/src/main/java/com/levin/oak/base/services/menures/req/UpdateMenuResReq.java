package com.levin.oak.base.services.menures.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Eq;
import com.levin.commons.dao.annotation.update.Update;
import com.levin.commons.service.support.*;
import com.levin.commons.rbac.MenuItem.ActionType;
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

////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 更新菜单
 * Auto gen by simple-dao-codegen 2022-3-25 17:01:37
 */
@Schema(title = "更新菜单")
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
public class UpdateMenuResReq extends MultiTenantReq {

    private static final long serialVersionUID = -887712701L;

    @Schema(title = "id", required = true)
    @NotNull
    @Eq(require = true)
    private String id;

    @Schema(title = "可编辑条件", hidden = true)
    @Eq(condition = "!#" + InjectConsts.IS_SUPER_ADMIN)
    final boolean eqEditable = true;

    @Size(max = 128)
    @Schema(title = "子系统")
    private String domain;

    @Size(max = 1800)
    @Schema(title = "需要的授权，权限或角色，json数组")
    private String requireAuthorizations;

    @Schema(title = "无权限时是否展示")
    private Boolean alwaysShow;

    @Size(max = 64)
    @Schema(title = "目标")
    private String target;

    @Schema(title = "打开方式")
    private ActionType actionType;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "路径/链接")
    private String path;

    @Size(max = 1800)
    @Schema(title = "参数")
    private String params;

    @Schema(title = "父ID")
    private String parentId;

    @Size(max = 1800)
    @Schema(title = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;

    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称")
    private String name;

    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(title = "更新时间")
    private Date lastUpdateTime;

    @Schema(title = "排序代码")
    private Integer orderCode;

    @Schema(title = "是否允许")
    private Boolean enable;

    @Schema(title = "是否可编辑")
    private Boolean editable;

    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;


    public UpdateMenuResReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if (getLastUpdateTime() == null) {
            setLastUpdateTime(new Date());
        }
    }

}
