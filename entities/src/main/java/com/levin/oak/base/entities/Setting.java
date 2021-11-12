package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity(name = EntityConst.PREFIX + "setting")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "系统设置")

@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = MultiTenantNamedEntity.Fields.tenantId),
                @Index(columnList = E_Setting.code),
                @Index(columnList = E_Setting.categoryName),
                @Index(columnList = E_Setting.groupName),
        }

//        ,
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {MultiTenantNamedEntity.Fields.tenantId, E_Setting.code}),
//        }
)
public class Setting
        extends MultiTenantNamedEntity  {

    public enum ValueType {
        @Schema(description = "文本")
        Text,
        @Schema(description = "Json")
        Json,
        @Schema(description = "数值")
        Digit,
        @Schema(description = "图片")
        Image,
    }

    @Id
    protected Long id;

    @Schema(description = "分类名称")
    @Column(nullable = false)
    protected String categoryName;

    @Schema(description = "分组名称")
    protected String groupName;

    @Schema(description = "编码")
    @Column(nullable = false)
    protected String code;

    @Schema(description = "值类型")
    @Column(nullable = false)
    protected ValueType valueType;

    @Schema(description = "值")
    @Lob
    protected String value;

    @Schema(description = "值是否可空")
    protected Boolean nullable;

    @Schema(description = "输入占位提示")
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
