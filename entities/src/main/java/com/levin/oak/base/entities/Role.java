package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject; 
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

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
                @Index(columnList = E_TenantOrgNamedEntity.tenantId),
                @Index(columnList = E_Role.name),
                @Index(columnList = E_Role.code),
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
        },

        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_Role.code}),
                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_TenantOrgNamedEntity.name}),
        }
)
public class Role
        extends TenantOrgNamedEntity {

    public enum OrgDataScope implements EnumDesc {
        @Schema(title = "所有部门") All,
        @Schema(title = "指定部门") Assigned,
        @Schema(title = "仅本部门（不含子部门）") MyDept,
        @Schema(title = "本部门及子部门") MyDeptAndChildren,
        @Schema(title = "仅本人数据") MySelf,
    }

    @Id
//    @GeneratedValue
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "编码")
    @Column(nullable = false, length = 128)
    @Contains
    protected String code;

    @Schema(title = "图标")
    protected String icon;

    @Schema(title = "部门数据权限")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    protected OrgDataScope orgDataScope;

    @Schema(title = "指定的部门列表", description = "Json数组")
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
