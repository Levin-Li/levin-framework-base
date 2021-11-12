package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractTreeObject;
import com.levin.commons.rbac.MenuItem;
import com.levin.commons.service.domain.InjectVar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "menu_res")
@Data
@Accessors(chain = true)

@FieldNameConstants
@Schema(description = "菜单")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = AbstractTreeObject.Fields.parentId),
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
        extends AbstractTreeObject<Long, MenuRes>
        implements MenuItem<MenuRes, MenuRes>, MultiTenantObject {

    @Id
    @GeneratedValue
    Long id;

    @Schema(description = "租户ID")
    @InjectVar
    Long tenantId;

    @Schema(description = "子域")
    String domain;

    @Schema(description = "需要的授权，权限或角色用逗号隔开")
    @Column(length = 1800)
    String requireAuthorizations;

    @Schema(description = "无权限时是否展示")
    @Column(nullable = false)
    Boolean alwaysShow;

    @Schema(description = "目标")
    String target;

    @Schema(description = "打开方式")
    @Enumerated(EnumType.STRING)
    MenuItem.ActionType actionType;

    @Schema(description = "图标")
    String icon;

    @Schema(description = "路径/链接")
    String path;

    @Schema(description = "参数")
    @Column(length = 1800)
    String params;


    @Override
    public boolean isAlwaysShow() {
        return Boolean.TRUE.equals(alwaysShow);
    }

}

