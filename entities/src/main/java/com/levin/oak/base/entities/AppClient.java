package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = EntityConst.PREFIX + "AppClient")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "应用接入")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = MultiTenantNamedEntity.Fields.domain),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Setting.code}),
//        }
)
public class AppClient extends MultiTenantNamedEntity {

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "应用ID")
    @Column(unique = true, nullable = false, length = 128)
    String appId;

    @Schema(description = "应用密钥")
    @Column(nullable = false, length = 128)
    String appSecret;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (appId == null) {
            appId = UUID.randomUUID().toString();
        }

        if (appSecret == null) {
            appSecret = UUID.randomUUID().toString();
        }

    }

}
