package com.levin.oak.base.entities;


import com.levin.commons.dao.Unique;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.EndsWith;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "抽象实体")
public abstract class SimpleEntity
        extends TenantOrgSharedEntity {

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "域名")
    @EndsWith
    protected String domain;

    @Schema(title = "类型")
    @Column(nullable = false, length = 128)
    protected String type;

    @Schema(title = "分类")
    @Column(nullable = false, length = 128)
    protected String category;

    @Schema(title = "分组名称")
    @Column(nullable = false, length = 128)
    @Contains
    protected String groupName;

    @Schema(title = "图标")
    protected String icon;

    @Schema(title = "访问路径", description = "")
    @Column(nullable = false, length = 800)
    protected String path;

    @Schema(title = "需要的权限或角色")
    @Column(length = 1800)
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    protected String requireAuthorizations;

    @Schema(title = "内容")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    protected String content;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        //是否可编辑
        this.editable = orgId == null;

    }

}
