package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "AppClientFile")
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "客户端文件")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = AbstractNamedMultiTenantObject.Fields.tenantId),
                @Index(columnList = AbstractNamedMultiTenantObject.Fields.domain),

                @Index(columnList = E_AppClientFile.clientType),

        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {AbstractNamedMultiTenantObject.Fields.tenantId, E_Setting.code}),
//        }
)
public class AppClientFile extends AbstractNamedMultiTenantObject {

    @Id
//    @GeneratedValue
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(description = "客户端类型")
    @Column(length = 64)
    protected String clientType;

    @Schema(description = "文件类型")
    @Column(length = 128)
    protected String mimeType;

    @Schema(description = "文件路径")
    @Column(nullable = false)
    protected String path;

    @Schema(description = "文件内容")
    @Lob
    protected String content;

}
