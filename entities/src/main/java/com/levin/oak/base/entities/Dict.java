package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.EnumDesc;
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
@Schema(title = "字典")

@Entity(name = EntityConst.PREFIX + "Dict")
@Table(
        indexes = {
                @Index(columnList = E_Dict.orderCode),
                @Index(columnList = E_Dict.enable),
                @Index(columnList = E_Dict.createTime),
                @Index(columnList = E_Dict.creator),

                @Index(columnList = E_Dict.name),
                @Index(columnList = E_Dict.code),
                @Index(columnList = E_Dict.type),
                @Index(columnList = E_Dict.tenantId),
                @Index(columnList = E_Dict.orgId),

                @Index(columnList = E_TenantOrgNamedEntity.tenantId + "," + E_TenantOrgNamedEntity.orgId),

                @Index(columnList = E_Dict.tenantId + "," + E_Dict.orgId + "," + E_Dict.code),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_Dict.code}),
//                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_TenantOrgNamedEntity.name}),
        }
)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class Dict
        extends TenantOrgNamedEntity {

    private static final long serialVersionUID = -123456789L;

    public enum Type implements EnumDesc {
        @Schema(title = "系统") System,
        @Schema(title = "自定义") Custom,
    }

    @Data
    @Accessors(chain = true)
    @FieldNameConstants
    @Schema(title = "字典项")
    public static class Item
            extends AbstractNamedEntityObject {

        @Id
        @Schema(title = "编码")
        @Column(nullable = false)
        protected String code;

        @Override
        public String getId() {
            return code;
        }

    }

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "类型")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    protected Type type;

    @Schema(title = "编码")
    @Column(nullable = false, length = 256)
    @Contains
    protected String code;

    @Schema(title = "编码项", description = "Json 存储")
    @Lob
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {Item.class}, converter = DefaultJsonConverter.class, isRequired = "false")
    protected String itemList;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
