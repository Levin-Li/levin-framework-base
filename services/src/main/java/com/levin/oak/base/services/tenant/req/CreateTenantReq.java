package com.levin.oak.base.services.tenant.req;

import com.levin.commons.dao.TargetOption;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.*;
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.oak.base.entities.E_Tenant;
import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.services.commons.req.BaseReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/////////////////////////////////////////////////////
///////////////////////////////////////////////////////
////////////////////////////////////
//自动导入列表
////////////////////////////////////


/**
 * 新增租户
 * //Auto gen by simple-dao-codegen 2022-4-2 13:49:52
 */
@Schema(title = "新增租户")
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS)
public class CreateTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;


    @Schema(title = "系统名称")
    private String sysName;


    @Schema(title = "系统Logo")
    private String sysLogo;

    @Schema(title = "租户头像")
    private String logo;

    @Schema(title = "企业信用编码")
    private String code;

    @Schema(title = "租户编码", required = true)
    @NotBlank
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

    @Schema(title = "域名列表")
    @InjectVar(domain = "dao", expectBaseType = String.class, converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    private List<String> domainList;

    @Schema(title = "appId")
    @Size(max = 32)
    private String appId;

    @Schema(title = "appSecret")
    @Size(max = 256)
    private String appSecret;

    @Schema(title = "EncryptKey")
    private String encryptKey;

    @Schema(title = "名称", required = true)
    @NotBlank
    @Size(max = 128)
    private String name;

    @Schema(title = "拼音，格式：全拼(简拼)")
    @Size(max = 128)
    private String pinyinName;

    @Schema(title = "创建者", hidden = true)
    //@InjectVar()
    //@Size(max = 128)
    @InjectVar(InjectConsts.USER_ID)
    private String creator;

    @Schema(title = "创建时间", hidden = true)
    //@NotNull
    private Date createTime;

    @Schema(title = "更新时间", hidden = true)
    private Date lastUpdateTime;

    @Schema(title = "排序代码", hidden = true)
    private Integer orderCode;

    @Schema(title = "是否允许", hidden = true)
    //@NotNull
    private Boolean enable;

    @Schema(title = "是否可编辑", hidden = true)
    //@NotNull
    private Boolean editable;

    @Schema(title = "备注")
    //@Size(max = 512)
    private String remark;


    @PostConstruct
    public void prePersist() {

        //@todo 保存之前初始化数据


        if (getCreateTime() == null) {
            setCreateTime(new Date());
        }

    }

}
