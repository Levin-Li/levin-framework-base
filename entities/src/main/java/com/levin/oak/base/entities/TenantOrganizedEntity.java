package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConsts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Schema(title = "租户部门实体")
@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class TenantOrganizedEntity
        extends AbstractNamedMultiTenantObject
        implements OrganizedObject {

    @Schema(title = "机构ID")
    @Column(length = 64)
    @InjectVar(InjectConsts.ORG_ID)
    protected String orgId;

}
