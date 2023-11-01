package com.levin.oak.base.services.accesslog.req;

//import static com.levin.oak.base.ModuleOption.*;
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
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.levin.commons.service.domain.InjectVar;
////////////////////////////////////

/**
 * 新增访问日志
 *
 * @author Auto gen by simple-dao-codegen, @time: 2023年11月1日 下午3:17:42, 代码生成哈希校验码：[2ea70cf4b198dfcc8ba07a7703850e80]，请不要修改和删除此行内容。
 *
 */
@Schema(title = CREATE_ACTION + BIZ_NAME)
@Data
@Accessors(chain = true)
@ToString
//@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TargetOption(entityClass = AccessLog.class, alias = E_AccessLog.ALIAS)
public class CreateAccessLogReq extends MultiTenantOrgReq {

    private static final long serialVersionUID = 1030736962L;

    @Schema(title = L_domain , description = D_domain )
    String domain;

    @Schema(title = L_module , description = D_module )
    String module;

    @Schema(title = L_visitor )
    @Size(max = 64)
    String visitor;

    @Schema(title = L_title )
    @NotBlank
    String title;

    @Schema(title = L_logType )
    @Size(max = 64)
    String logType;

    @Schema(title = L_diffModifyData )
    String diffModifyData;

    @Schema(title = L_bizKey )
    String bizKey;

    @Schema(title = L_bizType )
    String bizType;

    @Schema(title = L_requestUri )
    @Size(max = 768)
    String requestUri;

    @Schema(title = L_requestMethod )
    @Size(max = 32)
    String requestMethod;

    @Schema(title = L_headInfo )
    String headInfo;

    @Schema(title = L_requestParams )
    String requestParams;

    @Schema(title = L_requestBody )
    String requestBody;

    @Schema(title = L_responseBody )
    String responseBody;

    @Schema(title = L_remoteAddr )
    @Size(max = 128)
    String remoteAddr;

    @Schema(title = L_accessRegion )
    @Size(max = 256)
    String accessRegion;

    @Schema(title = L_serverAddr )
    @Size(max = 64)
    String serverAddr;

    @Schema(title = L_isException )
    Boolean isException;

    @Schema(title = L_exceptionInfo )
    String exceptionInfo;

    @Schema(title = L_userAgent )
    @Size(max = 1800)
    String userAgent;

    @Schema(title = L_deviceName )
    @Size(max = 128)
    String deviceName;

    @Schema(title = L_browserName )
    @Size(max = 128)
    String browserName;

    @Schema(title = L_executeTime )
    Long executeTime;

    @Schema(title = L_createTime )
    @NotNull
    Date createTime;


    @PostConstruct
    public void prePersist() {
       //@todo 保存之前初始化数据，比如时间，初始状态等

        if(getCreateTime() == null){
            setCreateTime(new Date());
        }
    }
}
