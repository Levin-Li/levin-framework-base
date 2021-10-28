package com.levin.oak.base.entities;

import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

//@Entity(name = TableOption.PREFIX + "dict_item")
@Data
@Accessors(chain = true)
@FieldNameConstants
@Schema(description = "字典项")

//@Table(
//
//        indexes = {
//                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
//                @Index(columnList = AbstractNamedEntityObject.Fields.name),
//                @Index(columnList = DictItem.Fields.code),
//                @Index(columnList = DictItem.Fields.dictCode),
//        },
//
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {
//                        DictItem.Fields.dictCode,
//                        DictItem.Fields.code
//                })
//        }
//
//)

/**
 * 暂时废弃，使用 json 存储
 */
@Deprecated
public class DictItem extends AbstractNamedEntityObject<String> {

    @Id
    @Schema(description = "id")
    protected String id;

    @Schema(description = "字典编码")
    @Column(nullable = false)
    protected String dictCode;

    @Schema(description = "编码")
    @Column(nullable = false)
    protected String code;

}
