package com.levin.oak.base.services.role.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.order.OrderBy;
import com.levin.commons.dao.annotation.order.SimpleOrderBy;
import com.levin.commons.rbac.RbacRoleObject;
import com.levin.oak.base.entities.E_Role;
import com.levin.oak.base.entities.Role;
import com.levin.oak.base.entities.Role.OrgDataScope;
import com.levin.oak.base.services.commons.req.MultiTenantReq;
import com.levin.oak.base.services.role.info.RoleInfo;
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
 * 查询角色
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 17:01:35
 */
@Schema(title = "查询角色")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Role.class, alias = E_Role.ALIAS, resultClass = RoleInfo.class)
public class QueryRoleReq extends MultiTenantReq {

    private static final long serialVersionUID = -445356492L;


    //不允许查询出 SA 角色
    @Schema(title = "无需设置", hidden = true)
    @NotEq(require = true)
    private final String notEqCode = RbacRoleObject.SA_ROLE;

    @Ignore
    @Schema(title = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(title = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull

    @Schema(title = "id")
    private String id;


    //@NotBlank

    @Schema(title = "编码")
    private String code;

    @Schema(title = "模糊匹配 - 编码")
    @Contains
    private String containsCode;


    @Schema(title = "图标")
    private String icon;


    //@NotNull

    @Schema(title = "部门数据权限")
    private OrgDataScope orgDataScope;


//    @Schema(title = "指定的部门列表")
//    @OR(autoClose = true)
//    @InjectVar(domain = "dao", expectBaseType = String.class, converter = JsonStrLikeConverter.class)
//    @Contains
//    private List<String> assignedOrgIdList;
//
//    @Schema(title = "资源权限列表")
//    @OR(autoClose = true)
//    @InjectVar(domain = "dao", expectBaseType = String.class, converter = JsonStrLikeConverter.class)
//    @Contains
//    private List<String> permissionList;

    //@Size(max = 64)

    @Schema(title = "系统子域")
    private String domain;


    //@NotBlank
    //@Size(max = 128)

    @Schema(title = "名称")
    private String name;

    @Schema(title = "模糊匹配 - 名称")
    @Contains
    private String containsName;


    //@Size(max = 128)

    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;

    @Schema(title = "模糊匹配 - 拼音，格式：全拼(简拼)")
    @Contains
    private String containsPinyinName;


    //@Size(max = 128)

    @Schema(title = "创建者")
    private String creator;


    //@NotNull

    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteCreateTime;

    @Schema(title = "小于等于创建时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteCreateTime;


    // @DateTimeFormat(iso = ISO.DATE_TIME) // Spring mvc 默认的时间格式：yyyy/MM/dd HH:mm:ss
    @Schema(title = "大于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Gte
    private Date gteLastUpdateTime;

    @Schema(title = "小于等于更新时间，默认的时间格式：yyyy/MM/dd HH:mm:ss")
    @Lte
    private Date lteLastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    //@NotNull

    @Schema(title = "是否允许")
    private Boolean enable;


    //@NotNull

    @Schema(title = "是否可编辑")
    private Boolean editable;


    //@Size(max = 512)

    @Schema(title = "备注")
    private String remark;


    public QueryRoleReq(String id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
