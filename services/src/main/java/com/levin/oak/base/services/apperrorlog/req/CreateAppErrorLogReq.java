package com.levin.oak.base.services.apperrorlog.req;

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
import static com.levin.oak.base.entities.E_AppErrorLog.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import com.levin.commons.service.support.InjectConsts;
import com.levin.commons.service.domain.InjectVar;
import java.util.Date;
////////////////////////////////////


/**
 *  新增应用错误日志
 *  //Auto gen by simple-dao-codegen 2023年6月26日 下午6:06:02
 * 代码生成哈希校验码：[01d9fc149b4912bb5b77538259f6af68]
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
@TargetOption(entityClass = AppErrorLog.class, alias = E_AppErrorLog.ALIAS)
public class CreateAppErrorLogReq extends MultiTenantReq {

    private static final long serialVersionUID = 1594864095L;


    @Schema(title = L_moduleId  )
    @Size(max = 64)
    String moduleId;

    @Schema(title = L_occurTime  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    Date occurTime;

    @Schema(title = L_title  , required = true, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank
    @Size(max = 768)
    String title;

    @Schema(title = L_errorLevel  )
    String errorLevel;

    @Schema(title = L_rootExceptionType  )
    String rootExceptionType;

    @Schema(title = L_exceptionFullInfo  )
    String exceptionFullInfo;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getOccurTime() == null){
            setOccurTime(new Date());
        }

    }

}
