package com.levin.oak.base.entities;


import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.StartsWith;
import com.levin.commons.dao.domain.support.AbstractBaseEntityObject;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
import com.levin.commons.service.domain.EnumDesc;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants

@Entity(name = EntityConst.PREFIX + "AccessWhitelist")

@Table(
        indexes = {
                @Index(columnList = E_AccessWhitelist.enable),
                @Index(columnList = E_AccessWhitelist.title),
                @Index(columnList = E_AccessWhitelist.tenantId),
                @Index(columnList = E_AccessWhitelist.createTime),
                @Index(columnList = E_AccessWhitelist.orderCode),
        }
)
@Schema(title = "访问白名单", description = "主要是IP限制")
@EntityCategory(EntityOpConst.PLATFORM_TYPE_NAME)
public class AccessWhitelist extends AbstractMultiTenantObject {

    @Id
    @Column(length = 384)
    @StartsWith
    @Schema(title = "租户URL路径", description = "格式：tenantId+@+URL路径,如：tid@/base/sendSms，路径去除域名后的路径(不含参数)，规范的URL路径，中间不能有2个斜杠等")
    protected String id;

    @Schema(title = "标题")
    @Column(nullable = false)
    @Contains
    protected String title;

    @Schema(title = "模块ID")
    @Column(length = 64)
    protected String moduleId;

    @Schema(title = "名称")
    @Column(length = 64)
    protected String moduleName;

    @Schema(title = "域名列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String domainList;

    @Schema(title = "地区列表", description = "逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符")
    @Column(length = 1800)
    protected String regionList;

    @Schema(title = "IP地址列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String ipList;

    @Schema(title = "IP黑名单列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String ipBlacklist;

    @Schema(title = "操作系统列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String osList;

    @Schema(title = "浏览器列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String browserList;

    @Schema(title = "浏览器类型列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String browserTypeList;

    @Schema(title = "Http方法列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 64)
    protected String methodList;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}
