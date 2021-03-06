package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.MultiTenantObject;
import com.levin.commons.dao.domain.OrganizedObject;
import com.levin.commons.dao.domain.PersonalObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import com.levin.commons.service.domain.EnumDesc;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConsts;
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
@Schema(description = "通知")

@Entity(name = EntityConst.PREFIX + "Notice")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = AbstractNamedMultiTenantObject.Fields.tenantId),
                @Index(columnList = TenantOrganizedEntity.Fields.orgId),

                @Index(columnList = E_Notice.ownerId),
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
        extends TenantOrganizedEntity
        implements PersonalObject, OrganizedObject, MultiTenantObject {

    @Schema(description = "通知内容类型")
    public enum ContentType implements EnumDesc {
        @Schema(description = "文本")
        Text,
        @Schema(description = "网页")
        Html,
        @Schema(description = "图片")
        Pic,
        @Schema(description = "MarkDown")
        Markdown,
        @Schema(description = "AmisJsonView")
        AmisJsonView,
    }

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(title = "所有者ID")
    @Column(length = 128)
    @InjectVar(InjectConsts.USER_ID)
    String ownerId;
    ////////////////////////////////////////////////////////////////////

    @Schema(description = "通知类别")
    @Column(length = 64)
    String category;

    @Schema(description = "通知内容类型")
    @Enumerated(EnumType.STRING)
    ContentType contentType;

    @Schema(description = "通知内容")
    @Lob
    String content;

    @Schema(description = "过期时间")
    @Temporal(TemporalType.TIMESTAMP)
    Date expiredDate;

    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }

}

