package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "simple_api")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "简单接口")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = MultiTenantNamedEntity.Fields.domain),
                @Index(columnList = MultiTenantAndOrganizedEntity.Fields.orgId),
                @Index(columnList = E_SimpleApi.path),
                @Index(columnList = E_SimpleApi.category),
                @Index(columnList = E_SimpleApi.groupName),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Setting.code}),
//        }
)
public class SimpleApi
        extends MultiTenantAndOrganizedEntity {

    @Id
    protected Long id;

    public enum Language {
        Groovy,
        Spel,
        JavaScript,
    }

    @Schema(description = "分类名称")
    @Column(nullable = false)
    protected String category;

    @Schema(description = "分组名称")
    @Column(nullable = false)
    protected String groupName;

    @Schema(description = "路径")
    @Column(nullable = false)
    protected String path;

    @Schema(description = "http方法", title = "逗号隔开")
    protected String methods;

    @Schema(description = "脚本语言")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Language language;

    @Schema(description = "处理脚本")
    @Lob
    protected String script;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        this.editable = orgId == null;

    }

}
