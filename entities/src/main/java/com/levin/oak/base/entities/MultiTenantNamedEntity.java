package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConsts;
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
    @Column(length = 128)
    @InjectVar(InjectConsts.TENANT_ID)
    protected String tenantId;

    @Schema(description = "系统子域")
    @Column(length = 128)
    protected String domain;
}
