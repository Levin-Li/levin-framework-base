package com.levin.oak.base.services.accesslog.req;

import static com.levin.oak.base.entities.EntityConst.*;

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
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////

/**
 *  更新访问日志
 *  Auto gen by simple-dao-codegen 2023年6月28日 上午9:18:57
 *  代码生成哈希校验码：[d9ec79bb83a73a9d077320bd61f5a9d3]
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
public class UpdateAccessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1030736962L;

    @Schema(title = L_id, required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    @Eq(require = true)
    Long id;



    @Schema(title = L_domain)
    String domain;

    @Size(max = 64)
    @Schema(title = L_visitor)
    String visitor;

    @NotBlank
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

    @Schema(title = L_requestUri)
    String requestUri;

    @Size(max = 32)
    @Schema(title = L_requestMethod)
    String requestMethod;

    @Schema(title = L_requestParams)
    String requestParams;

    @Schema(title = L_headInfo)
    String headInfo;

    @Schema(title = L_responseData)
    String responseData;

    @Size(max = 128)
    @Schema(title = L_remoteAddr)
    String remoteAddr;

    @Size(max = 64)
    @Schema(title = L_serverAddr)
    String serverAddr;

    @Schema(title = L_isException)
    Boolean isException;

    @Schema(title = L_exceptionInfo)
    String exceptionInfo;

    @Size(max = 768)
    @Schema(title = L_userAgent)
    String userAgent;

    @Size(max = 128)
    @Schema(title = L_deviceName)
    String deviceName;

    @Size(max = 64)
    @Schema(title = L_browserName)
    String browserName;

    @Schema(title = L_executeTime)
    Long executeTime;


    public UpdateAccessLogReq(Long id) {
        this.id = id;
    }

    public UpdateAccessLogReq updateIdWhenNotBlank(Long id){
        if(isNotBlank(id)){
        this.id = id;
        }
        return this;
    }

    @PostConstruct
    public void preUpdate() {
        //@todo 更新之前初始化数据
    }

}
