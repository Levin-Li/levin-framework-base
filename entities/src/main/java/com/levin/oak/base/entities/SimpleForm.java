package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.E_AbstractMultiTenantObject;
import com.levin.commons.dao.domain.support.E_AbstractMultiTenantOrgObject;
import com.levin.commons.service.support.InjectConsts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity(name = EntityConst.PREFIX + "SimpleForm")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "简单表单")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = InjectConsts.TENANT_ID),
                @Index(columnList = InjectConsts.ORG_ID),
                @Index(columnList = E_TenantOrgNamedEntity.domain),
//                @Index(columnList = SimpleEntity.Fields.type),
                @Index(columnList = SimpleEntity.Fields.path),
                @Index(columnList = SimpleEntity.Fields.category),
                @Index(columnList = SimpleEntity.Fields.groupName),

                @Index(columnList = E_SimpleEntity.tenantId + "," + E_SimpleEntity.orgId),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_Setting.code}),
//        }
)
public class SimpleForm extends SimpleEntity {

    @Schema(title = "提交地址")
    protected String commitApi;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}
