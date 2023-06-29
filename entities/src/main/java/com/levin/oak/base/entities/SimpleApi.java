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
@Schema(title = "简单动态接口")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = E_TenantOrgNamedEntity.tenantId),
                @Index(columnList = E_TenantOrgNamedEntity.orgId),
                @Index(columnList = E_TenantOrgNamedEntity.domain),

//                @Index(columnList = E_SimpleEntity.type),
                @Index(columnList = E_SimpleEntity.path),
                @Index(columnList = E_SimpleEntity.category),
                @Index(columnList = E_SimpleEntity.groupName),

                @Index(columnList = E_SimpleApi.language),

        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_Setting.code}),
//        }
)
public class SimpleApi extends SimpleEntity {

    public enum Language {
        Groovy,
        Spel,
        JavaScript,
    }

    @Schema(title = "url", description = "默认为公共地址")
    @Column(length = 512)
    String url;

    @Schema(title = "http方法", description = "逗号隔开，默认POST")
    @Column(length = 16)
    String methods;

    @Schema(title = "脚本语言")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    Language language;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }


}
