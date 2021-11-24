package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "simple_form")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "简单表单")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = MultiTenantNamedEntity.Fields.domain),
                @Index(columnList = MultiTenantAndOrganizedEntity.Fields.orgId),
                @Index(columnList = SimpleEntity.Fields.path),
                @Index(columnList = SimpleEntity.Fields.category),
                @Index(columnList = SimpleEntity.Fields.groupName),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Setting.code}),
//        }
)
public class SimpleForm extends SimpleEntity {

    @Schema(description = "提交地址")
    protected String commitApi;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
