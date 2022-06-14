package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Schema(description = "租户部门实体")
@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class TenantOrganizedEntity
        extends AbstractNamedMultiTenantObject
        implements OrganizedObject {

    @Schema(description = "机构ID")
    @Column(length = 64)
    protected String orgId;

}
