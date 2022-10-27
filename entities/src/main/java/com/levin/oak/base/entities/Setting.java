package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedMultiTenantObject;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "Setting")
@Data

@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "系统设置")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.creator),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = AbstractNamedMultiTenantObject.Fields.tenantId),
                @Index(columnList = E_Setting.code),
                @Index(columnList = E_Setting.categoryName),
                @Index(columnList = E_Setting.groupName),
        }

        //如果同一个租户下重复的设置项目
        ,

        uniqueConstraints = {
                @UniqueConstraint(columnNames = {AbstractNamedMultiTenantObject.Fields.tenantId, E_Setting.code}),
        }
)
public class Setting
        extends AbstractNamedMultiTenantObject {

    public enum ValueType  implements EnumDesc {
        @Schema(description = "富文本")
        Html,
        @Schema(description = "CSS")
        Css,
        @Schema(description = "JS")
        Js,
        @Schema(description = "文本")
        Text,
        @Schema(description = "Json")
        Json,
        @Schema(description = "数值")
        Digit,
        @Schema(description = "图片")
        Image,
        @Schema(description = "视频")
        Video,
        @Schema(description = "文件")
        File,
    }

    @Id
//    @GeneratedValue
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(description = "分类名称")
    @Column(nullable = false, length = 64)
    protected String categoryName;

    @Schema(description = "分组名称")
    @Column(length = 64)
    @Contains
    protected String groupName;

    @Schema(description = "编码")
    @Column(nullable = false, length = 64)
    protected String code;

    @Schema(description = "值类型")
    @Column(nullable = false,length = 64)
    @Enumerated(EnumType.STRING)
    protected ValueType valueType;

    @Schema(description = "值")
    @Lob
    protected String value;

    @Schema(description = "值是否可空")
    protected Boolean nullable;

    @Schema(description = "输入占位提示")
    @Column(length = 128)
    protected String inputPlaceholder;

    @Override
    @PrePersist
    public void prePersist() {

        super.prePersist();

        if (nullable == null) {
            nullable = true;
        }

        if (valueType == null) {
            valueType = ValueType.Text;
        }

    }

}
