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
@Schema(description = "查询角色")
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

    @Schema(description = "是否包含公共数据", hidden = true)
    @Ignore
    private boolean isContainsPublicData = true;

    /**
     * 是否为公共数据
     *
     * @return
     */
    @Override
    public boolean isContainsPublicData() {
        //允许查询公共的角色
        return isContainsPublicData;
    }

    //不允许查询出 SA 角色
    @Schema(description = "无需设置", hidden = true)
    @NotEq(require = true)
    private final String notEqCode = RbacRoleObject.SA_ROLE;

    @Ignore
    @Schema(description = "排序字段")
    private String orderBy;

    //@Ignore
    @Schema(description = "排序方向-desc asc")
    @SimpleOrderBy(expr = "orderBy + ' ' + orderDir", condition = "orderBy != null && orderDir != null", remark = "生成排序表达式")
    private OrderBy.Type orderDir;


    //@NotNull

    @Schema(description = "id")
    private Long id;


    //@NotBlank

    @Schema(description = "编码")
    private String code;

    @Schema(description = "模糊匹配 - 编码")
    @Contains
    private String containsCode;


    @Schema(description = "图标")
    private String icon;


    //@NotNull

    @Schema(description = "部门数据权限")
    private OrgDataScope orgDataScope;


//    @Schema(description = "指定的部门列表")
//    @OR(autoClose = true)
//    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class)
//    @Contains
//    private List<String> assignedOrgIdList;
//
//    @Schema(description = "资源权限列表")
//    @OR(autoClose = true)
//    @InjectVar(domain = "dao", converter = JsonStrLikeConverter.class)
//    @Contains
//    private List<String> permissionList;

    //@Size(max = 64)

    @Schema(description = "系统子域")
    private String domain;


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


    public QueryRoleReq(Long id) {
        this.id = id;
    }

    @PostConstruct
    public void preQuery() {
        //@todo 查询之前初始化数据
    }

}
