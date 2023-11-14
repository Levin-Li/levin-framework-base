package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)

@ToString(exclude = "org")

@FieldNameConstants
@Schema(title = "通知")

@Entity(name = EntityConst.PREFIX + "Notice")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = E_TenantOrgNamedEntity.tenantId),
                @Index(columnList = E_TenantOrgNamedEntity.orgId),

                @Index(columnList = E_Notice.creator),
                @Index(columnList = E_Notice.expiredDate),
                @Index(columnList = E_Notice.category),
                @Index(columnList = E_Notice.contentType),
        }
        ,
        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.telephone}),
//                @UniqueConstraint(columnNames = {E_User.tenantId, E_User.email}),
        }
)
/**
 *
 * 标题：name
 * 摘要：remark
 * 内容：content
 * 内容类型：contentType
 *
 */
public class Notice
        extends TenantOrgNamedEntity {

    @Schema(title = "通知内容类型")
    public enum ContentType implements EnumDesc {
        @Schema(title = "文本")
        Text,
        @Schema(title = "网页")
        Html,
        @Schema(title = "图片")
        Pic,
        @Schema(title = "MarkDown")
        Markdown,
        @Schema(title = "AmisJsonView")
        AmisJsonView,
    }

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(title = "通知类别")
    @Column(length = 64)
    String category;

    @Schema(title = "通知内容类型")
    @Enumerated(EnumType.STRING)
    ContentType contentType;

    @Schema(title = "通知内容")
    @Lob
    String content;

    @Schema(title = "过期时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date expiredDate;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}

