package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.EnumDesc;
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
@Schema(title = "工作岗位")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = JobPost.Fields.code),
                @Index(columnList = JobPost.Fields.type),
                @Index(columnList = E_JobPost.tenantId),
        }

        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_JobPost.tenantId, E_JobPost.code}),
                @UniqueConstraint(columnNames = {E_JobPost.tenantId, E_JobPost.name}),
        }
)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class JobPost
        extends TenantOrgSharedEntity {

    @Schema(title = "职位类型")
    public enum Type implements EnumDesc {
        @Schema(title = "管理岗")
        Manager,
        @Schema(title = "专业岗")
        Professional,
        @Schema(title = "操作岗")
        Operator,

        ;
        @Override
        public String toString() {
            return nameAndDesc();
        }
    }

    @Id
//    @GeneratedValue
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "编码")
    @Column(nullable = false, length = 64)
    @Contains
    protected String code;

    @Schema(title = "类型")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    protected Type type;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }
}
