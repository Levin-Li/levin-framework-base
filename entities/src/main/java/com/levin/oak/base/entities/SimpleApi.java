package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "SimpleApi")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "简单动态接口")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = MultiTenantNamedEntity.Fields.domain),
                @Index(columnList = MultiTenantAndOrganizedEntity.Fields.orgId),
                @Index(columnList = SimpleEntity.Fields.path),
                @Index(columnList = SimpleEntity.Fields.category),
                @Index(columnList = SimpleEntity.Fields.groupName),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Setting.code}),
//        }
)
public class SimpleApi extends SimpleEntity {

    public enum Language {
        Groovy,
        Spel,
        JavaScript,
    }

    @Schema(description = "http方法", title = "逗号隔开")
    @Column(length = 16)
    protected String methods;

    @Schema(description = "脚本语言")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Language language;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }


}
