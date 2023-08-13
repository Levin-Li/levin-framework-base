package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.E_AbstractMultiTenantObject;
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
@Schema(title = "国际化资源")

@Table(
        indexes = {
                @Index(columnList = E_I18nRes.orderCode),
                @Index(columnList = E_I18nRes.enable),
                @Index(columnList = E_I18nRes.createTime),
                @Index(columnList = E_I18nRes.creator),
                @Index(columnList = E_I18nRes.name),
                @Index(columnList = E_I18nRes.tenantId),
                @Index(columnList = E_I18nRes.category),
                @Index(columnList = E_I18nRes.lang),
                @Index(columnList = E_I18nRes.label),

                @Index(columnList = E_TenantOrgNamedEntity.tenantId + "," + E_TenantOrgNamedEntity.orgId),

                @Index(columnList = E_I18nRes.tenantId + "," + E_I18nRes.orgId + "," + E_I18nRes.name),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_I18nRes.tenantId, E_I18nRes.orgId, E_I18nRes.name}),
//                @UniqueConstraint(columnNames = {E_AbstractMultiTenantObject.tenantId, E_I18nRes.label}),
        }
)
public class I18nRes
        extends TenantOrgNamedEntity {

    private static final long serialVersionUID = -123456789L;

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(title = "分类")
    @Column(nullable = false, length = 128)
    String category;

    @Schema(title = "语言")
    @Column(nullable = false, length = 64)
    String lang;

    @Schema(title = "标签")
    @Column(nullable = false, length = 768)
    @Contains
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
