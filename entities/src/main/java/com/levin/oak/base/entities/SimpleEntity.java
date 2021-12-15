package com.levin.oak.base.entities;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "抽象实体")
public abstract class SimpleEntity
        extends MultiTenantAndOrganizedEntity {

    @Id
    @GeneratedValue
    protected Long id;

    @Schema(description = "分类名称")
    @Column(nullable = false,length = 64)
    protected String category;

    @Schema(description = "分组名称")
    @Column(nullable = false,length = 64)
    protected String groupName;

    @Schema(description = "访问路径")
    @Column(nullable = false)
    protected String path;

    @Schema(description = "内容")
    @Lob
    protected String content;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        //是否可编辑
        this.editable = orgId == null;

    }

}
