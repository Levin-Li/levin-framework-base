package com.levin.oak.base.services.accesslog.info;

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
import static com.levin.oak.base.entities.E_AccessLog.*;
////////////////////////////////////
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 * 访问日志
 * @author Auto gen by simple-dao-codegen, @time: 2023年6月29日 上午10:11:12, 请不要修改和删除此行内容。
 * 代码生成哈希校验码：[cfdf2e98b901c7e9b2b88e2186d35286], 请不要修改和删除此行内容。
 */
@Schema(title = BIZ_NAME)
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {})
@FieldNameConstants
@JsonIgnoreProperties(tenantId)
public class AccessLogInfo implements Serializable {

    private static final long serialVersionUID = 1030736962L;


    @NotNull
    @Schema(title = L_id , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Long id;


    @Schema(title = L_domain )
    String domain;


    @Size(max = 64)
    @Schema(title = L_visitor )
    String visitor;


    @NotBlank
    @Schema(title = L_title , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    String title;


    @Size(max = 64)
    @Schema(title = L_logType )
    String logType;


    @Schema(title = L_diffModifyData )
    String diffModifyData;


    @Schema(title = L_bizKey )
    String bizKey;


    @Schema(title = L_bizType )
    String bizType;


    @Schema(title = L_requestUri )
    String requestUri;


    @Size(max = 32)
    @Schema(title = L_requestMethod )
    String requestMethod;


    @Schema(title = L_requestParams )
    String requestParams;


    @Schema(title = L_headInfo )
    String headInfo;


    @Schema(title = L_responseData )
    String responseData;


    @Size(max = 128)
    @Schema(title = L_remoteAddr )
    String remoteAddr;


    @Size(max = 64)
    @Schema(title = L_serverAddr )
    String serverAddr;


    @Schema(title = L_isException )
    Boolean isException;


    @Schema(title = L_exceptionInfo )
    String exceptionInfo;


    @Size(max = 768)
    @Schema(title = L_userAgent )
    String userAgent;


    @Size(max = 128)
    @Schema(title = L_deviceName )
    String deviceName;


    @Size(max = 64)
    @Schema(title = L_browserName )
    String browserName;


    @Schema(title = L_executeTime )
    Long executeTime;


    @Size(max = 128)
    @Schema(title = L_tenantId )
    String tenantId;


    @Size(max = 128)
    @Schema(title = L_orgId )
    String orgId;


    @NotNull
    @Schema(title = L_createTime , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    Date createTime;


}
