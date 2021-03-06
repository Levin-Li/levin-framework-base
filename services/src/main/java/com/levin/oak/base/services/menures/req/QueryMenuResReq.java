package com.levin.oak.base.services.menures.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.Gte;
import com.levin.commons.dao.annotation.Ignore;
import com.levin.commons.dao.annotation.Lte;
import com.levin.commons.dao.annotation.misc.Fetch;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.commons.rbac.MenuItem.ActionType;
import com.levin.oak.base.entities.E_MenuRes;
import com.levin.oak.base.entities.MenuRes;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.menures.info.MenuResInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import java.util.Date;

////////////////////////////////////
//自动导入列表
////////////////////////////////////

/**
 * 查询菜单
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:37
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
@TargetOption(entityClass = MenuRes.class, alias = E_MenuRes.ALIAS, resultClass = MenuResInfo.class)
public class QueryMenuResReq extends MultiTenantReq {

    private static final long serialVersionUID = -887712701L;

    @Schema(description = "是否包含公共数据", hidden = true)
    @Ignore
    private boolean isContainsPublicData = true;

    @Ignore
    @Schema(description = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull

    @Schema(description = "id")
    private String id;


    //@Size(max = 128)

    @Schema(description = "子系统")
    private String domain;


    //@Size(max = 1800)

    @Schema(description = "需要的授权，权限或角色，json数组")
    private String requireAuthorizations;


    //@NotNull

    @Schema(description = "无权限时是否展示")
    private Boolean alwaysShow;


    //@Size(max = 64)

    @Schema(description = "目标")
    private String target;


    @Schema(description = "打开方式")
    private ActionType actionType;


    @Schema(description = "图标")
    private String icon;


    @Schema(description = "路径/链接")
    private String path;

    @Schema(description = "模糊匹配 - 路径/链接")
    @Contains
    private String containsPath;


    //@Size(max = 1800)

    @Schema(description = "参数")
    private String params;


    @Schema(description = "父ID")
    private String parentId;


    @Schema(description = "是否加载父对象")
    @Fetch(attrs = E_MenuRes.parent, condition = "#_val == true")
    private Boolean loadParent;


    @Schema(description = "是否加载子节点")
    @Fetch(attrs = E_MenuRes.children, condition = "#_val == true")
    private Boolean loadChildren;


    //@Size(max = 1800)

    @Schema(description = "id路径， 使用|包围，如|1|3|15|")
    private String idPath;


    //@NotBlank
    //@Size(max = 128)

    @Schema(description = "名称")
    private String name;

    @Schema(description = "模糊匹配 - 名称")
    @Contains
    private String containsName;


    //@Size(max = 128)

    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(description = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    private String containsPinyinName;


    //@Size(max = 128)

    @Schema(description = "创建者")
    private String creator;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(description = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(description = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(description = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;

    //@NotNull
    @Schema(description = "是否允许")
    private Boolean enable;


    //@NotNull

    @Schema(description = "是否可编辑")
    private Boolean editable;


    //@Size(max = 512)

    @Schema(description = "备注")
    private String remark;


    public QueryMenuResReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
