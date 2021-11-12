package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.InjectVar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class MultiTenantNamedEntity
        extends AbstractNamedEntityObject
        implements MultiTenantObject {

    @Schema(description = "租户ID")
    @InjectVar
    protected String tenantId;

}
