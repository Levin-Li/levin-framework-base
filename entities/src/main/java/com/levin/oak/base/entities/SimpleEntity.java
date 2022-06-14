package com.levin.oak.base.entities;


import com.levin.commons.dao.annotation.Contains;
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
        extends TenantOrganizedEntity {

    @Id
//    @GeneratedValue
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(description = "类型")
    @Column(nullable = false, length = 128)
    protected String type;

    @Schema(description = "分类名称")
    @Column(nullable = false, length = 128)
    protected String category;

    @Schema(description = "分组名称")
    @Column(nullable = false, length = 128)
    @Contains
    protected String groupName;

    @Schema(description = "图标")
    protected String icon;

    @Schema(description = "访问路径")
    @Column(nullable = false)
    protected String path;

    @Schema(description = "需要的权限或角色，json数组")
    @Column(length = 1800)
//    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    protected String requireAuthorizations;

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
