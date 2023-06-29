package com.levin.oak.base.services.appclient.info;

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
import static com.levin.oak.base.entities.E_AppClient.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 * 应用接入
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:11, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[b8e976e6cdb31f20b023b4ab1e35ed8c], 请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class AppClientInfo implements Serializable {

    private static final long serialVersionUID = -115048882L;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String id;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_appId , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String appId;


    @NotBlank
    @Size(max = 512)
    @Schema(title = L_appSecret , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String appSecret;


    @Size(max = 128)
    @Schema(title = L_domain )
    String domain;


    @NotBlank
    @Size(max = 64)
    @Schema(title = L_name , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String name;


    @Size(max = 128)
    @Schema(title = L_orgId )
    String orgId;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


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
