package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractTreeObject;
import com.levin.commons.rbac.MenuItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "MenuRes")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = {"id"})
@FieldNameConstants
@Schema(description = "菜单")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = E_MenuRes.parentId),
                @Index(columnList = E_MenuRes.domain),
                @Index(columnList = E_MenuRes.actionType),
                @Index(columnList = E_MenuRes.tenantId),
        }
//        ,
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {AbstractMultiTenantObject.Fields.tenantId, Setting.Fields.code}),
//        }
)
public class MenuRes
        extends AbstractTreeObject<String, MenuRes>
        implements MenuItem<MenuRes, MenuRes>, MultiTenantObject {

    @Id
    @GeneratedValue(generator = "default_uuid")
    @Column(length = 128)
    protected String id;

    @Schema(description = "父ID")
    @Column(length = 128)
    String parentId;

    @Schema(description = "租户ID")
    @Column(length = 128)
    String tenantId;

    @Schema(description = "子系统")
    @Column(length = 128)
    String domain;

    @Schema(description = "需要的授权，权限或角色，json数组")
    @Column(length = 1800)
//    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String requireAuthorizations;

    @Schema(description = "无权限时是否展示")
    @Column(nullable = false)
    Boolean alwaysShow;

    @Schema(description = "目标")
    @Column(length = 64)
    String target;

    @Schema(description = "打开方式")
    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    MenuItem.ActionType actionType;

    @Schema(description = "图标")
    String icon;

    @Schema(description = "路径/链接")
    @Contains
    String path;

    @Schema(description = "参数")
    @Column(length = 1800)
    String params;


    @Override
    public boolean isAlwaysShow() {
        return Boolean.TRUE.equals(alwaysShow);
    }


    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (alwaysShow == null) {
            alwaysShow = false;
        }
    }
}

