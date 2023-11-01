package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@Schema(title = "租户应用")
@Data
@Accessors(chain = true)
@FieldNameConstants

//6、必须建立索引
@Entity(name = EntityConst.PREFIX + "TenantApp")

//7、必须建立索引
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_TenantApp.name})
        }
)
public class TenantApp
        extends AbstractMultiTenantObject implements NamedObject {

    @Schema(title = "ID")
    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(title = "名称")
    @Column(length = 64, nullable = false)
    String name;

    @Schema(title = "模块列表")
    @Column(length = 1800)
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String modules;

}
