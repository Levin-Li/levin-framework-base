package com.levin.oak.base.services.tenant.info;

import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.*;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.*;
/////////////////////////////////////////////////////
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_Tenant.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
import java.util.List;
import com.levin.commons.service.support.PrimitiveArrayJsonConverter;
////////////////////////////////////

/**
 * 平台租户
 * @Author Auto gen by simple-dao-codegen 2023年6月28日 下午4:18:30
 * 代码生成哈希校验码：[4fc44c14b9cba6f176335dfef75bb92c]
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants

public class TenantInfo implements Serializable {

    private static final long serialVersionUID = 1557223144L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String id;


    @Size(max = 128)
    @Schema(title = L_sysName )
    String sysName;


    @Schema(title = L_sysLogo )
    String sysLogo;


    @Schema(title = L_logo )
    String logo;


    @Size(max = 128)
    @Schema(title = L_code )
    String code;


    @NotBlank
    @Schema(title = L_tenantKey , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String tenantKey;


    @Schema(title = L_balance )
    Double balance;


    @Schema(title = L_licenseCnt )
    Integer licenseCnt;


    @Schema(title = L_remainingLicenseCnt )
    Integer remainingLicenseCnt;


    @Schema(title = L_licenseExpire )
    Date licenseExpire;


    @Size(max = 32)
    @Schema(title = L_contractPerson )
    String contractPerson;


    @Size(max = 32)
    @Schema(title = L_contractPhone )
    String contractPhone;


    @Size(max = 1200)
    @InjectVar(domain = "dao",  converter = PrimitiveArrayJsonConverter.class, isRequired = "false")
    @Schema(title = L_domainList )
    List<String> domainList;


    @Size(max = 64)
    @Schema(title = L_appId )
    String appId;


    @Size(max = 512)
    @Schema(title = L_appSecret )
    String appSecret;


    @Size(max = 512)
    @Schema(title = L_encryptKey )
    String encryptKey;


    @NotBlank
    @Size(max = 128)
    @Schema(title = L_name , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String name;


    @Size(max = 128)
    @Schema(title = L_pinyinName , description = D_pinyinName )
    String pinyinName;


    @Size(max = 128)
    @Schema(title = L_creator )
    String creator;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date createTime;


    @Schema(title = L_lastUpdateTime )
    Date lastUpdateTime;


    @Schema(title = L_orderCode )
    Integer orderCode;


    @NotNull
    @Schema(title = L_enable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean enable;


    @NotNull
    @Schema(title = L_editable , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean editable;


    @Size(max = 512)
    @Schema(title = L_remark )
    String remark;


}
