package com.levin.oak.base.services.accesslog.req;

import static com.levin.oak.base.entities.EntityConst.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
import io.swagger.v3.oas.annotations.media.Schema;

import com.levin.commons.service.domain.*;
import com.levin.commons.service.support.*;

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

import com.levin.oak.base.entities.AccessLog;
import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AccessLog.*;
import com.levin.oak.base.services.commons.req.*;

////////////////////////////////////
//自动导入列表
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
import com.levin.commons.service.support.InjectConst;
////////////////////////////////////

/**
 * 更新访问日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月24日 下午9:39:10, 代码生成哈希校验码：[f2bcea2c993ab1f4a3003f060fcab453]，请不要修改和删除此行内容。
 *
 */
@Schema(title = UPDATE_ACTION + BIZ_NAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper = true)
@ToString
@Accessors(chain = true)
@FieldNameConstants
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
//默认更新注解
@Update
public class SimpleUpdateAccessLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1030736962L;


    @Schema(title = L_domain , description = D_domain)
    String domain;

    @Schema(title = L_module , description = D_module)
    String module;

    @Size(max = 64)
    @InjectVar(value = InjectConst.USER_NAME, isRequired = "false")
    @Schema(title = L_visitor)
    String visitor;

    @Size(max = 255)
    @Schema(title = L_title)
    String title;

    @Size(max = 64)
    @Schema(title = L_logType)
    String logType;

    @Schema(title = L_diffModifyData)
    String diffModifyData;

    @Schema(title = L_bizKey)
    String bizKey;

    @Schema(title = L_bizType)
    String bizType;

    @Size(max = 768)
    @Schema(title = L_requestUri)
    String requestUri;

    @Size(max = 32)
    @Schema(title = L_requestMethod)
    String requestMethod;

    @Schema(title = L_headInfo)
    String headInfo;

    @Schema(title = L_requestParams)
    String requestParams;

    @Schema(title = L_requestBody)
    String requestBody;

    @Schema(title = L_responseBody)
    String responseBody;

    @Size(max = 128)
    @Schema(title = L_remoteAddr)
    String remoteAddr;

    @Size(max = 256)
    @Schema(title = L_accessRegion)
    String accessRegion;

    @Size(max = 64)
    @Schema(title = L_serverAddr)
    String serverAddr;

    @Schema(title = L_isException)
    Boolean isException;

    @Schema(title = L_exceptionInfo)
    String exceptionInfo;

    @Size(max = 1800)
    @Schema(title = L_userAgent)
    String userAgent;

    @Size(max = 128)
    @Schema(title = L_deviceName)
    String deviceName;

    @Size(max = 128)
    @Schema(title = L_browserName)
    String browserName;

    @Schema(title = L_executeTime)
    Long executeTime;


    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }
}
