package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "SimplePage")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "简单页面")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = AbstractNamedMultiTenantObject.Fields.tenantId),
                @Index(columnList = AbstractNamedMultiTenantObject.Fields.domain),
                @Index(columnList = TenantOrganizedEntity.Fields.orgId),
                @Index(columnList = E_SimplePage.type),
                @Index(columnList = SimpleEntity.Fields.path),
                @Index(columnList = SimpleEntity.Fields.category),
                @Index(columnList = SimpleEntity.Fields.groupName),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {AbstractNamedMultiTenantObject.Fields.tenantId, E_Setting.code}),
//        }
)
public class SimplePage extends SimpleEntity {

    @Schema(title = "页面类型")
    public enum Type implements EnumDesc {
        @Schema(title = "Json") json,
        @Schema(title = "Jsonp") jsonp,
        @Schema(title = "Html") html,
        @Schema(title = "Java") java,
        @Schema(title = "JavaScript") js,
        @Schema(title = "Groovy") groovy,
        @Schema(title = "Freemark") ftlh,
    }

//    @Schema(title = "类型")
//    @Column(nullable = false, length = 128)
//    @Enumerated(EnumType.STRING)
//    protected Type type;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
