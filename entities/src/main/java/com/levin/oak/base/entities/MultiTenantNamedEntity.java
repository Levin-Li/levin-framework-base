package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class MultiTenantNamedEntity
        extends AbstractNamedEntityObject
        implements MultiTenantObject {

    @Schema(description = "租户ID")
    @Column(length = 64)
    protected String tenantId;

    @Schema(description = "系统子域")
    @Column(length = 64)
    protected String domain;
}
