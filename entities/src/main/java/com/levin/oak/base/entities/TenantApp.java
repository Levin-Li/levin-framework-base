package com.levin.oak.base.entities;

import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.domain.NamedObject;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Schema(title = "租户应用")
@Data
@Accessors(chain = true)
@FieldNameConstants

//6、必须建立索引
@Entity(name = EntityConst.PREFIX + "TenantApp")

//7、必须建立索引
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_TenantApp.name})
        }
)
@EntityCategory(EntityOpConst.SYS_TYPE_NAME)
public class TenantApp
        extends AbstractMultiTenantObject implements NamedObject {

    @Schema(title = "ID")
    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 64)
    String id;

    @Schema(title = "应用名称")
    @Column(length = 64, nullable = false)
    String name;

    @Schema(title = "应用Logo", description = "")
    String logo;

    @Schema(title = "应用入口", description = "")
    String entryUrl;

    @Schema(title = "应用说明页", description = "")
    String infoUrl;

    @Schema(title = "模块列表")
    @Column(length = 1800)
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    String modules;

    @Schema(title = "应用密钥", description = "租户安装后获得的应用密钥")
    String appSecret;

    @Schema(title = "销售价格", description = "为空或是为0表示免费")
    BigDecimal salePrice;

    @Schema(title = "采购价格", description = "购买价格")
    BigDecimal purchasePrice;

    @Schema(title = "订单编号", description = "购买的订单编号")
    String orderNo;

    @Schema(title = "到期时间", description = "为空表示永不过期")
    @Temporal(value = TemporalType.TIMESTAMP)
    Date expiredTime;

}
