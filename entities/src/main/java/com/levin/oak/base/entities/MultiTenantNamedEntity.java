package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class MultiTenantNamedEntity<TID extends Serializable>
        extends AbstractMultiTenantObject<TID>
        implements MultiTenantObject<TID> {

    @Schema(description = "名称")
    @Column(nullable = false, length = 768)
    protected String name;

}
