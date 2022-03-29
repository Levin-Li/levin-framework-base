package com.levin.oak.base.services.apperrorlog.req;

import io.swagger.v3.oas.annotations.media.Schema;

/////////////////////////////////////////////////////
import javax.validation.constraints.*;
import javax.annotation.*;
import lombok.*;
import lombok.experimental.*;
import java.util.*;

///////////////////////////////////////////////////////
import com.levin.commons.service.domain.*;
import com.levin.commons.dao.*;
import com.levin.commons.dao.annotation.*;
import com.levin.commons.dao.annotation.update.*;
import com.levin.commons.dao.annotation.select.*;
import com.levin.commons.dao.annotation.stat.*;
import com.levin.commons.dao.annotation.order.*;
import com.levin.commons.dao.annotation.logic.*;
import com.levin.commons.dao.annotation.misc.*;


import com.levin.oak.base.entities.*;
import com.levin.oak.base.services.commons.req.*;
////////////////////////////////////
//自动导入列表
import java.util.Date;
////////////////////////////////////


/**
 *  新增应用错误日志
 *  //Auto gen by simple-dao-codegen 2022-3-29 22:58:02
 */
@Schema(description = "新增应用错误日志")
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



    @Schema(description = "模块ID" )
    //@Size(max = 64)
    private String moduleId;


    @Schema(description = "发生时间" , required = true)
    //@NotNull
    private Date occurTime;


    @Schema(description = "标题" , required = true)
    //@NotBlank
    //@Size(max = 1000)
    private String title;


    @Schema(description = "错误级别" )
    private String errorLevel;


    @Schema(description = "根异常类型" )
    private String rootExceptionType;


    @Schema(description = "完整异常堆栈" )
    private String exceptionFullInfo;


    @PostConstruct
    public void prePersist() {

       //@todo 保存之前初始化数据


        if(getOccurTime() == null){
            setOccurTime(new Date());
        }

    }

}
