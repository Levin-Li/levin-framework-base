package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.StartsWith;
import com.levin.commons.dao.domain.TreeObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Set;

@Entity(name = EntityConst.PREFIX + "Permission")
@Cacheable
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "权限清单")
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),

                @Index(columnList = E_Permission.type),
        }
)
@EntityCategory(EntityOpConst.PLATFORM_TYPE_NAME)
public class Permission
        extends AbstractNamedEntityObject {

    @Id
    @Column(length = 64)
    @Contains
    protected String id;

    @Schema(title = "图标")
    protected String icon;

    @Schema(title = "模块")
    @Column(length = 128)
    protected String moduleId;

    @Schema(title = "资源类型")
    @Column(length = 128)
    protected String type;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
