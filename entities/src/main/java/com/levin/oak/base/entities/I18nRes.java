package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.EndsWith;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import com.levin.commons.dao.domain.MultiTenantSharedObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.OrganizedPublicObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantOrgObject;
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

@Table(indexes = {
        @Index(columnList = E_I18nRes.orderCode),
        @Index(columnList = E_I18nRes.enable),
        @Index(columnList = E_I18nRes.createTime),
        @Index(columnList = E_I18nRes.creator),
        @Index(columnList = E_I18nRes.name),
        @Index(columnList = E_I18nRes.tenantId),
        @Index(columnList = E_I18nRes.category),
        @Index(columnList = E_I18nRes.lang),
        @Index(columnList = E_I18nRes.country),

        @Index(columnList = E_I18nRes.tenantId + "," + E_I18nRes.name),
},
        uniqueConstraints = {@UniqueConstraint(columnNames = {E_I18nRes.tenantId, E_I18nRes.name})}

)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class I18nRes extends AbstractMultiTenantObject implements MultiTenantPublicObject, MultiTenantSharedObject, NamedObject {

    private static final long serialVersionUID = -123456789L;

    @Id
    @GeneratedValue
    Long id;

    @Schema(title = "是否可在租户之间共享")
    @Column(nullable = false)
    boolean tenantShared;

    @Schema(title = "域名")
    @EndsWith
    String domain;

    @Schema(title = "名称", description = "")
    @Column(length = 512, nullable = false)
    String name;

    @Schema(title = "分类", description = "")
    @Column(length = 128)
    String category;

    @Schema(title = "语言", description = "ISO 639：https://datahub.io/core/language-codes")
    @Column(nullable = false, length = 64)
    String lang;

    @Schema(title = "国家或地区", description = "ISO 3166：https://datahub.io/core/country-list")
    @Column(nullable = false, length = 64)
    String country;

    @Schema(title = "标签")
    @Column(nullable = false, length = 1800)
    @Contains
    String label;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (lang == null) {
            lang = "zh";
        }

        if (country == null) {
            country = "CN";
        }
    }

//    /**
//     * 是否可共享
//     *
//     * @return
//     */
//    @Override
//    public boolean isTenantShared() {
//        return Boolean.TRUE.equals(tenantShared);
//    }

}
