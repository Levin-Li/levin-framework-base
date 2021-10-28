package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Set;

@Entity(name = TableOption.PREFIX + "area")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "区域")
@Table(indexes = {
        @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
        @Index(columnList = AbstractNamedEntityObject.Fields.name),
        @Index(columnList = E_Area.code),
        @Index(columnList = E_Area.parentCode),
        @Index(columnList = E_Area.type),
})
public class Area
        extends AbstractNamedEntityObject<String>
        implements
        TreeObject<String, Area> {

    public enum Type {
        @Schema(description = "国家")
        Nation,
        @Schema(description = "省份、直辖市")
        Province,
        @Schema(description = "地市")
        City,
        @Schema(description = "区县")
        County,
        @Schema(description = "乡镇")
        Township,
        @Schema(description = "村庄")
        Village,
    }

    @Schema(description = "编码")
    @Id
    protected String code;

    @Schema(description = "图标")
    protected String icon;

    @Schema(description = "父区域ID")
    protected String parentCode;

    @Schema(description = "父区域")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code", insertable = false, updatable = false)
    protected Area parent;

    @Schema(description = "子区域")
    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @OrderBy("orderCode ASC , code ASC")
    protected Set<Area> children;

    @Schema(description = "类型")
    @Column(nullable = false)
    @Enumerated
    protected Type type;

    @Override
    public String getParentId() {
        return parentCode;
    }

    @Override
    public String getId() {
        return code;
    }

}
