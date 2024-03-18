package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.StartsWith;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Set;

@Entity(name = EntityConst.PREFIX + "Area")
@Cacheable
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "区域")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = E_Area.id),
                @Index(columnList = E_Area.parentId),
                @Index(columnList = E_Area.type),
        },
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_Area.code})
        }
)
@EntityCategory(EntityOpConst.PLATFORM_TYPE_NAME)

public class Area
        extends AbstractNamedEntityObject
        implements
        TreeObject<Area, Area> {

    public enum Type implements EnumDesc {
        @Schema(title = "国家")
        Nation,
        @Schema(title = "省份/直辖市")
        Province,
        @Schema(title = "地市")
        City,
        @Schema(title = "区县")
        County,
        @Schema(title = "乡镇")
        Township,
        @Schema(title = "村庄")
        Village,
        ;

        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Schema(title = "编码")
    @Id
    @Column(length = 64)
    @StartsWith
    protected String id;

    @Schema(title = "自定义编码", description = "自定义的映射编码，通常不使用")
    protected String code;

    @Schema(title = "图标")
    protected String icon;

    @Schema(title = "父区域ID")
    @Column(length = 64)
    protected String parentId;

    @Schema(title = "父区域")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId", insertable = false, updatable = false)
    protected Area parent;

    @Schema(title = "子区域")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @OrderBy("orderCode ASC , id ASC")
    protected Set<Area> children;

    @Schema(title = "类型")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    protected Type type;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
