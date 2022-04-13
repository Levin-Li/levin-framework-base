package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "JobPost")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "工作岗位")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = JobPost.Fields.code),
                @Index(columnList = JobPost.Fields.type),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
        }

        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_JobPost.code}),
                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_MultiTenantNamedEntity.name}),
        }
)

public class JobPost
        extends MultiTenantNamedEntity {

    public enum Type {
        @Schema(description = "管理岗")
        Manager,
        @Schema(description = "专业岗")
        Professional,
        @Schema(description = "操作岗")
        Operator,
    }

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "编码")
    @Column(nullable = false, length = 64)
    @Contains
    protected String code;

    @Schema(description = "类型")
    @Column(nullable = false)
    @Enumerated
    protected Type type;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }
}
