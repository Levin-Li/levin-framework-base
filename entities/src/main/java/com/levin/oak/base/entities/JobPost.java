package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = TableOption.PREFIX + "job_post")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "工作岗位")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
//                @Index(columnList = JobPost.Fields.code),
//                @Index(columnList = JobPost.Fields.type),
                @Index(columnList = AbstractMultiTenantObject.Fields.tenantId),
        },

        uniqueConstraints = {
                @UniqueConstraint(columnNames = {AbstractMultiTenantObject.Fields.tenantId, E_JobPost.code}),
                @UniqueConstraint(columnNames = {AbstractMultiTenantObject.Fields.tenantId, E_MultiTenantNamedEntity.name}),
        }
)

public class JobPost
        extends MultiTenantNamedEntity<Long> {

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
    @Column(nullable = false)
    protected String code;

    @Schema(description = "类型")
    @Column(nullable = false)
    @Enumerated
    protected Type type;

}
