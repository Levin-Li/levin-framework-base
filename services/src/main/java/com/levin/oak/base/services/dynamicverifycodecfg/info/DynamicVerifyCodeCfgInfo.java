package com.levin.oak.base.services.dynamicverifycodecfg.info;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
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
import static com.levin.oak.base.entities.E_DynamicVerifyCodeCfg.*;
////////////////////////////////////
import java.util.Date;
import com.levin.oak.base.entities.VerifyCodeType;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////


/**
 * 动态验证码配置
 *
 * @author Auto gen by simple-dao-codegen, @time: 2024年3月10日 上午9:21:46, 代码生成哈希校验码：[53306bf810dc6c4747ba230dfea14966]，请不要修改和删除此行内容。
 *
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties({"tenantId"})
public class DynamicVerifyCodeCfgInfo implements Serializable {

    private static final long serialVersionUID = -491629507L;


    @NotBlank
    @Size(max = 384)
    @Schema(title = L_id , description = D_id)
    String id;

    @NotBlank
    @Size(max = 255)
    @Schema(title = L_title)
    String title;

    @Size(max = 64)
    @Schema(title = L_moduleId)
    String moduleId;

    @Size(max = 64)
    @Schema(title = L_moduleName)
    String moduleName;

    @Size(max = 1800)
    @Schema(title = L_domainList , description = D_domainList)
    String domainList;

    @Size(max = 1800)
    @Schema(title = L_regionList , description = D_regionList)
    String regionList;

    @Size(max = 1800)
    @Schema(title = L_ipList , description = D_ipList)
    String ipList;

    @Size(max = 1800)
    @Schema(title = L_ipExcludeList , description = D_ipExcludeList)
    String ipExcludeList;

    @NotNull
    @Schema(title = L_verifyCodeType)
    VerifyCodeType verifyCodeType;

    @Size(max = 16)
    @Schema(title = L_verifyCodeParamName , description = D_verifyCodeParamName)
    String verifyCodeParamName;

    @Size(max = 16)
    @Schema(title = L_appIdParamName , description = D_appIdParamName)
    String appIdParamName;

    @Size(max = 128)
    @InjectVar(value = InjectConst.TENANT_ID)
    @Schema(title = L_tenantId)
    String tenantId;

    @InjectVar(value = InjectConst.USER_ID, isRequired = "false")
    @Size(max = 128)
    @Schema(title = L_creator)
    String creator;

    @NotNull
    @Schema(title = L_createTime)
    Date createTime;

    @Schema(title = L_lastUpdateTime)
    Date lastUpdateTime;

    @Schema(title = L_orderCode)
    Integer orderCode;

    @NotNull
    @Schema(title = L_enable)
    Boolean enable;

    @NotNull
    @Schema(title = L_editable)
    Boolean editable;

    @Size(max = 512)
    @Schema(title = L_remark)
    String remark;

    @Schema(title = L_optimisticLock)
    Integer optimisticLock;

}
