package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.support.InjectConst;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "AppClientFile")
//@Cacheable
@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "客户端文件", description = "简单的文件存储，一般不建议使用")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = InjectConst.TENANT_ID),

                @Index(columnList = E_AppClientFile.clientType),

                @Index(columnList = E_AppClientFile.tenantId + "," + E_AppClientFile.orgId),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {E_TenantOrgNamedEntity.tenantId, E_Setting.code}),
//        }
)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class AppClientFile extends TenantOrgSharedEntity {

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "客户端类型")
    @Column(length = 64)
    protected String clientType;

    @Schema(title = "文件类型")
    @Column(length = 128)
    protected String mimeType;

    @Schema(title = "文件路径", description = "本地路径或是网络路径")
    @Column(nullable = false)
    protected String path;

    @Schema(title = "文件内容", description = "一般不建议存储在数据库")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    protected byte[] content;

}
