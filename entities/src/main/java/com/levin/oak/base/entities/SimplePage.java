package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.support.InjectConst;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
                @Index(columnList = InjectConst.TENANT_ID),
                @Index(columnList = InjectConst.ORG_ID),
                @Index(columnList = E_TenantOrgNamedEntity.domain),

                @Index(columnList = E_SimplePage.type),
                @Index(columnList = SimpleEntity.Fields.path),
                @Index(columnList = SimpleEntity.Fields.category),
                @Index(columnList = SimpleEntity.Fields.groupName),

                @Index(columnList = E_SimpleEntity.tenantId + "," + E_SimpleEntity.orgId),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_Setting.code}),
//        }
)
@EntityCategory(EntityOpConst.PLATFORM_TYPE_NAME)
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

        ;
        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
