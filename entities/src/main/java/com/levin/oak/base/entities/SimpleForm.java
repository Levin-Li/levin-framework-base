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
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = MultiTenantNamedEntity.Fields.domain),
                @Index(columnList = MultiTenantAndOrganizedEntity.Fields.orgId),
                @Index(columnList = E_SimpleForm.path),
                @Index(columnList = E_SimpleForm.path),
                @Index(columnList = E_SimpleForm.category),
                @Index(columnList = E_SimpleForm.groupName),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Setting.code}),
//        }
)
public class SimpleForm
        extends MultiTenantAndOrganizedEntity {

    @Id
    protected Long id;

    @Schema(description = "分类名称")
    @Column(nullable = false)
    protected String category;

    @Schema(description = "分组名称")
    @Column(nullable = false)
    protected String groupName;

    @Schema(description = "路径")
    @Column(nullable = false)
    protected String path;

    @Schema(description = "表单文本")
    @Lob
    protected String formText;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        this.editable = orgId == null;

    }

}
