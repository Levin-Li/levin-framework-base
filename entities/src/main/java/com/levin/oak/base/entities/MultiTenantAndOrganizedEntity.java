package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.service.domain.InjectVar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class MultiTenantAndOrganizedEntity
        extends MultiTenantNamedEntity
        implements OrganizedObject {

    @Schema(description = "机构ID")
    @InjectVar
    protected String orgId;

    @Schema(description = "子域")
    protected String domain;

}
