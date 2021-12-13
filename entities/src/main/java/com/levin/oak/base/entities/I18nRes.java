package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "I18nRes")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "国际化资源")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = E_I18nRes.category),
                @Index(columnList = E_I18nRes.lang),
        }
//        ,
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Dict.code}),
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_MultiTenantNamedEntity.name}),
//        }
)
public class I18nRes
        extends MultiTenantNamedEntity {

    private static final long serialVersionUID = -123456789L;

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "分类")
    @Column(nullable = false)
    String category;

    @Schema(description = "语言")
    @Column(nullable = false, length = 32)
    String lang;

    @Schema(description = "标签")
    @Column(nullable = false, length = 768)
    String label;


    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (lang == null) {
            lang = "zh_CN";
        }

    }
}
