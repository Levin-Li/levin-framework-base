package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractTreeObject;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * RBAC2（角色限制模型）
 * <p>
 * a、互斥角色：互斥关系角色指的是在某些情况下，两个或多个角色是互斥的，即它们不能同时分配给同一个用户。这是一种静态的限制，通常在角色分配的静态配置阶段就确定。
 * <p>
 * b、基数约束：基数约束涉及到限制用户能够拥有的角色数量，或者某个角色在系统中的实例数。这种约束可以是静态的，也可以是动态的，取决于在哪个阶段进行约束的检查。
 * <p>
 * c、先决条件约束：先决条件角色是指在分配某个角色之前，用户必须先拥有另一个或一组先决条件角色。这种机制可以用于确保用户在获得某个高级别角色之前必须具备一定的背景或权限。
 */
@Entity(name = EntityConst.PREFIX + "Role")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "角色")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = E_Role.tenantId),
                @Index(columnList = E_Role.name),
                @Index(columnList = E_Role.code),

                //优化性能
                @Index(columnList = E_Role.tenantId),
                @Index(columnList = E_Role.tenantId + "," + E_Role.id),
                @Index(columnList = E_Role.tenantId + "," + E_Role.code),
        },

        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_Role.tenantId, E_Role.code}),
                @UniqueConstraint(columnNames = {E_Role.tenantId, E_Role.name}),
        }
)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class Role
        extends AbstractTreeObject<String, Role>
        implements MultiTenantObject, MultiTenantPublicObject {

    @Schema(title = "组织权限")
    public enum OrgDataScope implements EnumDesc {
        @Schema(title = "继承父角色") Inherited,

        @Schema(title = "所有部门") All,
        @Schema(title = "指定部门") Assigned,
        @Schema(title = "本部门及子部门") MyOrgAndChildren,
        @Schema(title = "仅本部门(不含子部门)") OnlyMyOrg,
        @Schema(title = "仅子部门(不含本部门)") OnlyChildren,
        @Schema(title = "仅本人") MySelf;

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "系统域", hidden = true, description = "归属的子系统或应用")
    @Column(length = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    protected String domain;

    @Schema(title = "租户ID")
    @Column(length = 128)
    @InjectVar(InjectConst.TENANT_ID)
    protected String tenantId;

    @Schema(title = "父节点")
    @Column(length = 128)
    protected String parentId;

    @Schema(title = "是否可继承")
    @Column
    protected Boolean extendable;

    @Schema(title = "是否互斥")
    @Column
    protected Boolean mutex;

    @Schema(title = "用户数限制")
    protected Integer userLimit;

    @Schema(title = "先决条件表达式", description = "支持括号优先级、&&和||操作，如：(R_SA || R_ADMIN) && R_STAFF")
    @Column(length = 1800)
    protected String precondition;

    @Schema(title = "编码")
    @Column(nullable = false, length = 128)
    @Contains
    protected String code;

    @Schema(title = "过期时间")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date expiredDate;

    @Schema(title = "图标")
    protected String icon;

    @Schema(title = "组织数据权限", description = "参考组织ID列表")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    protected OrgDataScope orgDataScope;

    @Schema(title = "组织ID列表", description = "指定的组织ID列表，Json数组")
    @Lob
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    protected String assignedOrgIdList;

    @Schema(title = "资源权限列表", description = "Json数组")
    @Lob
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    protected String permissionList;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (orgDataScope == null) {
            orgDataScope = OrgDataScope.MySelf;
        }

    }

}
