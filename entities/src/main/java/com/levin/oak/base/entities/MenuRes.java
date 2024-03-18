package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.EndsWith;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import com.levin.commons.dao.domain.support.AbstractTreeObject;
import com.levin.commons.rbac.MenuItem;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
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
@Schema(title = "菜单")
@Table(
        indexes = {
                @Index(columnList = E_MenuRes.orderCode),
                @Index(columnList = E_MenuRes.enable),
                @Index(columnList = E_MenuRes.createTime),
                @Index(columnList = E_MenuRes.creator),
                @Index(columnList = E_MenuRes.name),
                @Index(columnList = E_MenuRes.parentId),
                @Index(columnList = E_MenuRes.domain),
                @Index(columnList = E_MenuRes.actionType),
                @Index(columnList = E_MenuRes.tenantId),

                @Index(columnList = E_MenuRes.tenantId + "," + E_MenuRes.parentId),
        }
//        ,
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {AbstractMultiTenantObject.Fields.tenantId, Setting.Fields.code}),
//        }
)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class MenuRes
        extends AbstractTreeObject<String, MenuRes>
        implements MenuItem<MenuRes, MenuRes>, MultiTenantPublicObject {

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(title = "父ID")
    @Column(length = 64)
    String parentId;

    @Schema(title = "租户ID")
    @Column(length = 64)
    @InjectVar(InjectConst.TENANT_ID)
    String tenantId;

    @Schema(title = "域名", description = "访问的域名")
    @EndsWith
    String domain;

    @Schema(title = "模块ID", description = "归属的子系统")
    @Column(length = 128)
    String moduleId;

    @Schema(title = "需要的授权，权限或角色，json数组")
    @Column(length = 1800)
//    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String requireAuthorizations;

    @Schema(title = "无权限时是否展示")
    @Column(nullable = false)
    Boolean alwaysShow;

    @Schema(title = "目标")
    @Column(length = 64)
    String target;

    @Schema(title = "打开方式")
    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    MenuItem.ActionType actionType;

    @Schema(title = "图标")
    String icon;

    @Schema(title = "路径/链接")
    @Contains
    String path;

    @Schema(title = "参数")
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

