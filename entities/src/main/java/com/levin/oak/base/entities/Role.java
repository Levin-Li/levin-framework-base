package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.rbac.ResPermission;
import com.levin.commons.rbac.RoleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@Entity(name = EntityConst.PREFIX + "role")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "角色")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = E_MultiTenantNamedEntity.name),
                @Index(columnList = E_Role.code),
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Role.code}),
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_MultiTenantNamedEntity.name}),
//        }
)
public class Role
        extends MultiTenantNamedEntity
        implements RoleObject {

    public enum OrgDataScope {
        @Schema(description = "所有部门") All,
        @Schema(description = "指定部门") Assigned,
        @Schema(description = "仅本部门（不含子部门）") MyDept,
        @Schema(description = "本部门及子部门") MyDeptAndChildren,
        @Schema(description = "仅本人数据") MySelf,
    }

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "编码")
    @Column(nullable = false)
    protected String code;

    @Schema(description = "图标")
    protected String icon;

    @Schema(description = "部门数据权限")
    @Column(nullable = false)
    protected OrgDataScope orgDataScope;

    @Schema(description = "指定的部门列表", title = "半角逗号隔开")
    @Lob
    protected String assignedOrgIdList;

    @Schema(description = "资源权限", title = "JSON List 存储")
    @Lob
    protected String permissions;

    @Transient
    @Schema(description = "资源权限列表")
    protected List<ResPermission> permissionList;

    @Override
    public void prePersist() {

        super.prePersist();

        if (orgDataScope == null) {
            orgDataScope = OrgDataScope.MySelf;
        }
    }

}
