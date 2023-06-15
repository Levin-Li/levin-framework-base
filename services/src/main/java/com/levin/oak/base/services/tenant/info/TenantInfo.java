package com.levin.oak.base.services.tenant.info;


import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
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
@Schema(title = "租户")
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1557223144L;


    @NotBlank
    @Schema(title = "ID")
    private String id;


    @Schema(title = "租户头像")
    private String logo;


    @Schema(title = "系统名称")
    private String sysName;


    @Schema(title = "系统Logo")
    private String sysLogo;


    @Schema(title = "企业信用编码")
    private String code;


    @NotBlank
    @Schema(title = "租户编码")
    private String tenantKey;


    @Schema(title = "帐号余额")
    private Double balance;


    @Schema(title = "总许可数")
    private Integer licenseCnt;


    @Schema(title = "剩余许可数")
    private Integer remainingLicenseCnt;


    @Schema(title = "到期时间")
    private Date licenseExpire;


    @Schema(title = "联系人")
    private String contractPerson;


    @Schema(title = "联系电话")
    private String contractPhone;


    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = "域名列表")
    private List<String> domainList;


    @Size(max = 32)
    @Schema(title = "appId")
    private String appId;


    @Size(max = 256)
    @Schema(title = "appSecret")
    private String appSecret;


    @Schema(title = "EncryptKey")
    private String encryptKey;


    @NotBlank
    @Size(max = 128)
    @Schema(title = "名称")
    private String name;


    @Size(max = 128)
    @Schema(title = "拼音，格式：全拼(简拼)")
    private String pinyinName;


    @Size(max = 128)
    @Schema(title = "创建者")
    private String creator;


    @NotNull
    @Schema(title = "创建时间")
    private Date createTime;


    @Schema(title = "更新时间")
    private Date lastUpdateTime;


    @Schema(title = "排序代码")
    private Integer orderCode;


    @NotNull
    @Schema(title = "是否允许")
    private Boolean enable;


    @NotNull
    @Schema(title = "是否可编辑")
    private Boolean editable;


    @Size(max = 512)
    @Schema(title = "备注")
    private String remark;


}
