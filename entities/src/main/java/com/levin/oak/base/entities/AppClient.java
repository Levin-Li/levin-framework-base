package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.support.InjectConsts;
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
@Schema(title = "应用接入")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = E_TenantOrgNamedEntity.tenantId),
                @Index(columnList = E_TenantOrgNamedEntity.domain),

                //复合索引
                @Index(columnList = E_TenantOrgNamedEntity.tenantId + "," + E_AppClient.id),
                @Index(columnList = E_TenantOrgNamedEntity.tenantId + "," + E_TenantOrgNamedEntity.orgId),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_AppClient.appId}),
        }
)
public class AppClient extends TenantOrgNamedEntity {

    @Id
//    @GeneratedValue
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "应用ID")
    @Column(unique = true, nullable = false, length = 64)
    protected String appId;

    @Schema(title = "应用密钥")
    @Column(nullable = false, length = 512)
    protected String appSecret;

    @Schema(title = "应用Token")
    @Column(length = 512)
    protected String appToken;

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
