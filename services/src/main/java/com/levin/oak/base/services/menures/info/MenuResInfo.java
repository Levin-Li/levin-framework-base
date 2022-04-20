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
@Schema(description = "菜单")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"parent", "children",})
@FieldNameConstants
public class MenuResInfo implements Serializable {

    private static final long serialVersionUID = -887712701L;


    @NotNull
    @Schema(description = "id", required = true)
    private Long id;


    @Schema(description = "租户ID")
    private String tenantId;


    @Size(max = 128)
    @Schema(description = "子系统")
    private String domain;


    @Size(max = 1800)
    @Schema(description = "需要的授权，权限或角色，json数组")
    private String requireAuthorizations;


    @NotNull
    @Schema(description = "无权限时是否展示", required = true)
    private Boolean alwaysShow;


    @Size(max = 64)
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


    @Schema(description = "父ID")
    private Long parentId;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "父对象")
    private MenuResInfo parent;


    //@Fetch //默认不加载，请通过查询对象控制
    @Schema(description = "子节点")
    private Set<MenuResInfo> children;


    @Size(max = 1800)
    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称", required = true)
    private String name;


    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(description = "创建者")
    private String creator;


    @NotNull
    @Schema(description = "创建时间", required = true)
    private Date createTime;


    @Schema(description = "更新时间")
    private Date lastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(description = "是否允许", required = true)
    private Boolean enable;


    @NotNull
    @Schema(description = "是否可编辑", required = true)
    private Boolean editable;


    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


}
