package com.levin.oak.base.services.tenant.info;


import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////////////
////////////////////////////////////
////////////////////////////////////

/**
 * 租户
 *
 * @Author Auto gen by simple-dao-codegen 2022-3-25 18:38:00
 */
@Schema(description = "租户")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1557223144L;


    @NotBlank
    @Schema(description = "ID")
    private String id;


    @Schema(description = "租户头像")
    private String logo;


    @Schema(description = "企业信用编码")
    private String code;


    @NotBlank
    @Schema(description = "租户编码")
    private String tenantKey;


    @Schema(description = "帐号余额")
    private Double balance;


    @Schema(description = "总许可数")
    private Integer licenseCnt;


    @Schema(description = "剩余许可数")
    private Integer remainingLicenseCnt;


    @Schema(description = "到期时间")
    private Date licenseExpire;


    @Schema(description = "联系人")
    private String contractPerson;


    @Schema(description = "联系电话")
    private String contractPhone;


    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(description = "域名列表")
    private List<String> domainList;


    @Size(max = 32)
    @Schema(description = "appId")
    private String appId;


    @Size(max = 256)
    @Schema(description = "appSecret")
    private String appSecret;


    @Schema(description = "EncryptKey")
    private String encryptKey;


    @NotBlank
    @Size(max = 128)
    @Schema(description = "名称")
    private String name;


    @Size(max = 128)
    @Schema(description = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(description = "创建者")
    private String creator;


    @NotNull
    @Schema(description = "创建时间")
    private Date createTime;


    @Schema(description = "更新时间")
    private Date lastUpdateTime;


    @Schema(description = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(description = "是否允许")
    private Boolean enable;


    @NotNull
    @Schema(description = "是否可编辑")
    private Boolean editable;


    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


}
