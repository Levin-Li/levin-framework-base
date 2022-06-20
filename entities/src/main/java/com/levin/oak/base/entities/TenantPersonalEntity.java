package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Schema(description = "租户个人实体")
@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class TenantPersonalEntity
        extends AbstractNamedMultiTenantObject
        implements OrganizedObject {

    @Schema(description = "所有者标识")
    @Column(length = 64)
    protected String ownerId;

}
