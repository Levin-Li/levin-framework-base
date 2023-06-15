package com.levin.oak.base.services.menures.info;


import com.levin.commons.rbac.MenuItem.ActionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 菜单
 *
 * @Author Auto gen by simple-dao-codegen 2022-4-1 17:40:27
 */
@Schema(title = "菜单")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"parent", "children",})
@FieldNameConstants
public class MenuResInfo implements Serializable {

    private static final long serialVersionUID = -887712701L;

    @NotNull
    @Schema(title = "id", required = true)
    private String id;


    @Schema(title = "租户ID")
    private String tenantId;


    @Size(max = 128)
    @Schema(title = "子系统")
    private String domain;


    @Size(max = 1800)
    @Schema(title = "需要的授权，权限或角色，json数组")
    private String requireAuthorizations;


    @NotNull
    @Schema(title = "无权限时是否展示", required = true)
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


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = "父对象")
    private MenuResInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(title = "子节点")
    private Set<MenuResInfo> children;


    @Size(max = 1800)
    @Schema(title = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称", required = true)
    private String name;


    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(title = "创建者")
    private String creator;


    @NotNull
    @Schema(title = "创建时间", required = true)
    private Date createTime;


    @Schema(title = "更新时间")
    private Date lastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(title = "是否允许", required = true)
    private Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑", required = true)
    private Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;


}
