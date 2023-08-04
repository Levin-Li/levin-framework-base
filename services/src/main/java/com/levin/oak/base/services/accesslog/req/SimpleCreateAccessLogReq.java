package com.levin.oak.base.services.accesslog.req;

// import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;
/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
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

import com.levin.oak.base.entities.*;
import static com.levin.oak.base.entities.E_AccessLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
// 自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;

////////////////////////////////////

/**
 * 新增访问日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年7月27日 下午6:25:44, 代码生成哈希校验码：[db340b94a69fd6d755e74c27e4408bf1]，请不要修改和删除此行内容。
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
// @EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
public class SimpleCreateAccessLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1030736962L;

    @Schema(title = L_domain)
    String domain;

    @Schema(title = L_visitor)
    @Size(max = 64)
    String visitor;

    @Schema(title = L_title)
    @NotBlank
    String title;

    @Schema(title = L_logType)
    @Size(max = 64)
    String logType;

    @Schema(title = L_diffModifyData)
    String diffModifyData;

    @Schema(title = L_bizKey)
    String bizKey;

    @Schema(title = L_bizType)
    String bizType;

    @Schema(title = L_requestUri)
    @Size(max = 768)
    String requestUri;

    @Schema(title = L_requestMethod)
    @Size(max = 32)
    String requestMethod;

    @Schema(title = L_headInfo)
    String headInfo;

    @Schema(title = L_requestParams)
    String requestParams;

    @Schema(title = L_requestBody)
    String requestBody;

    @Schema(title = L_responseBody)
    String responseBody;

    @Schema(title = L_remoteAddr)
    @Size(max = 128)
    String remoteAddr;

    @Schema(title = L_serverAddr)
    @Size(max = 64)
    String serverAddr;

    @Schema(title = L_isException)
    Boolean isException;

    @Schema(title = L_exceptionInfo)
    String exceptionInfo;

    @Schema(title = L_userAgent)
    @Size(max = 1800)
    String userAgent;

    @Schema(title = L_deviceName)
    @Size(max = 128)
    String deviceName;

    @Schema(title = L_browserName)
    @Size(max = 128)
    String browserName;

    @Schema(title = L_executeTime)
    Long executeTime;

    @Schema(title = L_createTime)
    @NotNull
    Date createTime;

    @PostConstruct
    public void prePersist() {
        // @todo 保存之前初始化数据，比如时间，初始状态等
    }
}