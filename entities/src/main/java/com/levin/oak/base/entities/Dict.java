package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.DefaultJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;


@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "字典")

@Entity(name = EntityConst.PREFIX + "Dict")
@Table(

        indexes = {
//                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
//                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
//                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
//                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
//
//                @Index(columnList = AbstractNamedEntityObject.Fields.name),
//                @Index(columnList = E_Dict.code),
//                @Index(columnList = E_Dict.type),
//                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
        }
//        ,
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Dict.code}),
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_MultiTenantNamedEntity.name}),
//        }
)

public class Dict
        extends MultiTenantNamedEntity {

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
            extends AbstractNamedEntityObject {

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
    @Column(nullable = false, length = 64)
    @Contains
    protected String code;

    @Schema(description = "编码项", title = "Json 存储")
    @Lob
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {Item.class}, converter = DefaultJsonConverter.class)
    protected String itemList;

}
