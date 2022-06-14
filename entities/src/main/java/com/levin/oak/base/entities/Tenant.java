package com.levin.oak.base.entities;

import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractNamedEntityObject;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

//1、lobmok get set
@Data

//2、必须注解主键字段
@EqualsAndHashCode(of = {"id"})

//3、必须使用链式设置
@Accessors(chain = true)

//4、必须生成常量字段
@FieldNameConstants

//5、必须注解业务名称
@Schema(description = "租户")

//6、必须建立索引
@Entity(name = EntityConst.PREFIX + "Tenant")

//7、必须建立索引
@Table(
        indexes = {
                @Index(columnList = AbstractBaseEntityObject.Fields.orderCode),
                @Index(columnList = AbstractBaseEntityObject.Fields.createTime),
                @Index(columnList = AbstractBaseEntityObject.Fields.enable),
                @Index(columnList = AbstractNamedEntityObject.Fields.name),
                @Index(columnList = E_Tenant.code),
                @Index(columnList = E_Tenant.tenantKey),
                @Index(columnList = E_Tenant.licenseExpire),
                @Index(columnList = E_Tenant.remainingLicenseCnt),
                @Index(columnList = E_Tenant.encryptKey),
                @Index(columnList = E_Tenant.appId),
        }
        ,
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {E_Tenant.tenantKey})
        }
)

public class Tenant extends AbstractNamedEntityObject {

    @Schema(description = "ID")
    @Id
    @GeneratedValue(generator = "default_id")
    @Column(length = 128)
    String id;

    @Schema(description = "系统名称")
    @Column(length = 128)
    String sysName;

    @Schema(description = "系统Logo")
    String sysLogo;

    @Schema(description = "租户Logo")
    String logo;

    @Schema(description = "企业信用编码")
    @Column(length = 128)
    String code;

    @Schema(description = "租户编码")
    @Column(nullable = false)
    String tenantKey;

    @Schema(description = "帐号余额")
    Double balance;

    @Schema(description = "总许可数")
    Integer licenseCnt;

    @Schema(description = "剩余许可数")
    Integer remainingLicenseCnt;

    @Schema(description = "到期时间")
    @Temporal(TemporalType.DATE)
    Date licenseExpire;

    @Schema(description = "联系人")
    @Column(length = 32)
    String contractPerson;

    @Schema(description = "联系电话")
    @Column(length = 32)
    String contractPhone;

    ////////////////////////////////////////////////
    @Schema(description = "域名列表")
    @Contains
    @InjectVar(domain = "dao", expectBaseType = List.class, expectGenericTypes = {String.class}, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Column(length = 1200)
    String domainList;

    @Schema(description = "appId")
    @Column(length = 128)
    String appId;

    @Schema(description = "appSecret")
    @Column(length = 128)
    String appSecret;

    @Schema(description = "encryptKey")
    @Column(length = 128)
    String encryptKey;

    @PrePersist
    @Override
    public void prePersist() {

        super.prePersist();

        if (!StringUtils.hasText(tenantKey)) {
            tenantKey = UUID.randomUUID().toString();
        }
    }

}
