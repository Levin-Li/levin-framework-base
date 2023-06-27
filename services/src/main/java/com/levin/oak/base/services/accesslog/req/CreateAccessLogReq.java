package com.levin.oak.base.services.accesslog.req;

//import static com.levin.oak.base.ModuleOption.*;
import static com.levin.oak.base.entities.EntityConst.*;

import io.swagger.v3.oas.annotations.media.Schema;

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
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////


/**
 *  新增访问日志
 *  //Auto gen by simple-dao-codegen 2023年6月28日 上午12:31:50
 * 代码生成哈希校验码：[44813a7e11a30d7097dabd9ea81b12df]
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
public class CreateAccessLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1030736962L;


    @Schema(title = L_domain  )
    String domain;

    @Schema(title = L_visitor  )
    @Size(max = 64)
    String visitor;

    @Schema(title = L_createTime  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    Date createTime;

    @Schema(title = L_title  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    String title;

    @Schema(title = L_logType  )
    @Size(max = 64)
    String logType;

    @Schema(title = L_diffModifyData  )
    String diffModifyData;

    @Schema(title = L_bizKey  )
    String bizKey;

    @Schema(title = L_bizType  )
    String bizType;

    @Schema(title = L_requestUri  )
    String requestUri;

    @Schema(title = L_requestMethod  )
    @Size(max = 32)
    String requestMethod;

    @Schema(title = L_requestParams  )
    String requestParams;

    @Schema(title = L_headInfo  )
    String headInfo;

    @Schema(title = L_responseData  )
    String responseData;

    @Schema(title = L_remoteAddr  )
    @Size(max = 128)
    String remoteAddr;

    @Schema(title = L_serverAddr  )
    @Size(max = 64)
    String serverAddr;

    @Schema(title = L_isException  )
    Boolean isException;

    @Schema(title = L_exceptionInfo  )
    String exceptionInfo;

    @Schema(title = L_userAgent  )
    @Size(max = 768)
    String userAgent;

    @Schema(title = L_deviceName  )
    @Size(max = 128)
    String deviceName;

    @Schema(title = L_browserName  )
    @Size(max = 64)
    String browserName;

    @Schema(title = L_executeTime  )
    Long executeTime;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getCreateTime() == null){
            setCreateTime(new Date());
        }

    }

}
