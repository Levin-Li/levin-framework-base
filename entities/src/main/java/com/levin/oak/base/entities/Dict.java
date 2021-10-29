package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = TableOption.PREFIX + "dict")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "字典")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = E_Dict.code),
                @Index(columnList = E_Dict.type),
                @Index(columnList = AbstractMultiTenantObject.Fields.tenantId),
        },

        uniqueConstraints = {
                @UniqueConstraint(columnNames = {AbstractMultiTenantObject.Fields.tenantId, E_Dict.code}),
                @UniqueConstraint(columnNames = {AbstractMultiTenantObject.Fields.tenantId, E_MultiTenantNamedEntity.name}),
        }
)

public class Dict extends MultiTenantNamedEntity<Long> {

    private static final long serialVersionUID = -123456789L;

    public enum Type {
        @Schema(description = "系统")
        System,
        @Schema(description = "自定义")
        Custom,
    }

    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    @Schema(description = "字典项")
    public static class Item
            extends AbstractNamedEntityObject<String>
            implements Serializable {

        @Id
        @Schema(description = "编码")
        @Column(nullable = false)
        protected String code;

        @Override
        public String getId() {
            return code;
        }

    }

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "类型")
    @Column(nullable = false)
    @Enumerated
    protected Type type;

    @Schema(description = "编码")
    @Column(nullable = false)
    protected String code;

    @Schema(description = "编码项", title = "Json 存储")
    @Lob
    protected String items;

    @Transient
    @Schema(description = "编码项", title = "Json 存储")
    protected List<Item> itemList;

}