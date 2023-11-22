package com.levin.oak.base.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.levin.commons.dao.domain.MultiTenantPublicObject;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantOrgObject;
import com.levin.commons.service.domain.InjectVar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Schema(title = "租户部门可命名实体")
@MappedSuperclass
@Data
@Accessors(chain = true)
@FieldNameConstants
public abstract class TenantOrgNamedEntity
        extends AbstractMultiTenantOrgObject implements MultiTenantPublicObject, NamedObject {

    @Schema(title = "系统域", hidden = true, description = "归属的子系统或应用")
    @Column(length = 128)
    @InjectVar(value = "sysDomain", isRequired = "false")
    protected String domain;

    @Schema(title = "名称")
    @Column(length = 64, nullable = false)
    protected String name;

    @Schema(title = "乐观锁")
    @Version
    @JsonIgnore
    protected Integer optimisticLock;

}
