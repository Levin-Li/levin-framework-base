package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantOrgObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Schema(title = "租户部门可命名实体")
@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class TenantOrgNamedEntity
        extends AbstractMultiTenantOrgObject implements NamedObject {

    @Schema(title = "系统域")
    @Column(length = 128)
    protected String domain;

    @Schema(title = "名称")
    @Column(length = 64, nullable = false)
    protected String name;

}
