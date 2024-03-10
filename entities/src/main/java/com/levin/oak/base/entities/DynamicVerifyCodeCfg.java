package com.levin.oak.base.entities;


import com.levin.commons.dao.EntityCategory;
import com.levin.commons.dao.EntityOpConst;
import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.StartsWith;
import com.levin.commons.dao.domain.support.AbstractMultiTenantObject;
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

@Entity(name = EntityConst.PREFIX + "DynamicVerifyCodeCfg")

@Table(
        indexes = {
                @Index(columnList = E_DynamicVerifyCodeCfg.enable),
                @Index(columnList = E_DynamicVerifyCodeCfg.title),
                @Index(columnList = E_DynamicVerifyCodeCfg.tenantId),
                @Index(columnList = E_DynamicVerifyCodeCfg.createTime),
                @Index(columnList = E_DynamicVerifyCodeCfg.orderCode),
        }
)
@Schema(title = "动态验证码配置", description = "部分注重安全的接口需要动态验证码的支持，需要先询问服务器是否要验证码，如果要验证码则需要客户端提交验证码")
@EntityCategory(EntityOpConst.PLATFORM_TYPE_NAME)
public class DynamicVerifyCodeCfg extends AbstractMultiTenantObject {

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

    @Schema(title = "IP地址排除列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String ipExcludeList;

    @Schema(title = "验证码类型")
    @Column(length = 16, nullable = false)
    @Enumerated(EnumType.STRING)
    protected VerifyCodeType verifyCodeType;

    @Schema(title = "验证码参数名称", description = "默认参数名：verifyCode")
    @Column(length = 16)
    protected String verifyCodeParamName;

    @Schema(title = "应用ID参数名称", description = "默认参数名：appId")
    @Column(length = 16)
    protected String appIdParamName;

    @PrePersist
    public void prePersist() {
        if (createTime == null) {
            createTime = new Date();
        }
    }
}
