package com.levin.oak.base.entities.vo;


import com.levin.commons.dao.annotation.Contains;
import com.levin.commons.dao.annotation.StartsWith;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = {"id"})
@Accessors(chain = true)
@FieldNameConstants
@Schema(title = "访问白名单", description = "URL访问控制，主要是IP限制")
public class UrlAcl implements Serializable {

    @Id
    @Column(length = 384)
    @StartsWith
    @Schema(title = "标识", description = "")
    protected String id;

    @Schema(title = "是否启用")
    protected Boolean enable;

    @Schema(title = "标题")
    @Column(nullable = false)
    @Contains
    protected String title;

    @Schema(title = "URL匹配列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String urlPathList;

    @Schema(title = "例外的URL列表", description = "匹配后直接通过，逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String urlPathExcludeList;

    @Schema(title = "域名匹配列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String domainList;

    @Schema(title = "地区匹配列表", description = "逗号或是回车隔开，通常根据IP判别地区，可以支持*通配符")
    @Column(length = 1800)
    protected String regionList;

    @Schema(title = "IP地址匹配列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String ipList;

    @Schema(title = "例外的IP地址列表", description = "匹配后直接通过的列表，逗号或是回车隔开，可以支持*通配符")
    @Column(length = 1800)
    protected String ipExcludeList;

    @Schema(title = "操作系统匹配列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String osList;

    @Schema(title = "浏览器匹配列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String browserList;

    @Schema(title = "浏览器类型匹配列表", description = "逗号或是回车隔开,可以支持*通配符")
    protected String browserTypeList;

    @Schema(title = "Http方法匹配列表", description = "逗号或是回车隔开，可以支持*通配符")
    @Column(length = 64)
    protected String methodList;

}
