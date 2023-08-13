package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.dao.domain.support.E_AbstractMultiTenantObject;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity(name = EntityConst.PREFIX + "Setting")
@Data

@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "系统设置")

@Table(
        indexes = {
                @Index(columnList = E_Setting.orderCode),
                @Index(columnList = E_Setting.enable),
                @Index(columnList = E_Setting.createTime),
                @Index(columnList = E_Setting.creator),
                @Index(columnList = E_Setting.name),
                @Index(columnList = E_Setting.tenantId),
                @Index(columnList = E_Setting.code),
                @Index(columnList = E_Setting.categoryName),
                @Index(columnList = E_Setting.groupName),

                @Index(columnList = E_TenantOrgNamedEntity.tenantId + "," + E_TenantOrgNamedEntity.orgId),
                @Index(columnList = E_Setting.tenantId + "," + E_Setting.orgId + "," + E_Setting.id),
                @Index(columnList = E_Setting.tenantId + "," + E_Setting.orgId + "," + E_Setting.code),

        }
        //如果同一个租户下重复的设置项目
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_AbstractMultiTenantObject.tenantId, E_Setting.code}),
        }
)
public class Setting
        extends TenantOrgNamedEntity {
    public enum ValueType implements EnumDesc {
        @Schema(title = "Html")
        Html,
        @Schema(title = "Css")
        Css,
        @Schema(title = "Js")
        Js,
        @Schema(title = "文本")
        Text,
        @Schema(title = "数值")
        Digit,
        @Schema(title = "Yaml")
        Yaml,
        @Schema(title = "Json")
        Json,
        @Schema(title = "图片")
        Image,
        @Schema(title = "视频")
        Video,
        @Schema(title = "文件")
        File,
    }

    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    protected String id;

    @Schema(title = "分类名称")
    @Column(nullable = false, length = 64)
    protected String categoryName;

    @Schema(title = "分组名称")
    @Column(length = 64)
    @Contains
    protected String groupName;

    @Schema(title = "编码")
    @Column(nullable = false, length = 64)
    protected String code;

    @Schema(title = "值类型")
    @Column(nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    protected ValueType valueType;

    @Schema(title = "值")
    @Lob
    @Basic(fetch = FetchType.EAGER)
    protected String valueContent;

    @Schema(title = "值是否可空")
    protected Boolean nullable;

    @Schema(title = "输入占位提示")
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
