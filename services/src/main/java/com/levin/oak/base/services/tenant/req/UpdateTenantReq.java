package com.levin.oak.base.services.tenant.req;

import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;

import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import javax.validation.constraints.*;
import javax.annotation.*;

import lombok.*;
import lombok.experimental.*;
import java.util.*;

import com.levin.oak.base.entities.Tenant;
import com.levin.oak.base.entities.*;

import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////


/**
 *  更新租户
 *  Auto gen by simple-dao-codegen 2022-3-25 18:38:00
 */
@Schema(description = "更新租户")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = Tenant.class, alias = E_Tenant.ALIAS)
//默认更新注解
@Update
public class UpdateTenantReq extends BaseReq {

    private static final long serialVersionUID = 1557223144L;

    @Schema(description = "ID" , required = true)
    @NotNull
    @Eq(require = true)
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

    @Schema(description = "总坐席数")
    private Integer licenseCnt;

    @Schema(description = "剩余坐席数")
    private Integer remainingLicenseCnt;

    @Schema(description = "到期时间")
    private Date licenseExpire;

    @Schema(description = "联系人")
    private String contractPerson;

    @Schema(description = "联系电话")
    private String contractPhone;

    @InjectVar(domain = "dao", converter = PrimitiveArrayJsonConverter.class)
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

    @Schema(description = "更新时间")
    private Date lastUpdateTime;

    @Schema(description = "排序代码")
    private Integer orderCode;

    @Schema(description = "是否允许")
    private Boolean enable;

    @Schema(description = "是否可编辑")
    private Boolean editable;

    @Size(max = 512)
    @Schema(description = "备注")
    private String remark;


    public UpdateTenantReq(String id) {
        this.id = id;
    }
    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据

        if(getLastUpdateTime() == null){
            setLastUpdateTime(new Date());
        }
    }

}
